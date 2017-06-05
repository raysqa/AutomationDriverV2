package raysullivan.operation;

public class AdException extends Exception {


	private static final long serialVersionUID = 1L;

	/**
	 * AutomationDriverException
	 */
	public AdException(){}
	
	/**
	 * AutomationDriverException
	 * @param message
	 */
	public AdException( String message){
		super(message);
	}
	/**
	 * AutomationDriverException
	 * @param cause
	 */
    public AdException (Throwable cause) {
        super (cause);
    }
    /**
     * AutomationDriverException
     * @param message
     * @param cause
     */
    public AdException (String message, Throwable cause) {
        super (message, cause);
    }
}
