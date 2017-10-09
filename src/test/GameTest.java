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
				new Thread(new Runnable() {
					@Override
					public void run() {
						Client c = Client.initializeClient("Alice", addr, 5000);
						c.startClientGameLoop();
						System.out.println(c.getName() +"'s board state");
						System.out.println(Arrays.toString(c.getGameState().getCardsOnTable()));
						System.out.println(c.getHand().toString());
					}}).start();
				new Thread(new Runnable() {
					@Override
					public void run() {
						Client c = Client.initializeClient("Bob", addr, 5000);
						c.startClientGameLoop();
						System.out.println(c.getName() +"'s board state");
						System.out.println(Arrays.toString(c.getGameState().getCardsOnTable()));
						System.out.println(c.getHand().toString());
					}}).start();
				new Thread(new Runnable() {
					@Override
					public void run() {
						Client c = Client.initializeClient("Mallory", addr, 5000);
						c.startClientGameLoop();
						System.out.println(c.getName() +"'s board state");
						System.out.println(Arrays.toString(c.getGameState().getCardsOnTable()));
						System.out.println(c.getHand().toString());
					}}).start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
