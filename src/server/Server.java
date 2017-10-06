package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import util.GameState;

public class Server extends GameState {
	
	public static final int NUM_OF_PLAYERS = 3;
	public static final int SERVER_PORT = 5000;
	
	private DatagramSocket socket;
	
	private ArrayList<ServerPlayer> players = new ArrayList<>();
	
	public void initializePlayers() throws SocketException {
		
		socket = new DatagramSocket(SERVER_PORT);
		byte[] clientInfo = new byte[100];
		DatagramPacket recvClientInfo = 
				new DatagramPacket(clientInfo, clientInfo.length);
		try {
			for(int i=0; i<NUM_OF_PLAYERS; i++) {
				socket.receive(recvClientInfo);
				InetAddress addr = recvClientInfo.getAddress()
			}
			System.out.printf()
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
