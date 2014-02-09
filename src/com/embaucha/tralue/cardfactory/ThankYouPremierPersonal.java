package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ThankYouPremierPersonal extends Card {
    public ThankYouPremierPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Thank You Premier', 'Citibank', 125, 'Yes', 'ty', 50000, 5000, 3, 0, 1, 0, 'Chip and signature', " +
				"'Thank you points are worth 25% more when redeemed for airfare on the ThankYou portal.', 'http://thepointsguy.com/go/CitiThankYouPremierTD/', " +
				"'personal', 'everyone', 14)");
        */

        this.setName("Thank You Premier");
        this.setIssuer("Citibank");
        this.setAnnual_fee(125);
        this.setFee_waived_first_year(true);
        this.setPoints_program("ty");
        this.setSpend_bonus(50000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("Chip and signature");
        this.setNotes("Thank you points are worth 25% more when redeemed for airfare on the ThankYou portal.");
        this.setUrl("http://thepointsguy.com/go/CitiThankYouPremierTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(14);
    }

    public Card getCard() {
        return this;
    }
}