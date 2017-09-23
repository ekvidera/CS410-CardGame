package util;

public class Card {
	enum Rank {TWO, THREE, FOUR, 
		FIVE, SIX, SEVEN, 
		EIGHT, NINE, TEN, 
		JACK, QUEEN, KING, ACE};
	enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES};

	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getValue() {
		return rank.ordinal() + 2;
	}
	
}
