package out.script;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import in.ParseException;
import util.ByteConversions;
import util.Util;
import util.Vector2f;

public class Script {
	
	public static class Frame {
		Vector2f lStick, rStick;
		boolean a, b, x, y, l, r, zl, zr, plus, minus, triggerLStick, triggerRStick, dUp, dRight, dDown, dLeft;
		
		private Frame() {}
		public static Frame EMPTY = new Frame();
		
		public Frame(String[] parts) { //ignore first, as it's the line number
			String[] buttons = parts[1].split(";");
			for(String button : buttons) {
				switch(button) {
				case "KEY_A" -> a = true;
				case "KEY_B" -> b = true;
				case "KEY_X" -> x = true;
				case "KEY_Y" -> y = true;
				case "KEY_ZR" -> zr = true;
				case "KEY_ZL" -> zl = true;
				case "KEY_R" -> r = true;
				case "KEY_L" -> l = true;
				case "KEY_PLUS" -> plus = true;
				case "KEY_MINUS" -> minus = true;
				case "KEY_DLEFT" -> dLeft = true;
				case "KEY_DRIGHT" -> dRight = true;
				case "KEY_DUP" -> dUp = true;
				case "KEY_DDOWN" -> dDown = true;
				case "KEY_LSTICK" -> triggerLStick = true;
				case "KEY_RSTICK" -> triggerRStick = true;
				case "NONE" -> {}
				default -> throw new ParseException("Failed to parse buttons of script: "+button+" in frame "+parts[0]);
				}
			}
			
			String[] l = parts[2].split(";");
			lStick = new Vector2f(Integer.parseInt(l[0])/32767.f, Integer.parseInt(l[1])/32767.f);
			String[] r = parts[3].split(";");
			rStick = new Vector2f(Integer.parseInt(r[0])/32767.f, Integer.parseInt(r[1])/32767.f);
		}
		
		public byte[] getData() {
			byte[] returns = Util.mergeArrays(
					lStick.getData(), rStick.getData(),
					ByteConversions.fromBooleans(a, b, x, y, l, r, zl, zr, plus, minus, triggerLStick, triggerRStick, dUp, dRight, dDown, dLeft),
					new byte[] {0,0} //padding to 0x20 size
			);
			return returns;
		}
	}
	
	public ArrayList<Frame> frames;
	
	public Script(File file) throws IOException {
		this(Files.readAllLines(file.toPath()).toArray(new String[0]));
	}
	public Script(String data) {
		this(data.split("\\n"));
	}
	public Script(String[] data) {
		frames = new ArrayList<>();
		
		for(String line : data) {
			String[] parts = line.split(" ");
			int frameNo = Integer.parseInt(parts[0]);
			while(frameNo > frames.size()) {
				frames.add(Frame.EMPTY);
			}
			frames.add(new Frame(parts));
		}
	}
	public Script(Frame[] frames) {
		this.frames = new ArrayList<Frame>(Arrays.asList(frames));
	}
	
	public byte[] getData() {
		byte[][] data = new byte[frames.size()][];
		
		for(int i=0; i<frames.size(); i++) {
			data[i] = frames.get(i).getData();
		}
		
		return Util.mergeArrays(data);
	}

}
