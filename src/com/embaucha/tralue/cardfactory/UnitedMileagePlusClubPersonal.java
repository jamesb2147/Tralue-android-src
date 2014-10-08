package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class UnitedMileagePlusClubPersonal extends Card {
    public UnitedMileagePlusClubPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes" +
				", business_personal, intended_audience)" +
				" VALUES ('United MileagePlus Club', 'Chase', 395, 'Yes', 'united', 0, 1, 1, 0, 1.5, 0, 'No', 'Full United Club membership " +
				"included. Earns 1.5 miles per dollar on all purchases. First and second checked bags are free. This offer is only available" +
				" in Chase branches, otherwise you have to pay the annual fee your first year.', 'personal', 'everyone')");
        */

        this.setName("United MileagePlus Club");
        this.setIssuer("Chase");
        this.setAnnual_fee(395);
        this.setFee_waived_first_year(true);
        this.setPoints_program("united");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend((float)1.5);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Full United Club membership included. Earns 1.5 miles per dollar on all purchases. " +
        		"First and second checked bags are free. This offer is only available in Chase branches, otherwise " +
        		"you have to pay the annual fee your first year.");
        //this.setUrl("http://bit.ly/mp-explorer-chase");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        //this.setImage(32);
    }

    public Card getCard() {
        return this;
    }
}