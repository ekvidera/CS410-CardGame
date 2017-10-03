package util;

import java.util.ArrayList;

public class Player extends BasicPlayer {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Card> hand;
	
	public Player(String name) {
		super(name);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public void addCardsWon(ArrayList<Card> cardsWon) {
		this.cardsWon.addAll(cardsWon);
	}

}
