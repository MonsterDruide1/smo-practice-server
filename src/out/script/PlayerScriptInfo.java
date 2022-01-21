package out.script;

import out.OutPacket;
import out.OutPacketType;

public class PlayerScriptInfo extends OutPacket {
	
	String name;
	
	public PlayerScriptInfo(String name) {
		this.name = name;
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.PlayerScriptInfo;
	}

	@Override
	public byte[] getPlainData() {
		return name.getBytes();
	}

}
