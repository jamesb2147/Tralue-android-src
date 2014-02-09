package com.embaucha.tralue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		
	}
	
	public void emailDeveloper(View v) {
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@embaucha.com"});		  
		email.putExtra(Intent.EXTRA_SUBJECT, "Tralue - About us");
		//email.putExtra(Intent.EXTRA_TEXT, url);
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Choose an Email client :"));
	}
}