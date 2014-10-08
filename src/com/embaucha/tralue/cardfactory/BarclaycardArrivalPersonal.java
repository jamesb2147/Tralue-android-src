package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class BarclaycardArrivalPersonal extends Card {
    public BarclaycardArrivalPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('BarclayCard Arrival', 'Barclays', 89, 'Yes', 'arrival', 40000, 1000, 3, 0, 2.0, 0, 'No', 'You may be approved for a lower " +
				"offer on a Platinum card (the usual Barclays warnings apply).', 'http://thepointsguy.com/go/ArrivalTD/', 'personal', 'everyone', 3)");
        */

        this.setName("BarclayCard Arrival");
        this.setIssuer("Barclays");
        this.setAnnual_fee(89);
        this.setFee_waived_first_year(true);
        this.setPoints_program("arrival");
        this.setSpend_bonus(40000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(2);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("You may be approved for a lower offer on a Platinum card (the usual Barclays warnings apply).");
        this.setUrl("http://www.barclaycardarrival.com/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(3);
    }

    public Card getCard() {
        return this;
    }
}