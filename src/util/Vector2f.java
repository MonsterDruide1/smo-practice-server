package util;

import java.nio.ByteOrder;

public class Vector2f {
	
	public float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	private Vector2f(Vector2f copy) {
		x = copy.x;
		y = copy.y;
	}
	
	@Override
	public Vector2f clone() {
		return new Vector2f(this);
	}
	
	public byte[] getData() {
		byte[] xb = ByteConversions.fromFloat(x, ByteOrder.LITTLE_ENDIAN);
		byte[] yb = ByteConversions.fromFloat(y, ByteOrder.LITTLE_ENDIAN);
		
		return Util.mergeArrays(xb, yb);
	}

}
