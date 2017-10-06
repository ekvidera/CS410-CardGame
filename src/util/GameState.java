package util;

import java.io.Serializable;

public class GameState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Status status;
	
	Player thisPlayer;
	BasicPlayer leftPlayer;
	BasicPlayer rightPlayer;
	
	//cards on table in order:
	//leftPlayer's card, thisPlayer's card, rightPlayer's card
	Card[] cardsOnTable;
	
	public enum Status {
		STATUS_OK,
		STATUS_PLAYER_DISCONNECTED,
		STATUS_WAITING_FOR_PLAYERS,
		STATUS_GAME_ENDED,
	}
	
}
