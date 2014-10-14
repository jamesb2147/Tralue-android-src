package com.embaucha.tralue;

import com.parse.Parse;

import android.app.Application;

public class Application2 extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this, "u9ZfHSQYG9KQ5F3Z2Sfuqfj2MhZFjbOHSX7xviqu", "InvTtbMr77KxroQ7iJirIUAUbIiqeRxkOUEJjRiL");
	}
}