package com.embaucha.tralue;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_main);
		
		setUpDB();
		
		orig_airports = new String[] {"Chicago - CHI ( ORD, MDW )", "Los Angeles - LAX", "New York - NYC ( JFK, LGA )"};
		dest_airports = new String[] {"Tokyo - TYO ( NRT, HND )", "Beijing - BJS ( PEK, NAY )", "Sydney - SYD", "London - LON ( LHR, LGW, STN, LCY )", "Paris - PAR ( CDG, ORY )", "Hong Kong - HKG", "Managua - MGA"};
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
				System.out.println("Economy fallback.");
				break;
			}
			case R.id.business: {
				System.out.println("Business fallback.");
				break;
			}
			case R.id.first: {
				System.out.println("First fallback.");
				break;
			}
			default: {
				System.out.println("Fuck.");
			}
			}
			if (buttonChecked.equals("")) {
				System.out.println("FUUUUUUUCK.");
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
			Toast.makeText(getApplicationContext(), "Hey, please put a reasonable ticket price in next time. Assuming $100 for now.", Toast.LENGTH_LONG).show();
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
				System.out.println("CPM is good; leave it alone.");
			} else if (cpm < (float)1) {
				//cpm is no good; redeem points for cash/travel instead to buy ticket outright
				System.out.println("Redeem points for cash instead. Setting CPM to 1.");
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
		// TODO Auto-generated method stub
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
			System.out.println("No selection found. Assuming economy.");
			economy.setChecked(true);
			return checkRadioButton();
		}
		}
	}
	
	public void emailDeveloper(View v) {
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@embaucha.com"});		  
		email.putExtra(Intent.EXTRA_SUBJECT, "Tralue - About us");
		//email.putExtra(Intent.EXTRA_TEXT, url);
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Choose an Email client :"));
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
	
	public void onDestroy() {
		rodb.close();
		rwdb.close();
		
		super.onDestroy();
	}
}