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
	
	public static void main(String[] args) {
		
	}




		/*
ImageIcon icon1 = new ImageIcon("Mati.jpg");

JLabel label1 = new JLabel(icon1);

JLabel label2 = new JLabel();

h.add(label1);
		 */

	

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;



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
		button1 = new JButton(icon1);
		ImageIcon r_icon1 = new ImageIcon("resources/mainmenu/NewGame_H.png");
		button1.setBounds(210, 130, 220, 70);
		button1.setRolloverIcon(r_icon1);
		button1.addActionListener(bl);

		ImageIcon icon3 = new ImageIcon("resources/mainmenu/Rules.png");
		button3 = new JButton(icon3);
		ImageIcon r_icon3 = new ImageIcon("resources/mainmenu/Rules_H.png");
		button3.setBounds(210, 210, 220, 70);
		button3.setRolloverIcon(r_icon3);
		button3.addActionListener(bl);

		ImageIcon icon4 = new ImageIcon("resources/mainmenu/Credit.png");
		 button4 = new JButton(icon4);
		ImageIcon r_icon4 = new ImageIcon("resources/mainmenu/Credit_H.png");
		button4.setBounds(210, 290, 100, 70);
		button4.setRolloverIcon(r_icon4);
		button4.addActionListener(bl);

		ImageIcon icon2 = new ImageIcon("resources/mainmenu/Quit.png");
		button2 = new JButton(icon2);
		ImageIcon r_icon2 = new ImageIcon("resources/mainmenu/Quit_H.png");
		button2.setBounds(330, 290, 100, 70);
		button2.setRolloverIcon(r_icon2);
		button2.addActionListener(bl);



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


		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getSource().toString());
				if(e.getSource() == button1) {
					System.out.println("MAKES A NEW GAME");
					InetAddress addr;
					String clientName = JOptionPane.showInputDialog("Enter your nickname:");
					String addressStr = JOptionPane.showInputDialog("Enter server address");
					try {
						addr = InetAddress.getByName(addressStr);
						Client c = Client.initializeClient(clientName, addr, 5000);
					} catch(UnknownHostException uhe) {
						JOptionPane.showMessageDialog(null,"Could not resolve hostname '"+addressStr+"'");
					}

				}
				if(e.getSource() == button2) {
					System.exit(0);
				}
				if(e.getSource() == button3) {
					if (Desktop.isDesktopSupported()) {
						try {
							File myFile = new File("resources/Rules.pdf");
							Desktop.getDesktop().open(myFile);
						} catch (IOException ex) {
							// no application registered for PDFs
						}
					}
				}
				if(e.getSource() ==button4) {
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