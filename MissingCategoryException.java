package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Exception thrown when a required pricing category for a retail item cannot be found
 * in the supermarket system. This typically occurs during checkout or price calculation
 * when an item's category isn't registered in the pricing system.
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see Checkout
 */
public class MissingCategoryException extends Exception {
	  /**
     * Unique identifier for serialization/deserialization of the exception.
     * @see java.io.Serializable
     */
	private static final long serialVersionUID = 117846501600611021L;

	 /**
     * Constructs a new MissingCategoryException with a default error message.
     * The message will be printed to standard output when this exception is thrown.
     */
	public MissingCategoryException() {
		System.out.println("Category is not found for this type of item");
	}
	
	   /**
     * Constructs a new MissingCategoryException with the specified detail message.
     * 
     * @param message the detailed error message (cannot be null)
     */
    public MissingCategoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new MissingCategoryException with the specified detail message and cause.
     * 
     * @param message the detailed error message (cannot be null)
     * @param cause the underlying cause of this exception (can be null)
     */
    public MissingCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}


