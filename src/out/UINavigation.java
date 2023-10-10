package out;

import java.nio.ByteOrder;

import util.ByteConversions;

public class UINavigation extends OutPacket {

	public static UINavigation d_left = new UINavigation(1 << 12);
	public static UINavigation d_up = new UINavigation(1 << 13);
	public static UINavigation d_right = new UINavigation(1 << 14);
	public static UINavigation d_down = new UINavigation(1 << 15);
	
	long input;
	
	public UINavigation(long input) {
		this.input = input;
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.UINavigation;
	}

	@Override
	public byte[] getPlainData() {
		return ByteConversions.fromLong(input, ByteOrder.LITTLE_ENDIAN);
	}

}
