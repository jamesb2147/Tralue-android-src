package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class SapphirePersonal extends Card {
    public SapphirePersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Sapphire', 'Chase', 0, 'No', 'ur', 10000, 500, 3, 0, 1, 3, 'No', 'I am uncertain whether Chase considers " +
				"this a different product from the Sapphire Preferred, which is important because the sign-up bonus for the Preferred is 4x the " +
				"Sapphire. In addition, the Sapphire points are not transferrable to airline miles.', 'http://thepointsguy.com/go/SapphireTD/', " +
				"'personal', 'everyone', 11)");
        */

        this.setName("Sapphire");
        this.setIssuer("Chase");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("ur");
        this.setSpend_bonus(10000);
        this.setSpend_requirement(500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("The Sapphire points are not transferrable to airline miles.");
        this.setUrl("http://bit.ly/chase-sapphire-comparison");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(11);
    }

    public Card getCard() {
        return this;
    }
}