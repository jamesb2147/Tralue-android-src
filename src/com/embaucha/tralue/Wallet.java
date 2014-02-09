package com.embaucha.tralue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Wallet {
    //array or arraylist of cards to traverse and store information
    //http://javarevisited.blogspot.com/2011/05/example-of-arraylist-in-java-tutorial.html
    ArrayList<CardInWallet> wallet;

    public Wallet() {
        //load existing cards, and fail gracefully to an empty wallet if no cards exist in the DB
    	wallet = new ArrayList<CardInWallet>();
    }

    public void addCardToWallet(Card card) {
    	wallet.add(new CardInWallet(card));
    }

    public void removeCardFromWallet(int i) {
    	
    }

    public Card getCard(int i) {
        return new Card();
    }
    
    public int size() {
    	return wallet.size();
    }

    private class CardInWallet extends Card {
        Date dateApplied;
        int minimumMonthsBetweenApplications, monthsUntilAnnualFee;
        int spend_bonus, spend_requirement, time_to_reach_spend_in_months;

        public CardInWallet() {
            super();
            dateApplied = new Date();
            minimumMonthsBetweenApplications = 12;
            monthsUntilAnnualFee = 10;
        }

        public CardInWallet(Card card) {
            dateApplied = new Date();
            monthsUntilAnnualFee = 10;
            spend_bonus = card.getSpend_bonus();
            spend_requirement = card.getSpend_requirement();
            time_to_reach_spend_in_months = card.getTime_to_reach_spend_in_months();
            
            /*
            if (card.getMinimumMonthsBetweenApplications() != -1) {
                minimumMonthsBetweenApplications = card.getMinimumMonthsBetweenApplications();
            } else {
                minimumMonthsBetweenApplications = -1;
            }
            */
        }
    }
}