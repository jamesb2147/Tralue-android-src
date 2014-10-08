package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class AlaskaAirlinesBusiness extends Card {
    public AlaskaAirlinesBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Alaska Airlines Visa Business card', 'FIA/Bank of America', 75, 'No', 'alaska', 0, 0, 3, 25000, 1, 3, 'No', " +
				"'Foreign transaction fee is not listed in terms and conditions. Annual companion fare for $118. Three miles per dollar earned " +
				"on Alaska Airlines purchases.', 'http://thepointsguy.com/go/AlaskaVisaBizTD/', 'business', 'everyone', 29)");
        */

        this.setName("Alaska Airlines Visa Business card");
        this.setIssuer("FIA/Bank of America");
        this.setAnnual_fee(75);
        this.setFee_waived_first_year(false);
        this.setPoints_program("alaska");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(25000);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Foreign transaction fee is not listed in terms and conditions. Annual companion fare for $118. Three miles per dollar earned " +
				"on Alaska Airlines purchases.");
        this.setUrl("http://bit.ly/1rcInH1");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(29);
    }

    public Card getCard() {
        return this;
    }
}