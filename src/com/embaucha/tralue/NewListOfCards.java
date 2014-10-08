package com.embaucha.tralue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ly.count.android.api.Countly;

//import com.testflightapp.lib.TestFlight;

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
//		TestFlight.passCheckpoint("Loaded the new list of cards.");
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
				OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' ";
		query += " UNION ALL "; //when using a transfer partner, use their transfer value
		query += "SELECT *, CASE WHEN " + OpenHelper.KEY_TRANSFERRING_PROGRAM + " != NULL THEN (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 * " + OpenHelper.KEY_TRANSFER_VALUE + " / " + OpenHelper.COL_COST + " ELSE (" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " END AS " + percentage + 
				" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
				" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
				" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
				OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
				" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
				OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' " + "ORDER BY " + ORDER;
		//SELECT * FROM providers INNER JOIN partners ON providers.points_program=partners.partner_points_program
		
		//half of the filter
		//SELECT *, (spend_bonus + first_purchase_bonus) * 100 / cost_in_miles AS percentage FROM providers INNER JOIN partners ON providers.points_program=partners.points_program INNER JOIN compiled_awards ON partners.partner_points_program=compiled_awards.airline WHERE origin LIKE '%chi%' and destination like '%hkg%' and class_of_service='economy' ORDER BY percentage DESC;
		
		
		Cursor cursor = rodb.rawQuery(query, null);
		if (cursor.getCount() == 0) {
//			TestFlight.log("Cursor is empty.");
			Toast.makeText(getApplicationContext(), "We didn't find any results. :'-( Double check your location entries, then email us if you're still having issues.", Toast.LENGTH_LONG).show();
		}
		
		String[] fromColumns = { OpenHelper.KEY_NAME, percentage, OpenHelper.KEY_CARRIER_NAMES };
		int[] toViews = {R.id.card_text, R.id.card_text_2, R.id.card_text_3};
		
		
		
		//take top card, add all subsequent cards one at a time in percentage descending order where "via" matches and issuer does not,
		//move to next card and repeat
		//but only repeat for cards listed BELOW current card to avoid duplicates
		int index = 0;
		cursor.moveToFirst();
//		Cursor cursor2 = cursor;
		Cursor cursor2 = rodb.rawQuery(query, null);
		
		//
		ArrayList<CardContainer[]> toDisplay = new ArrayList<CardContainer[]>();
		
		do {
			//add each card individually
			CardContainer container2[] = new CardContainer[]{ new CardContainer(cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_NAME)), 
					cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)), 
					cursor.getFloat(cursor.getColumnIndex(percentage)), 
					origin, 
					destination, 
					service_class, 
					price, 
					cursor.getInt(cursor.getColumnIndex(OpenHelper.KEY_CARD_ID)))};
			
			toDisplay.add(container2);
			
			index++;

			System.out.println("Started iterating.");
			System.out.println("Cursor rows: " + cursor.getCount());
			System.out.println("Cursor columns: " + cursor.getColumnCount());
			System.out.println("Cursor2 rows: " + cursor2.getCount());
			System.out.println("Cursor2 columns: " + cursor2.getColumnCount());
			System.out.println("Cursor isFirst: " + cursor.isFirst());
			System.out.println("Cursor isLast: " + cursor.isLast());
			System.out.println("Cursor2 rows: " + cursor2.isFirst());
			System.out.println("Cursor2 columns: " + cursor2.isLast());
			System.out.println("Cursor position: " + cursor.getPosition());
			System.out.println("Cursor2 position: " + cursor2.getPosition());
			
			//Get cursor values (e.g. name, percentage, key_carrier_names [this is the "via" field??], and issuer)
			//then compare key_carrier_name (if match, then keep!) and issuer (if match, then toss!)
			
			//use move to index instead?
			for (int i =0; i < index; i++) {
				cursor2.moveToNext();
			}
			
			//use do/while instead with moveToFirst method?
