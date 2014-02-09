package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class SpiritPersonal extends Card {
    public SpiritPersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience)" +
				" VALUES ('Spirit World Mastercard', 'FIA/Bank of America', 59, 'Yes', 'spirit', 0, 1, 1, 15000, 2, 3.0, 'No', " +
				"'If approved for a credit limit of less than $5000, you will receive the lesser Platinum Plus card. The platinum card " +
				"earns a first purchase bonus of 5000 miles, and has a $19 annual fee. Mastercard benefits also differ. Both cards " +
				"receive 5000 bonus miles per year for spending $10,000 and paying your annual fee.', " +
				"'http://doublizer.com/index.php?code=VABV3J', 'personal', 'everyone')");
        */

        this.setName("Spirit World Mastercard");
        this.setIssuer("FIA/Bank of America");
        this.setAnnual_fee(59);
        this.setFee_waived_first_year(true);
        this.setPoints_program("spirit");
        this.setSpend_bonus(0);
        this.setSpend_requirement(0);
        this.setTime_to_reach_spend_in_months(1);
        this.setFirst_purchase_bonus(15000);
        this.setPoints_per_dollar_spent_general_spend(2);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("If approved for a credit limit of less than $5000, you will receive the lesser Platinum Plus card. The platinum card " +
				"earns a first purchase bonus of 5000 miles, and has a $19 annual fee. Mastercard benefits also differ. Both cards " +
				"receive 5000 bonus miles per year for spending $10,000 and paying your annual fee.");
        this.setUrl("http://doublizer.com/index.php?code=VABV3J");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
    }

    public Card getCard() {
        return this;
    }
}