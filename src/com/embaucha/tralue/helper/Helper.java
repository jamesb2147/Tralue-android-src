package com.embaucha.tralue.helper;

import com.embaucha.tralue.model.CardRow;
import com.embaucha.tralue.model.PVRow;

//import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Build;
import android.util.Log;

public class Helper extends SQLiteOpenHelper {
	public static final String LOG = "DatabaseHelper";
	public static final int dbver = 4;
	
	private static final String DATABASE_NAME = "tralue.db";
	private static final String TABLE_PROVIDERS = "providers";
	private static final String TABLE_VALUES = "point_values";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_ISSUER = "issuer";
	private static final String KEY_ANNUAL_FEE = "annual_fee";
	private static final String KEY_FEE_WAIVED_FIRST_YEAR = "fee_waived_first_year";
	private static final String KEY_POINTS_PROGRAM = "points_program";
	private static final String KEY_SPEND_BONUS = "spend_bonus";
	private static final String KEY_SPEND_REQUIREMENT = "spend_requirement";
	private static final String KEY_TIME_TO_REACH_SPEND_IN_MONTHS = "time_to_reach_spend_in_months";
	private static final String KEY_FIRST_PURCHASE_BONUS = "first_purchase_bonus";
	private static final String KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND = "points_per_dollar_spent_general_spend";
	private static final String KEY_FOREIGN_TRANSACTION_FEE = "foreign_transaction_fee";
	private static final String KEY_CHIP = "chip";
	private static final String KEY_NOTES = "notes";
	private static final String KEY_POINTS_VALUE = "points_value";
	
	private static final String CREATE_TABLE_PROVIDERS = "CREATE TABLE " + TABLE_PROVIDERS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	KEY_NAME + " TEXT NOT NULL, " + KEY_ISSUER + " TEXT NOT NULL, " + KEY_ANNUAL_FEE + " INT, " + KEY_FEE_WAIVED_FIRST_YEAR + 
	" TEXT NOT NULL, " + KEY_POINTS_PROGRAM + " TEXT NOT NULL, " + KEY_SPEND_BONUS + " INT, " + KEY_SPEND_REQUIREMENT + " INT, " + 
	KEY_TIME_TO_REACH_SPEND_IN_MONTHS + " INT, " + KEY_FIRST_PURCHASE_BONUS + " INT, " + KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND + 
	" REAL, " + KEY_FOREIGN_TRANSACTION_FEE + " REAL, " + KEY_CHIP + " TEXT NOT NULL, " + KEY_NOTES + " TEXT)";
	
	private static final String CREATE_TABLE_VALUES = "CREATE TABLE " + TABLE_VALUES + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	KEY_POINTS_PROGRAM + " TEXT NOT NULL, " + KEY_POINTS_VALUE + " REAL)";
	
	//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public Helper (Context context) {
		super(context, DATABASE_NAME, null, dbver);
	}
	/*
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public Helper (Context context) {
		super(context, DATABASE_NAME, null, dbver);
	}
	*/
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PROVIDERS);
		db.execSQL(CREATE_TABLE_VALUES);
		
		
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PROVIDERS);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_VALUES);
		
		onCreate(db);
	}
	
	public int createPVRow(PVRow pvrow) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_POINTS_PROGRAM, pvrow.getPointsProgram());
		values.put(KEY_POINTS_VALUE, pvrow.getPointValue());
		
		return (int)rwdb.insert(TABLE_VALUES, null, values);
	}
	
	public PVRow getPVRow(int rowId) {
		SQLiteDatabase rodb = this.getReadableDatabase();
		
		String SELECT_QUERY = "SELECT * FROM " + TABLE_VALUES + " WHERE " + KEY_ID + " = " + rowId;
		
		Log.e(LOG, SELECT_QUERY);
		
		Cursor cursor = rodb.rawQuery(SELECT_QUERY, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		} else {
			Log.e(LOG, "The cursor was null. Dying now.");
		}
		
		PVRow pvrow = new PVRow();
		pvrow.setPointsProgram(cursor.getString(cursor.getColumnIndex(KEY_POINTS_PROGRAM)));
		pvrow.setPointValue(cursor.getFloat(cursor.getColumnIndex(KEY_POINTS_VALUE)));
		pvrow.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
		
		return pvrow;
	}
	
	public int updatePVRow(PVRow pvrow) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_POINTS_PROGRAM, pvrow.getPointsProgram());
		values.put(KEY_POINTS_VALUE, pvrow.getPointValue());
		
		return rwdb.update(TABLE_VALUES, values, KEY_ID + " = ?", new String[] {String.valueOf(pvrow.getId())});
	}
	
	public void deletePVRow(int pvrowid) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		
		rwdb.delete(TABLE_VALUES, KEY_ID + " = ?", new String[] {String.valueOf(pvrowid)});
	}
	/*
	private static final String CREATE_TABLE_PROVIDERS = "CREATE TABLE " + TABLE_PROVIDERS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	KEY_NAME + " TEXT NOT NULL, " + KEY_ISSUER + " TEXT NOT NULL, " + KEY_ANNUAL_FEE + " INT, " + KEY_FEE_WAIVED_FIRST_YEAR + 
	" TEXT NOT NULL, " + KEY_POINTS_PROGRAM + " TEXT NOT NULL, " + KEY_SPEND_BONUS + " INT, " + KEY_SPEND_REQUIREMENT + " INT, " + 
	KEY_TIME_TO_REACH_SPEND_IN_MONTHS + " INT, " + KEY_FIRST_PURCHASE_BONUS + " INT, " + KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND + 
	" REAL, " + KEY_FOREIGN_TRANSACTION_FEE + " REAL, " + KEY_CHIP + " TEXT NOT NULL, " + KEY_NOTES + " TEXT)";
	*/
	
	public int createCardRow(CardRow cardrow) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, cardrow.getName());
		values.put(KEY_ISSUER, cardrow.getIssuer());
		values.put(KEY_ANNUAL_FEE, cardrow.getAnnualFee());
		values.put(KEY_FEE_WAIVED_FIRST_YEAR, cardrow.getFeeWaivedFirstYear());
		values.put(KEY_POINTS_PROGRAM, cardrow.getPointsProgram());
		values.put(KEY_SPEND_BONUS, cardrow.getSpendBonus());
		values.put(KEY_SPEND_REQUIREMENT, cardrow.getSpendRequirement());
		values.put(KEY_TIME_TO_REACH_SPEND_IN_MONTHS, cardrow.getTimeToReachSpendInMonths());
		values.put(KEY_FIRST_PURCHASE_BONUS, cardrow.getFirstPurchaseBonus());
		values.put(KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND, cardrow.getPointsPerDollarSpentGeneralSpend());
		values.put(KEY_FOREIGN_TRANSACTION_FEE, cardrow.getForeignTransactionFee());
		values.put(KEY_CHIP, cardrow.getChip());
		values.put(KEY_NOTES, cardrow.getNotes());
		
		return (int)rwdb.insert(TABLE_PROVIDERS, null, values);
	}
	
	public void closeDB() {
		SQLiteDatabase rodb = this.getReadableDatabase();
		if (rodb != null && rodb.isOpen()) {
			rodb.close();
		} else {
			Log.e(LOG, "DB appears to be closed already.");
		}
	}
}