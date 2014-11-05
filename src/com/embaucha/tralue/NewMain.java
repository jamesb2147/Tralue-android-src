package com.embaucha.tralue;

import ly.count.android.api.Countly;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class NewMain extends Activity implements OnClickListener {
	static OpenHelper open_helper;
	SQLiteDatabase rodb, rwdb;
	String[] orig_airports, dest_airports;
	AutoCompleteTextView originACTV, destinationACTV;
	RadioButton economy, business, first;
	RadioGroup rg;
	String buttonChecked;
	float price;
	EditText price_btn;
	int taps;
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_main);
		
//		TestFlight.takeOff(this.getApplication(), "34e328e0-8dd4-4a6a-8f71-16c5c00d345b");
		Countly.sharedInstance().init(this, "http://countly.embaucha.com", "a731bfa33eb9c5d2455b10120c778d06ffd73a12");
//		Parse.enableLocalDatastore(this);
//		Parse.initialize(this, "u9ZfHSQYG9KQ5F3Z2Sfuqfj2MhZFjbOHSX7xviqu", "InvTtbMr77KxroQ7iJirIUAUbIiqeRxkOUEJjRiL");
		
		//test parse
//		ParseObject testObject = new ParseObject("TestObject");
//		testObject.put("foo", "bar");
//		testObject.saveInBackground();
		
		//test parse push
		PushService.setDefaultPushCallback(this, NewMain.class);
		
		setUpDB();
		
