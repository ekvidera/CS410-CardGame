package client;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.event.ActionListener;
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
	
	Client client;
	
	GamePanel gamePanel;

void renderGameState(GameState g) {
	
	
	player1=g.getLeftPlayer().getName();
	JLabel label1 = new JLabel ("player1");
	add(label1);
	label1.setBounds(10,320,10,10);
	
	player2=g.getThisPlayer().getName();
	JLabel label2 = new JLabel ("player2");
	add(label2);
	label1.setBounds(512,630,10,10);
	
	player3=g.getRightPlayer().getName();
	JLabel label3 = new JLabel ("player3");
	label1.setBounds(1014,320,10,10);
	add(label3);
}


	public void setGameState(GameState gameState) {
		gamePanel.removeAll();
		this.gamePanel.setGameState(gameState);
	}

	public GameUI(Client c){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10,10,650,650);
		this.setTitle("Jellyfish Card Game");
		this.setSize(new Dimension(1024, 640));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.client = c;

		gamePanel = new GamePanel(c);
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
	Client client;
	Image backgroundImage;
	JLabel messageLabel;
	
	
	ArrayList<JButton> buttons = new ArrayList<JButton>();

	GamePanel(Client c) {
		this.client = c;
		this.setLayout(null);
		backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/GameScreen/GameBackground.png"); 
		setOpaque(false); 
	}
	
	public void showMessage(String message) {
		this.messageLabel.setText(message);;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		this.messageLabel = new JLabel("Default message");
		messageLabel.setBounds(400, 300, 200, 100);
		messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		messageLabel.setVisible(true);
		this.add(messageLabel, -1, -1);
		ArrayList<Card> cardsInHand = this.gameState.getThisPlayer().getHand();
		System.out.println(cardsInHand.toString());
		for (int i=cardsInHand.size()-1; i>=0; i--) {
			System.out.println(i);
			CardButton b = new CardButton(cardsInHand.get(i));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(client.canPlayCard(b.getCard())) {
						client.playCard(b.getCard());
						for (JButton button : buttons) {
							button.setEnabled(false);
						}
					} else {
						showMessage("Please select a card of the leading suit");
					}
				}
			});
			
			
			this.add(b, -1, -1);
			b.setLocation(165 + i*35, 380);
		}
		
		ImageIcon cardBackLeft = new ImageIcon("resources/cards/cardback2.png");
		ImageIcon cardBackRight = new ImageIcon("resources/cards/cardback3.png");
		
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

	@Override 
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0,0, this);
	}

}