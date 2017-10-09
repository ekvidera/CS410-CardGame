package client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.MainMenu.*;
import util.Card;
import util.GameState;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Component;



public class GameUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String text1;


	GamePanel gamePanel;

	public void setGameState(GameState gameState) {
		this.gamePanel.setGameState(gameState);
	}

	public GameUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10,10,650,650);
		this.setTitle("Jellyfish Card Game");
		this.setSize(new Dimension(1024, 640));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gamePanel = new GamePanel();
		gamePanel.setLayout(null);
		getContentPane().add(gamePanel);

		this.setVisible(true);
	}
}

class GamePanel extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameState gameState;
	Image backgroundImage;

	GamePanel() {
		this.setLayout(null);
		backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/GameScreen/GameBackground.png"); 
		setOpaque(false); 
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		ArrayList<Card> cardsInHand = this.gameState.getThisPlayer().getHand();
		System.out.println(cardsInHand.toString());
		for (int i=cardsInHand.size()-1; i>=0; i--) {
			System.out.println(i);
			CardButton b = new CardButton(cardsInHand.get(i));
			this.add(b, -1, -1);
			b.setLocation(140 + i*35, 380);
		}
		
		ImageIcon cardBackLeft = new ImageIcon("resources/cards/cardback2.png");
		ImageIcon cardBackRight = new ImageIcon("resources/cards/cardback3.png");
		
		int pLeftCards = this.gameState.getThisPlayer().getQtyCardsInHand();
		System.out.print(pLeftCards);
		int pRightCards = this.gameState.getThisPlayer().getQtyCardsInHand();
		
		for (int i=0; i<pLeftCards; i++) {
			JLabel cbl = new JLabel(cardBackLeft);
			cbl.setLocation(20, 30 +i*10);
			cbl.setVisible(true);
			this.add(cbl, -1, -1);
		}
		
	}

	@Override 
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0,0, this);
	}

}