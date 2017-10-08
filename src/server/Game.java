package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import util.BasicPlayer;
import util.Card;
import util.Player;
import util.Card.Rank;
import util.Card.Suit;
import util.GameState;

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
		//System.out.println("Gameloop started");
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
			Collections.sort(hand);
			Collections.reverse(hand);
			
			sPlayers.get(p).setHand(hand);
			cardsOnTable[p] = hand.get(0);
			//System.out.println("player "+p+" has a hand");
			//System.out.println(sPlayers.get(p).getHand().toString());
		}
		//System.out.println("making gamestate");
		GameState currentState= generateGameState(player_turn);
		//System.out.println("I made a gamestate");
		for (rounds=0; rounds<17; rounds++) {
			System.out.println("Round "+(rounds+1));
			for(int p=0; p<3; p++) {
				System.out.println("Player turn "+p);
				for(int i=0; i<3;i++) {
					sPlayers.get(i).setGameState(generateGameState(i));
					sPlayers.get(i).sendGameState();
					//System.out.println("I sent this game state to player "+i+" "+sPlayers.get(i).getName());
				}
				ServerPlayer currentPlayer = sPlayers.get(player_turn);
				currentPlayer.receiveGameState();
				currentState = currentPlayer.getGameState();
			this.IncrementTurn();
			//System.out.println("I Incremented turn");
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
		System.out.println("Lets find the winner!");
		System.out.println("Player 1 has: "+p1+"points");	
		System.out.println("Player 2 has: "+p2+"points");	
		System.out.println("Player 3 has: "+p3+"points");	
		if(p1>p2&&p1>p3) {
			System.out.println("Player 1 wins");	
			sPlayers.get(0).setGameState(generateGameState(0,GameState.Status.STATUS_GAME_WON));
			System.out.println("set player 1");
			//sPlayers.get(0).sendGameState();
			System.out.println("Sent gamestate to player 1");
			sPlayers.get(1).setGameState(generateGameState(1,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 2");
			sPlayers.get(2).setGameState(generateGameState(2,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 3");
		}
		else if(p2>p1&&p2>p3) {
			System.out.println("Player 2 wins");	
			sPlayers.get(1).setGameState(generateGameState(1,GameState.Status.STATUS_GAME_WON));
			System.out.println("set player 2");
			//sPlayers.get(1).sendGameState();
			System.out.println("Sent gamestate to player 2");
			sPlayers.get(0).setGameState(generateGameState(0,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 1");
			sPlayers.get(2).setGameState(generateGameState(2,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 3");
		}
		else if(p3>p1&&p3>p2) {
			System.out.println("Player 3 wins");	
			sPlayers.get(2).setGameState(generateGameState(2,GameState.Status.STATUS_GAME_WON));
			System.out.println("set player 3");
			sPlayers.get(2).sendGameState();
			System.out.println("Sent gamestate to player 3");
			sPlayers.get(1).setGameState(generateGameState(1,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 2");
			sPlayers.get(0).setGameState(generateGameState(0,GameState.Status.STATUS_GAME_LOST));
			System.out.println("set player 1");
		}
		else {
			sPlayers.get(0).setGameState(generateGameState(0,GameState.Status.STATUS_GAME_DISCONNECTED));
			sPlayers.get(1).setGameState(generateGameState(1,GameState.Status.STATUS_GAME_DISCONNECTED));
			sPlayers.get(2).setGameState(generateGameState(2,GameState.Status.STATUS_GAME_DISCONNECTED));
		}
		sPlayers.get(0).sendGameState();
		System.out.println("sent to player 1");
		sPlayers.get(1).sendGameState();
		System.out.println("sent to player 2");
		sPlayers.get(2).sendGameState();
		System.out.println("sent to player 3");
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
			cardsOnTable.toString();
			System.out.println(cardsOnTable.toString());
			sPlayers.get(0).addCardsWon(cardsOnTable);
			sPlayers.get(0).setRoundsWon(sPlayers.get(0).getRoundsWon()+1);	
			player_turn=0;
		}
		else if(p2Val>p1Val&&p2Val>p3Val) {
			System.out.println(cardsOnTable.toString());
			sPlayers.get(1).addCardsWon(cardsOnTable);
			sPlayers.get(1).setRoundsWon(sPlayers.get(1).getRoundsWon()+1);	
			player_turn=1;
		}
		else if(p3Val>p2Val&&p3Val>p1Val) {
			System.out.println(cardsOnTable.toString());
			sPlayers.get(2).addCardsWon(cardsOnTable);
			sPlayers.get(2).setRoundsWon(sPlayers.get(2).getRoundsWon()+1);	
			player_turn=2;
		}
		else
			generateGameState(p1pos,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p2pos,GameState.Status.STATUS_GAME_DISCONNECTED);
			generateGameState(p3pos,GameState.Status.STATUS_GAME_DISCONNECTED);
	}
	public void IncrementTurn() {
		player_turn++;
		player_turn=player_turn % 3;
	}
	
}
