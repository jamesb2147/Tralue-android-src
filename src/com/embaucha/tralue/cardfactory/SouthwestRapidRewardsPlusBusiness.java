package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class SouthwestRapidRewardsPlusBusiness extends Card {
    public SouthwestRapidRewardsPlusBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Southwest Airlines Rapid Rewards Plus Business card', 'Chase', 69, 'No', 'southwest', 25000, 1000, 3, 0, 1, 3, 'No', " +
				"'Earn 100,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for free on any " +
				"Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it! " +
				"Also, 3,000 points every year you pay your annual fee.', 'http://thepointsguy.com/go/SouthwestPlusBusinessTD/', 'business', 'everyone', 28)");
        */

        this.setName("Southwest Airlines Rapid Rewards Plus Business card");
        this.setIssuer("Chase");
        this.setAnnual_fee(69);
        this.setFee_waived_first_year(false);
        this.setPoints_program("southwest");
        this.setSpend_bonus(25000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Earn 110,000 points in a year and receive the Southwest Companion Pass that allows one person to fly with you for free on any " +
				"Southwest paid or award ticket for one year. Phenomenal value, for those who can make use of it! " +
				"Also, 3,000 points every year you pay your annual fee.");
        this.setUrl("http://bit.ly/rapidrewards-plus-business");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(28);
    }

    public Card getCard() {
        return this;
    }
}