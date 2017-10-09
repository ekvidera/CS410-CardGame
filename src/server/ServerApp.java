package server;

import java.net.SocketException;

public class ServerApp {

	public static void main(String[] args) {
		Server server = new Server();

	
			try {
				server.initializePlayers();
				server.startGame();
			} catch (SocketException e) {
				e.printStackTrace();
			} 
		}

	}

