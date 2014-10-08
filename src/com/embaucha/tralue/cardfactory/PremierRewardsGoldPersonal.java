package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class PremierRewardsGoldPersonal extends Card {
    public PremierRewardsGoldPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Premier Rewards Gold', 'American Express', 175, 'Yes', 'mr', 25000, 2000, 3, 0, 1, 2.7, 'No', " +
				"'3 points per dollar on airfare and 2 points on US gas and groceries. A bonus of 15,000 points is awarded after $30,000 in spending " +
				"in a year. Particularly useful for big spenders.', 'http://thepointsguy.com/go/PremierRewardsTD/', 'personal', 'everyone', 10)");
         */

        this.setName("Premier Rewards Gold");
        this.setIssuer("American Express");
        this.setAnnual_fee(175);
        this.setFee_waived_first_year(true);
        this.setPoints_program("mr");
        this.setSpend_bonus(0);
        this.setSpend_requirement(1);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("3 points per dollar on airfare and 2 points on US gas and groceries.");
        this.setUrl("http://bit.ly/amex-premier-rewards-gold-personal");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(10);
    }

    public Card getCard() {
        return this;
    }
}