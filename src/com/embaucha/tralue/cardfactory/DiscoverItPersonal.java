package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class DiscoverItPersonal extends Card {
    public DiscoverItPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Discover It', 'Discover', 0, 'No', 'discover', 0, 1, 1, 0, 1, 0, 'No', 'Discover is one of the more conservative issuers and " +
				"is unlikely to issuer cards to heavy churners. They also offer little in the way of sign-up bonuses. However, the cash back promotions " +
				"are occasionally useful. e.g. Six Flags often offers 5% cash back when paying with Discover.', " +
				"'http://thepointsguy.com/go/DiscoverItTD/', 'personal', 'everyone', 5)");
        */

        this.setName("Discover It");
        this.setIssuer("Discover");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("discover");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("Discover is one of the more conservative issuers and is unlikely to issuer cards to heavy churners. " +
        		"They also offer little in the way of sign-up bonuses. However, the cash back promotions are occasionally useful. " +
        		"e.g. Six Flags often offers 5% cash back when paying with Discover.");
        this.setUrl("http://thepointsguy.com/go/DiscoverItTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(5);
    }

    public Card getCard() {
        return this;
    }
}