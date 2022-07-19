package in;

import java.util.Arrays;

public class Log extends InPacket {
	
	public enum LogType {
		LOG, WARNING, ERROR, FATAL;
	}
	
	private LogType logType;
	private String message;

	protected Log(byte[] data) {
		super(data);
		logType = LogType.values()[data[0]];
		message = new String(Arrays.copyOfRange(data, 1, data.length)).trim();
	}

	public LogType getLogType() {
		return logType;
	}
	public String getMessage() {
		return message;
	}

}
