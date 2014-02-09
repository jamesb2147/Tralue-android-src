package com.embaucha.tralue;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewListOfCards extends Activity implements OnItemClickListener {
	SQLiteDatabase rodb;
	String origin, destination, service_class;
	float price;
	static String percentage = "percentage_of_award";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_list_of_cards);
		
		loadValuesFromParent();
		
		//listview id is lv
		OpenHelper oh = new OpenHelper(this);
		rodb = oh.getReadableDatabase();
		
		String ORDER = percentage + " DESC";
		String query = "SELECT *, (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " AS " + percentage + 
				" FROM " + OpenHelper.TABLE_PROVIDERS + " LEFT OUTER JOIN " + OpenHelper.TABLE_PARTNERS + 
				" ON " + "providers.points_program=partners.points_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
				" ON " + "providers.points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
				OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
				" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
				OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' ";
		query += " UNION ALL ";
		query += "SELECT *, (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " AS " + percentage + 
				" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
				" ON " + "providers.points_program=partners.points_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
				" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
				OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
				" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
				OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' " + "ORDER BY " + ORDER;
		//SELECT * FROM providers INNER JOIN partners ON providers.points_program=partners.partner_points_program
		
		//half of the filter
		//SELECT *, (spend_bonus + first_purchase_bonus) * 100 / cost_in_miles AS percentage FROM providers INNER JOIN partners ON providers.points_program=partners.points_program INNER JOIN compiled_awards ON partners.partner_points_program=compiled_awards.airline WHERE origin LIKE '%chi%' and destination like '%hkg%' and class_of_service='economy' ORDER BY percentage DESC;
		
		
		System.out.println("Query: " + query);
		System.out.println("Running query...");
		Cursor cursor = rodb.rawQuery(query, null);
		if (cursor.getCount() == 0) {
			System.out.println("Cursor is empty.");
			Toast.makeText(getApplicationContext(), "We didn't find any results. :'-( Double check your location entries, then email us if you're still having issues.", Toast.LENGTH_LONG).show();
		}
		String[] fromColumns = { OpenHelper.KEY_NAME, percentage, OpenHelper.KEY_CARRIER_NAMES };
		int[] toViews = {R.id.card_text, R.id.card_text_2, R.id.card_text_3};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.new_list_view_item, cursor, fromColumns, toViews, 0);
		ListView listView = ((ListView) findViewById(R.id.lv));
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	
	private void loadValuesFromParent() {
		Intent intent = getIntent();
		origin = intent.getStringExtra("origin");
		destination = intent.getStringExtra("destination");
		service_class = intent.getStringExtra("service_class");
		price = intent.getFloatExtra("price", 0);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(this, NewDisplaySingleCard.class);
		//intent = new Intent(getActivity(), Test.class);
		System.out.println("String to save/pass: " + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
		System.out.println("compiled_awards_id to save: " + ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.COL_ID)));
		if (intent == null) {
			System.out.println("Single card intent is null.");
		} else {
			intent.putExtra("card_selected", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
			intent.putExtra("compiled_awards_id", ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.COL_ID)));
			intent.putExtra("origin", origin);
			intent.putExtra("destination", destination);
			intent.putExtra("class_of_service", service_class);
			intent.putExtra("price", price);
			//intent.putExtra("cost_in_miles", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("cost_in_miles")));
			//intent.putExtra("miles_provided", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_SPEND_BONUS)) + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_FIRST_PURCHASE_BONUS)));
		}
		try {
			startActivity(intent);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}