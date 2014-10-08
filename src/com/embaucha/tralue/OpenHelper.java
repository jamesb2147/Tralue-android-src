package com.embaucha.tralue;

//import com.testflightapp.lib.TestFlight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 58;
	private static final String DATABASE_NAME = "tralue.db";
	static final String TABLE_PROVIDERS = "providers";
	static final String TABLE_VALUES = "point_values";
	static final String TABLE_COMPILED_AWARDS = "compiled_awards";
	static final String TABLE_PARTNERS = "partners";
	static final String TABLE_AIRLINE_NAMES = "airline_names";
	
	private static final String KEY_ID = "_id";
	static final String KEY_CARD_ID = "card_id";
	static final String KEY_NAME = "name";
	static final String KEY_ISSUER = "issuer";
	static final String KEY_ANNUAL_FEE = "annual_fee";
	static final String KEY_FEE_WAIVED_FIRST_YEAR = "fee_waived_first_year";
	static final String KEY_POINTS_PROGRAM = "points_program";
	static final String KEY_SPEND_BONUS = "spend_bonus";
	static final String KEY_SPEND_REQUIREMENT = "spend_requirement";
	static final String KEY_TIME_TO_REACH_SPEND_IN_MONTHS = "time_to_reach_spend_in_months";
	static final String KEY_FIRST_PURCHASE_BONUS = "first_purchase_bonus";
	static final String KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND = "points_per_dollar_spent_general_spend";
	static final String KEY_FOREIGN_TRANSACTION_FEE = "foreign_transaction_fee";
	static final String KEY_CHIP = "chip";
	static final String KEY_NOTES = "notes";
	static final String KEY_URL = "url";
	static final String KEY_BUSINESS_PERSONAL = "business_personal";
	static final String KEY_INTENDED_AUDIENCE = "intended_audience";
	static final String KEY_IMAGE = "image";
	static final String KEY_PLANNED_SPEND_PER_MONTH = "planned_spend_per_month";
	static final String KEY_LOSS_RATE = "loss_rate";
	static final String KEY_CARRIERS = "carriers";
	static final String KEY_CARRIER_NAMES = "carrier_names";
	static final String KEY_CARRIER_NOTES = "carrier_notes";
	
	//used in transfer programs table
	static final String KEY_TRANSFERRING_PROGRAM = "transferring_point_program";
	static final String KEY_PARTNER_PROGRAM = "partner_points_program";
	static final String KEY_TRANSFER_VALUE = "standard_transfer_value";
	
	//db setup from demo program
	static final String COL_ORIGIN = "origin";
	static final String COL_DESTINATION = "destination";
	static final String COL_CLASS = "class_of_service";
	static final String COL_COST = "cost_in_miles";
	static final String COL_AIRLINE = "airline";
	static final String COL_ID = "award_chart_id";
	static final String COL_AWARD_NOTES = "award_notes";
	
	//airlines/points programs
	static final String united = "united";
	static final String delta = "delta";
	static final String american = "american";
	static final String spirit = "spirit";
	static final String alaska = "alaska";
	static final String ba = "ba";
	static final String mr = "mr";
	static final String ur = "ur";
	static final String ty = "ty";
	static final String spg = "spg";
	//not yet implemented
	static final String arrival = "arrival";
	static final String amtrak = "amtrak";
	static final String singapore = "singapore";
	static final String thai = "thai";
	static final String korean = "korean";
	static final String alitalia = "alitalia";
	static final String cathay_pacific = "cathay_pacific";
	static final String virgin_atlantic = "virgin_atlantic";
	
	//classes of service
	static final String economy = "economy";
	static final String business = "business";
	static final String first = "first";
	
	//origins
	static final String chi = "Chicago - CHI ( ORD, MDW )";
	static final String nyc = "New York - NYC ( JFK, LGA )";
	static final String lax = "Los Angeles - LAX";
	
	//destinations
	static final String tokyo = "Tokyo - TYO ( NRT, HND )";
	static final String beijing = "Beijing - BJS ( PEK, NAY )";
	static final String sydney = "Sydney - SYD";
	static final String london = "London - LON ( LHR, LGW, STN, LCY )";
	static final String paris = "Paris - PAR ( CDG, ORY )";
	static final String hong_kong = "Hong Kong - HKG";
	static final String managua = "Managua - MGA";
	static final String delhi = "New Delhi - DEL";
	
	static final CardCatalog cat = new CardCatalog();
	
	public OpenHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onUpgrade(SQLiteDatabase rwdb, int oldVer, int newVer) {
		rwdb.beginTransaction();
		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDERS);
		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPILED_AWARDS);
		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNERS);
		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_AIRLINE_NAMES);
//		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUES);
		rwdb.setTransactionSuccessful();
		rwdb.endTransaction();
		
		//NOTE: Do NOT ever use this for the providers table. It adds unnecessary complexity to the system.
		//There is no reason to preserve the data in the providers table at any time. It is created entirely programmatically anyway.
		//User data is what we're trying to preserve across versions.
		switch(oldVer) {
		case 13: {
			//This is how to update values in the TABLE_VALUES table, which is the only one you should have this kind of update code for.
			/*
			ContentValues values = new ContentValues();
			values.put(KEY_NOTES, "Current offer grants an additional bonus of 25k points at $10,000 spend and $20,000 spend in one year. Combined with the standard sign-up offer, you will receive 100,000 Avios. British Airways offers family accounts to pool your miles. At $30,000 in spend, an award certificate is issued that allows a companion to fly free on award tickets on BA metal.");
			rwdb.update(TABLE_PROVIDERS, values, KEY_NAME + "=?", new String[] {"British Airways Visa Signature"});
			*/
		}
		case 16: {
			//nada in TABLE_VALUES
		}
		case 17: {
			//nada in values
		}
		case 18: {
			//nada in values
		}
		case 19: {
			//nada in values
		}
		case 20: {
			//nada in values
		}
		case 21: {
			//nada in values
		}
		case 22: {
			//nada in values
		}
		case 23: {
			//nada in values
		}
		case 24: {
			//nada in values
		}
		case 25: {
			//nada in values
		}
		case 26: {
			//nada in values
		}
		case 27: {
			//added amtrak
			rwdb.execSQL("INSERT INTO point_values (points_program, points_value) VALUES ('amtrak', 0)");
		}
        case 28: {
            //nada in values
            //testing new method of adding and maintaining cards
        }
        case 29: {
        	//nada in values
        }
        case 30: {
        	//nada in values
        }
        case 31: {
        	//nada in values
        }
        case 32: {
        	//nada in values
        }
        case 33: {
        	//create award chart DB
        	//nada in values
        }
        case 34: {
        	//create partners table
        	//nada in values
        }
        case 35: {
        	//nada in values
        }
        case 36: {
        	//added proper name table
        	//nada in values
        }
        case 37: {
        	//nada in values
        }
        case 38: {
        	//updated id column in compiled awards table
        	//nada in values
        }
        case 39: {
        	//nada in values
        }
        case 40: {
        	//something is wrong with my debug build because no data is populating
        	//attempting to force a rebuild of the on-device database by incrementing DB version
        	//wish me luck!
        	
        	//nada in values
        }
        case 41: {
        	//nada in values
        }
        case 42: {
        	//added delhi as a destination
        	//nada in values
        }
        case 43: {
        	//changed card table to use unique key id
        	//nada in values
        }
        case 44: {
        	//corrected a pricing error
        	//nada in values
        }
        case 45: {
        	//nada in values
        }
        case 46: {
        	//added new programs (ty and singapore)
        	//added new transfer partners (ty and, separately, singapore)
        }
        case 47: {
        	//trivial error corrected where ba was left in place instead of singapore
        }
        //after 47, realizing that i don't care anymore, unless i make a change to the table of values
        case 50: {
        	//added singapore and thank you points to the database
        	rwdb.execSQL("INSERT INTO point_values (points_program, points_value) VALUES ('singapore', 0)");
        	rwdb.execSQL("INSERT INTO point_values (points_program, points_value) VALUES ('ty', 0)");
        }
        case 54: {
        	//uh-oh, i changed a column name and did not update the schema! :X
        }
		}
		
