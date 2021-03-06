package client;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import util.Card;

/*
 * This CardButton class is to import card image to use user interface in Game screen
 */

public class CardButton extends JButton {

	private static final long serialVersionUID = 1L;//Manage version of Serialized class
	private Card card; //Put Card for using card image
	public Card getCard() {
		return this.card; //Return card if this method was called
	}

	//constructor
	public CardButton(Card card) {
		this.card = card;
		String cardFileName = getCardFileName(card);
		this.setSize(new Dimension(125,175));
		ImageIcon cardIcon = new ImageIcon("resources/cards/"+cardFileName);
		this.setIcon(cardIcon);
		this.setDisabledIcon(cardIcon);
	}
	

	private String getCardFileName(Card card) {	//Get card's file name
		String cardFileName = "";
		
		switch (card.getRank()) { 
			case ACE:
				cardFileName+="a";
				break;
			case KING:
				cardFileName+="k";
				break;
			case QUEEN:
				cardFileName+="q";
				break;
			case JACK:
				cardFileName+="j";
				break;
			default:
				cardFileName+=card.getValue();
				break;
		}
		
		switch (card.getSuit()) { //Get suit for 4 kind of marks
			case SPADES:
				cardFileName+="s";
				break;
			case CLUBS:
				cardFileName+="c";
				break;
			case DIAMONDS:
				cardFileName+="d";
				break;
			case HEARTS:
				cardFileName+="h";
				break;
			default:
				System.err.println("suit not found");
				return null;
		}
				
		return cardFileName + ".png";  //Return the card file name with .png to get designate card image
	}
	
}
