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
	
	public String toString() {
		String suitStr;
		String rankStr;
		
		/*switch (suit) {
		case CLUBS:
			suitStr = "clubs";
			break;
		case DIAMONDS:
			suitStr = "diamonds";
			break;
		case HEARTS:
			suitStr = "hearts";
			break;
		case SPADES:
			suitStr = "spades";
			break;
		default:
			suitStr = "err";
		}
		
		switch (rank) {
			case JACK:
				rankStr = "jack"; 
				break;
			case QUEEN:
				rankStr = "queen";
				break;
			case KING:
				rankStr = "king";
				break;
			case ACE:
				rankStr = "ace";
				break;
			default:
				rankStr = Integer.toString(getValue());
		}*/
		
		return String.format("%s of %s\n", rank, suit);
	}
}
