package out;

public class ChangePage extends OutPacket {
	
	byte page;
	
	public ChangePage(byte page) {
		this.page = page;
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.ChangePage;
	}

	@Override
	public byte[] getPlainData() {
		return new byte[] {page};
	}

}
