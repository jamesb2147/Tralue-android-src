package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class FlexPerksTravelRewardsBusiness extends Card {
    public FlexPerksTravelRewardsBusiness() {
        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
				"time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url" +
				", business_personal, intended_audience, image)" +
				" VALUES ('FlexPerks Business Travel Rewards card', 'US Bank', 55, 'Yes', 'usbank', 20000, 3500, 4, 0, 1, 3, 'No', " +
				"'Spend $24,000 in a year and the annual fee will be waived and you will earn 3,500 bonus FlexPoints.', " +
				"'http://thepointsguy.com/go/USBankFlexPerksVisaBusinessTD/', 'business', 'everyone', 31)");
        */

        this.setName("FlexPerks Business Travel Rewards card");
        this.setIssuer("US Bank");
        this.setAnnual_fee(55);
        this.setFee_waived_first_year(true);
        this.setPoints_program("usbank");
        this.setSpend_bonus(20000);
        this.setSpend_requirement(3500);
        this.setTime_to_reach_spend_in_months(4);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend(1);
        this.setForeign_transaction_fee(3);
        this.setChip("No");
        this.setNotes("Spend $24,000 in a year and the annual fee will be waived and you will earn 3,500 bonus FlexPoints.");
        this.setUrl("http://thepointsguy.com/go/USBankFlexPerksVisaBusinessTD/");
        this.setBusiness_personal("business");
        this.setIntended_audience("everyone");
        this.setImage(31);
    }

    public Card getCard() {
        return this;
    }
}