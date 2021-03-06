package util;

import java.io.Serializable;

/*
 * 
 */

public class GameState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Status {
		STATUS_YOUR_TURN,
		STATUS_OTHER_PLAYERS_TURN,
		STATUS_ROUND_END,
		STATUS_GAME_WON,
		STATUS_GAME_LOST,
		STATUS_GAME_TIED,
		STATUS_GAME_DISCONNECTED,
	}
	
	private Player thisPlayer;
	private BasicPlayer leftPlayer;
	private BasicPlayer rightPlayer;

	//cards on table in order:
	//leftPlayer's card, thisPlayer's card, rightPlayer's card
	private Card[] cardsOnTable;
	private Status status;
	private int playerTurn; 
	
	public GameState(Player thisPlayer, BasicPlayer leftPlayer,
			BasicPlayer rightPlayer, Card[] cardsOnTable, Status status) {
		
		this.thisPlayer = thisPlayer;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.cardsOnTable = cardsOnTable;
		this.status = status;
	}
	
	public int getPlayerTurn() {
		return this.playerTurn;
	}
	
	public void setPlayerTurn(int pNum) {
		this.playerTurn = pNum;
	}
	
	public Player getThisPlayer() {
		return thisPlayer;
	}

	public BasicPlayer getLeftPlayer() {
		return leftPlayer;
	}
	
	public BasicPlayer getRightPlayer() {
		return rightPlayer;
	}

	public Card[] getCardsOnTable() {
		return cardsOnTable;
	}
	
	public void setCardsOnTable(Card newCard) {
		this.cardsOnTable[1]=newCard;
	}

	public Status getStatus() {
		return status;
	}
	
}
