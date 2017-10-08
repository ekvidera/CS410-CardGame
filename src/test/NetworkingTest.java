package test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import client.Client;
import server.Server;

public class NetworkingTest {
	public static void main(String[] args) {
		try {
			Server server = new Server();
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						server.initializePlayers();
					} catch (SocketException e) {
						e.printStackTrace();
					}}});
			t.start();

			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Client client1 = Client.initializeClient("JimBob", addr, 5000);
			Client client2 = Client.initializeClient("Jehovah", addr, 5000);
			Client client3 = Client.initializeClient("JamesRobert", addr, 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
