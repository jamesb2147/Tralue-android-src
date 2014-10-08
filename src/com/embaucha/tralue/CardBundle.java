package com.embaucha.tralue;

public class CardBundle {
	Card[] cards;
	
	public CardBundle(Card card) {
		cards[0] = card;
	}
	
	public void addCard(Card card) {
		cards[cards.length] = card;
	}
	
	public int getLength() {
		return cards.length;
	}
	
	public Card getCardAtIndex(int index) {
		return cards[index];
	}
}