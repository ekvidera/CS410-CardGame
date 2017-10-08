package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import util.GameState;
import util.Player;

public class Server {
	
	public static final int NUM_OF_PLAYERS = 3;
	public static final int SERVER_PORT = 5000;
	
	private DatagramSocket socket;
	
	private ArrayList<ServerPlayer> players = new ArrayList<>();
	
	public void initializePlayers() throws SocketException {
		socket = new DatagramSocket(SERVER_PORT);
		System.out.println("Awaiting Player connection");
		players.add(ServerPlayer.awaitPlayerConnection(socket));
		System.out.println("Player connected");
	}
	
	
}
