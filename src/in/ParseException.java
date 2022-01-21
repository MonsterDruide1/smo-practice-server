package in;

public class ParseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParseException(String arg0) {
		super(arg0);
	}
	
	public ParseException(Exception e) {
		super(e);
	}

}
