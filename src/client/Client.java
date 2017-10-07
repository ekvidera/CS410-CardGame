package client;

import java.net.InetAddress;

public class Client {
	
	InetAddress serverAddress;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInterface gui = new UserInterface();
		gui.display();
		System.out.println("GUI made");
	}

}
