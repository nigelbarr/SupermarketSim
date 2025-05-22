package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Enumerates the different types of retail items available in the supermarket system.
 * Each type represents a distinct category of products that share common characteristics
 * and pricing/taxation rules.
 * 
 * <p>Example usage:
 * <pre>
 * RetailItem cereal = new RetailItem("Corn Flakes", RetailItemType.CEREAL);
 * </pre>
 * 
 * @author Nigel Barreras
 * @see RetailItem
 * @see PricingCategory
 */
public enum RetailItemType {
    /** Breakfast cereal products */
    CEREAL,
    
    /** Packaged snack chips */
    CHIPS,
    
    /** Personal hygiene soap products */
    SOAP,
    
    /** Carbonated beverage products */
    SODA;
    
    /**
     * Returns a formatted string representation of the enum value
     * with proper capitalization and spacing.
     * 
     * @return the formatted enum name
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
    
    /**
     * Parses a string into a RetailItemType enum value (case-insensitive).
     * 
     * @param value the string to parse
     * @return the corresponding RetailItemType
     * @throws IllegalArgumentException if the string doesn't match any enum value
     */
    public static RetailItemType fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        return RetailItemType.valueOf(value.trim().toUpperCase());
    }
    
    /**
     * Checks if this item type is considered a food product.
     * 
     * @return true if the item is food (CEREAL, CHIPS, or SODA), false otherwise
     */
    public boolean isFood() {
        return this == CEREAL || this == CHIPS || this == SODA;
    }
    
    /**
     * Checks if this item type is considered a non-food product.
     * 
     * @return true if the item is non-food (SOAP), false otherwise
     */
    public boolean isNonFood() {
        return !isFood();
    }
}