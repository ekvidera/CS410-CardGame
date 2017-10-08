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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;



public class GameUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String text1;
    String player1;
	String player2;
	String player3;
	
	GamePanel gamePanel;

	

	public void setGameState(GameState gameState) {
		gamePanel.removeAll();
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
			b.setLocation(165 + i*35, 380);
		}
		
		
		
		ImageIcon cardBackLeft = new ImageIcon("resources/cards/cardback2.png");
		ImageIcon cardBackRight = new ImageIcon("resources/cards/cardback3.png");
		renderGameState(gameState);
		
		int pLeftCards = this.gameState.getLeftPlayer().getQtyCardsInHand();
		int pRightCards = this.gameState.getRightPlayer().getQtyCardsInHand();
		
		for (int i=0; i<pLeftCards; i++) {
			JLabel cbl = new JLabel(cardBackLeft);
			cbl.setSize(175,125);
			this.add(cbl, -1, 0);
			cbl.setLocation(50, 20 + 12*i);
			cbl.setVisible(true);
		}
		
		for(int i=0; i<pRightCards; i++) {
			JLabel cbr = new JLabel(cardBackRight);
			cbr.setSize(175,125);
			this.add(cbr, -1, 0);
			cbr.setLocation(790, 210 - 12*i);
			cbr.setVisible(true);
		}
		
		this.repaint();
		
	}


	

	public void renderGameState(GameState g) {
		
		GamePanel GamePanel = new GamePanel();
			
		String player1 = gameState.getLeftPlayer().getName();
		JLabel label1 = new JLabel (player1);
		label1.setFont(new Font("Monotype Corsiva", Font.PLAIN,23));
		label1.setForeground(Color.white);
		add(label1);
		label1.setBounds(10,290,100,150);
		
		String player2 = gameState.getThisPlayer().getName();
		JLabel label2 = new JLabel (player2);
		label2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		label2.setForeground(Color.white);
		add(label2);
		label2.setBounds(490,520,100,150);
		
		String player3 = gameState.getRightPlayer().getName();
		JLabel label3 = new JLabel (player3);
		label3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		label3.setForeground(Color.white);
		label3.setBounds(945,290,100,150);
		add(label3);
		
		
	}

	@Override 
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0,0, this);
	}

}