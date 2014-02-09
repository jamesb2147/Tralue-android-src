package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class GoldDeltaSkymilesBusiness extends Card {
    public GoldDeltaSkymilesBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Gold Delta Skymiles Business card', 'American Express', 95, 'Yes', 'delta', 30000, 1000, 3, 0, 1, 2.7, 'No', " +
				"'$50 statement credit on a Delta purchase within the first three months. 2 miles per dollar on Delta purchases, first checked bag " +
				"free, and priority boarding on Delta flights.', 'http://thepointsguy.com/go/DeltaBusinessTD/', 'business', 'everyone', 18)");
        */

        this.setName("Gold Delta Skymiles Business card");
        this.setIssuer("American Express");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("delta");
        this.setSpend_bonus(30000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("$50 statement credit on a Delta purchase within the first three months. 2 miles per dollar on Delta purchases, first checked bag " +
				"free, and priority boarding on Delta flights.");
        this.setUrl("http://thepointsguy.com/go/DeltaBusinessTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(18);
    }

    public Card getCard() {
        return this;
    }
}