package com.embaucha.tralue;

//import com.flurry.android.FlurryAgent;
import com.testflightapp.lib.TestFlight;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;

public class CardsActivity extends Fragment implements OnItemClickListener {
	SQLiteDatabase rodb;
	Cursor cursor;
	View layout;
	SimpleCursorAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.cards_activity, container, false);
		//configureUpButtons();
		rodb = MainActivity.open_helper.getReadableDatabase();
		
		resort();
		
		return layout;
	}
	
	private void populateCursor() {
		TestFlight.log("MainActivity.sort: " + MainActivity.sort);
		
		while (!rodb.isOpen()) {
			TestFlight.log("DB is not open.");
			//if (rodb.isDbLockedByOtherThreads()) {
			//	TestFlight.log("DB is locked by another thread.");
			//}
			//if (rodb.isDbLockedByCurrentThread()) {
			//	TestFlight.log("DB is locked by current thread.");
			//}
			
			refreshDB();
		}
		
		String query = "SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value', " +
				"spend_bonus * points_value + first_purchase_bonus * points_value AS maximum_bonus_value, " +
				"points_per_dollar_spent_general_spend * points_value * planned_spend_per_month * 12 / 100 - annual_fee AS value_of_keeping_card, " +
				"first_purchase_bonus * points_value AS first_purchase_bonus_value, " +
				"spend_requirement / time_to_reach_spend_in_months AS spend_per_month_for_bonus, " +
				"CASE WHEN fee_waived_first_year = 'No' " +
				"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate / 100 + annual_fee) " +
				"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate) / 100 " + 
				"END AS value_with_manufactured_spend, " +
				"CASE WHEN fee_waived_first_year = 'No' " +
				"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - annual_fee " +
				"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 " +
				"END AS max_bonus_less_annual_fee FROM providers INNER JOIN point_values ON " +
				"providers.points_program=point_values.points_program";
		
		//if business is true, personal is false
		if (PointValues.business_boolean && !PointValues.personal_boolean) {
			query += " WHERE business_personal='business'";
		}
		//if business is false, personal is true
		else if (!PointValues.business_boolean && PointValues.personal_boolean) {
			query += " WHERE business_personal='personal'";
		}
		//if business is true, personal is true
		else if (PointValues.business_boolean && PointValues.personal_boolean) {
			query += " WHERE business_personal='business' OR business_personal='personal'";
		}
		
		switch (MainActivity.sort) {
		case R.id.total_bonus_value_radio: {
			TestFlight.log("Sort is total_bonus_value_radio");
			query += " ORDER BY maximum_bonus_value DESC";
			//cursor = rodb.rawQuery("SELECT *, spend_bonus * points_value + first_purchase_bonus * points_value AS maximum_bonus_value FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY maximum_bonus_value DESC", null);
			cursor = rodb.rawQuery(query, null);
			//Map<String, String> articleParams = new HashMap<String, String>();
			//articleParams.put("sort", sort);
			//FlurryAgent.logEvent("Sort_selected", articleParams);
			break;
		}
		case R.id.net_bonus_value_radio: {
			TestFlight.log("Sort is net_bonus_value_radio");
			query += " ORDER BY max_bonus_less_annual_fee DESC";
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.annual_value_of_keeping_card_radio: {
			TestFlight.log("Sort is annual_value_of_keeping_card_radio");
			query += " ORDER BY value_of_keeping_card DESC";
			//cursor = rodb.rawQuery("SELECT *, points_per_dollar_spent_general_spend * points_value * planned_spend_per_month * 12 / 100 - annual_fee AS value_of_keeping_card FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY value_of_keeping_card DESC", null);
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.alphabetical_radio: {
			query += " ORDER BY providers.name";
			//cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY providers.name", null);
			TestFlight.log("Sort is alphabetical_radio");
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.first_purchase_bonus_value_radio: {
			query += " ORDER BY first_purchase_bonus_value DESC";
			//cursor = rodb.rawQuery("SELECT *, first_purchase_bonus * points_value AS 'first_purchase_bonus_value' FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY first_purchase_bonus_value DESC", null);
			TestFlight.log("Sort is first_purchase_bonus_value_radio");
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.spend_per_month_for_bonus_radio: {
			query += " ORDER BY spend_per_month_for_bonus";
			//cursor = rodb.rawQuery("SELECT *, spend_requirement / time_to_reach_spend_in_months AS spend_per_month_for_bonus FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY spend_per_month_for_bonus", null);
			TestFlight.log("Sort is spend_per_month_for_bonus_radio");
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.foreign_transaction_fee_radio: {
			query += " ORDER BY foreign_transaction_fee";
			//cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY foreign_transaction_fee", null);
			TestFlight.log("Sort is foreign_transaction_fee_radio");
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		case R.id.net_bonus_value_with_manufactured_spend_radio: {
			query += " ORDER BY value_with_manufactured_spend DESC";
			/*
			cursor = rodb.rawQuery("SELECT *, " +
					"CASE WHEN fee_waived_first_year = 'No' " +
					"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate / 100 + annual_fee) " +
					"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate) / 100 " + 
					"END AS value_with_manufactured_spend " + 
					"FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program " +
					"ORDER BY value_with_manufactured_spend DESC", null);
			*/
			TestFlight.log("Sort is net_bonus_value_with_manufactured_spend");
			cursor = rodb.rawQuery(query, null);
			
			break;
		}
		default: {
			TestFlight.log("Using default sorting. This should not have happened.");
			cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program", null);
			break;
		}
		}
	}
	
	private void populateAdapter() {
		String[] fromColumns = {"name"};
		int[] toViews = {R.id.card_text};
		
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_view_card, cursor, fromColumns, toViews, 0);
		ListView listView = ((ListView) layout.findViewById(R.id.listview));
		if (adapter == null) 
			TestFlight.log("Adapter is null.");
		if (listView == null) {
			TestFlight.log("listView is null.");
		} else {
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		}
	}
	
	public void resort() {
		checkDB();
		populateCursor();
		populateAdapter();
	}
	
	public void refreshDataset() {
		if (adapter == null) {
			TestFlight.log("Uh oh. Adapter is null on resume.");
		} else {
			TestFlight.log("Notifying of dataset changes.");
			adapter.notifyDataSetChanged();
		}
	}
	
	
	
	/*
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cards_activity);
		//String text = getIntent().getStringExtra("sort");
		//rodb.execSQL("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program");
		
		String sort = this.getIntent().getStringExtra("sort");
		rodb = TestFragment.open_helper.getReadableDatabase();
		
		boolean business, personal;
		business = true;
		personal = true;
		
		if (sort.equals("Alphabetical")) {
			cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY providers.name", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("First purchase bonus")) {
			cursor = rodb.rawQuery("SELECT *, first_purchase_bonus * points_value AS 'first_purchase_bonus_value' FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY first_purchase_bonus_value DESC", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Maximum bonus value")) {
			cursor = rodb.rawQuery("SELECT *, spend_bonus * points_value + first_purchase_bonus * points_value AS maximum_bonus_value FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY maximum_bonus_value DESC", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Spend per month for bonus")) {
			cursor = rodb.rawQuery("SELECT *, spend_requirement / time_to_reach_spend_in_months AS spend_per_month_for_bonus FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY spend_per_month_for_bonus", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Maximum bonus less annual fee")) {
			//maximum_bonus_value - annual_fee AS max_bonus_less_annual_fee
			
			
			//the start of code to implement business and personal filtering
			/*
			String QUERY = "SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value', " +
					"CASE WHEN fee_waived_first_year = 'No' " +
					"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - annual_fee " +
					"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 " +
					"END AS max_bonus_less_annual_fee";
			if (business == true && personal == false) {
				QUERY += " WHERE business_personal = 'business'";
			} else if (business == false && personal == true) {
				QUERY += " WHERE business_personal = 'personal'";
			} else if (business == true && personal == true) {
				//nothing needed
			} else {
				TestFlight.log("WTF?");
			}
			
			cursor = rodb.rawQuery("SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value', " +
					"CASE WHEN fee_waived_first_year = 'No' " +
					"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - annual_fee " +
					"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 " +
					"END AS max_bonus_less_annual_fee FROM providers INNER JOIN point_values ON " +
					"providers.points_program=point_values.points_program ORDER BY max_bonus_less_annual_fee DESC", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Foreign transaction fee")) {
			cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY foreign_transaction_fee", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Annual value of keeping card")) {
			cursor = rodb.rawQuery("SELECT *, points_per_dollar_spent_general_spend * points_value * planned_spend_per_month * 12 / 100 - annual_fee AS value_of_keeping_card FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program ORDER BY value_of_keeping_card DESC", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else if (sort.equals("Value after manufactured spend")) {
			cursor = rodb.rawQuery("SELECT *, " +
					"CASE WHEN fee_waived_first_year = 'No' " +
					"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate / 100 + annual_fee) " +
					"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate) / 100 " + 
					"END AS value_with_manufactured_spend " + 
					"FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program " +
					"ORDER BY value_with_manufactured_spend DESC", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		} else {
			cursor = rodb.rawQuery("SELECT * FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program", null);
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", sort);
			FlurryAgent.logEvent("Sort_selected", articleParams);
		}
		
		String[] fromColumns = {"name"};
		int[] toViews = {R.id.card_text};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_view_card, cursor, fromColumns, toViews, 0);
		ListView listView = (ListView)findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	*/

	@SuppressWarnings("unused")
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(getActivity(), DisplaySingleCard.class);
		//intent = new Intent(getActivity(), Test.class);
		TestFlight.log("String to save/pass: " + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
		if (intent == null) {
			TestFlight.log("Single card intent is null.");
		} else {
			intent.putExtra("card_selected", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
		}
		try {
			startActivity(intent);
		} catch (Exception e) {
			TestFlight.log(e.toString());
		}
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
//		FlurryAgent.onStartSession(getActivity(), "B8HFG9HTK5C3RQRNNFY5");
		TestFlight.log("Called Flurry.");
	}
	 
	@Override
	public void onStop()
	{
		super.onStop();		
//		FlurryAgent.onEndSession(getActivity());
		TestFlight.log("Stopped Flurry.");
	}
	
	@Override
	public void onPause() {
		TestFlight.log("Cards activity destroyed.");
		if (rodb != null) {
			rodb.close();
			TestFlight.log("RODB destroyed.");
		}
		
		super.onPause();
	}
	
	private void checkDB() {
		if (rodb == null) {
			TestFlight.log("RODB in cards activity is null.");
			refreshDB();
		} else {
			TestFlight.log("RODB is not null. It is at: " + rodb);
		}
	}
	
	private void refreshDB() {
		rodb = MainActivity.open_helper.getReadableDatabase();
	}
	
	public void onResume() {
		refreshDataset();
		checkDB();
		
		super.onResume();
		
		//resort();
	}
}