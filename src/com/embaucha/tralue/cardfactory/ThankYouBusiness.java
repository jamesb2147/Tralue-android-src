package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ThankYouBusiness extends Card {
    public ThankYouBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('ThankYou CitiBusiness card', 'Citibank', 0, 'No', 'ty', 15000, 3000, 3, 0, 1, 3, 'No', " +
				"'Three points per dollar earned in quarterly rotating categories.', " +
				"'http://thepointsguy.com/go/CitiBusinessThankYouTD/', 'business', 'everyone', 24)");
        */

        this.setName("ThankYou CitiBusiness card");
        this.setIssuer("Citibank");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("ty");
        this.setSpend_bonus(15000);
        this.setSpend_requirement(3000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Three points per dollar earned in quarterly rotating categories.");
        this.setUrl("http://thepointsguy.com/go/CitiBusinessThankYouTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(24);
    }

    public Card getCard() {
        return this;
    }
}