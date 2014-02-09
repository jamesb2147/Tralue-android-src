package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class PlatinumCardBusiness extends Card {
    public PlatinumCardBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('The Business Platinum card', 'American Express', 450, 'No', 'mr', 25000, 5000, 3, 0, 1, 0, 'No', '$200 airline fee rebate " +
				"available as a statement credit, access to several airlines lounges (and the new Centurion lounges), and reimbursement for " +
				"Global Entry fees.', 'http://thepointsguy.com/go/BusinessPlatinumTD/', 'business', 'everyone', 30)");
        */

        this.setName("The Platinum card for Business");
        this.setIssuer("American Express");
        this.setAnnual_fee(450);
        this.setFee_waived_first_year(false);
        this.setPoints_program("mr");
        this.setSpend_bonus(25000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("$200 airline fee rebate available as a statement credit, access to several airlines lounges " +
        		"(and the new Centurion lounges), and reimbursement for Global Entry fees.");
        this.setUrl("http://thepointsguy.com/go/BusinessPlatinumTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(30);
    }

    public Card getCard() {
        return this;
    }
}