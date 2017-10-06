package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import util.Player;
import util.GameState;

public class ServerPlayer extends Player {
	
	private static final long serialVersionUID = 1L;
	
	InetAddress clientAddr;
	int clientPort;
	private DatagramSocket socket;
	
	private ServerPlayer(String name, InetAddress clientAddr, int clientPort, DatagramSocket socket) {		
		super(name);
		this.socket = socket;
		this.clientAddr = clientAddr;
		this.clientPort = clientPort;
	}
	
	public static ServerPlayer awaitPlayerConnection(DatagramSocket socket) {
		byte[] clientInfo = new byte[100];
		DatagramPacket recvClientInfo = 
				new DatagramPacket(clientInfo, clientInfo.length);
		try {
			socket.receive(recvClientInfo);
			InetAddress addr = recvClientInfo.getAddress();
			int port = recvClientInfo.getPort();
			String name = new String(recvClientInfo.getData(), 0, recvClientInfo.getLength());
			return new ServerPlayer(name.trim(), addr, port, socket);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
	
	public 
	
	void sendGameState(GameState game) {
		try {
			ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
			ObjectOutputStream objOutStream = new ObjectOutputStream(bOutStream);
			objOutStream.writeObject(game);
			byte[] data = bOutStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, clientAddr, clientPort);
			Socket.send
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameState notifyAndAwaitTurn() {
		
	}
	
	
	
}
