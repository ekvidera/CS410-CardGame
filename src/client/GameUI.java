package client;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.MainMenu.*;
import util.GameState;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;



public class GameUI extends JFrame{
	
	private static final Component Component = null;
	String text1;
    String player1;
	String player2;
	String player3;
	
	//MainMenu ClientName
	//ClientName = new MainMenu();
	

public static void main(String[] args){
GameUI frame = new GameUI();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(10,10,650,650);
frame.setTitle("Jellyfish Card Game");
frame.setVisible(true);
frame.setSize(new Dimension(1024, 640));
frame.setResizable(false);
JPanel h = new JPanel();
h.setOpaque(false);


EventQueue.invokeLater(new Runnable(){
    @Override
    public void run() {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
  });

}

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

GameUI(){
    JPanel g = new JPanel();
    
    //text1 = ButtonListener.clientName;
    JLabel label = new JLabel();

    

    Container contentPane = getContentPane();
    contentPane.add(g, BorderLayout.CENTER);
    contentPane.add(label, BorderLayout.SOUTH);


JPanel p = new GamePanel();
p.setLayout(null);
getContentPane().add(p, BorderLayout.CENTER);
}
}


class GamePanel extends JPanel {
Image backgroundImage;
GamePanel() {
backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/GameScreen/GameBackground.png"); 
setOpaque(false); 
}
@Override 
public void paint(Graphics g) {
g.drawImage(backgroundImage, 0,0, this);
super.paint(g); 
this.setSize(new Dimension(1024,640));
}
	  
}