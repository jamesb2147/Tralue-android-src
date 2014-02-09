package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ForwardPersonal extends Card {
    public ForwardPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Forward card for college students', 'Citibank', 0, 'No', 'ty', 2500, 500, 3, 0, 1, 3, 'No', " +
				"'5x points on food, books, and entertainment. 100 points per month for paying on time and staying under your credit limit.', " +
				"'http://thepointsguy.com/go/CitiForwardTD/', 'personal', 'everyone', 6)");
        */

        this.setName("Forward card for college students");
        this.setIssuer("Citibank");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("ty");
        this.setSpend_bonus(2500);
        this.setSpend_requirement(500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("5x points on food, books, and entertainment. 100 points per month for paying on time and staying under your credit limit.");
        this.setUrl("http://thepointsguy.com/go/CitiForwardTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(6);
    }

    public Card getCard() {
        return this;
    }
}