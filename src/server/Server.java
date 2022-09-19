package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import in.InPacket;
import in.Init;
import in.Log;
import in.ParseException;
import out.OutPacket;

public class Server {

	int clientPort, serverPort;
	DatagramSocket socket; //server
	InetAddress client; //instance of SMO
	
	private volatile boolean keepRunning;
	
	public Server(int clientPort, int serverPort) {
		this.clientPort = clientPort;
		this.serverPort = serverPort;
	}
	
	public Thread startLoopThread() {
		Thread thread = new Thread(() -> {
			try {
				startLoop();
			} catch (SocketException e) {
				System.err.println("Unable to start server:");
				e.printStackTrace();
			}
		});
		thread.start();
		return thread;
	}
	
	public void startLoop() throws SocketException {
		socket = new DatagramSocket(serverPort);
		keepRunning = true;
		
		try {
			System.out.println("Server started.");
			while(keepRunning) {
				receiveLoop();
			}
		} catch(IOException e) {
			if(!keepRunning) { // expected "crash" as shutdown
				System.out.println("Server stopped.");
			} else {
				System.err.println("Server crashed.");
				e.printStackTrace();
			}
		}
		
		stopLoopThread();
	}
	
	// might be executed twice - manually, then from above (end of startLoop)
	public void stopLoopThread() {
		keepRunning = false;
		client = null;
		if(socket != null)
			socket.close();
		socket = null;
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
		} else if(packet instanceof Log) {
			Log log = (Log) packet;
			System.out.println("["+log.getLogType().toString()+"]: "+log.getMessage());
		} else {
			System.err.println("Unhandled packet type: "+packet.getClass().getName());
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
		if(!isClientConnected()) throw new IllegalStateException("No client currently connected");
		
		DatagramPacket packet = new DatagramPacket(data, data.length, client, clientPort);
		socket.send(packet);
		
		/*for(byte b : data)
			System.out.print((b&0xff)+",");
		System.out.println();*/
	}
	
	public boolean isClientConnected() {
		return client != null;
	}
	
}
