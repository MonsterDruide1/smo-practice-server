package util;

import java.nio.ByteOrder;

public class Vector2i {
	
	public int x, y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	private Vector2i(Vector2i copy) {
		x = copy.x;
		y = copy.y;
	}
	
	@Override
	public Vector2i clone() {
		return new Vector2i(this);
	}
	
	public byte[] getData() {
		byte[] xb = ByteConversions.fromInt(x, ByteOrder.LITTLE_ENDIAN);
		byte[] yb = ByteConversions.fromInt(y, ByteOrder.LITTLE_ENDIAN);
		
		return Util.mergeArrays(xb, yb);
	}

}
