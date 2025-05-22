package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Interface defining shelf operations available to customers in a supermarket system.
 * Extends the base {@link IShelf} interface with customer-specific item retrieval functionality.
 * 
 * @param <T> the type of retail items this shelf can hold, must extend {@link RetailItem}
 * @author Nigel Barreras
 * @class COSC 2436
 * @section 1PM - 2:45PM
 * @version 1.0
 * @see IShelf
 * @see RetailItem
 */
public interface IShelfCustomer<T extends RetailItem> extends IShelf<T>  {
	
	/**
     * Finds and removes a specific retail item from the shelf for customer purchase.
     * Implements a modified sequential search algorithm to locate the item while
     * maintaining inventory consistency.
     * 
     * @param name the name of the retail item to retrieve (case-sensitive, cannot be null or empty)
     * @param type the category/type of the retail item (cannot be null)
     * @return the found retail item (never null)
     * @throws OutofStockException if the requested item is not available on the shelf
     * @throws IllegalArgumentException if name is null/empty or type is null
     * 
     * @implNote Implementations should:
     * <ul>
     *   <li>Maintain thread safety for concurrent access</li>
     *   <li>Preserve shelf organization after removal</li>
     *   <li>Update inventory counts appropriately</li>
     * </ul>
     */
	public T findAndTake(String name, RetailItemType type) throws OutofStockException;

}
