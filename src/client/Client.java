package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import util.NetworkPlayer;

public class Client extends NetworkPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Client(String name, DatagramSocket socket, InetAddress srvConnectionAddr, int srvConnectionPort) {
		super(name, socket, srvConnectionAddr, srvConnectionPort);
	}
	
	public void startClientGameLoop() {
		while (true) {
			receiveGameState();
			switch(this.getGameState().getStatus()) {
			case STATUS_YOUR_TURN:
				System.out.println(this.getName()+"It's my turn");
				this.gameState.setCardsOnTable(this.getHand().get(0));
				System.out.println("I played this card "+this.getHand().get(0));
				this.getHand().remove(0);
				this.setHand(this.getHand());
				System.out.println(this.getHand().toString());
				this.sendGameState();
				break;
			case STATUS_OTHER_PLAYERS_TURN:
				System.out.println(this.getName()+" It's not my turn");				
				break;
			case STATUS_GAME_WON:
				System.out.println(this.getName()+"I won");
				//System.exit(0);
				break;
			case STATUS_GAME_LOST:
				System.out.println(this.getName()+"I lost");

				break;
			case STATUS_GAME_TIED:
				System.out.println(this.getName()+"I tied");
				break;
			default:
				break;
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
