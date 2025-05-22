package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class represents giving retail items a price and putting them into a map
 * to quickly find and take the item and its given price
 * @author Nigel Barreras cocs2436 1PM - 2:45PM
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a pricing category that manages retail item prices and tax status.
 * This class maintains a collection of retail items with their associated prices
 * and provides lookup functionality while handling tax considerations.
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see MissingItemException
 */
public class PricingCategory {
    /** Taxable status of this pricing category (immutable) */
    private final boolean taxable;
    
    /** Map storing retail items and their corresponding prices */
    private final Map<RetailItem, Double> itemPrices = new HashMap<>();

    /**
     * Constructs a new PricingCategory with the specified tax status.
     * 
     * @param taxable true if items in this category are subject to tax, false otherwise
     */
    public PricingCategory(boolean taxable) {
        this.taxable = taxable;
    }

    /**
     * Returns the taxable status of this pricing category.
     * 
     * @return true if items in this category are taxable, false otherwise
     */
    public boolean isTaxable() {
        return taxable;
    }

    /**
     * Adds a retail item with its associated price to this category.
     * 
     * @param item the retail item to add (cannot be null)
     * @param price the price of the item (must be positive)
     * @throws IllegalArgumentException if item is null or price is not positive
     * @throws NullPointerException if price is null
     */
    public void addItem(RetailItem item, Double price) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (price == null) {
            throw new NullPointerException("Price cannot be null");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        itemPrices.put(item, price);
    }

    /**
     * Looks up and returns the price of the specified retail item.
     * 
     * @param item the retail item to look up (cannot be null)
     * @return the price associated with the item
     * @throws MissingItemException if the item is not found in this category
     * @throws IllegalArgumentException if item is null
     */
    public double lookUp(RetailItem item) throws MissingItemException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (!itemPrices.containsKey(item)) {
            throw new MissingItemException(item.getName());
        }
        return itemPrices.get(item);
    }

    /**
     * Returns the number of items in this pricing category.
     * 
     * @return the number of items in this category
     */
    public int size() {
        return itemPrices.size();
    }

    /**
     * Checks if this pricing category contains any items.
     * 
     * @return true if the category is empty, false otherwise
     */
    public boolean isEmpty() {
        return itemPrices.isEmpty();
    }
}
