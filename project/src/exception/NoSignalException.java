package exception;

public class NoSignalException extends RuntimeException {

	private static final long serialVersionUID = -8127061165132571336L;

	public NoSignalException() {
		super("Could not retrieve a signal");
	}	

}