//		onCreate(rwdb);
		createProviders(rwdb);
		createAwardChartDB(rwdb);
		createPartnersTable(rwdb);
		createCarrierNameTable(rwdb);
//		createValues(rwdb);
	}
	
	public void onCreate (SQLiteDatabase rwdb) {
		//I need to add a network (Visa, AmEx, MC, Discover, Diner's Club, etc.) field
		//Business or personal card field
		//churnable notes?
		//Available to existing cardmembers (particularly AmEx, possibly Barclays)
		//Image field
		//column and filter for audience (e.g. college students)
		//credit versus charge card column and filter
		//add marriott card
		//adjust southwest card
		
		/*
		 * SELECT name, providers.points_program, spend_bonus, first_purchase_bonus, (spend_bonus + first_purchase_bonus) * 100 / cost_in_miles AS percentage_of_award FROM compiled_awards INNER JOIN providers ON providers.points_program=compiled_awards.airline INNER JOIN partners ON compiled_awards.airline=partners.partner_points_program WHERE class_of_service='economy' AND origin LIKE '%chi%' AND destination LIKE '%hkg%' 
		UNION
		SELECT name, partners.points_program, spend_bonus, first_purchase_bonus, (spend_bonus + first_purchase_bonus) * 100 / cost_in_miles AS percentage_of_award FROM compiled_awards INNER JOIN partners ON compiled_awards.airline=partners.partner_points_program INNER JOIN providers ON providers.points_program=compiled_awards.airline WHERE class_of_service='economy' AND origin LIKE '%chi%' AND destination LIKE '%hkg%'
		ORDER BY percentage_of_award DESC;
		 */
		
		createProviders(rwdb);
		
		//create award chart database (pasted from demo cost app)
		createAwardChartDB(rwdb);
		
		//create partner transfer table (work on transfer rates later - most are 1:1)
		createPartnersTable(rwdb);
		
		//create a table to display proper names
		createCarrierNameTable(rwdb);
		
		//db.execSQL("create table values (_id INTEGER PRIMARY KEY AUTOINCREMENT");
		//Manufactured spend loss rate
		//Planned spend per month per card
		createValues(rwdb);
	}
	
	private void createValues(SQLiteDatabase rwdb) {
		System.out.println("Creating values.");
		rwdb.beginTransaction();
		System.out.println("Transaction begun.");
		rwdb.execSQL("CREATE TABLE " + TABLE_VALUES + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, points_program TEXT NOT NULL, points_value REAL)");
		System.out.println("Table SQL loaded.");
		rwdb.setTransactionSuccessful();
		rwdb.endTransaction();
		System.out.println("Transaction successfullly ended.");
		
	}
	
	private void createCarrierNameTable(SQLiteDatabase rwdb) {
		rwdb.beginTransaction();
		rwdb.execSQL("CREATE TABLE " + TABLE_AIRLINE_NAMES + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CARRIERS + " TEXT NOT NULL, " + KEY_CARRIER_NAMES + " TEXT NOT NULL, " + KEY_CARRIER_NOTES + " TEXT)");
		
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + delta + "', '" + "Delta" + "', 'Delta has very few seats at the low level of their awards, unless you are a top-tier Delta elite frequent flyer. Check for award pricing and availability at Delta.com.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + ba + "', '" + "British Airways" + "', 'British Airways (BA) flights incur significant fuel surcharges. Some BA partners, however, do not incur fuel surcharges. Check partner availability on your route.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + united + "', '" + "United" + "', 'United awards are only priced based on flying on United operated flights, not partner flights. Partner flights are more expensive.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + alaska + "', '" + "Alaska" + "', 'Alaska awards are weird. They are highly dependent on date, destination, and partner airline availability, which is in turn also highly volatile. Go to the Alaska website and verify your dates and the points required.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + spirit + "', '" + "Spirit" + "', 'Spirit awards at the low level are only available to persons with the Spirit Mastercard.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ") VALUES ('" + american + "', '" + "American" + "')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + singapore + "', '" + "Singapore" + "', 'Most Singapore awards are not bookable via the website. You will need to the call Singapore KrisFlyer customer service line to book awards on partner airlines such as United.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + amtrak + "', '" + "Amtrak" + "', 'Amtrak can take multiple days and significant delays to reach a destination. Be sure that this is appropriate for you.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + thai + "', '" + "Thai Airways" + "', 'Thai Airways struggles financially and have historically threatened to gut their frequent flyer program as a way of improving their financials. Beware of holding points in the Royal Orchid Plus program.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + korean + "', '" + "Korean Air" + "', 'Korean Air miles cannot be used for one-way travel on partners.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ") VALUES ('" + alitalia + "', '" + "Alitalia" + "')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + cathay_pacific + "', '" + "Cathay Pacific" + "', 'The Cathay Pacific Asia Miles frequent flyer program has a $50 fee to join the program.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + virgin_atlantic + "', '" + "Virgin Atlantic" + "', 'Heavy fuel surcharges on this airline often make it not worthwhile to redeem for coach travel. Stick to business class on Virgin Atlantic, people.')");
		rwdb.execSQL("INSERT INTO " + TABLE_AIRLINE_NAMES + " (" + KEY_CARRIERS + ", " + KEY_CARRIER_NAMES + ", " + KEY_CARRIER_NOTES + ") VALUES ('" + arrival + "', '" + "Arrival" + "', '10% rebate on all points used for travel.')");
		
		
		/*
		 * 
		 * static final String arrival = "arrival";
	static final String amtrak = "amtrak";
	static final String singapore = "singapore";
	static final String thai = "thai";
	static final String korean = "korean";
	static final String alitalia = "alitalia";
	static final String cathay_pacific = "cathay_pacific";
	static final String virgin_atlantic = "virgin_atlantic";
		 */
		rwdb.setTransactionSuccessful();
		rwdb.endTransaction();
	}
	
	private void createPartnersTable(SQLiteDatabase rwdb) {
		//add transfer rate sometime
		rwdb.execSQL("CREATE TABLE " + TABLE_PARTNERS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TRANSFERRING_PROGRAM + " TEXT NOT NULL, " + KEY_PARTNER_PROGRAM + " TEXT NOT NULL, " + KEY_TRANSFER_VALUE + " REAL NOT NULL)");
		
		//Ultimate Rewards transfer partners
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ur + "', '" + united + "', 1.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ur + "', '" + ba + "', 1.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ur + "', '" + singapore + "', 1.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ur + "', '" + korean + "', 1.0)");
		
		//Membership Rewards transfer partners
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + mr + "', '" + delta + "', 1.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + mr + "', '" + ba + "', 1.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + mr + "', '" + singapore + "', 1.0)");
		
		//SPG transfer partners
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + spg + "', '" + ba + "', 10.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + spg + "', '" + delta + "', 10.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + spg + "', '" + united + "', 10.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + spg + "', '" + american + "', 10.0)");
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + spg + "', '" + singapore + "', 10.0)");
		
		//Thank You transfer partners (Citi program)
		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ty + "', '" + singapore + "', 1.0)");
