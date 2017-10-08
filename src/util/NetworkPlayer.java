package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkPlayer extends Player {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected transient GameState gameState;
	protected transient DatagramSocket socket;
	protected transient InetAddress destAddr;
	protected transient int destPort;

	
	protected NetworkPlayer(String name, DatagramSocket socket, InetAddress destAddr, int destPort) {
		super(name);
		this.socket = socket;
		this.destAddr = destAddr;
		this.destPort = destPort;
	}
	
	public void sendGameState() {
		sendData(this.gameState, this.socket, destAddr, destPort);
	}
	
	public void receiveGameState() {
		Object dataObj = receiveData(this.socket);
		GameState gState = (GameState) dataObj;
		this.gameState = gState;
		this.setHand(gameState.getThisPlayer().getHand());
	}
	
	public void setGameState(GameState game) {
		this.gameState = game;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	protected static DatagramPacket receivePacket(DatagramSocket socket) {
		byte[] dataBuffer = new byte[20000];
		DatagramPacket receivePacket = new DatagramPacket(dataBuffer, dataBuffer.length);
		try {
			socket.receive(receivePacket);
			return receivePacket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	protected static void sendData(Object dataObject, DatagramSocket socket, InetAddress addr, int port) {
		try {
			ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
			ObjectOutputStream objOutStream = new ObjectOutputStream(bOutStream);
			objOutStream.writeObject(dataObject);
			byte[] data = bOutStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, addr, port);
			socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static Object receiveData(DatagramSocket socket) {
		try {
			DatagramPacket recvPacket = receivePacket(socket);
			byte[] data = recvPacket.getData();
			ByteArrayInputStream bInStream = new ByteArrayInputStream(data);
			ObjectInputStream ObjInStream = new ObjectInputStream(bInStream);
			return ObjInStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
