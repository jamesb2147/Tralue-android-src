package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class CapitalOneVentureRewardsPersonal extends Card {
    public CapitalOneVentureRewardsPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Capital One Venture Rewards', 'Capital One', 59, 'No', 'capone', 10000, 1000, 3, 0, 2, 0, 'No', " +
				"'Points have a fixed value of 1 cent.', 'http://thepointsguy.com/go/VentureTD/', 'personal', 'everyone')");
        */

        this.setName("Capital One Venture Rewards");
        this.setIssuer("Capital One");
        this.setAnnual_fee(59);
        this.setFee_waived_first_year(false);
        this.setPoints_program("capone");
        this.setSpend_bonus(40000);
        this.setSpend_requirement(3000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(2);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Points have a fixed value of 1 cent.");
        this.setUrl("http://bit.ly/capone-venture");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}