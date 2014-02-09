package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class BarclaycardArrivalNoFeePersonal extends Card {
    public BarclaycardArrivalNoFeePersonal() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" + 
				" VALUES ('BarclayCard Arrival - No fee', 'Barclays', 0, 'No', 'arrival', 20000, 1000, 3, 0, 2, 0, 'No', 'You may be approved for a Platinum card " +
				"instead of World Mastercard (the standard Barclays warning. However, in this case, I could not find any material difference *in the offer.* " +
				"Now, Platinum cardholders will not have access to World Elite Mastercard benefits (e.g. hotel promotions worldwide), but everyone will " +
				"receive 20,000 bonus points and no annual fee. VICTORY!!!', 'http://thepointsguy.com/go/ArrivalNoFeeTD/', 'personal', 'everyone', 2)");
        */

        this.setName("BarclayCard Arrival - No fee");
        this.setIssuer("Barclays");
        this.setAnnual_fee(0);
        this.setFee_waived_first_year(false);
        this.setPoints_program("arrival");
        this.setSpend_bonus(20000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(0);
        this.setChip("No");
        this.setNotes("You may be approved for a Platinum card " +
				"instead of World Mastercard (the standard Barclays warning. However, in this case, I could not find any material difference *in the offer.* " +
				"Now, Platinum cardholders will not have access to World Elite Mastercard benefits (e.g. hotel promotions worldwide), but everyone will " +
				"receive 20,000 bonus points and no annual fee. VICTORY!!!");
        this.setUrl("http://thepointsguy.com/go/ArrivalNoFeeTD/");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");
        this.setImage(2);
    }

    public Card getCard() {
        return this;
    }
}