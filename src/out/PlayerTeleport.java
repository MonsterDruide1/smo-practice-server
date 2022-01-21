package out;

import util.Vector3f;

public class PlayerTeleport extends OutPacket {
	
	Vector3f pos;
	
	public PlayerTeleport(Vector3f pos) {
		this.pos = pos;
	}
	public PlayerTeleport(float x, float y, float z) {
		this(new Vector3f(x, y, z));
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.PlayerTeleport;
	}

	@Override
	public byte[] getPlainData() {
		return pos.getData();
	}

}
