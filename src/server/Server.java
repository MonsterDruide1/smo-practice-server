package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import in.InPacket;
import in.Init;
import in.ParseException;
import out.OutPacket;

public class Server {

	int clientPort;
	DatagramSocket socket; //server
	InetAddress client; //instance of SMO
	
	public Server(int clientPort, int serverPort) throws SocketException {
		socket = new DatagramSocket(serverPort);
		this.clientPort = clientPort;
	}
	
	public void startLoopThread() {
		new Thread(() -> startLoop()).start();
	}
	
	public void startLoop() {
		try {
			System.out.println("Server started.");
			while(true) {
				receiveLoop();
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Server crashed.");
		}
	}
	
	private void receiveLoop() throws IOException {
		byte[] buf = new byte[0x1000];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		
		try {
			handlePacket(packet);
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void handlePacket(DatagramPacket datagramPacket) throws ParseException {
		InPacket packet = InPacket.parse(datagramPacket.getData());
		
		if(packet instanceof Init) {
			client = datagramPacket.getAddress();
			System.out.println("Client connected from "+client.getHostAddress());
		}
	}
	
	public void sendPackets(OutPacket[] packets) throws IOException, InterruptedException {
		for(OutPacket packet : packets) {
			sendData(packet.getData());
			Thread.sleep(32);
		}
	}
	public void sendPacket(OutPacket packet) throws IOException {
		sendData(packet.getData());
	}
	
	private void sendData(byte[] data) throws IOException {
		if(client == null) throw new IllegalStateException("No client currently connected");
		DatagramPacket packet = new DatagramPacket(data, data.length, client, clientPort);
		socket.send(packet);
		
		/*for(byte b : data)
			System.out.print((b&0xff)+",");
		System.out.println();*/
	}
	
}
