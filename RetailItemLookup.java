package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages pricing and tax calculations for retail items by organizing them into
 * categorized pricing groups. Each retail item type is associated with a pricing
 * category that determines its base price and tax status.
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see RetailItemType
 * @see PricingCategory
 */
public class RetailItemLookup {
    /** Map of item types to their pricing categories */
    private final Map<RetailItemType, PricingCategory> typeCategory;
    
    /** The tax rate as a decimal (e.g., 0.08 for 8%) */
    private final double taxRate;
    
    /** Current pricing category being modified */
    private PricingCategory currentPricingCategory;

    /**
     * Constructs a RetailItemLookup with specified tax rate and initial categories.
     * 
     * @param taxPercentage the tax rate as a percentage (e.g., 8 for 8%)
     * @param initialCategories initial mapping of item types to pricing categories
     * @throws IllegalArgumentException if taxPercentage is negative
     * @throws NullPointerException if initialCategories is null
     */
    public RetailItemLookup(double taxPercentage, Map<RetailItemType, PricingCategory> initialCategories) {
        if (taxPercentage < 0) {
            throw new IllegalArgumentException("Tax percentage cannot be negative");
        }
        if (initialCategories == null) {
            throw new NullPointerException("Initial categories map cannot be null");
        }
        
        this.taxRate = taxPercentage / 100.0;
        this.typeCategory = new HashMap<>(initialCategories);
    }

    /**
     * Creates and adds a new pricing category for a retail item type.
     * 
     * @param type the retail item type to categorize
     * @param isTaxable whether items in this category are taxable
     * @throws DuplicateCategoryException if the type already has a pricing category
     * @throws NullPointerException if type is null
     */
    public void addPricingCategory(RetailItemType type, boolean isTaxable) 
            throws DuplicateCategoryException {
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        if (typeCategory.containsKey(type)) {
            throw new DuplicateCategoryException(type);
        }
        
        currentPricingCategory = new PricingCategory(isTaxable);
        typeCategory.put(type, currentPricingCategory);
        System.out.printf("Added pricing category for %s (Taxable: %b)%n", type, isTaxable);
    }

    /**
     * Removes the pricing category for a retail item type.
     * 
     * @param type the retail item type to remove
     * @return true if the category was removed, false if it didn't exist
     * @throws NullPointerException if type is null
     */
    public boolean removePricingCategory(RetailItemType type) {
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        
        PricingCategory removed = typeCategory.remove(type);
        if (removed != null) {
            System.out.printf("Removed pricing category for %s%n", type);
            return true;
        }
        return false;
    }

    /**
     * Adds a retail item with its price to the appropriate pricing category.
     * 
     * @param item the retail item to add
     * @param price the price of the item
     * @throws MissingCategoryException if no category exists for the item's type
     * @throws IllegalArgumentException if price is not positive
     * @throws NullPointerException if item is null
     */
    public void addItemEntry(RetailItem item, double price) throws MissingCategoryException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        
        PricingCategory category = getPricingCategory(item.getType());
        category.addItem(item, price);
        System.out.printf("Added %s ($%.2f) to %s category%n", 
                         item.getName(), price, item.getType());
    }

    /**
     * Gets the base price of a retail item.
     * 
     * @param item the retail item to price
     * @return the base price of the item
     * @throws MissingCategoryException if no category exists for the item's type
     * @throws MissingItemException if the item isn't found in its category
     * @throws NullPointerException if item is null
     */
    public double getPrice(RetailItem item) throws MissingCategoryException, MissingItemException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        return getPricingCategory(item.getType()).lookUp(item);
    }

    /**
     * Calculates the tax amount for a retail item.
     * 
     * @param item the retail item to tax
     * @return the tax amount (0 if item is non-taxable)
     * @throws MissingCategoryException if no category exists for the item's type
     * @throws MissingItemException if the item isn't found in its category
     * @throws NullPointerException if item is null
     */
    public double calculateTax(RetailItem item) throws MissingCategoryException, MissingItemException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        
        PricingCategory category = getPricingCategory(item.getType());
        return category.isTaxable() ? getPrice(item) * taxRate : 0.0;
    }

    /**
     * Gets the pricing category for a specific item type.
     * 
     * @param type the retail item type
     * @return the associated pricing category
     * @throws MissingCategoryException if no category exists for the type
     * @throws NullPointerException if type is null
     */
    private PricingCategory getPricingCategory(RetailItemType type) throws MissingCategoryException {
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        
        PricingCategory category = typeCategory.get(type);
        if (category == null) {
            throw new MissingCategoryException("No pricing category for " + type);
        }
        return category;
    }

    /**
     * Gets the current tax rate.
     * 
     * @return the tax rate as a decimal (e.g., 0.08 for 8%)
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Gets the number of pricing categories currently managed.
     * 
     * @return the number of pricing categories
     */
    public int getCategoryCount() {
        return typeCategory.size();
    }
}
	

