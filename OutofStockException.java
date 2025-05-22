package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Exception thrown when an attempt is made to retrieve an item that is not currently
 * available in inventory. This checked exception indicates the requested item is
 * out of stock on the specified shelf.
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see IShelfCustomer
 */
public class OutofStockException extends Exception {

    /**
     * Unique identifier for serialization/deserialization compatibility.
     * @see java.io.Serializable
     */
	private static final long serialVersionUID = -986079408444705443L;

    /**
     * Constructs a new OutofStockException with the specified item name and cause.
     * 
     * @param name the name of the retail item that is out of stock (cannot be null)
     * @param cause the underlying cause of this exception (can be null)
     */
    public OutofStockException(String name, Throwable cause) {
        super(name + " is currently out of stock", cause);
    }

    /**
     * Constructs a new OutofStockException with a detailed message.
     * 
     * @param message the detailed error message (cannot be null)
     */
    public OutofStockException(String message) {
        super(message);
    }
}

