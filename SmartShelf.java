package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.ArrayList;

/**
 * A smart shelf implementation that manages retail items with customer and employee operations.
 * Supports adding, removing, and sorting items using efficient algorithms. Items are maintained
 * in sorted order for quick retrieval.
 *
 * @param <T> the type of retail items this shelf can hold (must extend RetailItem)
 * @author Nigel Barreras
 * @see IShelfCustomer
 * @see IShelfEmployee
 * @see RetailItem
 */
public class SmartShelf<T extends RetailItem> implements IShelfCustomer<T>, IShelfEmployee<T> {

    /** The name/identifier of this shelf */
    private final String name;
    
    /** List of retail items stored on this shelf (maintained in sorted order) */
    private ArrayList<T> items;

    /**
     * Constructs a new SmartShelf with the specified name.
     * 
     * @param name the name/identifier of this shelf (cannot be null or empty)
     * @throws IllegalArgumentException if name is null or empty
     */
    public SmartShelf(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Shelf name cannot be null or empty");
        }
        this.name = name;
        this.items = new ArrayList<>();
    }

    /**
     * Gets the list of items on this shelf. The returned list should not be modified directly.
     * 
     * @return an unmodifiable view of the items on this shelf
     */
    protected ArrayList<T> getItems() {
        return new ArrayList<>(items); // Defensive copy
    }

    /**
     * Gets the name of this shelf.
     * 
     * @return the shelf name (never null)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Finds and removes a specific retail item from the shelf.
     * Uses a sequential search algorithm to locate the item.
     * 
     * @param name the name of the item to find (cannot be null)
     * @param type the type of the item to find (cannot be null)
     * @return the found and removed item
     * @throws OutofStockException if the item is not found
     * @throws NullPointerException if name or type is null
     */
    @Override
    public T findAndTake(String name, RetailItemType type) throws OutofStockException {
        if (name == null) throw new NullPointerException("Item name cannot be null");
        if (type == null) throw new NullPointerException("Item type cannot be null");

        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.getName().equals(name) && item.getType().equals(type)) {
                return items.remove(i);
            }
        }
        throw new OutofStockException(name);
    }

    /**
     * Adds a retail item to the shelf while maintaining sorted order.
     * Uses a modified binary search to determine the correct insertion point.
     * 
     * @param retailitem the item to add (cannot be null)
     * @throws NullPointerException if retailitem is null
     */
    @Override
    public void addItem(T retailitem) {
        if (retailitem == null) throw new NullPointerException("Item cannot be null");
        
        int position = findInsertionPoint(items, retailitem);
        items.add(position, retailitem);
    }

    /**
     * Places and sorts a collection of items on the shelf using quicksort.
     * Replaces any existing items on the shelf.
     * 
     * @param retailitemlist the items to place on the shelf (cannot be null)
     * @throws NullPointerException if retailitemlist is null
     */
    @Override
    public void placeItems(ArrayList<T> retailitemlist) {
        if (retailitemlist == null) throw new NullPointerException("Item list cannot be null");
        
        this.items = new ArrayList<>(retailitemlist);
        quickSort(this.items);
    }

    /**
     * Returns a string representation of the shelf's contents.
     * 
     * @return a string listing all items on the shelf
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shelf '").append(name).append("' contains:\n");
        for (T item : items) {
            builder.append("- ").append(item.getName())
                  .append(" (").append(item.getType()).append(")\n");
        }
        return builder.toString();
    }

    /* ========== PRIVATE HELPER METHODS ========== */

    /**
     * Finds the insertion point for a new item using binary search.
     * 
     * @param list the sorted list to search
     * @param target the item to find position for
     * @return the index where the item should be inserted
     */
    private static <T extends RetailItem> int findInsertionPoint(ArrayList<T> list, T target) {
        return binarySearchInsertionPoint(list, target, 0, list.size() - 1);
    }

    private static <T extends RetailItem> int binarySearchInsertionPoint(
            ArrayList<T> list, T target, int low, int high) {
        if (low > high) return low;

        int mid = low + (high - low) / 2;
        int comparison = list.get(mid).compareTo(target);

        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearchInsertionPoint(list, target, mid + 1, high);
        } else {
            return binarySearchInsertionPoint(list, target, low, mid - 1);
        }
    }

    /**
     * Sorts the list using quicksort algorithm.
     * 
     * @param list the list to sort
     */
    private static <T extends RetailItem> void quickSort(ArrayList<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static <T extends RetailItem> void quickSort(ArrayList<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static <T extends RetailItem> int partition(ArrayList<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(ArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}


	

