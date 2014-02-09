package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class DeltaReserveBusiness extends Card {
    public DeltaReserveBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Delta Reserve for Business card', 'American Express', 450, 'No', 'delta', 0, 0, 3, 10000, 1, 2.7, 'No', " +
				"'10,000 Mediallion Qualifying Miles after first purchase. Complimentary Skyclub access. Make $30,000 in purchases and " +
				"earn 15,000 bonus miles and 15,000 MQMs each calendar year. Make $60,000 in purchases and earn an additional 15,000 bonus " +
				"miles and 15,000 MQMs each calendar year.', 'http://thepointsguy.com/go/DeltaReserveBizTD/', 'business', 'everyone', 21)");
        */

        this.setName("Delta Reserve for Business card");
        this.setIssuer("American Express");
        this.setAnnual_fee(450);
        this.setFee_waived_first_year(false);
        this.setPoints_program("delta");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(10000);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("10,000 Mediallion Qualifying Miles after first purchase. Complimentary Skyclub access. Make $30,000 in purchases and " +
				"earn 15,000 bonus miles and 15,000 MQMs each calendar year. Make $60,000 in purchases and earn an additional 15,000 bonus " +
				"miles and 15,000 MQMs each calendar year.");
        this.setUrl("http://thepointsguy.com/go/DeltaReserveBizTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(21);
    }

    public Card getCard() {
        return this;
    }
}