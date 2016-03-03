package exception;

public class NoDataVolumeException extends RuntimeException {

	private static final long serialVersionUID = -724522403321390670L;

	public NoDataVolumeException(){
		super("Data volume is depleted");		
	}
	
}
