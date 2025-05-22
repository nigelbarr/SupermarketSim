package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.ArrayList;

/**
 * Interface defining shelf operations available to employees in a supermarket system.
 * Extends the base {@link IShelf} interface with employee-specific inventory management
 * functionality including adding items and bulk placement with sorting.
 *
 * @param <T> the type of retail items this shelf can hold, must extend {@link RetailItem}
 * @author Nigel Barreras
 * @class COSC 2436
 * @section 1PM - 2:45PM
 * @version 1.0
 * @see IShelf
 * @see RetailItem
 */
public interface IShelfEmployee <T extends RetailItem> extends IShelf<T>{
	   /**
     * Adds a single retail item to the shelf while maintaining the shelf's sorted order.
     * The implementation should preserve any existing organizational scheme (e.g., alphabetical,
     * by price, or other criteria).
     *
     * @param retailitem the retail item to add to the shelf (cannot be null)
     * @throws IllegalArgumentException if retailitem is null
     * @throws IllegalStateException if the shelf cannot accept additional items
     *
     * @implNote Implementations should:
     * <ul>
     *   <li>Maintain the shelf's sorting order during insertion</li>
     *   <li>Update inventory records accordingly</li>
     *   <li>Handle concurrent modification safely</li>
     * </ul>
     */
	void addItem(T retailitem);

	  /**
     * Places a collection of retail items onto the shelf and sorts them using quicksort.
     * This is typically used for initial stocking or restocking operations.
     *
     * @param retailitemlist ArrayList of unsorted items to place on the shelf (cannot be null)
     * @throws IllegalArgumentException if retailitemlist is null or contains null elements
     *
     * @implSpec The implementation must:
     * <ul>
     *   <li>Use an efficient sorting algorithm (typically quicksort)</li>
     *   <li>Clear any existing items before placing new ones (unless implementing incremental restocking)</li>
     *   <li>Maintain thread safety during the operation</li>
     * </ul>
     */
	void placeItems(ArrayList<T> retailitemlist);
}
