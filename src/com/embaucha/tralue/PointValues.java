package com.embaucha.tralue;

//import com.flurry.android.FlurryAgent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class PointValues extends Fragment implements OnLongClickListener, OnCheckedChangeListener {
	EditText delta, american, spirit, united, usair, jetblue, southwest, frontier, ba, alaska, ur, mr, carlson, arrival, spg, usbank, discover, lossrate, monthlyspend, amtrak, singapore, ty;
	EditText[] et_array = {delta, american, spirit, united, usair, jetblue, southwest, frontier, ba, alaska, ur, mr, carlson, arrival, spg, usbank, discover, lossrate, monthlyspend, amtrak, singapore, ty};
	TextView tv_delta, tv_american, tv_spirit, tv_united, tv_usair, tv_jetblue, tv_southwest, tv_frontier, tv_ba, tv_alaska, tv_ur, tv_mr, tv_carlson, tv_arrival, tv_spg, tv_usbank, tv_discover, tv_lossrate, tv_monthlyspend, tv_amtrak, tv_singapore, tv_ty;
	TextView[] tv_array = {tv_delta, tv_american, tv_spirit, tv_united, tv_usair, tv_jetblue, tv_southwest, tv_frontier, tv_ba, tv_alaska, tv_ur, tv_mr, tv_carlson, tv_arrival, tv_spg, tv_usbank, tv_discover, tv_lossrate, tv_monthlyspend, tv_amtrak, tv_singapore, tv_ty};
	PVData pvd;
	View layout;
	
	RadioGroup rg;
	RadioButton business, personal, business_and_personal;
	public static boolean business_boolean, personal_boolean;
	
	String spg_point = "SPG point";
	String ur_point = "Ultimate Rewards point";
	String mr_point = "Membership Rewards point";
	String delta_point = "Delta Skymile";
	String alaska_point = "Alaska Airlines mile";
	String american_point = "American AAdvantage mile";
	String united_point = "United Airlines mile";
	String usair_point = "US Airways Dividend mile";
	String jetblue_point = "JetBlue point";
	String spirit_point = "Free Spirit mile";
	String southwest_point = "Southwest Air point";
	String ba_point = "British Airways Avios";
	String frontier_point = "Frontier mile";
	String carlson_point = "Club Carlson point";
	String amtrak_point = "Amtrak Guest Rewards point";
	String arrival_point = "Arrival point";
	String usbank_point = "US Bank FlexPoint";
	String discover_point = "Discover point";
	String singapore_mile = "Singapore KrisFlyer mile";
	String ty_point = "Thank You point";
	String lossrate_text = "This is the loss rate for laundering spend on your credit card. For example, Square incurs a 2.75% loss via service fees, while Amazon Payments currently offers no-fee transactions between persons up to $1000/month.";
	String monthlyspend_text = "This is the amount you expect to spend on your next credit card on an ongoing basis. This is generally not useful for churners, but very useful for people who prefer to only have one card.";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.point_values, container, false);
		//configureUpButtons();
		
		pvd = new PVData();
		
		buttonUp();
		
		repopulateArrays();
		
		assignListeners();
		
		loadIt();
		
		//business/personal filtering and initialization logic here
		if (!business_boolean && !personal_boolean) {
			business_and_personal.setChecked(true);
		}
		
		return layout;
	}
	
	private void repopulateArrays() {
		//this array MUST remain in the same order as the PVData array!!!! ALWAYS!!!!!!!!
		et_array = new EditText[] {delta, american, spirit, united, usair, jetblue, southwest, frontier, ba, alaska, ur, mr, carlson, arrival, spg, usbank, discover, lossrate, monthlyspend, amtrak, singapore, ty};
		tv_array = new TextView[] {tv_delta, tv_american, tv_spirit, tv_united, tv_usair, tv_jetblue, tv_southwest, tv_frontier, tv_ba, tv_alaska, tv_ur, tv_mr, tv_carlson, tv_arrival, tv_spg, tv_usbank, tv_discover, tv_lossrate, tv_monthlyspend, tv_amtrak, singapore, ty};
	}
	
	private void assignListeners() {
		assignETListeners();
		assignTVListeners();
		assignRGListeners();
	}
	
	private void assignETListeners() {
		for (int i = 0; i < et_array.length; i++) {
			System.out.println("Setting PV EditText listener for element " + i);
			
			et_array[i].setOnLongClickListener(this);
		}
	}
	
	private void assignTVListeners() {
		for (int i = 0; i < tv_array.length; i++) {
			System.out.println("Setting PV TextView listener for element " + i);
			
			tv_array[i].setOnLongClickListener(this);
		}
	}
	
	private void assignRGListeners() {
		rg.setOnCheckedChangeListener(this);
	}
	
	private void buttonUp() {
		assignEditTextsById();
		assignTextViewsById();
		assignRadioGroupsById();
		assignRadioButtonsById();
	}
	
	private void assignRadioGroupsById() {
		System.out.println("Assigning RadioGroup...");
		rg = (RadioGroup) layout.findViewById(R.id.rg_business_personal);
		System.out.println("RadioGroup assigned.");
	}
	
	private void assignRadioButtonsById() {
		business = (RadioButton) layout.findViewById(R.id.show_business_cards);
		personal = (RadioButton) layout.findViewById(R.id.show_personal_cards);
		business_and_personal = (RadioButton) layout.findViewById(R.id.show_business_and_personal_cards);
	}
	
	private void assignEditTextsById() {
		ba = (EditText) layout.findViewById(R.id.et_ba);
		delta = (EditText) layout.findViewById(R.id.et_delta);
		american = (EditText) layout.findViewById(R.id.et_american);
		spirit = (EditText) layout.findViewById(R.id.et_spirit);
		united = (EditText) layout.findViewById(R.id.et_united);
		usair = (EditText) layout.findViewById(R.id.et_usair);
		jetblue = (EditText) layout.findViewById(R.id.et_jetblue);
		southwest = (EditText) layout.findViewById(R.id.et_southwest);
		frontier = (EditText) layout.findViewById(R.id.et_frontier);
		alaska = (EditText) layout.findViewById(R.id.et_alaska);
		ur = (EditText) layout.findViewById(R.id.et_ur);
		mr = (EditText) layout.findViewById(R.id.et_mr);
		carlson = (EditText) layout.findViewById(R.id.et_carlson);
		amtrak = (EditText) layout.findViewById(R.id.et_amtrak);
		arrival = (EditText) layout.findViewById(R.id.et_arrival);
		spg = (EditText) layout.findViewById(R.id.et_spg);
		usbank = (EditText) layout.findViewById(R.id.et_usbank);
		discover = (EditText) layout.findViewById(R.id.et_discover);
		singapore = (EditText) layout.findViewById(R.id.et_singapore);
		ty = (EditText) layout.findViewById(R.id.et_ty);
		lossrate = (EditText) layout.findViewById(R.id.et_lossrate);
		monthlyspend = (EditText) layout.findViewById(R.id.et_monthlyspend);
	}
	
	private void assignTextViewsById() {
		tv_ba = (TextView) layout.findViewById(R.id.tv_ba);
		tv_delta = (TextView) layout.findViewById(R.id.tv_delta);
		tv_american = (TextView) layout.findViewById(R.id.tv_american);
		tv_spirit = (TextView) layout.findViewById(R.id.tv_spirit);
		tv_united = (TextView) layout.findViewById(R.id.tv_united);
		tv_usair = (TextView) layout.findViewById(R.id.tv_usair);
		tv_jetblue = (TextView) layout.findViewById(R.id.tv_jetblue);
		tv_southwest = (TextView) layout.findViewById(R.id.tv_southwest);
		tv_frontier = (TextView) layout.findViewById(R.id.tv_frontier);
		tv_alaska = (TextView) layout.findViewById(R.id.tv_alaska);
		tv_ur = (TextView) layout.findViewById(R.id.tv_ur);
		tv_mr = (TextView) layout.findViewById(R.id.tv_mr);
		tv_carlson = (TextView) layout.findViewById(R.id.tv_carlson);
		tv_amtrak = (TextView) layout.findViewById(R.id.tv_amtrak);
		tv_arrival = (TextView) layout.findViewById(R.id.tv_arrival);
		tv_spg = (TextView) layout.findViewById(R.id.tv_spg);
		tv_usbank = (TextView) layout.findViewById(R.id.tv_usbank);
		tv_discover = (TextView) layout.findViewById(R.id.tv_discover);
		tv_singapore = (TextView) layout.findViewById(R.id.tv_singapore);
		tv_ty = (TextView) layout.findViewById(R.id.tv_ty);
		tv_lossrate = (TextView) layout.findViewById(R.id.tv_lossrate);
		tv_monthlyspend = (TextView) layout.findViewById(R.id.tv_monthlyspend);
	}
	
	/*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.point_values);
		
		pvd = new PVData();
		
		ba = (EditText) findViewById(R.id.et_ba);
		delta = (EditText) findViewById(R.id.et_delta);
		american = (EditText) findViewById(R.id.et_american);
		spirit = (EditText) findViewById(R.id.et_spirit);
		united = (EditText) findViewById(R.id.et_united);
		usair = (EditText) findViewById(R.id.et_usair);
		jetblue = (EditText) findViewById(R.id.et_jetblue);
		southwest = (EditText) findViewById(R.id.et_southwest);
		frontier = (EditText) findViewById(R.id.et_frontier);
		alaska = (EditText) findViewById(R.id.et_alaska);
		ur = (EditText) findViewById(R.id.et_ur);
		mr = (EditText) findViewById(R.id.et_mr);
		carlson = (EditText) findViewById(R.id.et_carlson);
		arrival = (EditText) findViewById(R.id.et_arrival);
		spg = (EditText) findViewById(R.id.et_spg);
		usbank = (EditText) findViewById(R.id.et_usbank);
		discover = (EditText) findViewById(R.id.et_discover);
		lossrate = (EditText) findViewById(R.id.et_lossrate);
		monthlyspend = (EditText) findViewById(R.id.et_monthlyspend);
		
		tv_spg = (TextView) findViewById(R.id.tv_spg);
		tv_spg.setOnLongClickListener(this);
		
		loadIt();
	}
	*/
	
	public void onResume() {
		loadIt();
		
		System.out.println("PointValues resumed.");
		
		super.onResume();
	}
	
	public void loadIt() {
		MainActivity.open_helper.updatePVData(pvd);
		
		for (int i = 0; i < et_array.length; i++) {
			System.out.println("Loading element " + et_array[i].getId() + " at position " + i + " and populating with value " + Float.toString(pvd.getPointsValue(i)));
			et_array[i].setText(Float.toString(pvd.getPointsValue(i)));
		}
		
		/*
		//System.out.println("The value of ba points is: " + pvd.getPointsValue("ba"));
		ba.setText(Float.toString(pvd.getPointsValue("ba")));
		delta.setText(Float.toString(pvd.getPointsValue("delta")));
		american.setText(Float.toString(pvd.getPointsValue("american")));
		spirit.setText(Float.toString(pvd.getPointsValue("spirit")));
		united.setText(Float.toString(pvd.getPointsValue("united")));
		usair.setText(Float.toString(pvd.getPointsValue("usair")));
		jetblue.setText(Float.toString(pvd.getPointsValue("jetblue")));
		southwest.setText(Float.toString(pvd.getPointsValue("southwest")));
		frontier.setText(Float.toString(pvd.getPointsValue("frontier")));
		alaska.setText(Float.toString(pvd.getPointsValue("alaska")));
		ur.setText(Float.toString(pvd.getPointsValue("ur")));
		mr.setText(Float.toString(pvd.getPointsValue("mr")));
		carlson.setText(Float.toString(pvd.getPointsValue("carlson")));
		arrival.setText(Float.toString(pvd.getPointsValue("arrival")));
		spg.setText(Float.toString(pvd.getPointsValue("spg")));
		usbank.setText(Float.toString(pvd.getPointsValue("usbank")));
		discover.setText(Float.toString(pvd.getPointsValue("discover")));
		lossrate.setText(Float.toString(pvd.getPointsValue("lossrate")));
		monthlyspend.setText(Float.toString(pvd.getPointsValue("monthlyspend")));
		*/
	}
	
	public void onDestroy() {
		saveIt();
		
		super.onDestroy();
	}
	
	public void saveIt() {
		//PVData pvd = new PVData();
		
		/*
		 * for (int i = 0; i < pvd.airlines.length; i++) {
		 * pvd.setPointsValue(i, Float.valueOf([insert EditText array here]i));
		 * }
		 * MainActivity.open_helper.updatePointValues(pvd);
		 * 
		 * finish();
		 */
		
		for (int i = 0; i < et_array.length; i++) {
			if (et_array[i] != null) {
				if (et_array[i].getText().toString().equals("")) {
					System.out.println("This field wasn't filled in by the user. Assuming zero in array element " + i);
					pvd.setPointsValue(i, (float) 0.0);
				} else {
					System.out.println("Looping on i = " + i + " and value is: " + Float.valueOf(et_array[i].getText().toString()));
					pvd.setPointsValue(i, Float.valueOf(et_array[i].getText().toString()));
				}
			} else if (et_array[i] == null) {
				System.out.println("et_array[" + i + "] is null.");
			}
		}
		MainActivity.open_helper.updatePointValues(pvd);
		System.out.println("Saved by iterating.");
		
		/*
		pvd.setPointsValue("ba", Float.valueOf(ba.getText().toString()));
		pvd.setPointsValue("delta", Float.valueOf(delta.getText().toString()));
		pvd.setPointsValue("american", Float.valueOf(american.getText().toString()));
		pvd.setPointsValue("spirit", Float.valueOf(spirit.getText().toString()));
		pvd.setPointsValue("united", Float.valueOf(united.getText().toString()));
		pvd.setPointsValue("usair", Float.valueOf(usair.getText().toString()));
		pvd.setPointsValue("jetblue", Float.valueOf(jetblue.getText().toString()));
		pvd.setPointsValue("southwest", Float.valueOf(southwest.getText().toString()));
		pvd.setPointsValue("frontier", Float.valueOf(frontier.getText().toString()));
		pvd.setPointsValue("alaska", Float.valueOf(alaska.getText().toString()));
		pvd.setPointsValue("ur", Float.valueOf(ur.getText().toString()));
		pvd.setPointsValue("mr", Float.valueOf(mr.getText().toString()));
		pvd.setPointsValue("carlson", Float.valueOf(carlson.getText().toString()));
		pvd.setPointsValue("arrival", Float.valueOf(arrival.getText().toString()));
		pvd.setPointsValue("spg", Float.valueOf(spg.getText().toString()));
		pvd.setPointsValue("usbank", Float.valueOf(usbank.getText().toString()));
		pvd.setPointsValue("discover", Float.valueOf(discover.getText().toString()));
		pvd.setPointsValue("lossrate", Float.valueOf(lossrate.getText().toString()));
		pvd.setPointsValue("monthlyspend", Float.valueOf(monthlyspend.getText().toString()));
		MainActivity.open_helper.updatePointValues(pvd);
		*/
		
		//System.out.println("The value of BA avios is: " + et_array[0]);
		
		System.out.println("Values saved.");
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
//		FlurryAgent.onStartSession(getActivity(), "B8HFG9HTK5C3RQRNNFY5");
		System.out.println("PointValues onStart.");
	}
	
	@Override
	public void onPause() {
		System.out.println("Point values paused.");
		saveIt();
		
		super.onPause();
		MainActivity.updateDataset();
	}
	 
	@Override
	public void onStop()
	{
		System.out.println("Point values stopped.");
		super.onStop();		
//		FlurryAgent.onEndSession(getActivity());
		System.out.println("PointValues stopped.");
	}
	
	public boolean onLongClick(View v) {
		String message = "Input how much a point is worth in cents per point. e.g. One ";
		String tail = " is worth 2.5 cents.";
		
		switch(v.getId()) {
		case R.id.tv_spg: {
			System.out.println("It was SPG TextView.");
		}
		case R.id.et_spg: {
			message += spg_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_ur: {
			
		}
		case R.id.et_ur: {
			message += "Ultimate Rewards";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_mr: {
			
		}
		case R.id.et_mr: {
			message += "Membership Rewards";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_delta: {
			
		}
		case R.id.et_delta: {
			message += "Delta";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_alaska: {
			
		}
		case R.id.et_alaska: {
			message += "Alaska";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_american: {
			
		}
		case R.id.et_american: {
			message += "American Airlines";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_united: {
			
		}
		case R.id.et_united: {
			message += "United";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_usair: {
			
		}
		case R.id.et_usair: {
			message += "US Airways";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_jetblue: {
			
		}
		case R.id.et_jetblue: {
			message += "JetBlue";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_spirit: {
			
		}
		case R.id.et_spirit: {
			message += "Spirit";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_southwest: {
			
		}
		case R.id.et_southwest: {
			message += "Southwest";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_ba: {
			
		}
		case R.id.et_ba: {
			message += "British Airways";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_frontier: {
			
		}
		case R.id.et_frontier: {
			message += "Frontier";
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_carlson: {
			
		}
		case R.id.et_carlson: {
			message += carlson_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_amtrak: {
			
		}
		case R.id.et_amtrak: {
			message += amtrak_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_arrival: {
			
		}
		case R.id.et_arrival: {
			message += arrival_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_usbank: {
			
		}
		case R.id.et_usbank: {
			message += usbank_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_discover: {
			
		}
		case R.id.et_discover: {
			message += discover_point;
			message += tail;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_lossrate: {
			
		}
		case R.id.et_lossrate: {
			message = lossrate_text;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.tv_monthlyspend: {
			
		}
		case R.id.et_monthlyspend: {
			message = monthlyspend_text;
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			break;
		}
		}
		
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup rg, int checkedId) {
		switch (rg.getCheckedRadioButtonId()) {
		case R.id.show_business_cards: {
			business_boolean = true;
			personal_boolean = false;
			break;
		}
		case R.id.show_personal_cards: {
			business_boolean = false;
			personal_boolean = true;
			break;
		}
		case R.id.show_business_and_personal_cards: {
			business_boolean = true;
			personal_boolean = true;
			break;
		}
		default: {
			System.out.println("Something went horribly wrong with your finger. Defaulting to showing only personal cards.");
			business_boolean = false;
			personal_boolean = true;
		}
		}
	}
}