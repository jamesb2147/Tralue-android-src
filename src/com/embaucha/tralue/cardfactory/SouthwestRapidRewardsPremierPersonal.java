package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class SouthwestRapidRewardsPremierPersonal extends Card {
    public SouthwestRapidRewardsPremierPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Southwest Rapid Rewards Premier card', 'Chase', 99, 'No', 'southwest', 25000, 2000, 3, 0, 2, 0, 'No', " +
				"'Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for " +
				"free on any Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it!', " +
				"'http://thepointsguy.com/go/SouthwestPremierBusinessTD/', 'personal', 'everyone', 15)");
        */

        this.setName("Southwest Rapid Rewards Premier card");
        this.setIssuer("Chase");
        this.setAnnual_fee(99);
        this.setFee_waived_first_year(false);
        this.setPoints_program("southwest");
        this.setSpend_bonus(25000);
        this.setSpend_requirement(2000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(2);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for " +
				"free on any Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it!");
        this.setUrl("http://thepointsguy.com/go/SouthwestPremierBusinessTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(15);
    }

    public Card getCard() {
        return this;
    }
}