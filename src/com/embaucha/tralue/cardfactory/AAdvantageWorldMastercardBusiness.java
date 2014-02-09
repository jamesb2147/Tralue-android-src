package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class AAdvantageWorldMastercardBusiness extends Card {
    public AAdvantageWorldMastercardBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('AAdvantage CitiBusiness World Mastercard', 'Citibank', 95, 'Yes', 'american', 50000, 3000, 3, 0, 1, 3, 'Chip and signature', " +
				"'This is an unofficial link from FlyerTalk.com. 2 miles per dollar spent on American Airlines purchases.', " +
				"'http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/citibusinessaatd/', 'business', 'everyone', 26)");
        */

        this.setName("AAdvantage CitiBusiness World Mastercard");
        this.setIssuer("Citibank");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("american");
        this.setSpend_bonus(50000);
        this.setSpend_requirement(3000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("Chip and signature");
        this.setNotes("This is an unofficial link from FlyerTalk.com. 2 miles per dollar spent on American Airlines purchases.");
        this.setUrl("http://thepointsguy.com/top-deals-3/www.thepointsguy.com/go/citibusinessaatd/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(26);
    }

    public Card getCard() {
        return this;
    }
}