package server;

import java.net.InetAddress;

import util.Player;

public class ServerPlayer extends Player {
	
	private static final long serialVersionUID = 1L;
	
	InetAddress clientAddr;
	
	private ServerPlayer(String name, InetAddress clientAddr) {
		super(name);
		this.clientAddr = clientAddr;
	}
	
	ServerPlayer awaitPlayerConnection() {
		
		
		
		return new ServerPlayer();
	}
	
	
	
}
