package util;

import java.util.ArrayList;
import java.util.Random;

import util.Card.*;
public class GameServer {
	public static void main(String[] args) {
		ArrayList<Card> deck = new ArrayList();
		Player p1= new Player();
		Player p2 = new Player();
		Player p3 = new Player();


		for(Rank ra : Rank.values()) {
			for(Suit su :Suit.values()) {
				Card card = new Card(ra, su);
				deck.add(card);
			}
		}	

		Random rand = new Random();
		ArrayList<Card> hand = new ArrayList(); 
		for(int i=0;i<17;i++)
		{
			int e=rand.nextInt(deck.size());
			hand.add(deck.get(e));
			deck.remove(e);
		}
		p1.setHand(hand);
		ArrayList<Card> hand2 = new ArrayList();
		for(int i=0;i<17;i++)
		{
			int e=rand.nextInt(deck.size());
			hand2.add(deck.get(e));
			deck.remove(e);
		}
		p2.setHand(hand2);
		ArrayList<Card> hand3 = new ArrayList();
		for(int i=0;i<17;i++)
		{
			int e=rand.nextInt(deck.size());
			hand3.add(deck.get(e));
			deck.remove(e);
		}
		p3.setHand(hand3);
		System.out.print(deck.toString());
		System.out.println("Player 1");
		System.out.print(p1.getHand().toString());
		System.out.println("Player 2");
		System.out.print(p2.getHand().toString());
		System.out.println("Player 3");
		System.out.print(p3.getHand().toString());
	}
}

