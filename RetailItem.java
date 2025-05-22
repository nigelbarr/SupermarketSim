package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Represents a retail item with a name and type that can be stored on store shelves.
 * Implements Comparable to enable natural ordering of items by name and type.
 * 
 * @author Nigel Barreras
 * @see RetailItemType
 * @see Comparable
 */
public class RetailItem implements Comparable<RetailItem> {
    /** The name of the retail item (immutable) */
    private final String name;
    
    /** The category/type of the retail item */
    private RetailItemType type;

    /**
     * Constructs a new RetailItem with specified name and type.
     * 
     * @param name the name of the retail item (cannot be null or empty)
     * @param type the category/type of the item (cannot be null)
     * @throws IllegalArgumentException if name is null or empty
     * @throws NullPointerException if type is null
     */
    public RetailItem(String name, RetailItemType type) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of this retail item.
     * 
     * @return the item name (never null)
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type/category of this retail item.
     * 
     * @return the item type (never null)
     */
    public RetailItemType getType() {
        return type;
    }

    /**
     * Sets the type/category of this retail item.
     * 
     * @param type the new type to set (cannot be null)
     * @throws NullPointerException if type is null
     */
    public void setType(RetailItemType type) {
        if (type == null) {
            throw new NullPointerException("Item type cannot be null");
        }
        this.type = type;
    }

    /**
     * Returns a hash code value for this retail item based on its name and type.
     * 
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    /**
     * Compares this retail item to the specified object for equality.
     * Returns true if the objects are both RetailItems with the same name and type.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RetailItem)) return false;
        RetailItem other = (RetailItem) obj;
        return name.equals(other.name) && type.equals(other.type);
    }

    /**
     * Compares this retail item with another for ordering.
     * Items are ordered by concatenated name and type strings.
     * 
     * @param other the RetailItem to be compared
     * @return a negative, zero, or positive integer as this object is less than,
     *         equal to, or greater than the specified object
     * @throws NullPointerException if the specified object is null
     */
    @Override
    public int compareTo(RetailItem other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null");
        }
        String thisKey = name.concat(type.toString());
        String otherKey = other.name.concat(other.type.toString());
        return thisKey.compareTo(otherKey);
    }

    /**
     * Returns a string representation of this retail item.
     * 
     * @return a string in the format "RetailItem[name=..., type=...]"
     */
    @Override
    public String toString() {
        return "RetailItem[name=" + name + ", type=" + type + "]";
    }
}

