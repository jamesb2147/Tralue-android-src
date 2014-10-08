package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class FlexperksTravelRewardsPersonal extends Card {
    public FlexperksTravelRewardsPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('FlexPerks Travel Rewards Visa Signature', 'US Bank', 49, 'Yes', 'usbank', 20000, 3500, 5, 0, 1, 3, 'Chip and signature', " +
				"'Points can only be used in large increments towards retail airfare.', 'http://thepointsguy.com/go/USBankFlexPerksVisaTD/', 'personal', 'everyone')");
        */

        this.setName("FlexPerks Travel Rewards Visa Signature");
        this.setIssuer("US Bank");
        this.setAnnual_fee(49);
        this.setFee_waived_first_year(true);
        this.setPoints_program("usbank");
        this.setSpend_bonus(20000);
        this.setSpend_requirement(3500);
        this.setTime_to_reach_spend_in_months(5);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("Chip and signature");
        this.setNotes("Points can only be used in large increments (think $200 increments) towards retail airfare.");
        this.setUrl("http://bit.ly/flexperks-travel-personal");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}