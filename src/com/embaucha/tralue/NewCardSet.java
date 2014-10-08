package com.embaucha.tralue;

import ly.count.android.api.Countly;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NewCardSet extends Activity implements OnItemClickListener {
	SQLiteDatabase rodb;
	String origin, destination, service_class;
	String carrier_1, carrier_2;
	float price;
	static String percentage = "percentage_of_award";
	int card_id_1, card_id_2;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.new_list_of_cards);
		
		loadValuesFromParent();
		
		//listview id is lv
		OpenHelper oh = new OpenHelper(this);
		rodb = oh.getReadableDatabase();
				
		String ORDER = percentage + " DESC";
		String query = "SELECT *, CASE WHEN " + OpenHelper.KEY_TRANSFERRING_PROGRAM + " != NULL THEN (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 * " + OpenHelper.KEY_TRANSFER_VALUE + " / " + OpenHelper.COL_COST + " ELSE (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " END AS " + percentage + 
						" FROM " + OpenHelper.TABLE_PROVIDERS + " LEFT OUTER JOIN " + OpenHelper.TABLE_PARTNERS + 
						" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
						" ON " + "providers.points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
						OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
						" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
						OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + 
						OpenHelper.KEY_CARD_ID + " = " + card_id_1 + " AND " + OpenHelper.KEY_CARRIER_NAMES + " ='" + carrier_1 + "'";
		query += " UNION ALL "; //when using a transfer partner, use their transfer value
		query += "SELECT *, CASE WHEN " + OpenHelper.KEY_TRANSFERRING_PROGRAM + " != NULL THEN (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 * " + OpenHelper.KEY_TRANSFER_VALUE + " / " + OpenHelper.COL_COST + " ELSE (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " END AS " + percentage + 
						" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
						" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
						" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
						OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
						" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
						OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + 
						OpenHelper.KEY_CARD_ID + " = " + card_id_1  + " AND " + OpenHelper.KEY_CARRIER_NAMES + " ='" + carrier_1 + "'" + " ORDER BY " + ORDER;
				//SELECT * FROM providers INNER JOIN partners ON providers.points_program=partners.partner_points_program
				
				//half of the filter
				//SELECT *, (spend_bonus + first_purchase_bonus) * 100 / cost_in_miles AS percentage FROM providers INNER JOIN partners ON providers.points_program=partners.points_program INNER JOIN compiled_awards ON partners.partner_points_program=compiled_awards.airline WHERE origin LIKE '%chi%' and destination like '%hkg%' and class_of_service='economy' ORDER BY percentage DESC;
		
		Cursor cursor = rodb.rawQuery(query, null);
		if (cursor.getCount() == 0) {
//			TestFlight.log("Cursor is empty.");
			System.out.println(query);
			Toast.makeText(getApplicationContext(), "We didn't find any results. :'-( Double check your location entries, then email us if you're still having issues.", Toast.LENGTH_LONG).show();
		}
		
		
		
		
		String[] fromColumns = { OpenHelper.KEY_NAME, percentage, OpenHelper.KEY_CARRIER_NAMES };
		int[] toViews = {R.id.card_text, R.id.card_text_2, R.id.card_text_3};
		SimpleCursorAdapter adapter;
		
		if (card_id_2 != -1) {
			query = "SELECT *, CASE WHEN " + OpenHelper.KEY_TRANSFERRING_PROGRAM + " != NULL THEN (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 * " + OpenHelper.KEY_TRANSFER_VALUE + " / " + OpenHelper.COL_COST + " ELSE (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " END AS " + percentage + 
					" FROM " + OpenHelper.TABLE_PROVIDERS + " LEFT OUTER JOIN " + OpenHelper.TABLE_PARTNERS + 
					" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
					" ON " + "providers.points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
					OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
					" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
					OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + 
					OpenHelper.KEY_CARD_ID + " = " + card_id_2 + " AND " + OpenHelper.KEY_CARRIER_NAMES + " ='" + carrier_2 + "'";
			query += " UNION ALL "; //when using a transfer partner, use their transfer value
			query += "SELECT *, CASE WHEN " + OpenHelper.KEY_TRANSFERRING_PROGRAM + " != NULL THEN (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 * " + OpenHelper.KEY_TRANSFER_VALUE + " / " + OpenHelper.COL_COST + " ELSE (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " END AS " + percentage + 
					" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
					" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
					" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
					OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
					" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
					OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + 
					OpenHelper.KEY_CARD_ID + " = " + card_id_2  + " AND " + OpenHelper.KEY_CARRIER_NAMES + " ='" + carrier_2 + "'" + " ORDER BY " + ORDER;
			
		Cursor cursor2 = rodb.rawQuery(query, null);
			if (cursor2.getCount() == 0) {
//				TestFlight.log("Cursor is empty.");
				System.out.println(query);
				Toast.makeText(getApplicationContext(), "We didn't find any results. :'-( Double check your location entries, then email us if you're still having issues.", Toast.LENGTH_LONG).show();
			}
			
			MergeCursor mergedCursor = new MergeCursor(new Cursor[]{cursor, cursor2});
			adapter = new SimpleCursorAdapter(this, R.layout.new_list_view_item, mergedCursor, fromColumns, toViews, 0);
		} else {
			adapter = new SimpleCursorAdapter(this, R.layout.new_list_view_item, cursor, fromColumns, toViews, 0);
		}
		
		ListView listView = ((ListView) findViewById(R.id.lv));
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	
	private void loadValuesFromParent() {
		Intent intent = getIntent();
		origin = intent.getStringExtra("origin");
		destination = intent.getStringExtra("destination");
		service_class = intent.getStringExtra("class_of_service");
		price = intent.getFloatExtra("price", 0);
		card_id_1 = intent.getIntExtra("compiled_awards_id",-1);
		card_id_2 = intent.getIntExtra("compiled_awards_id_2",-1);
		carrier_1 = intent.getStringExtra("award_program");
		carrier_2 = intent.getStringExtra("award_program_2");
	}
	
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		System.out.println("Card selected in new list of cards.");
		
		Intent intent = new Intent(this, NewDisplaySingleCard.class);
		//intent = new Intent(getActivity(), Test.class);
