package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.Card;
import util.GameState;
import util.NetworkPlayer;

public class Client extends NetworkPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int selectedCard=0;
	private Client(String name, DatagramSocket socket, InetAddress srvConnectionAddr, int srvConnectionPort) {
		super(name, socket, srvConnectionAddr, srvConnectionPort);
	}
	
	public void startClientGameLoop() {
		GameUI board = new GameUI(this);
		board.setTitle(getName());
		while (true) {
			System.out.println("Waiting for gamestate");
			receiveGameState();
			System.out.println("reciving gamestate");
			board.setGameState(this.gameState);
			board.revalidate();
			board.repaint();
			System.out.println("repaint board");
			switch(this.getGameState().getStatus()) {
			case STATUS_YOUR_TURN:
				System.out.println(this.getName()+"It's my turn");
				board.gamePanel.showMessage("Your turn!");
				for (JButton button : board.gamePanel.buttons) {
					button.setEnabled(true);
				}
				break;
			case STATUS_OTHER_PLAYERS_TURN:
				System.out.println(this.getName()+" It's not my turn");
				if (this.gameState.getPlayerTurn() == 0) {
					board.gamePanel.showMessage(this.gameState.getLeftPlayer().getName() + "'s turn");
				} else {
					board.gamePanel.showMessage(this.gameState.getRightPlayer().getName() + "'s turn");
				}
				break;
			case STATUS_GAME_WON:
				board.gamePanel.showMessage("You won!");
				//System.exit(0);
				break;
			case STATUS_GAME_LOST:
				System.out.println(this.getName()+"I lost");
				board.gamePanel.showMessage("You lost!");
				break;
			case STATUS_GAME_TIED:
				board.gamePanel.showMessage("It's a tie.");
				System.out.println(this.getName()+"I tied");
				break;
			case STATUS_GAME_DISCONNECTED:
				JOptionPane.showMessageDialog(null, "Game Disconnected.");
				MainMenu mainmenu = new MainMenu();
				mainmenu.setVisible(true);
				break;
			default:
				break;
			}
		}
	}
	
	public void playCard(Card card) {
		this.getHand().remove(card);
		this.gameState.setCardsOnTable(card);
		this.sendGameState();
	}
	
	public boolean canPlayCard(Card card) {
		Card firstCard = null;
		Card[] table = this.gameState.getCardsOnTable();
		for (int i=2; i<4; i++) {
			if(table[i % 3] != null) {
				firstCard = table[i % 3];
			}
		}
		
		if (firstCard == null) return true;
		else {
			Card.Suit suitToMatch = firstCard.getSuit();
			
			if (card.getSuit() == suitToMatch) {
				return true;
			} else {
				for (Card c : this.getHand()) {
					if (c.getSuit() == suitToMatch) {
						return false;
					}
				}
				return true;
			}
		}
	}
	
	public static Client initializeClient(String name, InetAddress serverAddr, int serverPort) {
		System.out.printf("Client %s: initializing\n", name);
		try {
			
			DatagramSocket socket = new DatagramSocket();
			
			System.out.printf("Client %s: sending client data to server\n", name);
			sendData(name, socket, serverAddr, serverPort);
			
			System.out.printf("Client %s: Awaiting server acknowledgement\n", name);
			byte[] serverResponse = new byte[100];
			DatagramPacket recvServerResponse = 
			new DatagramPacket(serverResponse, serverResponse.length);
			socket.receive(recvServerResponse);
			
			InetAddress addr = recvServerResponse.getAddress();
			int port = recvServerResponse.getPort();
			ByteArrayInputStream bInStream = new ByteArrayInputStream(recvServerResponse.getData());
			ObjectInputStream ObjInStream = new ObjectInputStream(bInStream);
			String msg = (String) ObjInStream.readObject();
			System.out.printf("Client %s: Received server response: %s\n", name, msg);
			
			if (msg.equals("connectionOK")) {
				System.out.printf("Client %s: Connection made with server %s on port %d\n", name, addr.toString(), port);
				return new Client(name, socket, addr, port);				
			} else {
				System.out.println("Received bad server response");
				return null;
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