//		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ty + "', '" + thai + "', 1.0)");
//		rwdb.execSQL("INSERT INTO " + TABLE_PARTNERS + " (" + KEY_TRANSFERRING_PROGRAM + ", " + KEY_PARTNER_PROGRAM + ", " + KEY_TRANSFER_VALUE + ") VALUES ('" + ty + "', '" + cathay_pacific + "', 1.0)");
	}
	
	private void createAwardChartDB(SQLiteDatabase rwdb) {
		rwdb.execSQL("CREATE TABLE " + TABLE_COMPILED_AWARDS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_AIRLINE + " TEXT NOT NULL, " + COL_CLASS + " TEXT NOT NULL, " + COL_ORIGIN + " TEXT NOT NULL, " + COL_DESTINATION + " TEXT NOT NULL, " + COL_COST + " REAL, " + COL_AWARD_NOTES + " TEXT)");
		
		rwdb.beginTransaction();
		//CHICAGO
		//...to beijing
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + beijing + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + beijing + "', " + 140000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + beijing + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + beijing + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + beijing + "', " + 70000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + beijing + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + beijing + "', " + 110000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + beijing + "', " + 135000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + beijing + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + beijing + "', " + 110000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + beijing + "', " + 120000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + beijing + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + beijing + "', " + 140000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + beijing + "', " + 210000 + ")");
		//...to tokyo
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + tokyo + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + tokyo + "', " + 130000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + tokyo + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + tokyo + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + tokyo + "', " + 70000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + tokyo + "', " + 65000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + tokyo + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + tokyo + "', " + 125000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + tokyo + "', " + 50000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + tokyo + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + tokyo + "', " + 100000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + tokyo + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + tokyo + "', " + 120000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + tokyo + "', " + 180000 + ")");
		//...to sydney
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + sydney + "', " + 80000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + sydney + "', " + 140000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + sydney + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + sydney + "', " + 50000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + sydney + "', " + 80000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + sydney + "', " + 75000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + sydney + "', " + 125000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + sydney + "', " + 145000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + sydney + "', " + 80000 + ")");
		//rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
		//		+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + sydney + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + sydney + "', " + 105000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + sydney + "', " + 120000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + sydney + "', " + 240000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + sydney + "', " + 360000 + ")");
		//...to london
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + london + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + london + "', " + 115000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + london + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + london + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + london + "', " + 125000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + london + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + london + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + london + "', " + 125000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + london + "', " + 40000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + london + "', " + 90000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + london + "', " + 90000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + london + "', " + 40000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + london + "', " + 80000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + london + "', " + 120000 + ")");
		//...to paris
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + paris + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + paris + "', " + 115000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + paris + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + paris + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + paris + "', " + 125000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + paris + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + paris + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + paris + "', " + 125000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + paris + "', " + 40000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + paris + "', " + 90000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + paris + "', " + 90000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + paris + "', " + 50000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + paris + "', " + 100000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + paris + "', " + 150000 + ")");
		//...to hong kong
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + hong_kong + "', " + 80000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + hong_kong + "', " + 140000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + hong_kong + "', " + 160000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + hong_kong + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + hong_kong + "', " + 70000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + hong_kong + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + hong_kong + "', " + 110000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + hong_kong + "', " + 135000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + hong_kong + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + hong_kong + "', " + 120000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + hong_kong + "', " + 120000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + hong_kong + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + hong_kong + "', " + 140000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + hong_kong + "', " + 210000 + ")");
		//...to managua
		//united
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + managua + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + managua + "', " + 80000 + ")");
		//delta
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + managua + "', " + 60000 + ")");
		//american
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + managua + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + managua + "', " + 80000 + ")");
		//alaska
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 30000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + managua + "', " + 60000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + managua + "', " + 60000 + ")");
		//ba
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + managua + "', " + 70000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + managua + "', " + 105000 + ")");
		//spirit
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST + ", " + COL_AWARD_NOTES
				+ ") VALUES ('" + spirit + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 10000 + ", 'Price out and book each segment separately. The connection is in Miami. The cost is 10,000 miles booked separately and 15,000 miles if booked together.')");
		//singapore (FAKE AND MADE UP)
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + singapore + "', '" + economy + "', '" + chi + "', '" + managua + "', " + 35000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + singapore + "', '" + business + "', '" + chi + "', '" + managua + "', " + 45000 + ")");
		rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				+ ") VALUES ('" + singapore + "', '" + first + "', '" + chi + "', '" + managua + "', " + 65000 + ")");
		//...to delhi
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + chi + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + chi + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + chi + "', '" + delhi + "', " + 180000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + chi + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + chi + "', '" + delhi + "', " + 140000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + chi + "', '" + delhi + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + chi + "', '" + delhi + "', " + 125000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + chi + "', '" + delhi + "', " + 180000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + chi + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + chi + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + chi + "', '" + delhi + "', " + 160000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + chi + "', '" + delhi + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + chi + "', '" + delhi + "', " + 180000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + chi + "', '" + delhi + "', " + 270000 + ")");
		
		
		//NEW YORK
		//...to beijing
		//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + beijing + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + beijing + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + beijing + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + beijing + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + beijing + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + beijing + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + beijing + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + beijing + "', " + 135000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + beijing + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + beijing + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + beijing + "', " + 110000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + beijing + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + beijing + "', " + 180000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + beijing + "', " + 270000 + ")");
				//...to tokyo
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + tokyo + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + tokyo + "', " + 130000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + tokyo + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + tokyo + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + tokyo + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + tokyo + "', " + 65000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + tokyo + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + tokyo + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + tokyo + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + tokyo + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + tokyo + "', " + 100000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + tokyo + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + tokyo + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + tokyo + "', " + 210000 + ")");
				//...to sydney
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + sydney + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + sydney + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + sydney + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + sydney + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + sydney + "', " + 80000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + sydney + "', " + 75000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + sydney + "', " + 125000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + sydney + "', " + 145000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + sydney + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + sydney + "', " + 110000 + ")");
				//rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
				//		+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + sydney + "', " + 105000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + sydney + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + sydney + "', " + 200000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + sydney + "', " + 300000 + ")");
				//...to london
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + london + "', " + 115000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + london + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + london + "', " + 125000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + london + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + london + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + london + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + london + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + london + "', " + 90000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + london + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + london + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + london + "', " + 120000 + ")");
				//...to paris
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + paris + "', " + 115000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + paris + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + paris + "', " + 125000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + paris + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + paris + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + paris + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + paris + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + paris + "', " + 90000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + paris + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + paris + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + paris + "', " + 120000 + ")");
				//...to hong kong
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + hong_kong + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + hong_kong + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + hong_kong + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + hong_kong + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + hong_kong + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + hong_kong + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + hong_kong + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + hong_kong + "', " + 135000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + hong_kong + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + hong_kong + "', " + 120000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + hong_kong + "', " + 120000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + hong_kong + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + hong_kong + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + hong_kong + "', " + 210000 + ")");
				//...to managua
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + managua + "', " + 80000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + managua + "', " + 80000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 30000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 30000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + managua + "', " + 90000 + ")");
				//spirit
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + spirit + "', '" + economy + "', '" + nyc + "', '" + managua + "', " + 15000 + ")");
				//...to delhi
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + nyc + "', '" + delhi + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + nyc + "', '" + delhi + "', " + 125000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + nyc + "', '" + delhi + "', " + 180000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + nyc + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + nyc + "', '" + delhi + "', " + 140000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + nyc + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + nyc + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + nyc + "', '" + delhi + "', " + 180000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + nyc + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + nyc + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + nyc + "', '" + delhi + "', " + 160000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + nyc + "', '" + delhi + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + nyc + "', '" + delhi + "', " + 180000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + nyc + "', '" + delhi + "', " + 270000 + ")");
				
				
				
		//LA
		//...to beijing
		//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + beijing + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + beijing + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + beijing + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + beijing + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + beijing + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + beijing + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + beijing + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + beijing + "', " + 135000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + beijing + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + beijing + "', " + 105000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + beijing + "', " + 110000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + beijing + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + beijing + "', " + 200000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + beijing + "', " + 300000 + ")");
				//...to tokyo
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + tokyo + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + tokyo + "', " + 130000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + tokyo + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + tokyo + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + tokyo + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + tokyo + "', " + 65000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + tokyo + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + tokyo + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + tokyo + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + tokyo + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + tokyo + "', " + 100000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + tokyo + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + tokyo + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + tokyo + "', " + 150000 + ")");
				//...to sydney
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + sydney + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + sydney + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + sydney + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + sydney + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + sydney + "', " + 80000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + sydney + "', " + 75000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + sydney + "', " + 125000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + sydney + "', " + 145000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + sydney + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + sydney + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + sydney + "', " + 140000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + sydney + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + sydney + "', " + 200000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + sydney + "', " + 300000 + ")");
				//...to london
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + london + "', " + 115000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + london + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + london + "', " + 125000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + london + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + london + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + london + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + london + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + london + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + london + "', " + 90000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + london + "', " + 50000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + london + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + london + "', " + 150000 + ")");
				//...to paris
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + paris + "', " + 115000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + paris + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + paris + "', " + 125000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + paris + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + paris + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + paris + "', " + 125000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + paris + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + paris + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + paris + "', " + 90000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + paris + "', " + 59000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + paris + "', " + 118000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + paris + "', " + 177000 + ")");
				//...to hong kong
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + hong_kong + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + hong_kong + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + hong_kong + "', " + 160000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + hong_kong + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + hong_kong + "', " + 70000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + hong_kong + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + hong_kong + "', " + 110000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + hong_kong + "', " + 135000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + hong_kong + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + hong_kong + "', " + 120000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + hong_kong + "', " + 120000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + hong_kong + "', " + 70000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + hong_kong + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + hong_kong + "', " + 210000 + ")");
				//...to managua
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + managua + "', " + 80000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + managua + "', " + 60000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 35000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + managua + "', " + 80000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 30000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + managua + "', " + 60000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + managua + "', " + 60000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 40000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + managua + "', " + 80000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + managua + "', " + 120000 + ")");
				//spirit
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + spirit + "', '" + economy + "', '" + lax + "', '" + managua + "', " + 90000 + ")");
				//...to delhi
				//united
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + economy + "', '" + lax + "', '" + delhi + "', " + 90000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + business + "', '" + lax + "', '" + delhi + "', " + 125000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + united + "', '" + first + "', '" + lax + "', '" + delhi + "', " + 180000 + ")");
				//delta
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + economy + "', '" + lax + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + delta + "', '" + business + "', '" + lax + "', '" + delhi + "', " + 140000 + ")");
				//american
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + economy + "', '" + lax + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + business + "', '" + lax + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + american + "', '" + first + "', '" + lax + "', '" + delhi + "', " + 180000 + ")");
				//alaska
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + economy + "', '" + lax + "', '" + delhi + "', " + 85000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + business + "', '" + lax + "', '" + delhi + "', " + 140000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + alaska + "', '" + first + "', '" + lax + "', '" + delhi + "', " + 160000 + ")");
				//ba
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + economy + "', '" + lax + "', '" + delhi + "', " + 100000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + business + "', '" + lax + "', '" + delhi + "', " + 200000 + ")");
				rwdb.execSQL("INSERT INTO " + TABLE_COMPILED_AWARDS + " (" + COL_AIRLINE + ", " + COL_CLASS + ", " + COL_ORIGIN + ", " + COL_DESTINATION + ", " + COL_COST 
						+ ") VALUES ('" + ba + "', '" + first + "', '" + lax + "', '" + delhi + "', " + 300000 + ")");
				
				rwdb.setTransactionSuccessful();
				rwdb.endTransaction();
				
	}
	
	private void createProviders(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_PROVIDERS + " (" + KEY_CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, " + KEY_ISSUER + " TEXT NOT NULL, " + KEY_ANNUAL_FEE + " INT, " + KEY_FEE_WAIVED_FIRST_YEAR + " TEXT NOT NULL, " + KEY_POINTS_PROGRAM + " TEXT NOT NULL, " + KEY_SPEND_BONUS + " INT, " + KEY_SPEND_REQUIREMENT + " INT, " + KEY_TIME_TO_REACH_SPEND_IN_MONTHS + " INT, " + KEY_FIRST_PURCHASE_BONUS + " INT, " + KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND + " REAL, " + KEY_FOREIGN_TRANSACTION_FEE + " REAL, " + KEY_CHIP + " TEXT NOT NULL, " + KEY_NOTES + " TEXT, " + KEY_URL + " TEXT, " + KEY_PLANNED_SPEND_PER_MONTH + " INT, " + KEY_LOSS_RATE + " REAL, " + KEY_BUSINESS_PERSONAL + " TEXT, " + KEY_INTENDED_AUDIENCE + " TEXT, " + KEY_IMAGE + " INT)");



		//1
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
				"business_personal, intended_audience) " +
				"VALUES('British Airways Visa Signature', 'Chase', 95, 'Yes', 'ba', 50000, 1000, 3, 0, 1.25, 0, 'Chip and signature', " +
				"'Current offer grants an additional bonus of 25k points at $10,000 spend and $20,000 spend in one year. Combined with the " +
				"standard sign-up offer, you will receive 100,000 Avios. British Airways offers family accounts to pool your miles. " +
				"At $30,000 in spend, an award certificate is issued that allows a companion to fly free on award tickets on BA metal.', " +
				"'http://www.thepointsguy.com/go/bavisatd', 'personal', 'everyone')");
				*/
		//2
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
				"business_personal, intended_audience) " +
				"VALUES('US Airways Premier World Mastercard', 'Barclays', 89, 'No', 'usair', 0, 1, 1, 30000, 1, 3.0, 'No', " +
				"'May be approved for \"Platinum Plus\" card instead of World Mastercard.  PP has a $19 annual fee, earns 1 mile for " +
				"every dollar spent, and earns 5000 bonus miles after first purchase. Spend $10K within a year and you will receive 5000 " +
				"bonus points annually.', 'http://thepointsguy.com/go/USAirwaysPremierFeewaivedTD/', 'personal', 'everyone')");
				*/
		//3
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
				"business_personal, intended_audience, image)" +
				" VALUES ('Ink Plus Business Card', 'Chase', 95, 'Yes', 'ur', 50000, 5000, 3, 0, 1, 0, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 5x category earnings on communication services.', 'http://www.thepointsguy.com/go/InkPlusTD/', 'business', 'everyone', 22)");
				*/
		//4
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Sapphire Preferred', 'Chase', 95, 'Yes', 'ur', 40000, 3000, 3, 0, 1, 0, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 2x category earnings on dining and travel.', 'http://thepointsguy.com/go/SapphirePrefTD/', 'personal', 'everyone', 1)");
				*/
		//5
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Enhanced Business Gold Rewards', 'American Express', 175, 'Yes', 'ur', 30000, 5000, 3, 0, 1, 2.7, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 3x category earnings on airfare, 2x for advertising, gas, shipping, and computer hardware/services. Also benefits from " +
				"OPEN program of 10% discount or 5x earning at OPEN merchants, and free Amazon Prime membership.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/businessgoldTD/', 'business', 'everyone')");
				*/
		//6
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Citi Executive/AAdvantage World Elite Mastercard', 'Citibank', 450, 'No', 'american', '60000', '5000', 3, 0, 1, 0, " +
				"'Chip and signature', 'With this card you�ll get your first checked bag free, Admirals Club membership privileges, " +
				"25% off in-flight purchases, and 10,000 elite qualifying miles after $40,000 in spend in one year.', " +
				"'http://thepointsguy.com/go/CitiAAExecTD/', 'personal', 'everyone')");
				*/
		//7
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Club Carlson Premier Rewards Visa Signature', 'Barclays', 75, 'No', 'carlson', 35000, 2500, 3, 50000, 5, 2.0, 'No', " +
				"'Carlson Gold status and last night free on award stays of at least 2 nights.', " +
				"'http://thepointsguy.com/go/ClubCarlsonPremierVisaTD/', 'personal', 'everyone', 4)");
				*/
		//8
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('BarclayCard Arrival', 'Barclays', 89, 'Yes', 'arrival', 40000, 1000, 3, 0, 2.0, 0, 'No', 'You may be approved for a lower " +
				"offer on a Platinum card (the usual Barclays warnings apply).', 'http://thepointsguy.com/go/ArrivalTD/', 'personal', 'everyone', 3)");
				*/
		//9
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Mercedes-Benz American Express Platinum', 'American Express', 475, 'No', 'mr', 50000, 3000, 3, 0, 1, 0, 'No', " +
				"'$200 airline fee rebate, lounge access for Delta, American, and US Airways lounges, Global Entry fees are rebated, 25% bonus on " +
				"pay-with-points (1.25 cents per point fixed value), and Starwood Gold status.', " +
				"'http://thepointsguy.com/go/MBPlatinumTD/', 'personal', 'everyone', 8)");
				*/
		//10
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Starwood Preferred Guest', 'American Express', 65, 'Yes', 'spg', 15000, 5000, 6, 10000, 1, 2.7, 'No', " +
				"'Unique experience offers can, apparently, be a decent deal. Plus, they really are unique.', " +
				"'http://thepointsguy.com/go/StarwoodAmexTD/', 'personal', 'everyone', 12)");
				*/
		//11
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Premier Rewards Gold', 'American Express', 175, 'Yes', 'mr', 25000, 2000, 3, 0, 1, 2.7, 'No', " +
				"'3 points per dollar on airfare and 2 points on US gas and groceries. A bonus of 15,000 points is awarded after $30,000 in spending " +
				"in a year. Particularly useful for big spenders.', 'http://thepointsguy.com/go/PremierRewardsTD/', 'personal', 'everyone', 10)");
				*/
		//12
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Platinum Card', 'American Express', 450, 'No', 'mr', 25000, 2000, 3, 0, 1, 0, 'No', '$200 airline fee rebate, " +
				"lounge access for Delta, American, and US Airways lounges, Global Entry fees are rebated, 25% bonus on pay-with-points " +
				"(1.25 cents per point fixed value), and Starwood Gold.', 'http://thepointsguy.com/go/AmexPlatinumTD/', 'personal', 'everyone', 9)");
				*/
		//13
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('BarclayCard Arrival - No fee', 'Barclays', 0, 'No', 'arrival', 20000, 1000, 3, 0, 2, 0, 'No', 'You may be approved for a Platinum card " +
				"instead of World Mastercard (the standard Barclays warning. However, in this case, I could not find any material difference *in the offer.* " +
				"Now, Platinum cardholders will not have access to World Elite Mastercard benefits (e.g. hotel promotions worldwide), but everyone will " +
				"receive 20,000 bonus points and no annual fee. VICTORY!!!', 'http://thepointsguy.com/go/ArrivalNoFeeTD/', 'personal', 'everyone', 2)");
				*/
		//14
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Gold Delta Skymiles', 'American Express', 95, 'Yes', 'delta', 30000, 500, 3, 0, 1, 2.7, 'No', " +
				"'$50 statement credit on Delta purchase within three months. 2 miles per dollar spent on Delta purchases.', " +
				"'http://thepointsguy.com/go/DeltaGoldTD/', 'personal', 'everyone')");
				*/
		//15
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('ThankYou Preferred', 'Citibank', 0, 'No', 'ty', 20000, 1500, 3, 0, 1, 3, 'Chip and signature', " +
				"'2 points per dollar on dining and entertainment.', 'http://thepointsguy.com/go/CitithankYouPreferredTD/', " +
				"'personal', 'everyone', 13)");
				*/
		//16
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('FlexPerks Travel Rewards Visa Signature', 'US Bank', 49, 'Yes', 'usbank', 20000, 3500, 5, 0, 1, 3, 'Chip and signature', " +
				"'Points can only be used in large increments towards retail airfare.', 'http://thepointsguy.com/go/USBankFlexPerksVisaTD/', 'personal', 'everyone')");
				*/
		//17
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Sapphire', 'Chase', 0, 'No', 'ur', 10000, 500, 3, 0, 1, 3, 'No', 'I am uncertain whether Chase considers " +
				"this a different product from the Sapphire Preferred, which is important because the sign-up bonus for the Preferred is 4x the " +
				"Sapphire. In addition, the Sapphire points are not transferrable to airline miles.', 'http://thepointsguy.com/go/SapphireTD/', " +
				"'personal', 'everyone', 11)");
				*/
		//18
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Capital One Venture Rewards', 'Capital One', 59, 'No', 'capone', 10000, 1000, 3, 0, 2, 0, 'No', " +
				"'Points have a fixed value of 1 cent.', 'http://thepointsguy.com/go/VentureTD/', 'personal', 'everyone')");
				*/
		//19
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Freedom', 'Chase', 0, 'No', 'mr', 20000, 500, 3, 0, 1, 3, 'No', 'Points cannot be transferred to partners, " +
				"unless you also hold a Chase card that can transfer points to partners. INO, having only the Freedom card only allows you " +
				"to redeem for cash back. However, holding the Freedom and a Sapphire Preferred card allows you to pool points and transfer " +
				"them to Membership Rewards (MR) partners.', 'http://www.thepointsguy.com/go/FreedomTD/', 'personal', 'everyone')");
				*/
		//20
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Discover It', 'Discover', 0, 'No', 'discover', 0, 1, 1, 0, 1, 0, 'No', 'Discover is one of the more conservative issuers and " +
				"is unlikely to issuer cards to heavy churners. They also offer little in the way of sign-up bonuses. However, the cash back promotions " +
				"are occasionally useful. e.g. Six Flags often offers 5% cash back when paying with Discover.', " +
				"'http://thepointsguy.com/go/DiscoverItTD/', 'personal', 'everyone', 5)");
				*/
		//21
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Dividend Platinum Select', 'Citibank', 0, 'No', 'ty', 10000, 500, 3, 0, 1, 3, 'Chip and signature', " +
				"'5x points back on rotating categories.', 'http://thepointsguy.com/go/CitiDividendPlatinumSelectTD/', 'personal', 'everyone')");
				*/
		//22
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Forward card for college students', 'Citibank', 0, 'No', 'ty', 2500, 500, 3, 0, 1, 3, 'No', " +
				"'5x points on food, books, and entertainment. 100 points per month for paying on time and staying under your credit limit.', " +
				"'http://thepointsguy.com/go/CitiForwardTD/', 'personal', 'everyone', 6)");
				*/
		//23
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Spirit World Mastercard', 'FIA/Bank of America', 59, 'Yes', 'spirit', 0, 1, 1, 15000, 2, 3.0, 'No', " +
				"'If approved for a credit limit of less than $5000, you will receive the lesser Platinum Plus card. The platinum card " +
				"earns a first purchase bonus of 5000 miles, and has a $19 annual fee. Mastercard benefits also differ. Both cards " +
				"receive 5000 bonus miles per year for spending $10,000 and paying your annual fee.', " +
				"'http://doublizer.com/index.php?code=VABV3J', 'personal', 'everyone')");
				*/
		//24
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Platinum Select/AAdvantage World Mastercard', 'Citibank', 95, 'Yes', 'american', 50000, 3000, 3, 0, 1, 3, " +
				"'Chip and signature', '2 points per dollar on American purchases.', 'http://thepointsguy.com/go/CitiAAPlatMC50kTD/', 'personal', 'everyone')");
				*/
		//25
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes" +
				", business_personal, intended_audience)" +
				" VALUES ('United MileagePlus Explorer', 'Chase', 95, 'Yes', 'united', 50000, 2000, 3, 0, 1, 0, 'No', 'Spending $25k on your card in a year " +
				"gets 10,000 bonus miles. 2 points per dollar on United purchases. The 50,000 mile offer is only available in Chase branches. " +
				"This offer is not available online.', 'personal', 'everyone')");
				*/
		//26
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes" +
				", business_personal, intended_audience)" +
				" VALUES ('United MileagePlus Club', 'Chase', 395, 'Yes', 'united', 0, 1, 1, 0, 1.5, 0, 'No', 'Full United Club membership " +
				"included. Earns 1.5 miles per dollar on all purchases. First and second checked bags are free. This offer is only available" +
				" in Chase branches, otherwise you have to pay the annual fee your first year.', 'personal', 'everyone')");
				*/
		//27
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Gold card', 'American Express', 125, 'Yes', 'mr', 0, 1, 1, 0, 1, 2.7, 'No', " +
				"'I am not certain whether American Express considers this the same product the Premier Rewards, Skymiles, etc. Gold cards.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepontsguy.com/go/AmexGoldTD', 'personal', 'everyone', 7)");
				*/
		//28
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Thank You Premier', 'Citibank', 125, 'Yes', 'ty', 50000, 5000, 3, 0, 1, 0, 'Chip and signature', " +
				"'Thank you points are worth 25% more when redeemed for airfare on the ThankYou portal.', 'http://thepointsguy.com/go/CitiThankYouPremierTD/', " +
				"'personal', 'everyone', 14)");
				*/
		//29
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Southwest Rapid Rewards Premier card', 'Chase', 99, 'No', 'southwest', 25000, 2000, 3, 0, 2, 0, 'No', " +
				"'Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for " +
				"free on any Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it!', " +
				"'http://thepointsguy.com/go/SouthwestPremierBusinessTD/', 'personal', 'everyone', 15)");
				*/
		//30
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Club Carlson Business Rewards Visa Card', 'Barclays', 60, 'No', 'carlson', 35000, 2500, 3, 50000, 5, 3, 'No', " +
				"'Double points for spend at Carlson Rezidor hotels. Also receive Club Carlson Gold status and last night free on awards stays " +
				"(must stay at least two nights).', 'http://thepointsguy.com/go/CarlsonBizTD/', 'business', 'everyone', 16)");
				*/
		//31
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Ink Bold', 'Chase', 95, 'Yes', 'ur', 50000, 5000, 3, 0, 1, 0, 'No', " +
				"'Flexible/transferable points, portal bonuses, and 5x category earnings on communication services.', " +
				"'http://www.thepointsguy.com/go/InkBoldTD/', 'business', 'everyone', 17)");
				*/
		//32
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Gold Delta Skymiles Business card', 'American Express', 95, 'Yes', 'delta', 30000, 1000, 3, 0, 1, 2.7, 'No', " +
				"'$50 statement credit on a Delta purchase within the first three months. 2 miles per dollar on Delta purchases, first checked bag " +
				"free, and priority boarding on Delta flights.', 'http://thepointsguy.com/go/DeltaBusinessTD/', 'business', 'everyone', 18)");
				*/
		//33
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Starwood Preferred Guest Business card', 'American Express', 65, 'Yes', 'spg', 15000, 5000, 6, 10000, 1, 2.7, 'No', " +
				"'Miles are the most portable of any program (e.g. this is the only card that allows direct transfers of miles to American AAdvantage). " +
				"Unique experience offers can, apparently, be a decent deal. Plus, they really are unique.', " +
				"'http://thepointsguy.com/go/StarwoodBusinessTD/', 'business', 'everyone', 19)");
				*/
		//34
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Platinum Delta SkyMiles Business card', 'American Express', 150, 'No', 'delta', 35000, 1000, 3, 0, 1, 2.7, 'No', " +
				"'5,000 Medallion Qualifying Miles with signup bonus, 2 miles per dollar on Delta purchases, and first bag free. In addition, " +
				"make $25,000 in purchases and earn 10,000 bonus miles and 10,000 MQMs each calendar year: make $50,000 in purchases and earn " +
				"an additional 10,000 bonus miles and 10,000 MQMs each calendar year.', " +
				"'http://thepointsguy.com/go/DeltaPlatinumBizTD/', 'business', 'everyone', 20)");
				*/
		//35
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Delta Reserve for Business card', 'American Express', 450, 'No', 'delta', 0, 0, 3, 10000, 1, 2.7, 'No', " +
				"'10,000 Mediallion Qualifying Miles after first purchase. Complimentary Skyclub access. Make $30,000 in purchases and " +
				"earn 15,000 bonus miles and 15,000 MQMs each calendar year. Make $60,000 in purchases and earn an additional 15,000 bonus " +
				"miles and 15,000 MQMs each calendar year.', 'http://thepointsguy.com/go/DeltaReserveBizTD/', 'business', 'everyone', 21)");
				*/
		//36
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Ink Cash card', 'Chase', 0, 'No', 'ur', 20000, 3000, 3, 0, 1, 3, 'No', 'This is actually a cash back card, NOT Ultimate " +
				"Rewards. See the Ink Classic for the points-earning version of this card. 5% cash back on the first $25,000 spent annually at office supply stores, and on cellular phone, landline, internet, and cable " +
				"TV services, 2% cash back on the first $25,000 spent annually at gas stations and restaurants, 1% on all other purchases.', " +
				"'http://thepointsguy.com/go/InkCashTD/', 'business', 'everyone', 23)");
				*/
		//37
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('ThankYou CitiBusiness card', 'Citibank', 0, 'No', 'ty', 15000, 3000, 3, 0, 1, 3, 'No', " +
				"'Three points per dollar earned in quarterly rotating categories.', " +
				"'http://thepointsguy.com/go/CitiBusinessThankYouTD/', 'business', 'everyone', 24)");
				*/
		//38
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Ink Classic card', 'Chase', 0, 'No', 'ur', 20000, 3000, 3, 0, 1, 3, 'No', 'See the Ink Cash for the cash-earning version of " +
				"this card. Earn 5 points per dollar on the first $25,000 spent annually at office supply stores and on cellular phone, landline, " +
				"internet and cable TV service, 2 points per $1 on the first $25,000 spent annually at gas stations and for hotel accommodations when " +
				"purchased directly with the hotel.', 'http://thepointsguy.com/go/InkClassicTD/', 'business', 'everyone', 25)");
				*/
		//39
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('AAdvantage CitiBusiness World Mastercard', 'Citibank', 95, 'Yes', 'american', 50000, 3000, 3, 0, 1, 3, 'Chip and signature', " +
				"'This is an unofficial link from FlyerTalk.com. 2 miles per dollar spent on American Airlines purchases.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/citibusinessaatd/', 'business', 'everyone', 26)");
				*/
		//40
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Southwest Airlines Rapid Rewards Premier Business card', 'Chase', 99, 'No', 'southwest', 25000, 1000, 3, 0, 1, 0, 'No', " +
				"'Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for free on any " +
				"Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it! " +
				"Also, 6,000 points every year you pay your annual fee.', " +
				"'http://thepointsguy.com/go/SouthwestPremierBusinessTD/', 'business', 'everyone', 27)");
				*/
		//41
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Southwest Airlines Rapid Rewards Plus Business card', 'Chase', 69, 'No', 'southwest', 25000, 1000, 3, 0, 1, 3, 'No', " +
				"'Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for free on any " +
				"Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it! " +
				"Also, 3,000 points every year you pay your annual fee.', 'http://thepointsguy.com/go/SouthwestPlusBusinessTD/', 'business', 'everyone', 28)");
				*/
		//42
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Alaska Airlines Visa Business card', 'FIA/Bank of America', 75, 'No', 'alaska', 0, 0, 3, 25000, 1, 3, 'No', " +
				"'Foreign transaction fee is not listed in terms and conditions. Annual companion fare for $118. Three miles per dollar earned " +
				"on Alaska Airlines purchases.', 'http://thepointsguy.com/go/AlaskaVisaBizTD/', 'business', 'everyone', 29)");
				*/
		//43
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('The Business Platinum card', 'American Express', 450, 'No', 'mr', 25000, 5000, 3, 0, 1, 0, 'No', '$200 airline fee rebate " +
				"available as a statement credit, access to several airlines lounges (and the new Centurion lounges), and reimbursement for " +
				"Global Entry fees.', 'http://thepointsguy.com/go/BusinessPlatinumTD/', 'business', 'everyone', 30)");
				*/
		//44
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('FlexPerks Business Travel Rewards card', 'US Bank', 55, 'Yes', 'usbank', 20000, 3500, 4, 0, 1, 3, 'No', " +
				"'Spend $24,000 in a year and the annual fee will be waived and you will earn 3,500 bonus FlexPoints.', " +
				"'http://thepointsguy.com/go/USBankFlexPerksVisaBusinessTD/', 'business', 'everyone', 31)");
				*/
		//45
		/*
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Amtrak Guest Rewards Mastercard', 'Chase', 0, 'No', 'amtrak', 12000, 500, 3, 0, 1, 3, 'No', " +
				"'2 points per dollar on Amtrak purchases and a 5% points rebate on redemptions for Amtrak travel.', 'https://creditcards.chase.com/credit-cards/amtrak-guest-rewards-mastercard.aspx', " +
				"'business', 'everyone', 32)");
				*/

        //SQLiteDatabase rwdb = this.getWritableDatabase();
		db.beginTransaction();
		
        for (Card c : cat.getCards()) {
            ContentValues values = new ContentValues();

            values.put(KEY_NAME, c.getName());
            values.put(KEY_ISSUER, c.getIssuer());
            values.put(KEY_ANNUAL_FEE, c.getAnnual_fee());
            values.put(KEY_FEE_WAIVED_FIRST_YEAR, c.isFee_waived_first_year());
            values.put(KEY_POINTS_PROGRAM, c.getPoints_program());
            values.put(KEY_SPEND_BONUS, c.getSpend_bonus());
            values.put(KEY_SPEND_REQUIREMENT, c.getSpend_requirement());
            values.put(KEY_TIME_TO_REACH_SPEND_IN_MONTHS, c.getTime_to_reach_spend_in_months());
            values.put(KEY_FIRST_PURCHASE_BONUS, c.getFirst_purchase_bonus());
            values.put(KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND, c.getPoints_per_dollar_spent_general_spend());
            values.put(KEY_FOREIGN_TRANSACTION_FEE, c.getForeign_transaction_fee());
            values.put(KEY_CHIP, c.getChip());
            values.put(KEY_NOTES, c.getNotes());
            values.put(KEY_URL, c.getUrl());
            values.put(KEY_BUSINESS_PERSONAL, c.getBusiness_personal());
            values.put(KEY_INTENDED_AUDIENCE, c.getIntended_audience());
            values.put(KEY_IMAGE, c.getImage());

            db.insert(TABLE_PROVIDERS, null, values);
        }
        
        db.setTransactionSuccessful();
        db.endTransaction();
	}
	
	public void addRow(Row row) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, row.getName());
		values.put(KEY_ISSUER, row.getIssuer());
		values.put(KEY_ANNUAL_FEE, row.getAnnualFee());
		values.put(KEY_FEE_WAIVED_FIRST_YEAR, row.getFeeWaivedFirstYear());
		values.put(KEY_POINTS_PROGRAM, row.getPointsProgram());
		values.put(KEY_SPEND_BONUS, row.getSpendBonus());
		values.put(KEY_SPEND_REQUIREMENT, row.getSpendRequirement());
		values.put(KEY_TIME_TO_REACH_SPEND_IN_MONTHS, row.getTimeToReachSpendInMonths());
		values.put(KEY_FIRST_PURCHASE_BONUS, row.getFirstPurchaseBonus());
		values.put(KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND, row.getPointsPerDollarSpentGeneralSpend());
		values.put(KEY_FOREIGN_TRANSACTION_FEE, row.getForeignTransactionFee());
		values.put(KEY_CHIP, row.getChip());
		values.put(KEY_NOTES, row.getNotes());
		values.put(KEY_URL, row.getUrl());
		values.put(KEY_LOSS_RATE, row.getLossRate());
		rwdb.insert(TABLE_PROVIDERS, null, values);
		
		rwdb.close();
	}
	
	public void updatePointValues(PVData pvd) {
		SQLiteDatabase rwdb = this.getWritableDatabase();
		rwdb.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUES);
		rwdb.execSQL("CREATE TABLE " + TABLE_VALUES + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, points_program TEXT NOT NULL, points_value REAL)");
		
		rwdb.beginTransaction();
		for(int i = 0; i < PVData.airlines.length; i++) {
			rwdb.execSQL("INSERT INTO " + TABLE_VALUES + " (points_program, points_value) VALUES ('" + pvd.getPointsProgram(i) + "', '" + pvd.getPointsValue(i) + "')");
		}
		
		rwdb.execSQL("UPDATE " + TABLE_PROVIDERS + " SET " + KEY_PLANNED_SPEND_PER_MONTH + "=" + pvd.getPointsValue("monthlyspend"));
		rwdb.execSQL("UPDATE " + TABLE_PROVIDERS + " SET " + KEY_LOSS_RATE + "=" + pvd.getPointsValue("lossrate"));
		
		rwdb.setTransactionSuccessful();
		rwdb.endTransaction();
	}
	
	public void updatePVData(PVData pvd) {
		SQLiteDatabase rodb = this.getReadableDatabase();
		Cursor cursor = rodb.rawQuery("SELECT * FROM " + TABLE_VALUES, null);
		
//		TestFlight.log("Kicking off while loop.");
		while (cursor.moveToNext()) {
//			TestFlight.log("Cursor's points_program is " + cursor.getString(cursor.getColumnIndex("points_program")) + " and value is " + cursor.getFloat(cursor.getColumnIndex("points_value")) + ".");
			pvd.setPointsValue(cursor.getString(cursor.getColumnIndex("points_program")), cursor.getFloat(cursor.getColumnIndex("points_value")));
		}
		
		/*
		for(int i = 0; i < PVData.airlines.length; i++) {
			
		}
		*/
	}
	
	/*
	public Row getRow(int id) {
		SQLiteDatabase rodb = this.getReadableDatabase();
		
		//more info on this call at: http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#query(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		Cursor cursor = rodb.query(TABLE_PROVIDERS, null, KEY_ID + "=?", new Stringp[] {String.valueOf(id)}, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Row row = new Row(KEY_ID, KEY_ISSUER, KEY_ANNUAL_FEE, KEY_FEE_WAIVED_FIRST_YEAR, KEY_POINTS_PROGRAM, KEY_SPEND_BONUS, KEY_SPEND_REQUIREMENT, KEY_TIME_TO_REACH_SPEND_IN_MONTHS, KEY_FIRST_PURCHASE_BONUS, KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND, KEY_FOREIGN_TRANSACTION_FEE, KEY_CHIP, KEY_NOTES);
	}
	*/
	
}