package util;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {

	private static final long serialVersionUID = 1L;

	public enum Rank {TWO, THREE, FOUR, 
		FIVE, SIX, SEVEN, 
		EIGHT, NINE, TEN, 
		JACK, QUEEN, KING, ACE};
	public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES};

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
	
	public String toString() {
		return String.format("%s of %s\n", rank, suit);
	}

	@Override
	public int compareTo(Card other) { //sort by suit, then by rank, ascending
		return (this.suit.ordinal() - other.suit.ordinal()) * 13 
				- (this.rank.ordinal() - other.rank.ordinal());
	}
}
