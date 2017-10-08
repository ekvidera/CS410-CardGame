package test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import client.Client;
import server.Server;

public class GameTest {

	public static void main(String[] args) {
		Server server = new Server();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server.initializePlayers();
					server.startGame();
				} catch (SocketException e) {
					e.printStackTrace();
				}}}).start();
		
		InetAddress addr;
		try {
			addr = InetAddress.getByName("127.0.0.1");

			for (int i=0; i<3; i++) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Client c = Client.initializeClient("Client", addr, 5000);
						c.receiveGameState();
						System.out.println(Arrays.toString(c.getGameState().getCardsOnTable()));
					}}).start();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
