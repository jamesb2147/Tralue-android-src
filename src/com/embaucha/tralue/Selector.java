package com.embaucha.tralue;

import java.util.HashMap;
import java.util.Map;

import ly.count.android.api.Countly;

//import com.flurry.android.FlurryAgent;

//import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

public class Selector extends Fragment implements OnItemSelectedListener, OnCheckedChangeListener {
	Spinner spinner;
	ArrayAdapter<CharSequence> array_adapter;
	RadioGroup rg;
	RadioButton rb_net_value, rb_total_value, rb_annual_value;
	View layout;
	static String title;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.selector, container, false);
		//configureUpButtons();
		
		rg = (RadioGroup) layout.findViewById(R.id.rg_sort);
		rb_net_value = (RadioButton) layout.findViewById(R.id.net_bonus_value_radio);
		rb_total_value = (RadioButton) layout.findViewById(R.id.total_bonus_value_radio);
		rb_annual_value = (RadioButton) layout.findViewById(R.id.annual_value_of_keeping_card_radio);
		
		rg.setOnCheckedChangeListener(this);
		title = getResources().getString(R.string.selector);
		
		switch (MainActivity.sort) {
		/*
		case null: {
			rb_total_value.setChecked(true);
			break;
		}
		*/
		case 0: {
			rb_net_value.setChecked(true);
			break;
		}
		case 1: {
			rb_total_value.setChecked(true);
			break;
		}
		case 2: {
			rb_annual_value.setChecked(true);
			break;
		}
		default: {
			rb_total_value.setChecked(true);
			break;
		}
		}
		
		return layout;
	}
	
	/*
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selector);
		
		spinner = (Spinner)findViewById(R.id.sort_spinner);
		spinner.setOnItemSelectedListener(this);
		
		array_adapter = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
		array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(array_adapter);
	}
	*/
	
	protected void loadCards(View v) {
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		System.out.println("I selected item: " + parent.getItemAtPosition(position).toString());
		if (((String)"Please select a sort").equals(parent.getItemAtPosition(position))) {
			
		} else {
			//Bundle bundle = new Bundle();
			//bundle.putString("sort", parent.getItemAtPosition(position).toString());
			Intent intent = new Intent(getActivity(), CardsActivity.class);
			System.out.println(parent.getItemAtPosition(position).toString());
			intent.putExtra("sort", parent.getItemAtPosition(position).toString());
			
			//tracking via Flurry analytics...
			Map<String, String> articleParams = new HashMap<String, String>();
			articleParams.put("sort", parent.getItemAtPosition(position).toString());
//			FlurryAgent.logEvent("Sort_selected", articleParams);
			
			//start activity
			startActivity(intent);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
//		FlurryAgent.onStartSession(getActivity(), "B8HFG9HTK5C3RQRNNFY5");
		System.out.println("Called Flurry.");
		Countly.sharedInstance().onStart();
	}
	 
	@Override
	public void onStop()
	{
		super.onStop();		
//		FlurryAgent.onEndSession(getActivity());
		System.out.println("Stopped Flurry.");
		Countly.sharedInstance().onStop();
	}

	@Override
	public void onCheckedChanged(RadioGroup rg1, int arg1) {
		int selected = rg1.getCheckedRadioButtonId();
		System.out.println("Checked button is: " + rg.getCheckedRadioButtonId());
		
		MainActivity.sort = selected;
		MainActivity.updateDataset();
	}
}