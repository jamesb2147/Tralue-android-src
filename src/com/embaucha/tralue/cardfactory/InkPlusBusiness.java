package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class InkPlusBusiness extends Card {
    public InkPlusBusiness() {
        /*
        //3
		db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
				"business_personal, intended_audience, image)" +
				" VALUES ('Ink Plus Business Card', 'Chase', 95, 'Yes', 'ur', 50000, 5000, 3, 0, 1, 0, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 5x category earnings on communication services.', 'http://www.thepointsguy.com/go/InkPlusTD/', 'business', 'everyone', 22)");
         */

        this.setName("Ink Plus Business card");
        this.setIssuer("Chase");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("ur");
        this.setSpend_bonus(50000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Flexible/transferable points, portal bonuses, and 5x category earnings on communication services up to $50,000 per year.");
        this.setUrl("http://bit.ly/plus-chase");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(22);
    }
}
