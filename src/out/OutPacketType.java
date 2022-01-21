package out;

public enum OutPacketType {
	
	PlayerScriptInfo(1),
	PlayerScriptData(2),
	PlayerTeleport(3),
	PlayerGo(4),
	ChangePage(5),
	;
	
	private byte id;
	private OutPacketType(byte id) {
		this.id = id;
	}
	private OutPacketType(int id) { //casting to the other ctor
		this((byte)id);
	}
	public byte getId() {
		return id;
	}
	
}
