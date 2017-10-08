package client;

import java.io.IOException;
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
	
	private void printClientMsg(String msg) {
	
	}

	public static Client initializeClient(String name, InetAddress serverAddr, int serverPort) {
		System.out.printf("Client %s: initializing", name);
		try {
			DatagramSocket socket = new DatagramSocket();
			System.out.println("Client %s, Sending client data to server");
			sendData(name, socket, serverAddr, serverPort);
			byte[] serverResponse = new byte[100];
			DatagramPacket recvServerResponse = 
			new DatagramPacket(serverResponse, serverResponse.length);
			System.out.println("Awaiting server acknowledgement");
			socket.receive(recvServerResponse);
			InetAddress addr = recvServerResponse.getAddress();
			int port = recvServerResponse.getPort();
			String msg = new String(recvServerResponse.getData(), recvServerResponse.getOffset(), recvServerResponse.getLength());
			System.out.println("Received server response: " + msg);
			if (msg.equals("connectionOK")) {
				System.out.printf("Connection made with server %s on port %d", addr.toString(), port);
				return new Client(name, socket, addr, port);				
			} else {
				System.out.println("BAD");
				return null;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
}
