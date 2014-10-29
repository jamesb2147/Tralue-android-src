package com.embaucha.tralue;

import android.util.Log;

import com.embaucha.tralue.cardfactory.*;
import com.parse.FindCallback;
//import com.testflightapp.lib.TestFlight;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class CardCatalog {
    ArrayList<Card> cards;
    List<ParseObject> cards_parse;

    public CardCatalog() {
    	cards = new ArrayList<Card>();
    	//cards_parse = new ArrayList<ParseObject>();
    	
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("Cards");
//    	query.whereEqualTo("playerName", "Dan Stemkoski");
    	query.findInBackground(new FindCallback<ParseObject>() {
    	    public void done(List<ParseObject> objects, ParseException e) {
    	        if (e == null) {
    	        	//success
    	            Log.d("name", "Retrieved " + objects.size() + " cards from Parse.");
    	            System.out.println("Got the object(s) from Parse!!!");
    	            cards_parse = objects;
    	        } else {
    	            Log.d("score", "Error: " + e.getMessage());
    	        }
    	    }
    	});
        //cards.add(new AmtrakPersonal());
//        TestFlight.log("Initializing cards catalog...");
//        TestFlight.log("Starting with first card.");
        
        cards.add(new BAPersonal());
        cards.add(new USAirwaysPersonal());
        cards.add(new InkPlusBusiness());
        cards.add(new AmtrakPersonal());
        cards.add(new SapphirePreferredPersonal());
        cards.add(new EnhancedBusinessGoldRewards());
        //change the name
        cards.add(new AAdvantageWorldEliteMastercardPersonal());
        cards.add(new ClubCarlsonPremierRewardsPersonal());
        cards.add(new BarclaycardArrivalPersonal());
        cards.add(new MercedesBenzAmExPlatinumPersonal());
        cards.add(new StarwoodPreferredGuestPersonal());
        cards.add(new PremierRewardsGoldPersonal());
        cards.add(new PlatinumCardPersonal());
        cards.add(new BarclaycardArrivalNoFeePersonal());
        cards.add(new GoldDeltaSkymilesPersonal());
        cards.add(new ThankYouPreferredPersonal());
        cards.add(new FlexperksTravelRewardsPersonal());
        cards.add(new SapphirePersonal());
        cards.add(new CapitalOneVentureRewardsPersonal());
        cards.add(new FreedomPersonal());
        cards.add(new DiscoverItPersonal());
        //killed; another dart on the Citi dartboard
//        cards.add(new DividendPlatinumSelectPersonal());
        //appears dead; TY, citi dartboard
//        cards.add(new ForwardPersonal());
        cards.add(new SpiritPersonal());
        //change the name
        cards.add(new PlatinumSelectAAdvantage());
        cards.add(new UnitedMileagePlusExplorerPersonal());
        cards.add(new UnitedMileagePlusClubPersonal());
        cards.add(new GoldCardPersonal());
        cards.add(new ThankYouPremierPersonal());
        cards.add(new SouthwestRapidRewardsPremierPersonal());
        cards.add(new ClubCarlsonRewardsBusiness());
        //chase killed the card, "simplifying" their product line
//        cards.add(new InkBoldBusiness());
        cards.add(new GoldDeltaSkymilesBusiness());
        cards.add(new StarwoodPreferredGuestBusiness());
        cards.add(new PlatinumDeltaSkymilesBusiness());
        cards.add(new DeltaReserveBusiness());
        cards.add(new InkCashBusiness());
        //ah, the citi dartboard
//        cards.add(new ThankYouBusiness());
        //murdered. damn you, chase... DAMN YOU ALL TO HELL!!!!
//        cards.add(new InkClassicBusiness());
        cards.add(new AAdvantageWorldMastercardBusiness());
        cards.add(new SouthwestRapidRewardsPremierBusiness());
        cards.add(new SouthwestRapidRewardsPlusBusiness());
        cards.add(new AlaskaAirlinesBusiness());
        cards.add(new PlatinumCardBusiness());
        cards.add(new FlexPerksTravelRewardsBusiness());
        //temp cards - end with aadvantage 100k offer
//        cards.add(new AAdvantageWorldEliteExecutiveMastercard2());
//        cards.add(new AAdvantageWorldEliteExecutiveMastercard3());
        
//        TestFlight.log("Finished adding cards to catalog. Handing catalog to requestor.");
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
