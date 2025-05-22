package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages promotional discounts for retail items, tracking active promotions,
 * their validity periods, and applicable items. Determines the best discount
 * to apply for items based on active promotions.
 *
 * @param <T> the type of retail items this promotion applies to (must extend RetailItem)
 * @author Nigel Barreras
 * @see RetailItem
 */
public class Promotions {
    /** The name/identifier of this promotion */
    private final String name;
    
    /** The discount percentage (0-100) */
    private final double discountPercent;
    
    /** The expiration date of this promotion */
    private LocalDate expireDate;
    
    /** Set of retail items included in this promotion */
    private Set<RetailItem> itemset = new HashSet<>();

    /**
     * Constructs a new Promotion with specified parameters.
     * 
     * @param name the name of the promotion (cannot be null or empty)
     * @param discountPercent the discount percentage (0-100)
     * @param expireDate the expiration date of the promotion (cannot be null)
     * @throws IllegalArgumentException if parameters are invalid
     * @throws NullPointerException if name or expireDate is null
     */
    public <T extends RetailItem> Promotions(String name, double discountPercent, LocalDate expireDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Promotion name cannot be null or empty");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        if (expireDate == null) {
            throw new NullPointerException("Expiration date cannot be null");
        }
        
        this.name = name;
        this.discountPercent = discountPercent;
        this.expireDate = expireDate;
    }

    /**
     * Gets the name of this promotion.
     * 
     * @return the promotion name (never null)
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the discount percentage of this promotion.
     * 
     * @return the discount percentage (0-100)
     */
    public double getDiscount() {
        return discountPercent;
    }

    /**
     * Gets the expiration date of this promotion.
     * 
     * @return the expiration date (never null)
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }
    
    /**
     * Checks if this promotion is currently active (not expired).
     * 
     * @return true if current date is before expiration date, false otherwise
     */
    public boolean isActive() {
        return LocalDate.now().isBefore(expireDate);
    }

    /**
     * Determines the applicable discount for a retail item if it's included
     * in this active promotion.
     * 
     * @param item the retail item to check (cannot be null)
     * @return the discount percentage if item is included and promotion is active, 0 otherwise
     * @throws IllegalArgumentException if item is null
     */
    public double determineDiscount(RetailItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        return isActive() && itemset.contains(item) ? getDiscount() : 0;
    }
    
    /**
     * Adds a retail item to this promotion.
     * 
     * @param item the retail item to add (cannot be null)
     * @return true if the item was added, false if it was already present
     * @throws IllegalArgumentException if item is null
     */
    public boolean addItem(RetailItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        return itemset.add(item);
    }
    
    /**
     * Adds a collection of retail items to this promotion.
     * 
     * @param <T> the type of retail items (must extend RetailItem)
     * @param collectionItems the items to add (cannot be null, can be empty)
     * @return true if the set changed as a result of this operation
     * @throws IllegalArgumentException if collectionItems is null
     */
    public <T extends RetailItem> boolean addAllItems(ArrayList<T> collectionItems) {
        if (collectionItems == null) {
            throw new IllegalArgumentException("Item collection cannot be null");
        }
        return itemset.addAll(collectionItems);
    }
    
    /**
     * Gets the number of items included in this promotion.
     * 
     * @return the number of items in this promotion
     */
    public int getItemCount() {
        return itemset.size();
    }
    
    /**
     * Checks if this promotion contains any items.
     * 
     * @return true if no items are included in this promotion, false otherwise
     */
    public boolean isEmpty() {
        return itemset.isEmpty();
    }
	
}
