package in;

import java.util.Arrays;
import java.util.function.Function;

public abstract class InPacket {
	
	protected InPacket(byte[] data) {} //force subclasses to have a constructor

	public static InPacket parse(byte[] data) {
		InPacketType type = InPacketType.getById(data[0]);
		if(type == null)
			throw new ParseException("Unimplemented packet type: "+data[0]);
		byte[] plainData = Arrays.copyOfRange(data, 1, data.length);
		
		Function<byte[], InPacket> constructor = type.getConstructor();
		if(constructor == null)
			throw new ParseException("Unimplemented packet type: "+type.name());
		
		return constructor.apply(plainData);
	}

}
