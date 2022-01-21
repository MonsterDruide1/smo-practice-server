package out.script;

import out.OutPacket;
import out.OutPacketType;
import out.script.Script.Frame;

public class PlayerScriptData extends OutPacket {
	
	Script script;
	
	public PlayerScriptData(Script script) {
		this.script = script;
	}

	public PlayerScriptData(Frame[] frames) {
		this.script = new Script(frames);
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.PlayerScriptData;
	}

	@Override
	public byte[] getPlainData() {
		return script.getData();
	}

}
