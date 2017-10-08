package server;

import java.util.ArrayList;
import java.util.Random;

import util.BasicPlayer;
import util.Card;
import util.Player;
import util.Card.Rank;
import util.Card.Suit;
import util.GameState;
import util.GameState.Status;

public class Game {
	public static final int PLAYERS_NEEDED = 3;
	private ArrayList<ServerPlayer> sPlayers = new ArrayList<ServerPlayer>();
	public int rounds;
	private int player_turn=0;//player one
	private int p1pos=0;
	private int p2pos=1;
	private int p3pos=2;
	
	private Card[] cardsOnTable = new Card[3];
	public Game(ArrayList<ServerPlayer> serverPlayers)
	{
		sPlayers = serverPlayers;
	}

	public void GameLoop() {
		//initialize the deck and populate with one of each card
		System.out.println("Gameloop started");
		ArrayList<Card> deck = new ArrayList<>();
		for(Rank ra : Rank.values()) {
			for(Suit su :Suit.values()) {
				Card card = new Card(ra, su);
				deck.add(card);
			}
		}	
		
		Random rand = new Random();
		for (int p = 0; p<PLAYERS_NEEDED; p++) {
			
			ArrayList<Card> hand = new ArrayList<>(); 
			for(int i=0;i<17;i++)
			{
				int e=rand.nextInt(deck.size());
				hand.add(deck.get(e));
				deck.remove(e);
			}
			sPlayers.get(p).setHand(hand);
			cardsOnTable[p] = hand.get(0);
			System.out.println("player "+p+" has a hand");
			//System.out.println(sPlayers.get(p).getHand().toString());
		}
		System.out.println("making gamestate");
		GameState currentState= generateGameState(player_turn);
		System.out.println("I made a gamestate");
		for (rounds=0; rounds<17; rounds++) {
			System.out.println("Round "+(rounds+1));
			for(int p=0; p<3; p++) {
				System.out.println("Player turn "+p);
				for(int i=0; i<3;i++) {
					sPlayers.get(i).setGameState(generateGameState(i));
					sPlayers.get(i).sendGameState();
					System.out.println("I sent this game state to player "+i+" "+sPlayers.get(i).getName());
				}
				ServerPlayer currentPlayer = sPlayers.get(player_turn);
				currentPlayer.receiveGameState();
				currentState = currentPlayer.getGameState();
			this.IncrementTurn();
			System.out.println("I Incremented turn");
			}
			this.FindWinner(currentState);
		}
		this.FindWinnerFinal();
		
	}
	
	private GameState generateGameState(int playerNum) {
		if (playerNum == player_turn) {
			return generateGameState(playerNum, GameState.Status.STATUS_YOUR_TURN);		
		} else {
			return generateGameState(playerNum, GameState.Status.STATUS_OTHER_PLAYERS_TURN);
		}
	}
	
	private GameState generateGameState(int playerNum, GameState.Status status) {
		Card[] cards = new Card[3];
		cards[0] = cardsOnTable[(playerNum+1) % 3]; //left card
		cards[1] = cardsOnTable[playerNum]; //your card
		cards[2] = cardsOnTable[(playerNum+2) % 3]; //right card
		Player thisPlayer = (Player) sPlayers.get(playerNum);
		BasicPlayer leftPlayer = (BasicPlayer) sPlayers.get((playerNum + 1) % 3);
		BasicPlayer rightPlayer = (BasicPlayer) sPlayers.get((playerNum + 2) % 3);
		return new GameState(thisPlayer, leftPlayer, rightPlayer, cards, status);
	}
	
	
	
	
	public void FindWinnerFinal() {
		int p1=sPlayers.get(0).getRoundsWon();
		int p2=sPlayers.get(1).getRoundsWon();
		int p3=sPlayers.get(2).getRoundsWon();
		if(p1>p2&&p1>p3) {
			generateGameState(p1,GameState.Status.STATUS_GAME_WON);
			generateGameState(p2,GameState.Status.STATUS_GAME_LOST);
			generateGameState(p3,GameState.Status.STATUS_GAME_LOST);
		}
		else if(p2>p1&&p2>p3) {
			generateGameState(p2,GameState.Status.STATUS_GAME_WON);
			generateGameState(p1,GameState.Status.STATUS_GAME_LOST);
			generateGameState(p3,GameState.Status.STATUS_GAME_LOST);
		}
		else if(p3>p1&&p3>p2) {
			generateGameState(p3,GameState.Status.STATUS_GAME_WON);
			generateGameState(p2,GameState.Status.STATUS_GAME_LOST);
			generateGameState(p1,GameState.Status.STATUS_GAME_LOST);
		}
		else {
			generateGameState(p1,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p2,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p3,GameState.Status.STATUS_GAME_DISCONNECTED);
		}
	}
	
	
	public void FindWinner(GameState currentState) {
		
		Card[] cardsOnTable = currentState.getCardsOnTable();
		Card p1=cardsOnTable[0];
		Card p2=cardsOnTable[1];
		Card p3=cardsOnTable[2];
		int p1Val=p1.getValue();
		int p2Val=p2.getValue();
		int p3Val=p3.getValue();
		Card start=cardsOnTable[player_turn];
		if(p1.getSuit()!=start.getSuit())
			p1Val=0;
		if(p2.getSuit()!=start.getSuit())
			p2Val=0;	
		if(p3.getSuit()!=start.getSuit())
			p3Val=0;
		
		if(p1Val>p2Val&&p1Val>p3Val) {
			sPlayers.get(p1pos).addCardsWon(cardsOnTable);
			sPlayers.get(p1pos).setRoundsWon(sPlayers.get(p1pos).getRoundsWon()+1);	
			player_turn=p1pos;
		}
		else if(p2Val>p1Val&&p2Val>p3Val) {
			sPlayers.get(p2pos).addCardsWon(cardsOnTable);
			sPlayers.get(p2pos).setRoundsWon(sPlayers.get(p2pos).getRoundsWon()+1);	
			player_turn=p2pos;
		}
		else if(p3Val>p2Val&&p3Val>p1Val) {
			sPlayers.get(p3pos).addCardsWon(cardsOnTable);
			sPlayers.get(p3pos).setRoundsWon(sPlayers.get(p3pos).getRoundsWon()+1);	
			player_turn=p3pos;
		}
		else
			generateGameState(p1pos,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p2pos,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p3pos,GameState.Status.STATUS_GAME_DISCONNECTED);
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
