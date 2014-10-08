package com.embaucha.tralue;

import java.util.HashMap;
import java.util.Map;

import ly.count.android.api.Countly;

//import com.flurry.android.FlurryAgent;
//import com.testflightapp.lib.TestFlight;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewDisplaySingleCard extends Activity {
	String name = "name", issuer = "issuer";
	String url, card, origin, destination, service_class;
	Float price;
	int compiled_awards_id;
	
	public void onCreate(Bundle bundle) {
//		TestFlight.log("Launching...");
		super.onCreate(bundle);
//		TestFlight.log("After super call...");
//		TestFlight.passCheckpoint("Opened a new-style card display.");
//		TestFlight.log("After TestFlight call...");
		setContentView(R.layout.new_display_single_card);
//		TestFlight.log("After setting view...");
		Intent intent = this.getIntent();
		Bundle extras = intent.getExtras();
		card = extras.getString("card_selected");
		origin = extras.getString("origin");
		destination = extras.getString("destination");
		service_class = extras.getString("class_of_service");
		price = extras.getFloat("price", (float)0.0);
		compiled_awards_id = extras.getInt("compiled_awards_id");
		
//		TestFlight.log("Grabbed data from bundle...");
		
		OpenHelper oh = new OpenHelper(this);
		SQLiteDatabase rodb = oh.getReadableDatabase();
		Cursor cursor = rodb.rawQuery("SELECT providers.name, providers.card_id FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program", null);
		
		//I need to add a button for sending email to one's self with a link to the card application
		System.out.println("Move to next...");
		while(cursor.moveToNext()) {
//			TestFlight.log("iterating...");
			if (cursor.getInt(cursor.getColumnIndex(OpenHelper.KEY_CARD_ID)) == (compiled_awards_id)) {
//				TestFlight.log("Match!");
				//TestFlight.log("SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value' FROM providers INNER JOIN point_values " +
				//		"ON providers.points_program=point_values.points_program WHERE providers.name = '" + cursor.getString(cursor.getColumnIndex(name)) + "'");
				
				String query = "SELECT *, " + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + " AS total_bonus_points, " +
						"(" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " AS " + NewListOfCards.percentage + 
						" FROM " + OpenHelper.TABLE_PROVIDERS + " LEFT OUTER JOIN " + OpenHelper.TABLE_PARTNERS + 
						" ON " + "providers.points_program=partners.transferring_point_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
						" ON " + "providers.points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
						OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
						" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + 
						OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + OpenHelper.KEY_CARD_ID + "=" + compiled_awards_id;
				query += " UNION ALL ";
				query += "SELECT *, " + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + " AS total_bonus_points, " +
						"(" + OpenHelper.KEY_SPEND_BONUS + " + " + OpenHelper.KEY_FIRST_PURCHASE_BONUS + ") * 100 / " + OpenHelper.COL_COST + " AS " + NewListOfCards.percentage + 
						" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
						" ON " + "providers.points_program=" + OpenHelper.KEY_TRANSFERRING_PROGRAM + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
						" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE + " LEFT OUTER JOIN " +
						OpenHelper.TABLE_AIRLINE_NAMES + " ON " + OpenHelper.TABLE_AIRLINE_NAMES + "." + OpenHelper.KEY_CARRIERS + "=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
						" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + 
						OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' AND " + OpenHelper.KEY_CARD_ID + "=" + compiled_awards_id;
//				TestFlight.log("Query:");
//				TestFlight.log(query);
				System.out.println("Running query:\n" + query);
				Cursor cursor2 = rodb.rawQuery(query, null);
				
				
				
				
				//OLD QUERY AND EXPERIMENTATION
				/*
				"SELECT *, first_purchase_bonus * point_values.points_value / 100 AS 'first_purchase_bonus_value', " +
				"(spend_bonus * point_values.points_value + first_purchase_bonus * point_values.points_value) / 100 AS maximum_bonus_value, spend_requirement / " +
				"time_to_reach_spend_in_months AS spend_per_month_for_bonus, spend_bonus * points_value / 100 AS spend_bonus_value, " +
				"CASE WHEN fee_waived_first_year = 'No' " +
				"THEN (spend_bonus * point_values.points_value + first_purchase_bonus * point_values.points_value) / 100 - annual_fee " +
				"ELSE (spend_bonus * point_values.points_value + first_purchase_bonus * point_values.points_value) / 100 " +
				"END AS max_bonus_less_annual_fee," +
				" points_per_dollar_spent_general_spend * point_values.points_value * planned_spend_per_month * 12 / 100 - annual_fee AS value_of_keeping_card, " +
				"CASE WHEN fee_waived_first_year = 'No' " +
				//create a column for the cost after spending monthly allocation
				"THEN (spend_bonus * point_values.points_value + first_purchase_bonus * point_values.points_value) / 100 - (spend_requirement * loss_rate / 100 + annual_fee) " +
				"ELSE (spend_bonus * point_values.points_value + first_purchase_bonus * point_values.points_value) / 100 - (spend_requirement * loss_rate) / 100 " + 
				"END AS value_with_manufactured_spend " +
				"FROM providers INNER JOIN point_values ON " +
				"providers.points_program=point_values.points_program " +
				" INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + " ON " + "compiled_awards.airline=providers.points_program" +
				//testing new code
				//" FROM " + OpenHelper.TABLE_PROVIDERS + " INNER JOIN " + OpenHelper.TABLE_PARTNERS + 
				//" ON " + "providers.points_program=partners.points_program" + " INNER JOIN " + OpenHelper.TABLE_COMPILED_AWARDS + 
				//" ON " + "partners.partner_points_program=" + OpenHelper.TABLE_COMPILED_AWARDS + "." + OpenHelper.COL_AIRLINE +
				//" INNER JOIN " + "point_values" + " ON " + "point_values.points_program=providers.points_program" +
				" WHERE " + OpenHelper.COL_CLASS + "='" + service_class + "' AND " + 
				OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%' " +
				"AND providers.name = '" + 
				cursor.getString(cursor.getColumnIndex(name)) + "'"
				
				*/
				
				
				
				
				cursor2.moveToFirst();
				
				//new format
				
				//origin - destination
				//miles required for journey at low level
				//disclaimer
				//miles provided by this card
				//annual fee of card
				//whether fee is waived the first year
				
				/*
				((TextView)findViewById(R.id.card_name)).setText(cursor2.getString(cursor2.getColumnIndex(name)));
				((TextView)findViewById(R.id.card_issuer)).setText(cursor2.getString(cursor2.getColumnIndex(issuer)));
				((TextView)findViewById(R.id.card_net_bonus)).setText(cursor2.getString(cursor2.getColumnIndex("max_bonus_less_annual_fee")));
				((TextView)findViewById(R.id.card_total_bonus)).setText(cursor2.getString(cursor2.getColumnIndex("maximum_bonus_value")));
				((TextView)findViewById(R.id.card_first_purchase_bonus)).setText(cursor2.getString(cursor2.getColumnIndex("first_purchase_bonus_value")));
				((TextView)findViewById(R.id.card_spend_bonus_value)).setText(cursor2.getString(cursor2.getColumnIndex("spend_bonus_value")));
				((TextView)findViewById(R.id.spend_per_month_for_bonus)).setText(cursor2.getString(cursor2.getColumnIndex("spend_per_month_for_bonus")));
				((TextView)findViewById(R.id.card_foreign_transaction_fee)).setText(cursor2.getString(cursor2.getColumnIndex("foreign_transaction_fee")));
				//TestFlight.log("Value of keeping card is:" + cursor2.getString(cursor2.getColumnIndex("value_of_keeping_card")));
				((TextView)findViewById(R.id.value_of_keeping_card)).setText(cursor2.getString(cursor2.getColumnIndex("value_of_keeping_card")));
				((TextView)findViewById(R.id.value_with_manufactured_spend)).setText(cursor2.getString(cursor2.getColumnIndex("value_with_manufactured_spend")));
				((TextView)findViewById(R.id.notes)).setText(cursor2.getString(cursor2.getColumnIndex("notes")));
				//TestFlight.log("The image is: " + cursor2.getString(cursor2.getColumnIndex("image")));
				//((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.sapphire_preferred);
				 * 
				 */
				
				//NEW card view data
				System.out.println("Starting to read data...");
				((TextView)findViewById(R.id.card_name)).setText(cursor2.getString(cursor2.getColumnIndex(name)));
				System.out.println("Read name.");
				((TextView)findViewById(R.id.card_issuer)).setText(cursor2.getString(cursor2.getColumnIndex(issuer)));
				System.out.println("Read issuer.");
				
				//cost to get from origin to destination in miles
				//origin
//				TestFlight.log("Origin is: " + origin);
				((TextView)findViewById(R.id.card_origin)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.COL_ORIGIN)));
				System.out.println("Read origin.");
				//destination
//				TestFlight.log("Destination is: " + destination);
				((TextView)findViewById(R.id.card_destination)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.COL_DESTINATION)));
				//cost in miles
				((TextView)findViewById(R.id.card_cost_in_miles)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.COL_COST)));
				//on carrier
				((TextView)findViewById(R.id.card_carrier)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NAMES)));
				//miles provided by card bonuses
				((TextView)findViewById(R.id.miles_from_card)).setText(cursor2.getString(cursor2.getColumnIndex("total_bonus_points")));
				//percentage of needed miles earned by signup
				((TextView)findViewById(R.id.card_percentage_of_miles)).setText(cursor2.getString(cursor2.getColumnIndex(NewListOfCards.percentage)));
				//spend per month to hit bonus
				//annual fee
				((TextView)findViewById(R.id.card_annual_fee)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_ANNUAL_FEE)));
				//foreign transaction fee
				((TextView)findViewById(R.id.card_foreign_transaction_fee)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_FOREIGN_TRANSACTION_FEE)));
				//award notes
				((TextView)findViewById(R.id.award_notes)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.COL_AWARD_NOTES)));
				//carrier notes
				((TextView)findViewById(R.id.points_program_notes)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NOTES)));
				//credit card notes
				((TextView)findViewById(R.id.notes)).setText(cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_NOTES)));
				
				Map<String, String> articleParams = new HashMap<String, String>();
				articleParams.put("card_name", cursor2.getString(cursor2.getColumnIndex(name)));
