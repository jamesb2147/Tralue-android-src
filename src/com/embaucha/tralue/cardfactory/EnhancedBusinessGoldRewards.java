package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

public class EnhancedBusinessGoldRewards extends Card {
	public EnhancedBusinessGoldRewards() {
		/*
		 * db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Enhanced Business Gold Rewards', 'American Express', 175, 'Yes', 'ur', 30000, 5000, 3, 0, 1, 2.7, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 3x category earnings on airfare, 2x for advertising, gas, shipping, and computer hardware/services. Also benefits from " +
				"OPEN program of 10% discount or 5x earning at OPEN merchants, and free Amazon Prime membership.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/businessgoldTD/', 'business', 'everyone')");
		 */
		
		this.setName("Enhanced Business Gold Rewards");
        this.setIssuer("American Express");
        this.setAnnual_fee(175);
        this.setFee_waived_first_year(true);
        this.setPoints_program("mr");
        this.setSpend_bonus(30000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("Flexible/transferable points, portal bonuses," +
				" and 3x category earnings on airfare, 2x for advertising, gas, shipping, and computer hardware/services. Also benefits from " +
				"OPEN program of 10% discount or 5x earning at OPEN merchants, and free Amazon Prime membership.");
        this.setUrl("http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/businessgoldTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
	}
	
	public Card getCard() {
		return this;
	}
}