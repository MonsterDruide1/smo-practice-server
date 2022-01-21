package server;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

import in.ParseException;
import out.ChangePage;
import out.OutPacket;
import out.PlayerGo;
import out.PlayerTeleport;
import util.Util;

public class Main {
	
	public static Server server;
	
	public static void main(String[] args) throws SocketException {
		server = new Server(7901, 7902);
		server.startLoopThread();
		new Main().startLoop();
	}
	
	Scanner console;
	
	public Main() {
		console = new Scanner(System.in);
	}
	
	public void startLoop() {
		try {
			while(true) {
				consoleLoop();
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Console crashed.");
		}
	}
	
	private void consoleLoop() throws IOException {
		String line;
		while ((line = console.nextLine()) != null) {
			try {
				OutPacket[] packet = parse(line);
				server.sendPackets(packet);
			} catch(ParseException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static OutPacket[] parse(String command) {
		String[] parts = command.split("\\s");
		return switch(parts[0]) {
		case "tp" -> {
			if(parts.length != 4)
				throw new ParseException("3 arguments have to be supplied. Usage: tp <x> <y> <z>");
			
			try {
				float x = Float.parseFloat(parts[1]);
				float y = Float.parseFloat(parts[2]);
				float z = Float.parseFloat(parts[3]);
				yield new OutPacket[]{new PlayerTeleport(x, y, z)};
			} catch(NumberFormatException e) {
				throw new ParseException(e);
			}
		}
		case "go" -> {
			if(parts.length < 2 || parts.length > 5)
				throw new ParseException("1-4 arguments have to be supplied. Usage: go <stage name> <scenario> <entrance> <start script (true:false)>");
			
			try {
				String stageName = parts[1];
				int scenario = parts.length > 2 ? Integer.parseInt(parts[2]) : -1;
				String entrance = parts.length > 3 ? parts[3] : "start";
				boolean startScript = parts.length > 4 ? parts[4].equals("true") : false;
				
				if(scenario == 0)
					throw new ParseException("Scenario cannot be 0");
				if(!Arrays.stream(PlayerGo.STAGES).anyMatch(stageName::equals))
					throw new ParseException("Stage does not exist");
				
				yield new OutPacket[]{new PlayerGo(stageName, scenario, entrance, startScript)};
			} catch(NumberFormatException e) {
				throw new ParseException(e);
			}
		}
		case "script" -> {
			if(parts.length != 2)
				throw new ParseException("1 argument has to be supplied. Usage: script <file>");
			
			try {
				yield Util.sendScript(parts[1]);
			} catch (IOException e) {
				throw new ParseException(e);
			}
		}
		case "page" -> {
			if(parts.length != 2)
				throw new ParseException("1 argument has to be supplied. Usage: script <file>");
			
			try {
				yield new OutPacket[]{new ChangePage(Byte.parseByte(parts[1]))};
			} catch (NumberFormatException e) {
				throw new ParseException(e);
			}
		}
		case "exit", "quit" -> {
			System.exit(0);
			yield null;
		}
		default -> throw new ParseException("Unknown command type: "+parts[0]);
		};
	}

}
