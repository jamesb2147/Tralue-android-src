package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ClubCarlsonPremierRewardsPersonal extends Card {
    public ClubCarlsonPremierRewardsPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Club Carlson Premier Rewards Visa Signature', 'Barclays', 75, 'No', 'carlson', 35000, 2500, 3, 50000, 5, 2.0, 'No', " +
				"'Carlson Gold status and last night free on award stays of at least 2 nights.', " +
				"'http://thepointsguy.com/go/ClubCarlsonPremierVisaTD/', 'personal', 'everyone', 4)");
        */

        this.setName("Club Carlson Premier Rewards Visa Signature");
        this.setIssuer("Barclays");
        this.setAnnual_fee(75);
        this.setFee_waived_first_year(false);
        this.setPoints_program("carlson");
        this.setSpend_bonus(35000);
        this.setSpend_requirement(2500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(50000);
        this.setPoints_per_dollar_spent_general_spend(5);
        this.setForeign_transaction_fee(2);
        this.setChip("No");
        this.setNotes("Carlson Gold status and last night free on award stays of at least 2 nights.");
        this.setUrl("http://bit.ly/carlson-visa-signature");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(4);
    }

    public Card getCard() {
        return this;
    }
}