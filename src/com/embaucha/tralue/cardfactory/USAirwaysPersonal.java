package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class USAirwaysPersonal extends Card {
    public USAirwaysPersonal() {
        /*
        //2
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
				"business_personal, intended_audience) " +
				"VALUES('US Airways Premier World Mastercard', 'Barclays', 89, 'No', 'usair', 0, 1, 1, 30000, 1, 3.0, 'No', " +
				"'May be approved for \"Platinum Plus\" card instead of World Mastercard.  PP has a $19 annual fee, earns 1 mile for " +
				"every dollar spent, and earns 5000 bonus miles after first purchase. Spend $10K within a year and you will receive 5000 " +
				"bonus points annually.', 'http://thepointsguy.com/go/USAirwaysPremierFeewaivedTD/', 'personal', 'everyone')");
         */

        this.setName("US Airways Premier World Mastercard");
        this.setIssuer("Barclays");
        this.setAnnual_fee(89);
        this.setFee_waived_first_year(false);
        this.setPoints_program("usair");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(0);
        this.setFirst_purchase_bonus(30000);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("May be approved for \"Platinum Plus\" card instead of World Mastercard.  PP has a $19 annual fee, earns 1 mile for " +
                "every dollar spent, and earns 5000 bonus miles after first purchase. Spend $10K within a year and you will receive 5000 bonus points annually.");
        this.setUrl("http://thepointsguy.com/go/USAirwaysPremierFeewaivedTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }
}
