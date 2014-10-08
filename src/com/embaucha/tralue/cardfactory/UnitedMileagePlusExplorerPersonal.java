package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class UnitedMileagePlusExplorerPersonal extends Card {
    public UnitedMileagePlusExplorerPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes" +
				", business_personal, intended_audience)" +
				" VALUES ('United MileagePlus Explorer', 'Chase', 95, 'Yes', 'united', 50000, 2000, 3, 0, 1, 0, 'No', 'Spending $25k on your card in a year " +
				"gets 10,000 bonus miles. 2 points per dollar on United purchases. The 50,000 mile offer is only available in Chase branches. " +
				"This offer is not available online.', 'personal', 'everyone')");
        */

        this.setName("United MileagePlus Explorer");
        this.setIssuer("Chase");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("united");
        this.setSpend_bonus(30000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Spending $25k on your card in a year gets 10,000 bonus miles. 2 points per dollar on United purchases. " +
        		"The 50,000 mile offer is only available in Chase branches. This offer is not available online.");
        this.setUrl("http://bit.ly/mp-explorer-chase");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        //this.setImage(32);
    }

    public Card getCard() {
        return this;
    }
}