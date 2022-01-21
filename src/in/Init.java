package in;

public class Init extends InPacket {

	protected Init(byte[] data) {
		super(data); //does not contain any data, the main information (ip address of game) is handled in the server
	}

}
