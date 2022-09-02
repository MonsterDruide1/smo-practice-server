package in;

import java.nio.charset.Charset;
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
		message = new String(Arrays.copyOfRange(data, 1, data.length), Charset.forName("UTF-8")).trim();
	}

	public LogType getLogType() {
		return logType;
	}
	public String getMessage() {
		return message;
	}

}
