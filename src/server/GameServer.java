package server;

import java.util.ArrayList;
import java.util.Random;

import util.*;
import util.Card.*;
public class GameServer {
	
	public static final int PLAYERS_NEEDED = 3;
	private Player[] players = new Player[PLAYERS_NEEDED];
	
	public static void main(String[] args) {
		//initialize the deck and populate with one of each card
		ArrayList<Card> deck = new ArrayList<>();
		for(Rank ra : Rank.values()) {
			for(Suit su :Suit.values()) {
				Card card = new Card(ra, su);
				deck.add(card);
			}
		}	
		
		Random rand = new Random();
		for (int p = 0; p<PLAYERS_NEEDED; p++) {
			players[p] = new Player();
			ArrayList<Card> hand = new ArrayList<>(); 
			for(int i=0;i<17;i++)
			{
				int e=rand.nextInt(deck.size());
				hand.add(deck.get(e));
				deck.remove(e);
			}
			//players[p].setHand(hand);
		}
	
	}
}

