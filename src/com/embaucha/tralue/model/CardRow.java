package com.embaucha.tralue.model;

public class CardRow {
	String name, issuer, fee_waived_first_year, points_program, chip, notes;
	int annual_fee, spend_bonus, spend_requirement, time_to_reach_spend_in_months, first_purchase_bonus;
	float points_per_dollar_spent_general_spend, foreign_transaction_fee;
	int id;
	
	public CardRow() {
		
	}
	
	public CardRow(String name, String issuer, String fee_waived_first_year, String points_program, String chip, String notes, int annual_fee, 
			int spend_bonus, int spend_requirement, int time_to_reach_spend_in_months, int first_purchase_bonus, 
			float points_per_dollar_spent_general_spend, float foreign_transaction_fee) {
		this.name = name;
		this.issuer = issuer;
		this.fee_waived_first_year = fee_waived_first_year;
		this.points_program = points_program;
		this.chip = chip;
		this.notes = notes;
		this.annual_fee = annual_fee;
		this.spend_bonus = spend_bonus;
		this.spend_requirement = spend_requirement;
		this.time_to_reach_spend_in_months = time_to_reach_spend_in_months;
		this.first_purchase_bonus = first_purchase_bonus;
		this.points_per_dollar_spent_general_spend = points_per_dollar_spent_general_spend;
		this.foreign_transaction_fee = foreign_transaction_fee;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public void setFeeWaivedFirstYear(String fee_waived_first_year) {
		this.fee_waived_first_year = fee_waived_first_year;
	}
	
	public void setPointsProgram(String points_program) {
		this.points_program = points_program;
	}
	
	public void setChip(String chip) {
		this.chip = chip;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setAnnualFee(int annual_fee) {
		this.annual_fee = annual_fee;
	}
	
	public void setSpendBonus(int spend_bonus) {
		this.spend_bonus = spend_bonus;
	}
	
	public void setSpendRequirement(int spend_requirement) {
		this.spend_requirement = spend_requirement;
	}
	
	public void setTimeToReachSpendInMonths (int time_to_reach_spend_in_months) {
		this.time_to_reach_spend_in_months = time_to_reach_spend_in_months;
	}
	
	public void setFirstPurchaseBonus(int first_purchase_bonus) {
		this.first_purchase_bonus = first_purchase_bonus;
	}
	
	public void setPointsPerDollarSpentGeneralSpend(float points_per_dollar_spent_general_spend) {
		this.points_per_dollar_spent_general_spend = points_per_dollar_spent_general_spend;
	}
	
	public void setForeignTransactionFee(float foreign_transaction_fee) {
		this.foreign_transaction_fee = foreign_transaction_fee;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public String getFeeWaivedFirstYear() {
		return fee_waived_first_year;
	}
	
	public String getPointsProgram() {
		return points_program;
	}
	
	public String getChip() {
		return chip;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public int getAnnualFee() {
		return annual_fee;
	}
	
	public int getSpendBonus() {
		return spend_bonus;
	}
	
	public int getSpendRequirement() {
		return spend_requirement;
	}
	
	public int getTimeToReachSpendInMonths () {
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
}