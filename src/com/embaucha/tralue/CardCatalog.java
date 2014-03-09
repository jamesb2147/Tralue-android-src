package com.embaucha.tralue;

import com.embaucha.tralue.cardfactory.*;
import com.testflightapp.lib.TestFlight;

import java.util.ArrayList;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class CardCatalog {
    ArrayList<Card> cards;

    public CardCatalog() {
    	cards = new ArrayList<Card>();
    	
        //cards.add(new AmtrakPersonal());
        TestFlight.log("Initializing cards catalog...");
        TestFlight.log("Starting with first card.");
        
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
        cards.add(new DividendPlatinumSelectPersonal());
        cards.add(new ForwardPersonal());
        cards.add(new SpiritPersonal());
        //change the name
        cards.add(new PlatinumSelectAAdvantage());
        cards.add(new UnitedMileagePlusExplorerPersonal());
        cards.add(new UnitedMileagePlusClubPersonal());
        cards.add(new GoldCardPersonal());
        cards.add(new ThankYouPremierPersonal());
        cards.add(new SouthwestRapidRewardsPremierPersonal());
        cards.add(new ClubCarlsonRewardsBusiness());
        cards.add(new InkBoldBusiness());
        cards.add(new GoldDeltaSkymilesBusiness());
        cards.add(new StarwoodPreferredGuestBusiness());
        cards.add(new PlatinumDeltaSkymilesBusiness());
        cards.add(new DeltaReserveBusiness());
        cards.add(new InkCashBusiness());
        cards.add(new ThankYouBusiness());
        cards.add(new InkClassicBusiness());
        cards.add(new AAdvantageWorldMastercardBusiness());
        cards.add(new SouthwestRapidRewardsPremierBusiness());
        cards.add(new SouthwestRapidRewardsPlusBusiness());
        cards.add(new AlaskaAirlinesBusiness());
        cards.add(new PlatinumCardBusiness());
        cards.add(new FlexPerksTravelRewardsBusiness());
        //temp cards - end with aadvantage 100k offer
        cards.add(new AAdvantageWorldEliteExecutiveMastercard2());
        cards.add(new AAdvantageWorldEliteExecutiveMastercard3());
        
        TestFlight.log("Finished adding cards to catalog. Handing catalog to requestor.");
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