//			List<CardContainer[]> array = new ArrayList<CardContainer[]>();
			
			while (cursor2.moveToNext()) {
				System.out.println("Iterating through cursor2. Current iteration: " + cursor2.getPosition());
				if (!cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_ISSUER)).equals(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_ISSUER)))) {
					System.out.println("Issuers did not match!"); //or one is business and one is personal?
					if (cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)).equals(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)))) {
						//woohoo! found a valid card combination to recommend!
						//apply to @id/card_2_text for name of second card and make @id/plus_sign visible as well
						System.out.println("Card with different issuers but a common transfer program found!");
						System.out.println("Name: " + cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_NAME)) + " - % miles needed: " + cursor.getFloat(cursor.getColumnIndex(percentage)) + " - via: " + cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)));
						System.out.println("Name: " + cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_NAME)) + " - % miles needed: " + cursor2.getFloat(cursor2.getColumnIndex(percentage)) + " - via: " + cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)));
						
						//necessary information for display on NewListOfCards:
						//name, percentage, key_carrier_names
						
						//necessary information per bundle to pass to SingleCardDisplay:
						//origin, destination, class of service, price
						
						//necessary information per card to pass to SingleCardDisplay:
						//name, KEY_CARD_ID
						
						//public CardContainer(String name, String award_program, float percentage, String origin, String destination, String service_class, float price, sql_id int)
						CardContainer container[] = new CardContainer[]{ new CardContainer(cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_NAME)), 
																							cursor.getString(cursor.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)), 
																							cursor.getFloat(cursor.getColumnIndex(percentage)), 
																							origin, 
																							destination, 
																							service_class, 
																							price, 
																							cursor.getInt(cursor.getColumnIndex(OpenHelper.KEY_CARD_ID))),
																		new CardContainer(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_NAME)),
																							cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)),
																							cursor2.getFloat(cursor2.getColumnIndex(percentage)),
																							origin,
																							destination,
																							service_class,
																							price,
																							cursor2.getInt(cursor2.getColumnIndex(OpenHelper.KEY_CARD_ID)))};
						toDisplay.add(container);
					}
				}
			}
			
			System.out.println("Cursor position: " + cursor.getPosition());
			System.out.println("Cursor2 position: " + cursor2.getPosition());
			cursor2.moveToFirst();
		} while (cursor.moveToNext());
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.new_list_view_item, cursor, fromColumns, toViews, 0);
		ListView listView = ((ListView) findViewById(R.id.lv));
		
		//sort the garbage
		Comparator<CardContainer[]> myComparator = new Comparator<CardContainer[]>() {
		    public int compare(CardContainer[] container1,CardContainer[] container2) {
//		        magic sauce goes here
		    	int container1_value, container2_value;
		    	container1_value = 0;
		    	container2_value = 0;
		    	
		    	if (container1.length > 0) {
		    		container1_value += container1[0].getPercentage();
		    	}
		    	if (container1.length > 1) {
		    		container1_value += container1[1].getPercentage();
		    	}
		    	
		    	if (container2.length > 0) {
		    		container2_value += container2[0].getPercentage();
		    	}
		    	if (container2.length > 1) {
		    		container2_value += container2[1].getPercentage();
		    	}
		    	
		    	//now, sort alphabetically when equal?
		    	
		    	return container2_value - container1_value;
		    }
		 };
		 
		 Collections.sort(toDisplay, myComparator);
		
		//new adapter test (this will fail)
		listView.setAdapter(new NewListAdapter(this, toDisplay));
		
//		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	
	private void loadValuesFromParent() {
		Intent intent = getIntent();
		origin = intent.getStringExtra("origin");
		destination = intent.getStringExtra("destination");
		service_class = intent.getStringExtra("service_class");
		price = intent.getFloatExtra("price", 0);
	}
	
	
	//FIX THIS
