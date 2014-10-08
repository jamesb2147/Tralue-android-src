package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class GoldCardPersonal extends Card {
    public GoldCardPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Gold card', 'American Express', 125, 'Yes', 'mr', 0, 1, 1, 0, 1, 2.7, 'No', " +
				"'I am not certain whether American Express considers this the same product the Premier Rewards, Skymiles, etc. Gold cards.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepontsguy.com/go/AmexGoldTD', 'personal', 'everyone', 7)");
        */

        this.setName("Gold card");
        this.setIssuer("American Express");
        this.setAnnual_fee(125);
        this.setFee_waived_first_year(true);
        this.setPoints_program("mr");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("Carefully inspect the terms and conditions of the bonus offer to be sure that you're eligible; many existing charge cardmembers will not be.");
        this.setUrl("http://bit.ly/amex-gold-personal");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(7);
    }

    public Card getCard() {
        return this;
    }
}