package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import out.OutPacket;
import out.script.PlayerScriptData;
import out.script.PlayerScriptInfo;
import out.script.Script;
import out.script.Script.Frame;

public class Util {
	
	public static byte[] mergeArrays(byte[]... arrays) {
		int size = 0;
		for(int i=0;i<arrays.length;i++)
			size += arrays[i].length;
		
		byte[] array = new byte[size];
		size = 0;
		for(int i=0;i<arrays.length;i++) {
			System.arraycopy(arrays[i], 0, array, size, arrays[i].length);
			size += arrays[i].length;
		}
		
		return array;
	}

	public static OutPacket[] sendScript(String file) throws IOException {
		return sendScript(new File(file), file);
	}
	public static OutPacket[] sendScript(File file, String name) throws IOException {
		return sendScript(new Script(file), name);
	}
	public static OutPacket[] sendScript(String script, String name) throws IOException {
		return sendScript(new Script(script), name);
	}
	public static OutPacket[] sendScript(Script script, String name) throws IOException {
		System.out.println("Loading script "+name+" of "+script.frames.size()+" frames");
		ArrayList<OutPacket> packets = new ArrayList<>();
		packets.add(new PlayerScriptInfo(name));
		
		ArrayList<Frame> frames = new ArrayList<>();
		for(Frame frame : script.frames) {
			frames.add(frame);
			if(frames.size() >= 1500) {
				packets.add(new PlayerScriptData(frames.toArray(new Frame[0])));
				frames.clear();
			}
		}
		
		if(frames.size() > 0)
			packets.add(new PlayerScriptData(frames.toArray(new Frame[0])));
		
		return packets.toArray(new OutPacket[0]);
	}

}
