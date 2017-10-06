package server;

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
		
	}
	
	
}
