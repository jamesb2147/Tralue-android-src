package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class PlatinumDeltaSkymilesBusiness extends Card {
    public PlatinumDeltaSkymilesBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('Platinum Delta SkyMiles Business card', 'American Express', 150, 'No', 'delta', 35000, 1000, 3, 0, 1, 2.7, 'No', " +
				"'5,000 Medallion Qualifying Miles with signup bonus, 2 miles per dollar on Delta purchases, and first bag free. In addition, " +
				"make $25,000 in purchases and earn 10,000 bonus miles and 10,000 MQMs each calendar year: make $50,000 in purchases and earn " +
				"an additional 10,000 bonus miles and 10,000 MQMs each calendar year.', " +
				"'http://thepointsguy.com/go/DeltaPlatinumBizTD/', 'business', 'everyone', 20)");
        */

        this.setName("Platinum Delta SkyMiles Business card");
        this.setIssuer("American Express");
        this.setAnnual_fee(150);
        this.setFee_waived_first_year(false);
        this.setPoints_program("delta");
        this.setSpend_bonus(35000);
        this.setSpend_requirement(1000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee((float)2.7);
        this.setChip("No");
        this.setNotes("5,000 Medallion Qualifying Miles with signup bonus, 2 miles per dollar on Delta purchases, and first bag free. In addition, " +
				"make $25,000 in purchases and earn 10,000 bonus miles and 10,000 MQMs each calendar year: make $50,000 in purchases and earn " +
				"an additional 10,000 bonus miles and 10,000 MQMs each calendar year.");
        this.setUrl("http://thepointsguy.com/go/DeltaPlatinumBizTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(20);
    }

    public Card getCard() {
        return this;
    }
}