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
		players.add(ServerPlayer.awaitPlayerConnection(socket));
		players.add(ServerPlayer.awaitPlayerConnection(socket));
		players.add(ServerPlayer.awaitPlayerConnection(socket));
	}

	public void startGame() {
		System.out.println("Initializing Players");
		System.out.println("------------");
		System.out.println("Players initialized");
		Game game = new Game(players);
		game.GameLoop();
	}



}
