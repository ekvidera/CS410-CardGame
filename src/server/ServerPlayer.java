package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import util.NetworkPlayer;

//server-side representation of the player with networking methods
public class ServerPlayer extends NetworkPlayer {
	
	private static final long serialVersionUID = 1L;
	
	//constructor
	private ServerPlayer(String name, DatagramSocket serverSocket, InetAddress clientAddr, int clientPort) {		
		super(name, serverSocket, clientAddr, clientPort);
	}
	
	//await client connection, set up connection and create new ServerPlayer
	public static ServerPlayer awaitPlayerConnection(DatagramSocket socket) {
		
		//buffer for receiving data
		byte[] clientInfo = new byte[100];
		DatagramPacket recvClientInfo = 
				new DatagramPacket(clientInfo, clientInfo.length);
		
		try {
			
			//receive client information
			System.out.println("ServerPlayer: Awaiting player connection");
			socket.receive(recvClientInfo);
			
			//set port and addr to origin of client connection
			InetAddress addr = recvClientInfo.getAddress();
			int port = recvClientInfo.getPort();
			
			//parse the string sent
			ByteArrayInputStream bInStream = new ByteArrayInputStream(recvClientInfo.getData());
			ObjectInputStream ObjInStream = new ObjectInputStream(bInStream);
			String name = (String) ObjInStream.readObject();
			
			//send OK and create new ServerPlayer
			System.out.println("ServerPlayer: Client connected:" + name);
			DatagramSocket connectionSocket = new DatagramSocket();
			sendData("connectionOK", connectionSocket, addr, port);
			return new ServerPlayer(name.trim(), connectionSocket, addr, port);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
