package in;

import java.util.function.Function;

public enum InPacketType {
	
	DummyInit(-1, null),
	Init(-2, Init::new),
	Log(-3, Log::new);
	
	private byte id;
	private Function<byte[], InPacket> constructor;
	private InPacketType(byte id, Function<byte[], InPacket> constructor) {
		this.id = id;
		this.constructor = constructor;
	}
	private InPacketType(int id, Function<byte[], InPacket> constructor) { //casting to the other ctor
		this((byte)id, constructor);
	}
	
	public Function<byte[], InPacket> getConstructor() {
		return constructor;
	}
	
	public static InPacketType getById(byte id) {
		for(InPacketType type : InPacketType.values()) {
			if(type.id == id)
				return type;
		}
		return null;
	}
	
}
