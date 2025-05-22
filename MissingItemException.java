package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Exception thrown when a required retail item cannot be found in the expected location.
 * Typically occurs during checkout or inventory operations when an item reference is invalid.
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see ShoppingCart
 * @see Checkout
 */
public class MissingItemException extends Exception {

	 /**
     * Unique identifier for serialization/deserialization compatibility.
     * @see java.io.Serializable
     */
	private static final long serialVersionUID = -4851821865574389089L;
	
	  /**
     * Constructs a new MissingItemException with the name of the missing item.
     * Outputs the error message to standard output.
     * 
     * @param name the name of the missing retail item (cannot be null or empty)
     */
	public MissingItemException(String name) {
		System.out.println(name + " is not found");
	}
	
    /**
     * Constructs a new MissingItemException with a detailed message.
     * 
     * @param message the detailed error message (cannot be null)
     */
    public MissingItemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new MissingItemException with chained exception.
     * 
     * @param message the detailed error message (cannot be null)
     * @param cause the underlying cause of this exception (can be null)
     */
    public MissingItemException(Throwable cause) {
        super(cause);
    }

}