//		setOldElementsToInvisible();
		((Button)findViewById(R.id.secretButton)).setVisibility(View.INVISIBLE);
		taps = 0;
		
		orig_airports = new String[] {OpenHelper.chi, OpenHelper.lax, OpenHelper.nyc};
		dest_airports = new String[] {OpenHelper.tokyo, OpenHelper.beijing, OpenHelper.sydney, OpenHelper.london, OpenHelper.paris, OpenHelper.hong_kong, OpenHelper.managua, OpenHelper.delhi};
		ArrayAdapter<String> autoCompleteAdapterOrig = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, orig_airports);
		ArrayAdapter<String> autoCompleteAdapterDest = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dest_airports);
		originACTV = (AutoCompleteTextView)findViewById(R.id.origin);
		originACTV.setAdapter(autoCompleteAdapterOrig);
		originACTV.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_FILTER);
		
		destinationACTV = (AutoCompleteTextView)findViewById(R.id.destination);
		destinationACTV.setAdapter(autoCompleteAdapterDest);
		destinationACTV.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_FILTER);
		
		price_btn = (EditText) findViewById(R.id.price);
		
		economy = (RadioButton)findViewById(R.id.economy);
		business = (RadioButton)findViewById(R.id.business);
		first = (RadioButton)findViewById(R.id.first);
		
		rg = (RadioGroup) findViewById(R.id.rg);
		rg.setOnClickListener(this);
	}
	
	public void setUpDB() {
		PVData pvd = new PVData(this);
		
		open_helper = new OpenHelper(this);
		rwdb = open_helper.getWritableDatabase();
		rodb = open_helper.getReadableDatabase();
	}
	
	public void launchListOfCards(View v) {
		//Intent intent = new Intent(this, ListOfCards.class);
		Intent intent = new Intent(this, NewListOfCards.class);
		String origin, destination;
		
		origin = originACTV.getText().toString();
		destination = destinationACTV.getText().toString();
		
		intent.putExtra("origin", origin);
		intent.putExtra("destination", destination);
		/*
		if (buttonChecked != null) {
			intent.putExtra("service_class", buttonChecked);
		} else {
			switch (checkRadioButton()) {
			case R.id.economy: {
				TestFlight.log("Economy fallback.");
				break;
			}
			case R.id.business: {
				TestFlight.log("Business fallback.");
				break;
			}
			case R.id.first: {
				TestFlight.log("First fallback.");
				break;
			}
			default: {
				TestFlight.log("Fuck.");
			}
			}
			if (buttonChecked.equals("")) {
				TestFlight.log("FUUUUUUUCK.");
			}
			intent.putExtra("service_class", buttonChecked);
		}
		*/
		checkRadioButton();
		intent.putExtra("service_class", buttonChecked);
		
		//ummmmm, we should probably be doing some checking here, and maybe change this to a float or something
		if (price_btn.getText().toString().equals("")) {
			price = 0;
		} else {
			price = Float.parseFloat(price_btn.getText().toString());
		}
		if (price <= 0 || price > 1000000) {
//			Toast.makeText(getApplicationContext(), "Hey, please put a reasonable ticket price in next time. Assuming $100 for now.", Toast.LENGTH_LONG).show();
			price = 100;
		}
		intent.putExtra("price", price);
		
		//calculate CPM and set values, including of fixed value points
		Cursor cursor = rodb.rawQuery("SELECT * FROM point_values INNER JOIN compiled_awards ON point_values.points_program=compiled_awards.airline  WHERE " + OpenHelper.COL_CLASS + "='" + buttonChecked + "' AND " + OpenHelper.COL_ORIGIN + " LIKE '%" + origin + "%' AND " + OpenHelper.COL_DESTINATION + " LIKE '%" + destination + "%'", null);
		rwdb.beginTransaction();
		while (cursor.moveToNext()) {
			//cpm = $ * 100 / miles
			float cpm = price * 100 / Float.parseFloat(cursor.getString(cursor.getColumnIndex("cost_in_miles")));
			if (cpm >= (float)1.0) {
				//cpm is good, leave it alone
//				TestFlight.log("CPM is good; leave it alone.");
			} else if (cpm < (float)1) {
				//cpm is no good; redeem points for cash/travel instead to buy ticket outright
//				TestFlight.log("Redeem points for cash instead. Setting CPM to 1.");
				cpm = (float)1.0;
			}
			
			rwdb.execSQL("UPDATE " + OpenHelper.TABLE_VALUES + " SET points_value=" + cpm + " WHERE points_program='" + cursor.getString(cursor.getColumnIndex("airline")) + "'");
		}
		rwdb.setTransactionSuccessful();
		rwdb.endTransaction();
		
		startActivity(intent);
	}
	
	@Override
	public void onClick(View view) {
		switch(checkRadioButton()) {
		case R.id.economy: {
			
			break;
		}
		case R.id.business: {
			
			break;
		}
		case R.id.first: {
			
			break;
		}
		}
	}
	
	private int checkRadioButton() {
		switch(rg.getCheckedRadioButtonId()) {
		case R.id.economy: {
			buttonChecked = "economy";
			return R.id.economy;
			//break;
		}
		case R.id.business: {
			buttonChecked = "business";
			return R.id.business;
			//break;
		}
		case R.id.first: {
			buttonChecked = "first";
			return R.id.first;
			//break;
		}
		default: {
//			TestFlight.log("No selection found. Assuming economy.");
			economy.setChecked(true);
			return checkRadioButton();
		}
		}
	}
	
	public void emailDeveloper(View v) {
//		TestFlight.passCheckpoint("Clicked button to send feedback from main program view.");
//		TestFlight.log("Clicked button to send feedback from main program view.");
		
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@embaucha.com"});		  
		email.putExtra(Intent.EXTRA_SUBJECT, "Tralue - About us");
		//email.putExtra(Intent.EXTRA_TEXT, url);
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Choose an Email client :"));
	}
	
	public void revealOldLayout(View v) {
		taps++;
		if (taps >= 5) {
			setOldElementsToVisible();
		}
	}
	
	protected void setOldElementsToInvisible() {
		((TextView)findViewById(R.id.origin_airport)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.origin_hint)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.origin)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.destination_airport)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.destination_hint)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.destination)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.class_of_service_text)).setVisibility(View.GONE);
		((RadioGroup)findViewById(R.id.rg)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.how_do_i_get_there_button)).setVisibility(View.GONE);
	}
	
	protected void setOldElementsToVisible() {
		((TextView)findViewById(R.id.origin_airport)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.origin_hint)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.origin)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.destination_airport)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.destination_hint)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.destination)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.class_of_service_text)).setVisibility(View.VISIBLE);
		((View)findViewById(R.id.rg)).setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.how_do_i_get_there_button)).setVisibility(View.VISIBLE);
	}
	
	//menu creation
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.new_main, menu);
		    return true;
		}

	    //menu listener (does not have to be manually assigned
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
//	    	TestFlight.passCheckpoint("Used actionbar item in simple view.");
	    	
	        switch (item.getItemId()) {
	            case R.id.about_us: {
	                Intent intent = new Intent(this, AboutUs.class);
	                startActivity(intent);
	                return true;
	            }
	            case R.id.manual_mode: {
	            	Intent intent = new Intent(this, MainActivity.class);
	            	startActivity(intent);
	            	return true;
	            }
	            default: {
	                return super.onOptionsItemSelected(item);
	            }
	        }
	    }
	public void onStart() {
		super.onStart();
		Countly.sharedInstance().onStart();
	}
		
	public void onStop() {
		System.out.println("NewMain onStop");
		super.onStop();
		Countly.sharedInstance().onStop();
	}
	
	public void onDestroy() {
		System.out.println("NewMain onDestroy");
		rodb.close();
		rwdb.close();
		
		super.onDestroy();
	}
}