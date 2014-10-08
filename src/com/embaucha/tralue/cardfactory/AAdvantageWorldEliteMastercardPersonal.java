package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class AAdvantageWorldEliteMastercardPersonal extends Card {
    public AAdvantageWorldEliteMastercardPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Citi Executive/AAdvantage World Elite Mastercard', 'Citibank', 450, 'No', 'american', '60000', '5000', 3, 0, 1, 0, " +
				"'Chip and signature', 'With this card you�ll get your first checked bag free, Admirals Club membership privileges, " +
				"25% off in-flight purchases, and 10,000 elite qualifying miles after $40,000 in spend in one year.', " +
				"'http://thepointsguy.com/go/CitiAAExecTD/', 'personal', 'everyone')");
        */

        this.setName("Citi Executive/AAdvantage World Elite Mastercard");
        this.setIssuer("Citibank");
        this.setAnnual_fee(450);
        this.setFee_waived_first_year(false);
        this.setPoints_program("american");
        this.setSpend_bonus(50000);
        this.setSpend_requirement(5000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("Chip and signature");
        this.setNotes("With this card you will receive your first checked bag free, Admirals Club membership privileges, " +
				"25% off in-flight purchases, and 10,000 elite qualifying miles after $40,000 in spend in one year.");
        this.setUrl("http://bit.ly/1sY7HYX");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}