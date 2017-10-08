package client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Container;


public class MainMenu extends JFrame {



		/*
ImageIcon icon1 = new ImageIcon("Mati.jpg");

JLabel label1 = new JLabel(icon1);

JLabel label2 = new JLabel();

h.add(label1);
		 */

	

	JButton newGameButton;
	JButton quitGameButton;
	JButton rulesButton;
	JButton creditsButton;



	public MainMenu(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10,10,650,650);
		this.setTitle("MainMenu");
		this.setVisible(true);
		this.setSize(new Dimension(640, 420));
		this.setResizable(false);
		JPanel h = new JPanel();
		h.setOpaque(false);
		ButtonListener bl = new ButtonListener();
		ImageIcon icon1 = new ImageIcon("resources/mainmenu/NewGame.png");
		newGameButton = new JButton(icon1);
		ImageIcon r_icon1 = new ImageIcon("resources/mainmenu/NewGame_H.png");
		newGameButton.setBounds(210, 130, 220, 70);
		newGameButton.setRolloverIcon(r_icon1);
		newGameButton.addActionListener(bl);

		ImageIcon icon3 = new ImageIcon("resources/mainmenu/Rules.png");
		rulesButton = new JButton(icon3);
		ImageIcon r_icon3 = new ImageIcon("resources/mainmenu/Rules_H.png");
		rulesButton.setBounds(210, 210, 220, 70);
		rulesButton.setRolloverIcon(r_icon3);
		rulesButton.addActionListener(bl);

		ImageIcon icon4 = new ImageIcon("resources/mainmenu/Credit.png");
		 creditsButton = new JButton(icon4);
		ImageIcon r_icon4 = new ImageIcon("resources/mainmenu/Credit_H.png");
		creditsButton.setBounds(210, 290, 100, 70);
		creditsButton.setRolloverIcon(r_icon4);
		creditsButton.addActionListener(bl);

		ImageIcon icon2 = new ImageIcon("resources/mainmenu/Quit.png");
		quitGameButton = new JButton(icon2);
		ImageIcon r_icon2 = new ImageIcon("resources/mainmenu/Quit_H.png");
		quitGameButton.setBounds(330, 290, 100, 70);
		quitGameButton.setRolloverIcon(r_icon2);
		quitGameButton.addActionListener(bl);



		JPanel p = new MenuPanel();
		p.setLayout(null);

		p.add(newGameButton);
		//p.add(Box.createRigidArea(new Dimension(5,8)));
		p.add(quitGameButton);
		//p.add(Box.createRigidArea(new Dimension(5,8)));
		p.add(rulesButton);
		//p.add(Box.createRigidArea(new Dimension(5,8)));
		p.add(creditsButton);

		getContentPane().add(p, BorderLayout.CENTER);

	}


		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getSource().toString());
				if(e.getSource() == newGameButton) {
					//System.out.println("MAKES A NEW GAME");
					InetAddress addr;
					String clientName = JOptionPane.showInputDialog("Enter your nickname:");
					String addressStr = JOptionPane.showInputDialog("Enter server address");
					try {
						addr = InetAddress.getByName(addressStr);
						//System.out.println("about to initalize clients");
						MainMenu.this.setVisible(false);
						//GameUI board = new GameUI(); 
						Client c = Client.initializeClient(clientName, addr, 5000);
						//System.out.println("make a new gui");
					} catch(UnknownHostException uhe) {
						JOptionPane.showMessageDialog(null,"Could not resolve hostname '"+addressStr+"'");
					}

				}
				if(e.getSource() == quitGameButton) {
					System.exit(0);
				}
				if(e.getSource() == rulesButton) {
					if (Desktop.isDesktopSupported()) {
						try {
							File myFile = new File("resources/Rules.pdf");
							Desktop.getDesktop().open(myFile);
						} catch (IOException ex) {
							// no application registered for PDFs
						}
					}
				}
				if(e.getSource() ==creditsButton) {
					System.out.println("A Team Jellyfish Production");
				}

			}
		}
	
}



class MenuPanel extends JPanel {
	Image backgroundImage;

	MenuPanel() {
		backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/mainmenu/Background_withTitle.jpg"); 
		setOpaque(false); 
	}

	@Override 
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0,0, this);
		super.paint(g); 
		this.setSize(new Dimension(640,400));
	}


}