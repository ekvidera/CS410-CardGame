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


/*
 *  This class is for GUI Main Menu which use can choose "Start Game," "Rule," "Credit," and "Rules"
 */

public class MainMenu extends JFrame {
	

	JButton newGameButton; //Button to start the Card Game
	JButton quitGameButton; //Button to quit this application
	JButton rulesButton; //Button to show rule PDF file
	JButton creditsButton; //Button to show credits

	public MainMenu(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Operation when user push "x" button
		this.setBounds(10,10,650,650); //Set the size of window
		this.setTitle("MainMenu"); //Set title which can show title bar
		this.setVisible(true);//set Visibility
		this.setSize(new Dimension(640, 420));//set default size of window
		this.setResizable(false);//setting to not change window size
//		JPanel h = new JPanel(); 
//		h.setOpaque(false);
		ButtonListener bl = new ButtonListener(); //setting clicked Button operation
		ImageIcon icon1 = new ImageIcon("resources/mainmenu/NewGame.png"); //insert image as icon1 for "New Game"
		newGameButton = new JButton(icon1);// set above image as button
		ImageIcon r_icon1 = new ImageIcon("resources/mainmenu/NewGame_H.png");//insert image as r_icon1 when user roll over the button
		newGameButton.setBounds(210, 130, 220, 70);//Set coordinate and size of button
		newGameButton.setRolloverIcon(r_icon1);//Setting when we roll over the icon
		newGameButton.addActionListener(bl);//Set button to get action

		/*
		 * Another three buttons are same procedure to put and action as above
		 */
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

		/*
		 * 
		 */


		JPanel p = new MenuPanel();//JPanel to output Menu on GUI
		p.setLayout(null); //Invalid layout manager by default in container

		p.add(newGameButton); //output new game button
		p.add(quitGameButton);//output quit game button
		p.add(rulesButton);//output rules button
		p.add(creditsButton);//output credits button

		getContentPane().add(p, BorderLayout.CENTER); //Specify where to place the component when placing it

	}


		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) { //Action method when button was pressed
				//System.out.println(e.getSource().toString());

				if(e.getSource() == newGameButton) { //When New Game Button are pressed

					InetAddress addr; //variable to get IP
					String clientName = JOptionPane.showInputDialog("Enter your nickname:"); //To let user know what string should they write 
					String addressStr = JOptionPane.showInputDialog("Enter server address");
					try {
						addr = InetAddress.getByName(addressStr); //put IP
						//System.out.println("about to initialize clients");

						new Thread(new Runnable() {

							@Override
							public void run() {
								Client c = Client.initializeClient(clientName, addr, 5000); //Nick name to be stored in c
								c.startClientGameLoop(); //To start the game
							}}).start();
						
					} catch(UnknownHostException uhe) {
						JOptionPane.showMessageDialog(null,"Could not resolve hostname '"+addressStr+"'");
					}

				}
				if(e.getSource() == quitGameButton) { //When user push quit button
					System.exit(0); //The game quit
				}
				if(e.getSource() == rulesButton) {//When user push rule button
					if (Desktop.isDesktopSupported()) {
						try {
							File myFile = new File("resources/Rules.pdf");//Show a PDF file
							Desktop.getDesktop().open(myFile);
						} catch (IOException ex) {
							// no application registered for PDFs
						}
					}
				}
				if(e.getSource() ==creditsButton) { //When credit buttons are pushed,
					System.out.println("A Team Jellyfish Production");
					String credits="Credits";
					ImageIcon iconj = new ImageIcon("resources/mainmenu/jellyfish.png");
					JOptionPane.showMessageDialog(null,"A Team Jellyfish Production \n Addison Jenness \n Evan Kvidera \n Mizuho Takayama \n Megan Wefel", credits ,JOptionPane.PLAIN_MESSAGE, iconj);
				}

			}
		}
	
}

/*
 * MenuPanel Class for show background image
 */

class MenuPanel extends JPanel {
	Image backgroundImage;//variable to insert background image

	MenuPanel() {
		backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/mainmenu/Background_withTitle.jpg"); //insert background image
		setOpaque(false); //to set a transparent
	}

	@Override 
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0,0, this);//draw a background image
		super.paint(g); 
		this.setSize(new Dimension(640,400));//set size of window
	}


}