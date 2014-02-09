package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class DividendPlatinumSelectPersonal extends Card {
    public DividendPlatinumSelectPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Dividend Platinum Select', 'Citibank', 0, 'No', 'ty', 10000, 500, 3, 0, 1, 3, 'Chip and signature', " +
				"'5x points back on rotating categories.', 'http://thepointsguy.com/go/CitiDividendPlatinumSelectTD/', 'personal', 'everyone')");
        */

        this.setName("Amtrak Guest Rewards Mastercard");
        this.setIssuer("Chase");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("amtrak");
        this.setSpend_bonus(12000);
        this.setSpend_requirement(500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("2 points per dollar on Amtrak purchases and a 5% points rebate on redemptions for Amtrak travel.");
        this.setUrl("https://creditcards.chase.com/credit-cards/amtrak-guest-rewards-masterthis.aspx");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}