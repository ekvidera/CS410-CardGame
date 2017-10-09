package test;

import java.util.ArrayList;
import java.util.Collections;

import client.GameUI;
import util.BasicPlayer;
import util.Card;
import util.Card.Rank;
import util.Card.Suit;
import util.GameState;
import util.Player;

public class GameUITest {
	public static void main(String[] args) throws InterruptedException {
		GameUI g = new GameUI(null);
		Player p = new Player("testPlayer");
		ArrayList<Card> Cards = new ArrayList<Card>();
		Card[] cardsOnTable = new Card[] {null, new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.JACK, Suit.HEARTS)};
		
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
		GameState gs = new GameState(p, (BasicPlayer) p, (BasicPlayer) p, cardsOnTable, null);
		gs.setPlayerTurn(0);
		

		g.setGameState(gs);
		g.repaint();
		
		Thread.sleep(3000);
		
		System.out.println("REMOVING A CARD");
		p.getHand().remove(0);
		System.out.println(p.getHand().size());
		g.setGameState(gs);
		g.validate();
		g.repaint();
		System.out.println("Card removed");
	}
}
