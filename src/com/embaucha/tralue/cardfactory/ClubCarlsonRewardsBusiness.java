package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ClubCarlsonRewardsBusiness extends Card {
    public ClubCarlsonRewardsBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Club Carlson Business Rewards Visa Card', 'Barclays', 60, 'No', 'carlson', 35000, 2500, 3, 50000, 5, 3, 'No', " +
				"'Double points for spend at Carlson Rezidor hotels. Also receive Club Carlson Gold status and last night free on awards stays " +
				"(must stay at least two nights).', 'http://thepointsguy.com/go/CarlsonBizTD/', 'business', 'everyone', 16)");
        */

        this.setName("Club Carlson Business Rewards Visa Card");
        this.setIssuer("Barclays");
        this.setAnnual_fee(60);
        this.setFee_waived_first_year(false);
        this.setPoints_program("carlson");
        this.setSpend_bonus(35000);
        this.setSpend_requirement(2500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(50000);
        this.setPoints_per_dollar_spent_general_spend(5);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Double points for spend at Carlson Rezidor hotels. Also receive Club Carlson Gold status and last night free on awards stays " +
				"(must stay at least two nights).");
        this.setUrl("http://bit.ly/carlson-visa-business");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(16);
    }

    public Card getCard() {
        return this;
    }
}