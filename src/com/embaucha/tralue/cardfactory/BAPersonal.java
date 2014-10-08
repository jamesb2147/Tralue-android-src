package com.embaucha.tralue.cardfactory;

import com.embaucha.tralue.Card;

/**
 * Created by jamesb2147 on 12/11/13.
 */
public class BAPersonal extends Card {
    Card card;

    public BAPersonal() {
        //card = new Card();

        /*
        db.execSQL("INSERT INTO providers (name, issuer, annual_fee, fee_waived_first_year, points_program, spend_bonus, spend_requirement, " +
                "time_to_reach_spend_in_months, first_purchase_bonus, points_per_dollar_spent_general_spend, foreign_transaction_fee, chip, notes, url, " +
                "business_personal, intended_audience) " +
                "VALUES('British Airways Visa Signature', 'Chase', 95, 'Yes', 'ba', 50000, 1000, 3, 0, 1.25, 0, 'Chip and signature', " +
                "'Current offer grants an additional bonus of 25k points at $10,000 spend and $20,000 spend in one year. Combined with the " +
                "standard sign-up offer, you will receive 100,000 Avios. British Airways offers family accounts to pool your miles. " +
                "At $30,000 in spend, an award certificate is issued that allows a companion to fly free on award tickets on BA metal.', " +
                "'http://www.thepointsguy.com/go/bavisatd', 'personal', 'everyone')");
        */
        System.out.println("Constructing BA card...");
        this.setName("British Airways Visa Signature");
        System.out.println("Set BA card property.");
        this.setIssuer("Chase");
        this.setAnnual_fee(95);
        this.setFee_waived_first_year(true);
        this.setPoints_program("ba");
        this.setSpend_bonus(50000);
        this.setSpend_requirement(2000);
        this.setTime_to_reach_spend_in_months(3);
        this.setFirst_purchase_bonus(0);
        this.setPoints_per_dollar_spent_general_spend((float)1.25);
        this.setForeign_transaction_fee(3);
        this.setChip("Chip and signature");
//        this.setNotes("Current offer grants an additional bonus of 25k points at $10,000 spend and $20,000 spend in one year. " +
//                "Combined with the standard sign-up offer, you will receive 100,000 Avios. British Airways offers family accounts " +
//                "to pool your miles. At $30,000 in spend, an award certificate is issued that allows a companion to fly free on award tickets on BA metal.");
        this.setNotes("At $30,000 in spend within 1 year, an award certificate is issued that allows a companion to fly free on award tickets on BA metal.");
        this.setUrl("http://bit.ly/1nWqNNc");
        this.setBusiness_personal("personal");
        this.setIntended_audience("everyone");

        System.out.println("Finished constructing BA card.");
    }

    public Card getCard() {
        return this;
    }
}
