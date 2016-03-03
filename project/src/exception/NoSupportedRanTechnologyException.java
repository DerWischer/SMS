package exception;

public class NoSupportedRanTechnologyException extends RuntimeException {

	private static final long serialVersionUID = 4064196453472869159L;

	public NoSupportedRanTechnologyException() {
		super("The terminals RAN Technologies do not support the selected service");
	}
	
}