//	Comparator<CardContainer[]> myComparator = new Comparator<CardContainer[]>() {
//	    public int compare(CardContainer[] container1,CardContainer[] container2) {
////	        magic sauce goes here
//	    	int container1_value, container2_value;
//	    	container1_value = 0;
//	    	container2_value = 0;
//	    	
//	    	if (container1.length > 0) {
//	    		container1_value += container1[0].getPercentage();
//	    	}
//	    	if (container1.length > 1) {
//	    		container1_value += container1[1].getPercentage();
//	    	}
//	    	
//	    	if (container2.length > 0) {
//	    		container2_value += container2[0].getPercentage();
//	    	}
//	    	if (container2.length > 1) {
//	    		container2_value += container2[1].getPercentage();
//	    	}
//	    	
//	    	//now, sort alphabetically when equal?
//	    	
//	    	return container1_value - container2_value;
//	    }
//	 };
	
	@SuppressWarnings("unused")
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		System.out.println("Card selected in new list of cards.");
		Intent intent;
		
		
		if (((CardContainer[]) (parent.getItemAtPosition(position))).length > 1) {
			System.out.println("More than one card selected.");
			
			intent = new Intent(this, NewCardSet.class);
			//intent = new Intent(getActivity(), Test.class);
//			TestFlight.log("String to save/pass: " + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
//			TestFlight.log("compiled_awards_id to save: " + ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.COL_ID)));
			if (intent == null) {
//				TestFlight.log("Single card intent is null.");
			} else {
				
				//toDisplay
				intent.putExtra("card_selected", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getName());
				intent.putExtra("compiled_awards_id", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getSql_id());
				intent.putExtra("origin", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getOrigin());
				intent.putExtra("destination", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getDestination());
				intent.putExtra("class_of_service", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getService_class());
				intent.putExtra("price", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getPrice());
				//carrier-names
				intent.putExtra("award_program", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getAward_program());

				intent.putExtra("card_selected_2", ((CardContainer[]) (parent.getItemAtPosition(position)))[1].getName());
				intent.putExtra("compiled_awards_id_2", ((CardContainer[]) (parent.getItemAtPosition(position)))[1].getSql_id());
				intent.putExtra("award_program_2", ((CardContainer[]) (parent.getItemAtPosition(position)))[1].getAward_program());
				
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
//				intent.putExtra("card_selected", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("name")));
//				intent.putExtra("compiled_awards_id", ((Cursor) parent.getItemAtPosition(position)).getInt(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_CARD_ID)));
//				intent.putExtra("origin", origin);
//				intent.putExtra("destination", destination);
//				intent.putExtra("class_of_service", service_class);
//				intent.putExtra("price", price);
				//secondary, optional card
				
				System.out.println("Extras inserted.");
				//intent.putExtra("cost_in_miles", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex("cost_in_miles")));
				//intent.putExtra("miles_provided", ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_SPEND_BONUS)) + ((Cursor) parent.getItemAtPosition(position)).getString(((Cursor) parent.getItemAtPosition(position)).getColumnIndex(OpenHelper.KEY_FIRST_PURCHASE_BONUS)));
			}
		} else {
			System.out.println("Single card selected.");
			
			intent = new Intent(this, NewDisplaySingleCard.class);
			
			intent.putExtra("card_selected", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getName());
			intent.putExtra("compiled_awards_id", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getSql_id());
			intent.putExtra("origin", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getOrigin());
			intent.putExtra("destination", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getDestination());
			intent.putExtra("class_of_service", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getService_class());
			intent.putExtra("price", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getPrice());
			//carrier-names
			intent.putExtra("award_program", ((CardContainer[]) (parent.getItemAtPosition(position)))[0].getAward_program());
		}
		
		
		
		try {
			System.out.println("Starting activity...");
			startActivity(intent);
		} catch (Exception e) {
//			TestFlight.log(e.toString());
			System.out.println("Oops. Exception thrown.");
			System.out.println(e.toString());
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