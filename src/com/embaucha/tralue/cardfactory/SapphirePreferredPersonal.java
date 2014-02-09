package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class SapphirePreferredPersonal extends Card {
    public SapphirePreferredPersonal() {

        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Sapphire Preferred', 'Chase', 95, 'Yes', 'ur', 40000, 3000, 3, 0, 1, 0, 'No', 'Flexible/transferable points, portal bonuses," +
				" and 2x category earnings on dining and travel.', 'http://thepointsguy.com/go/SapphirePrefTD/', 'personal', 'everyone', 1)");
        */

        this.setName("Sapphire Preferred");
        this.setIssuer("Chase");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("ur");
        this.setSpend_bonus(40000);
        this.setSpend_requirement(2000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("Chip and signature");
        this.setNotes("Flexible/transferable points, portal bonuses, and 2x category earnings on dining and travel.");
        this.setUrl("http://thepointsguy.com/go/SapphirePrefTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(1);
    }

    public Card getCard() {
        return this;
    }
}
