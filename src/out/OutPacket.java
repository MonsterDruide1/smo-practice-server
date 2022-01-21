package out;

public abstract class OutPacket {
	
	public abstract OutPacketType getOutPacketType();
	public abstract byte[] getPlainData();
	
	public byte[] getData() {
		byte[] data = getPlainData();
		byte[] fullData = new byte[data.length+1];
		fullData[0] = getOutPacketType().getId();
		System.arraycopy(data, 0, fullData, 1, data.length);
		return fullData;
	}

}
