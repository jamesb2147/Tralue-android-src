package com.embaucha.tralue;

/**
 * Created by jamesb2147 on 12/10/13.
 */
public class Card {
    String name;
    String issuer;
    int annual_fee;
    boolean fee_waived_first_year;
    String points_program;
    int spend_bonus;
    int spend_requirement;
    int time_to_reach_spend_in_months;
    int first_purchase_bonus;
    float points_per_dollar_spent_general_spend;
    float foreign_transaction_fee;
    String chip;
    String notes;
    String url;
    String business_personal;
    String intended_audience;
    int image;
    //added to card, but not yet to DB
    int minimumMonthsBetweenApplications;

    public Card() {
        name = "Default name";
        issuer = "Default issuer";
        annual_fee = 50;
        fee_waived_first_year = false;
        points_program = "american";
        spend_bonus = 50000;
        spend_requirement = 3000;
        time_to_reach_spend_in_months = 3;
        first_purchase_bonus = 15000;
        points_per_dollar_spent_general_spend = 1;
        foreign_transaction_fee = 3;
        chip = "No";
        notes = "This is a generic card. Please contact Embaucha LLC at feedback@embaucha.com and let us know that you " +
                "saw this. This is a test card, and you should not be able to see this.";
        business_personal = "personal";
        intended_audience = "everyone";
        //new field
        minimumMonthsBetweenApplications = 12;

        //url and image should remain blank -- the code will automagically remove the appropriate fields programmatically, so the user won't even know they weren't there
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getAnnual_fee() {
        return annual_fee;
    }

    public void setAnnual_fee(int annual_fee) {
        this.annual_fee = annual_fee;
    }

    public boolean isFee_waived_first_year() {
        return fee_waived_first_year;
    }

    public void setFee_waived_first_year(boolean fee_waived_first_year) {
        this.fee_waived_first_year = fee_waived_first_year;
    }

    public String getPoints_program() {
        return points_program;
    }

    public void setPoints_program(String points_program) {
        this.points_program = points_program;
    }

    public int getSpend_bonus() {
        return spend_bonus;
    }

    public void setSpend_bonus(int spend_bonus) {
        this.spend_bonus = spend_bonus;
    }

    public int getSpend_requirement() {
        return spend_requirement;
    }

    public void setSpend_requirement(int spend_requirement) {
        this.spend_requirement = spend_requirement;
    }

    public int getTime_to_reach_spend_in_months() {
        return time_to_reach_spend_in_months;
    }

    public void setTime_to_reach_spend_in_months(int time_to_reach_spend_in_months) {
        this.time_to_reach_spend_in_months = time_to_reach_spend_in_months;
    }

    public int getFirst_purchase_bonus() {
        return first_purchase_bonus;
    }

    public void setFirst_purchase_bonus(int first_purchase_bonus) {
        this.first_purchase_bonus = first_purchase_bonus;
    }

    public float getPoints_per_dollar_spent_general_spend() {
        return points_per_dollar_spent_general_spend;
    }

    public void setPoints_per_dollar_spent_general_spend(float points_per_dollar_spent_general_spend) {
        this.points_per_dollar_spent_general_spend = points_per_dollar_spent_general_spend;
    }

    public float getForeign_transaction_fee() {
        return foreign_transaction_fee;
    }

    public void setForeign_transaction_fee(float foreign_transaction_fee) {
        this.foreign_transaction_fee = foreign_transaction_fee;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusiness_personal() {
        return business_personal;
    }

    public void setBusiness_personal(String business_personal) {
        this.business_personal = business_personal;
    }

    public String getIntended_audience() {
        return intended_audience;
    }

    public void setIntended_audience(String intended_audience) {
        this.intended_audience = intended_audience;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getMinimumMonthsBetweenApplications() {
        return minimumMonthsBetweenApplications;
    }

    public void setMinimumMonthsBetweenApplications(int minimumMonthsBetweenApplications) {
        this.minimumMonthsBetweenApplications = minimumMonthsBetweenApplications;
    }
}
