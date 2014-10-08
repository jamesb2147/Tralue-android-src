package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class InkCashBusiness extends Card {
    public InkCashBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Ink Cash card', 'Chase', 0, 'No', 'ur', 20000, 3000, 3, 0, 1, 3, 'No', 'This is actually a cash back card, NOT Ultimate " +
				"Rewards. See the Ink Classic for the points-earning version of this card. 5% cash back on the first $25,000 spent annually at office supply stores, and on cellular phone, landline, internet, and cable " +
				"TV services, 2% cash back on the first $25,000 spent annually at gas stations and restaurants, 1% on all other purchases.', " +
				"'http://thepointsguy.com/go/InkCashTD/', 'business', 'everyone', 23)");
        */

        this.setName("Ink Cash card");
        this.setIssuer("Chase");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("ur");
        this.setSpend_bonus(20000);
        this.setSpend_requirement(3000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("This is actually a cash back card, NOT Ultimate Rewards. See the Ink Classic for the points-earning version of this card. " +
        		"5% cash back on the first $25,000 spent annually at office supply stores, and on cellular phone, landline, internet, and cable " +
        		"TV services, 2% cash back on the first $25,000 spent annually at gas stations and restaurants, 1% on all other purchases.");
        this.setUrl("http://bit.ly/cash-chase");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(23);
    }

    public Card getCard() {
        return this;
    }
}