package util;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {

	private static final long serialVersionUID = 1L;

	public enum Rank {TWO, THREE, FOUR, 
		FIVE, SIX, SEVEN, 
		EIGHT, NINE, TEN, 
		JACK, QUEEN, KING, ACE}; //Put variable each number in Rank
	public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES};//Put four marks in Suit

	private Rank rank;//set variable Rank as rank
	private Suit suit;//set variable Suit as suit
	
	public Card(Rank rank, Suit suit) { //Get rank and suit to set card
		this.rank = rank;
		this.suit = suit;
	}
	
	public Suit getSuit() { //Method to get suit
		return suit;
	}
	
	public Rank getRank() { //Method to get Rank
		return rank;
	}
	
	public int getValue() { //method to get Value
		return rank.ordinal() + 2;
	}
	
	public String toString() {
		return String.format("%s of %s\n", rank, suit);
	}

	@Override
	public int compareTo(Card other) { //sort by suit, then by rank, ascending
		return (this.suit.ordinal() - other.suit.ordinal()) * 13 
				+ (this.rank.ordinal() - other.rank.ordinal());
	}
}
