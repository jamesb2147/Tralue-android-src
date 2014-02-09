package com.embaucha.tralue;

public class Row {
	@SuppressWarnings("unused")
	private String _id, name, issuer, fee_waived_first_year, points_program, chip, notes, url;
	private int annual_fee, spend_bonus, spend_requirement, time_to_reach_spend_in_months, first_purchase_bonus;
	private float points_per_dollar_spent_general_spend, foreign_transaction_fee, lossrate;
	
	public Row(String KEY_NAME, String KEY_ISSUER, int KEY_ANNUAL_FEE, String KEY_FEE_WAIVED_FIRST_YEAR, String KEY_POINTS_PROGRAM, int KEY_SPEND_BONUS, int KEY_SPEND_REQUIREMENT, int KEY_TIME_TO_REACH_SPEND_IN_MONTHS, int KEY_FIRST_PURCHASE_BONUS, float KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND, float KEY_FOREIGN_TRANSACTION_FEE, String KEY_CHIP, String KEY_NOTES, String KEY_URL, float KEY_LOSS_RATE) {
		name = KEY_NAME;
		issuer = KEY_ISSUER;
		fee_waived_first_year = KEY_FEE_WAIVED_FIRST_YEAR;
		points_program = KEY_POINTS_PROGRAM;
		chip = KEY_CHIP;
		notes = KEY_NOTES;
		annual_fee = KEY_ANNUAL_FEE;
		spend_bonus = KEY_SPEND_BONUS;
		spend_requirement = KEY_SPEND_REQUIREMENT;
		time_to_reach_spend_in_months = KEY_TIME_TO_REACH_SPEND_IN_MONTHS;
		first_purchase_bonus = KEY_FIRST_PURCHASE_BONUS;
		points_per_dollar_spent_general_spend = KEY_POINTS_PER_DOLLAR_SPENT_GENERAL_SPEND;
		foreign_transaction_fee = KEY_FOREIGN_TRANSACTION_FEE;
		url = KEY_URL;
		lossrate = KEY_LOSS_RATE;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public int getAnnualFee() {
		return annual_fee;
	}
	
	public String getFeeWaivedFirstYear() {
		return fee_waived_first_year;
	}
	
	public String getPointsProgram() {
		return points_program;
	}
	
	public int getSpendBonus() {
		return spend_bonus;
	}
	
	public int getSpendRequirement() {
		return spend_requirement;
	}
	
	public int getTimeToReachSpendInMonths() {
		return time_to_reach_spend_in_months;
	}
	
	public int getFirstPurchaseBonus() {
		return first_purchase_bonus;
	}
	
	public float getPointsPerDollarSpentGeneralSpend() {
		return points_per_dollar_spent_general_spend;
	}
	
	public float getForeignTransactionFee() {
		return foreign_transaction_fee;
	}
	
	public String getChip() {
		return chip;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public String getUrl() {
		return url;
	}
	
	public float getLossRate() {
		return lossrate;
	}
}