//		TestFlight.log("String to save/pass: " + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
//		TestFlight.log("compiled_awards_id to save: " + ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.COL_ID)));
		if (intent == null) {
//			TestFlight.log("Single card intent is null.");
		} else {
			
			//toDisplay
//			intent.putExtra("card_selected", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getName());
//			intent.putExtra("compiled_awards_id", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getSql_id());
//			intent.putExtra("origin", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getOrigin());
//			intent.putExtra("destination", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getDestination());
//			intent.putExtra("class_of_service", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getService_class());
//			intent.putExtra("price", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getPrice());
//			//card_2
//			intent.putExtra("card_selected_2", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getName());
//			intent.putExtra("compiled_awards_id_2", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getSql_id());
			//intent.putExtra("origin", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getOrigin());
			//intent.putExtra("destination", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getDestination());
			//intent.putExtra("class_of_service", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getService_class());
			//intent.putExtra("price", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getPrice());
			
			
			
			//OLD CODE
			//
			//
			//
			//parentCursor.getString(parentCursor.getColumnIndex("name"));
			
			
			//OLD CODE
			//
			//
			//
			intent.putExtra("card_selected", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
			intent.putExtra("compiled_awards_id", ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_CARD_ID)));
			intent.putExtra("origin", origin);
			intent.putExtra("destination", destination);
			intent.putExtra("class_of_service", service_class);
			intent.putExtra("price", price);
			//secondary, optional card
			
			System.out.println("Extras inserted.");
			//intent.putExtra("cost_in_miles", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("cost_in_miles")));
			//intent.putExtra("miles_provided", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_SPEND_BONUS)) + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_FIRST_PURCHASE_BONUS)));
		}
		try {
			System.out.println("Starting activity...");
			startActivity(intent);
		} catch (Exception e) {
			System.out.println(e.toString());
//			TestFlight.log(e.toString());
		}
	}
	
	public void onStart() {
		super.onStart();
		Countly.sharedInstance().onStart();
	}
	
	public void onStop() {
		super.onStop();
		Countly.sharedInstance().onStop();
	}
}