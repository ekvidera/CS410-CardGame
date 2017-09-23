package util;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand;
	private ArrayList<Card> cardsWon;
	private int roundsWon =0;
	private int playerNum;
	private String playerName;
	public Player() {
		hand = new ArrayList<Card>();
		cardsWon = new ArrayList<Card>();
		int roundsWon;
		int playerNum;
		String playerName;
	}
	
}