//				FlurryAgent.logEvent("Card_selected", articleParams);
				
//				TestFlight.log("Card selected is: " + card);
				
				//temporary until card_percentage_of_miles is fixed
				((TextView)findViewById(R.id.card_percentage_of_miles_text)).setVisibility(View.GONE);
				((TextView)findViewById(R.id.card_percentage_of_miles)).setVisibility(View.GONE);
				
				url = cursor2.getString(cursor2.getColumnIndex("url"));
				if (url != null) {
					((Button)findViewById(R.id.apply_now_button)).setVisibility(View.VISIBLE);
					((Button)findViewById(R.id.email_to_self_button)).setVisibility(View.VISIBLE);
				} else {
					((Button)findViewById(R.id.apply_now_button)).setVisibility(View.GONE);
					((Button)findViewById(R.id.email_to_self_button)).setVisibility(View.GONE);
				}
				
				String carrier_notes = cursor2.getString(cursor2.getColumnIndex(OpenHelper.KEY_CARRIER_NOTES));
				if (carrier_notes != null) {
					((TextView)findViewById(R.id.points_program_notes_text)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.points_program_notes)).setVisibility(View.VISIBLE);
				} else {
					((TextView)findViewById(R.id.points_program_notes_text)).setVisibility(View.GONE);
					((TextView)findViewById(R.id.points_program_notes)).setVisibility(View.GONE);
				}
				
				String award_notes = cursor2.getString(cursor2.getColumnIndex(OpenHelper.COL_AWARD_NOTES));
				if (award_notes != null) {
					((TextView)findViewById(R.id.award_notes_text)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.award_notes)).setVisibility(View.VISIBLE);
				} else {
					((TextView)findViewById(R.id.award_notes_text)).setVisibility(View.GONE);
					((TextView)findViewById(R.id.award_notes)).setVisibility(View.GONE);
				}
				
				switch (cursor2.getInt(cursor2.getColumnIndex("image"))) {
				case 1: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.sapphire_preferred);
					break;
				}
				case 2: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.barclaycard_arrival_no_fee);
					break;
				}
				case 3: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.barclaycard_arrival);
					break;
				}
				case 4: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.club_carlson_premier_rewards_visa_signature);
					break;
				}
				case 5: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.discover_it);
					break;
				}
				case 6: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.forward_card);
					break;
				}
				case 7: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.gold_card);
					break;
				}
				case 8: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.mercedes_benz_platinum);
					break;
				}
				case 9: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.platinum_card);
					break;
				}
				case 10: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.premier_rewards_gold);
					break;
				}
				case 11: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.sapphire_card);
					break;
				}
				case 12: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.starwood_preferred_guest);
					break;
				}
				case 13: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.thank_you_preferred);
					break;
				}
				case 14: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.thank_you_premier);
					break;
				}
				case 15: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.southwest_premier_personal);
					break;
				}
				case 16: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.carlson_business);
					break;
				}
				case 17: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.ink_bold);
					break;
				}
				case 18: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.gold_skymiles_business);
					break;
				}
				case 19: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.starwood_preferred_guest_business);
					break;
				}
				case 20: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.platinum_skymiles_business);
					break;
				}
				case 21: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.delta_reserve_business_card);
					break;
				}
				case 22: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.ink_plus);
					break;
				}
				case 23: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.ink_cash);
					break;
				}
				case 24: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.thank_you_business);
					break;
				}
				case 25: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.ink_classic);
					break;
				}
				case 26: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.aadvantage_world_mastercard);
					break;
				}
				case 27: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.southwest_premier_business);
					break;
				}
				case 28: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.southwest_plus_business);
					break;
				}
				case 29: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.alaska_airlines_business);
					break;
				}
				case 30: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.platinum_business_card);
					break;
				}
				case 31: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.flexperks_business);
					break;
				}
				case 32: {
					((ImageView)findViewById(R.id.card_image)).setImageResource(R.drawable.amtrak_card);
					break;
				}
				default: {
					((ImageView)findViewById(R.id.card_image)).setVisibility(View.INVISIBLE);
					break;
				}
				}
			}
			
		}
		
		rodb.close();
	}
	
	public void sendToURL(View v) {
		//figure out how to do this based on data in the card's row/db entry
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(myIntent);
	}
	
	public void emailToSelf(View v) {
		//try {
			//Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[] {"com.google"}, false, null, null, null, null);
			
		//}
		
		Intent email = new Intent(Intent.ACTION_SEND);
		//email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});		  
		email.putExtra(Intent.EXTRA_SUBJECT, card);
		email.putExtra(Intent.EXTRA_TEXT, url);
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Choose an Email client :"));
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
//		FlurryAgent.onStartSession(this, "B8HFG9HTK5C3RQRNNFY5");
//		TestFlight.log("Called Flurry.");
		Countly.sharedInstance().onStart();
	}
	 
	@Override
	protected void onStop()
	{
		
		super.onStop();		
//		FlurryAgent.onEndSession(this);
//		TestFlight.log("Stopped Flurry.");
		Countly.sharedInstance().onStop();
	}
}