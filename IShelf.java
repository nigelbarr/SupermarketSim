package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Base interface for shelf operations, serving as a super interface for both
 * {@code IShelfEmployee} and {@code IShelfCustomer}. Defines common shelf operations
 * for retail item management in a supermarket system.
 *
 * @param <T> the type of retail items this shelf can hold, must extend {@code RetailItem}
 * @author Nigel Barreras
 * @see IShelfEmployee
 * @see IShelfCustomer
 * @see RetailItem
 */interface IShelf<T> {
	 
	 /**
	     * Gets the name/identifier of this shelf.
	     * 
	     * @return the name of the shelf (never null)
	     */
	public String getName();

	 /**
     * Finds and removes a specific retail item from the shelf using a modified
     * sequential search algorithm. The implementation should maintain inventory
     * consistency when items are removed.
     *
     * @param name the name of the retail item to find (cannot be null or empty)
     * @param type the type/category of the retail item (cannot be null)
     * @return the found retail item of type T (never null)
     * @throws OutofStockException if the requested item is not available
     * @throws IllegalArgumentException if name is null/empty or type is null
     */
	T findAndTake(String name, RetailItemType type) throws OutofStockException;
}
