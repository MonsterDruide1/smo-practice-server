package out;

public class PlayerScriptState extends OutPacket {
	
	byte state;
	
	public PlayerScriptState(byte state) {
		this.state = state;
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.PlayerScriptState;
	}

	@Override
	public byte[] getPlainData() {
		return new byte[] {state};
	}

}
