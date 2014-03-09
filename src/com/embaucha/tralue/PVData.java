package com.embaucha.tralue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PVData {
	//this array MUST remain in the same order as the PointValues array!!!! ALWAYS!!!!!!!!
	public static String[] airlines = {"delta", "usair", "united", "american", "ba", "southwest", "jetblue", "spirit", "frontier", "alaska", "ur", "mr", "carlson", "arrival", "spg", "usbank", "discover", "lossrate", "monthlyspend", "amtrak"};
	public static float[] values = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	SQLiteDatabase rodb, rwdb;
	
	public PVData() {

	}
	
	public PVData(Context context) {
		OpenHelper op = new OpenHelper(context);
		
		rodb = op.getReadableDatabase();
		rwdb = op.getWritableDatabase();
		
		op.updatePVData(this);
		op.updatePointValues(this);
	}
	
	public String getPointsProgram(int i) {
		return airlines[i];
	}
	
	public void setPointsValue(int i, float value) {
		values[i] = value;
	}
	
	public void setPointsValue(String airline, float value) {
		for (int i = 0; i < airlines.length; i++) {
			if (airline.equals(airlines[i])) {
				values[i] = value;
				System.out.println("Found the right airline! It's " + airlines[i] + " and its new value is " + values[i] + "!\n");
			}
		}
	}
	
	public float getPointsValue(int i) {
		return values[i];
	}
	
	public float getPointsValue(String airline) {
		for (int i = 0; i< airlines.length; i++) {
			if (airline.equals(airlines[i])) {
				System.out.println("Found the right airline! It's " + airlines[i] + " and its current value is " + values[i] + "!");
				return values[i];
			} else {
				System.out.println("This isn't the right airline! Moving on...");
			}
		}
		System.out.println("Uhhhh, we couldn't find your airline. Something must have gone horribly wrong. Please email Sean at shunter@embaucha.com immediately.");
		return (float)0.0;
	}
}