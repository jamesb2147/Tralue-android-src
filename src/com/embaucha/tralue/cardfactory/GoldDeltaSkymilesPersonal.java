package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class GoldDeltaSkymilesPersonal extends Card {
    public GoldDeltaSkymilesPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Gold Delta Skymiles', 'American Express', 95, 'Yes', 'delta', 30000, 500, 3, 0, 1, 2.7, 'No', " +
				"'$50 statement credit on Delta purchase within three months. 2 miles per dollar spent on Delta purchases.', " +
				"'http://thepointsguy.com/go/DeltaGoldTD/', 'personal', 'everyone')");
        */

        this.setName("Gold Delta Skymiles card");
        this.setIssuer("American Express");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("delta");
        this.setSpend_bonus(30000);
        this.setSpend_requirement(500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("$50 statement credit on Delta purchase within three months. 2 miles per dollar spent on Delta purchases.");
        this.setUrl("http://thepointsguy.com/go/DeltaGoldTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}