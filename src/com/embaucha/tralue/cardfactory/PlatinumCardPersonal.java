package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class PlatinumCardPersonal extends Card {
    public PlatinumCardPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('Platinum Card', 'American Express', 450, 'No', 'mr', 25000, 2000, 3, 0, 1, 0, 'No', '$200 airline fee rebate, " +
				"lounge access for Delta, American, and US Airways lounges, Global Entry fees are rebated, 25% bonus on pay-with-points " +
				"(1.25 cents per point fixed value), and Starwood Gold.', 'http://thepointsguy.com/go/AmexPlatinumTD/', 'personal', 'everyone', 9)");
        */

        this.setName("The Platinum card");
        this.setIssuer("American Express");
        this.setAnnual_fee(450);
        this.setFee_waived_first_year(false);
        this.setPoints_program("mr");
        this.setSpend_bonus(40000);
        this.setSpend_requirement(3000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("$200 airline fee rebate, lounge access for Delta, American, and US Airways lounges, Global Entry fees are rebated, " +
        		"25% bonus on pay-with-points (1.25 cents per point fixed value), and Starwood Gold.");
        this.setUrl("http://bit.ly/amex-platinum-personal");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(9);
    }

    public Card getCard() {
        return this;
    }
}