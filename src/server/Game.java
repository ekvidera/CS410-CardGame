package server;

import java.util.ArrayList;
import java.util.Random;

import util.Card;
import util.Player;
import util.Card.Rank;
import util.Card.Suit;
import util.GameState;

public class Game {
	public static final int PLAYERS_NEEDED = 3;
	private ArrayList<ServerPlayer> sPlayers = new ArrayList<ServerPlayer>();
	public int rounds;
	protected GameState gState= new GameState();
	private int player_turn=0;//player one
	private int p1pos=0;
	private int p2pos=1;
	private int p3pos=2;
	public Game(ArrayList<ServerPlayer> serverPlayers)
	{
		sPlayers = serverPlayers;
	}
	
	public void GameLoop() {

		for (rounds=0; rounds<17; rounds++) {
			for(int i=0; i<3; i++) {
			sPlayers.get(player_turn).notifyAndAwaitPlayerTurn();
			this.IncrementTurn();
			}
			this.FindWinner();
		}
		this.FindWinnerFinal();
		
	}
	public void FindWinnerFinal() {
		int p1=sPlayers.get(0).getRoundsWon();
		int p2=sPlayers.get(1).getRoundsWon();
		int p3=sPlayers.get(2).getRoundsWon();
		if(p1>p2&&p1>p3) {
			gState.Status = STATUS_GAME_ENDED;
			return ;
		}
		else if(p2>p1&&p2>p3) {
			gState.Status = STATUS_GAME_ENDED;
			return ;
		}
		else if(p3>p1&&p3>p2) {
			gState.Status = STATUS_GAME_ENDED;
			return ;
		}
		else
			gState.Status = STATUS_GAME_ENDED;
	}
	public void FindWinner() {
		Card p1=gState.cardsOnTable[0];
		Card p2=gState.cardsOnTable[1];
		Card p3=gState.cardsOnTable[2];
		
		
		if(p1.getValue()>p2.getValue()&&p1.getValue()>p3.getValue()) {
			sPlayers.get(p1pos).addCardsWon(gState.cardsOnTable<>);
			sPlayers.get(p1pos).setRoundsWon(sPlayers.get(p1pos).getRoundsWon()+1);	
			player_turn=p1pos;
		}
		else if(p2.getValue()>p1.getValue()&&p2.getValue()>p3.getValue()) {
			sPlayers.get(p2pos).addCardsWon(gState.cardsOnTable<>);
			sPlayers.get(p2pos).setRoundsWon(sPlayers.get(p2pos).getRoundsWon()+1);	
			player_turn=p2pos;
		}
		else if(p3.getValue()>p2.getValue()&&p3.getValue()>p1.getValue()) {
			sPlayers.get(p3pos).addCardsWon(gState.cardsOnTable<>);
			sPlayers.get(p3pos).setRoundsWon(sPlayers.get(p3pos).getRoundsWon()+1);	
			player_turn=p3pos;
		}
		else
			gState.Status = STATUS_GAME_ENDED;
	}
	public void IncrementTurn() {
		player_turn++;
		player_turn=player_turn % 3;
		p1pos++;
		p1pos=p1pos %3;
		p2pos++;
		p2pos=p2pos %3;
		p3pos++;
		p3pos=p3pos %3;
	}
	
}
