package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class FreedomPersonal extends Card {
    public FreedomPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Freedom', 'Chase', 0, 'No', 'mr', 20000, 500, 3, 0, 1, 3, 'No', 'Points cannot be transferred to partners, " +
				"unless you also hold a Chase card that can transfer points to partners. INO, having only the Freedom card only allows you " +
				"to redeem for cash back. However, holding the Freedom and a Sapphire Preferred card allows you to pool points and transfer " +
				"them to Membership Rewards (MR) partners.', 'http://www.thepointsguy.com/go/FreedomTD/', 'personal', 'everyone')");
        */

        this.setName("Freedom");
        this.setIssuer("Chase");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("mr");
        this.setSpend_bonus(10000);
        this.setSpend_requirement(500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Points cannot be transferred to partners, unless you also hold a Chase card that can transfer points to partners. " +
        		"INO, having only the Freedom card only allows you to redeem for cash back. However, holding the Freedom and a Sapphire Preferred " +
        		"card allows you to pool points and transfer them to Membership Rewards (MR) partners.");
        this.setUrl("http://bit.ly/freedom-chase");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}