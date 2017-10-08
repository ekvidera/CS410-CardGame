package util;

import java.io.Serializable;
import java.util.ArrayList;

public class BasicPlayer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int roundsWon;
	private int qtyCardsInHand;
	protected ArrayList<Card> cardsWon;
	
	public BasicPlayer(String name) {
		this.name = name;
	}
	
	public int getRoundsWon() {
		return roundsWon;
	}

	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}

	public int getQtyCardsInHand() {
		return qtyCardsInHand;
	}

	public void setQtyCardsInHand(int qtyCardsInHand) {
		this.qtyCardsInHand = qtyCardsInHand;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getCardsWon() {
		return cardsWon;
	}
	
}
