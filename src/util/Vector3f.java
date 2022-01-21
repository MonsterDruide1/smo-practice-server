package util;

import java.nio.ByteOrder;

public class Vector3f {
	
	public float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	private Vector3f(Vector3f copy) {
		x = copy.x;
		y = copy.y;
		z = copy.z;
	}
	
	@Override
	public Vector3f clone() {
		return new Vector3f(this);
	}
	
	public byte[] getData() {
		byte[] xb = ByteConversions.fromFloat(x, ByteOrder.LITTLE_ENDIAN);
		byte[] yb = ByteConversions.fromFloat(y, ByteOrder.LITTLE_ENDIAN);
		byte[] zb = ByteConversions.fromFloat(z, ByteOrder.LITTLE_ENDIAN);
		
		return Util.mergeArrays(xb, yb, zb);
	}

}
