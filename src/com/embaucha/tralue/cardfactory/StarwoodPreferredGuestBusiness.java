package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class StarwoodPreferredGuestBusiness extends Card {
    public StarwoodPreferredGuestBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Starwood Preferred Guest Business card', 'American Express', 65, 'Yes', 'spg', 15000, 5000, 6, 10000, 1, 2.7, 'No', " +
				"'Miles are the most portable of any program (e.g. this is the only card that allows direct transfers of miles to American AAdvantage). " +
				"Unique experience offers can, apparently, be a decent deal. Plus, they really are unique.', " +
				"'http://thepointsguy.com/go/StarwoodBusinessTD/', 'business', 'everyone', 19)");
        */

        this.setName("Starwood Preferred Guest Business card");
        this.setIssuer("American Express");
        this.setAnnual_fee(65);
        this.setFee_waived_first_year(true);
        this.setPoints_program("spg");
        this.setSpend_bonus(15000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(6);
        this.setFirst_purchase_bonus(10000);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("Miles are the most portable of any program (e.g. this is the only card that allows direct transfers of miles to American AAdvantage). " +
				"Unique experience offers can, apparently, be a decent deal. Plus, they really are unique.");
        this.setUrl("http://thepointsguy.com/go/StarwoodBusinessTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(19);
    }

    public Card getCard() {
        return this;
    }
}