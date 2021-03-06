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

public class DisplaySingleCard extends Activity {
	String name = "name", issuer = "issuer";
	String url, card;
	
	public void onCreate(Bundle bundle) {
//		TestFlight.log("Launching...");
		super.onCreate(bundle);
//		TestFlight.log("After super call...");
		setContentView(R.layout.display_single_card);
//		TestFlight.log("After setting view...");
		card = this.getIntent().getExtras().getString("card_selected");
//		TestFlight.log("Grabbing data from bundle...");
		
		//OpenHelper oh = new OpenHelper(this);
		SQLiteDatabase rodb = MainActivity.open_helper.getReadableDatabase();
		Cursor cursor = rodb.rawQuery("SELECT providers.name FROM providers INNER JOIN point_values ON providers.points_program=point_values.points_program", null);
		
		//I need to add a button for sending email to one's self with a link to the card application
		
		while(cursor.moveToNext()) {
//			TestFlight.log("iterating...");
			if (cursor.getString(cursor.getColumnIndex(name)).equals(card)) {
//				TestFlight.log("Match!");
//				TestFlight.log("SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value' FROM providers INNER JOIN point_values " +
//						"ON providers.points_program=point_values.points_program WHERE providers.name = '" + cursor.getString(cursor.getColumnIndex(name)) + "'");
				Cursor cursor2 = rodb.rawQuery("SELECT *, first_purchase_bonus * points_value / 100 AS 'first_purchase_bonus_value', " +
						"(spend_bonus * points_value + first_purchase_bonus * points_value) / 100 AS maximum_bonus_value, spend_requirement / " +
						"time_to_reach_spend_in_months AS spend_per_month_for_bonus, spend_bonus * points_value / 100 AS spend_bonus_value, " +
						"CASE WHEN fee_waived_first_year = 'No' " +
						"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - annual_fee " +
						"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 " +
						"END AS max_bonus_less_annual_fee," +
						" points_per_dollar_spent_general_spend * points_value * planned_spend_per_month * 12 / 100 - annual_fee AS value_of_keeping_card, " +
						"CASE WHEN fee_waived_first_year = 'No' " +
						//create a column for the cost after spending monthly allocation
						"THEN (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate / 100 + annual_fee) " +
						"ELSE (spend_bonus * points_value + first_purchase_bonus * points_value) / 100 - (spend_requirement * loss_rate) / 100 " + 
						"END AS value_with_manufactured_spend " +
						"FROM providers INNER JOIN point_values ON " +
						"providers.points_program=point_values.points_program WHERE providers.name = '" + 
						cursor.getString(cursor.getColumnIndex(name)) + "'", null);
				
				cursor2.moveToFirst();
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
				
				Map<String, String> articleParams = new HashMap<String, String>();
				articleParams.put("card_name", cursor2.getString(cursor2.getColumnIndex(name)));
//				FlurryAgent.logEvent("Card_selected", articleParams);
				
				url = cursor2.getString(cursor2.getColumnIndex("url"));
				if (url != null) {
					((Button)findViewById(R.id.apply_now_button)).setVisibility(View.VISIBLE);
					((Button)findViewById(R.id.email_to_self_button)).setVisibility(View.VISIBLE);
				} else {
					((Button)findViewById(R.id.apply_now_button)).setVisibility(View.INVISIBLE);
					((Button)findViewById(R.id.email_to_self_button)).setVisibility(View.INVISIBLE);
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