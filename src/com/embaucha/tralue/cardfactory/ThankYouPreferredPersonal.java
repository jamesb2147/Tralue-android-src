package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class ThankYouPreferredPersonal extends Card {
    public ThankYouPreferredPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('ThankYou Preferred', 'Citibank', 0, 'No', 'ty', 20000, 1500, 3, 0, 1, 3, 'Chip and signature', " +
				"'2 points per dollar on dining and entertainment.', 'http://thepointsguy.com/go/CitithankYouPreferredTD/', " +
				"'personal', 'everyone', 13)");
        */

        this.setName("ThankYou Preferred");
        this.setIssuer("Citibank");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("ty");
        this.setSpend_bonus(20000);
        this.setSpend_requirement(1500);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("Chip and signature");
        this.setNotes("2 points per dollar on dining and entertainment.");
        this.setUrl("http://thepointsguy.com/go/CitithankYouPreferredTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(13);
    }

    public Card getCard() {
        return this;
    }
}