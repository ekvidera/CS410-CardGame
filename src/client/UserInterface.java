package client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Container;


public class UserInterface extends JFrame{

public static void main(String[] args){
UserInterface frame = new UserInterface();

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(10,10,650,650);
frame.setTitle("Sample!!!");
frame.setVisible(true);
frame.setSize(new Dimension(640, 420));
frame.setResizable(false);
JPanel h = new JPanel();
h.setOpaque(false);
/*
ImageIcon icon1 = new ImageIcon("Mati.jpg");

JLabel label1 = new JLabel(icon1);

JLabel label2 = new JLabel();

h.add(label1);
*/

}


UserInterface(){
	
    ImageIcon icon1 = new ImageIcon("resources/NewGame.png");
    JButton button1 = new JButton(icon1);
    button1.setBounds(210, 130, 220, 70);
    
    ImageIcon icon3 = new ImageIcon("resources/Rules.png");
    JButton button3 = new JButton(icon3);
    button3.setBounds(210, 210, 220, 70);

    ImageIcon icon4 = new ImageIcon("resources/Credit.png");
    JButton button4 = new JButton(icon4);
    button4.setBounds(210, 290, 100, 70);
    
    ImageIcon icon2 = new ImageIcon("resources/Quit.png");
    JButton button2 = new JButton(icon2);
    button2.setBounds(330, 290, 100, 70);
	


JPanel p = new MenuPanel();
p.setLayout(null);

p.add(button1);
//p.add(Box.createRigidArea(new Dimension(5,8)));
p.add(button2);
//p.add(Box.createRigidArea(new Dimension(5,8)));
p.add(button3);
//p.add(Box.createRigidArea(new Dimension(5,8)));
p.add(button4);

getContentPane().add(p, BorderLayout.CENTER);
}
}


class MenuPanel extends JPanel {
Image backgroundImage;

MenuPanel() {
backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/Background_withTitle.jpg"); 
setOpaque(false); 
}

@Override 
public void paint(Graphics g) {
g.drawImage(backgroundImage, 0,0, this);
super.paint(g); 
this.setSize(new Dimension(640,400));
}

class NewGame implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
	}
}
class Rules implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
	}
}

class Quit implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
	}
}

class NewGam implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
	}
}

}