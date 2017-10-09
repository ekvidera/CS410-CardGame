package test;

import java.util.ArrayList;
import java.util.Collections;

import client.GameUI;
import util.Card;
import util.Card.Rank;
import util.Card.Suit;
import util.GameState;
import util.Player;

public class GameUITest {
	public static void main(String[] args) {
		GameUI g = new GameUI();
		Player p = new Player("testPlayer");
		ArrayList<Card> Cards = new ArrayList<Card>();
		for (Card card : new Card[] {
				new Card(Rank.ACE, Suit.SPADES),
				new Card(Rank.QUEEN, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.DIAMONDS),
				new Card(Rank.FOUR, Suit.DIAMONDS),
				new Card(Rank.JACK, Suit.CLUBS),
				new Card(Rank.ACE, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.SPADES),
				new Card(Rank.SIX, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.HEARTS),
				new Card(Rank.EIGHT, Suit.HEARTS),
				new Card(Rank.NINE, Suit.SPADES),
				new Card(Rank.FOUR, Suit.HEARTS),
				new Card(Rank.KING, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.TWO, Suit.SPADES),
				new Card(Rank.THREE, Suit.DIAMONDS),
				new Card(Rank.SEVEN, Suit.CLUBS)
		}) {
			Cards.add(card);
			Collections.sort(Cards);
		}
		p.setHand(Cards);
		GameState gs = new GameState(p, null, null, null, null);
		System.out.println();
		g.setGameState(gs);
		
	}
}
