package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Represents an entry in a shopping list, containing information about
 * a retail item including its name, type, and shelf location. This class
 * is immutable once created.
 * 
 * @author Nigel Barreras
 * @see RetailItemType
 */
public class ShoppingListEntry {
    /** The name of the retail item (immutable) */
    private final String name;
    
    /** The type/category of the retail item (immutable) */
    private final RetailItemType type;
    
    /** The name of the shelf where the item is located (immutable) */
    private final String shelfName;

    /**
     * Constructs a new ShoppingListEntry with specified properties.
     * 
     * @param name the name of the retail item (cannot be null or empty)
     * @param type the type/category of the item (cannot be null)
     * @param shelfName the name of the shelf where the item is located (cannot be null or empty)
     * @throws IllegalArgumentException if name or shelfName is empty
     * @throws NullPointerException if any parameter is null
     */
    public ShoppingListEntry(String name, RetailItemType type, String shelfName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        if (shelfName == null || shelfName.trim().isEmpty()) {
            throw new IllegalArgumentException("Shelf name cannot be null or empty");
        }
        
        this.name = name;
        this.type = type;
        this.shelfName = shelfName;
    }

    /**
     * Gets the name of the retail item.
     * 
     * @return the item name (never null)
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the retail item.
     * 
     * @return the item type (never null)
     */
    public RetailItemType getType() {
        return type;
    }

    /**
     * Gets the name of the shelf where the item is located.
     * 
     * @return the shelf name (never null)
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * Returns a hash code value for this shopping list entry.
     * 
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + shelfName.hashCode();
        result = prime * result + type.hashCode();
        return result;
    }

    /**
     * Compares this shopping list entry to the specified object for equality.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ShoppingListEntry other = (ShoppingListEntry) obj;
        return name.equals(other.name) &&
               type == other.type &&
               shelfName.equals(other.shelfName);
    }

    /**
     * Returns a string representation of this shopping list entry.
     * 
     * @return a string in the format "itemName from shelfName"
     */
    @Override
    public String toString() {
        return name + " from " + shelfName;
    }
}
