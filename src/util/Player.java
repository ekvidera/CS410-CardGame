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
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	public ArrayList<Card> getCardsWon() {
		return cardsWon;
	}
	public void setCardsWon(ArrayList<Card> cardsWon) {
		this.cardsWon.addAll(cardsWon);
	}
	public int getRoundsWon() {
		return roundsWon;
	}
	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
}
