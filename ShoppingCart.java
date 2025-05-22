package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.EmptyStackException;

/**
 * Represents a shopping cart that holds retail items for purchase.
 * The cart uses a stack-like structure for item management and has
 * a unique identifier. Items can be added, removed, and inspected.
 *
 * @author Nigel Barreras
 * @see RetailItem
 */
public class ShoppingCart {
    /** Current position in the cart array */
    private int top;
    
    /** Unique identifier for this shopping cart */
    private final String cartId;
    
    /** Array storing the retail items in the cart */
    private RetailItem[] cart;

    /**
     * Constructs a new ShoppingCart with specified initial capacity and ID.
     * 
     * @param initialCapacity the initial number of items the cart can hold (must be > 0)
     * @param cartId the unique identifier for this cart (cannot be null or empty)
     * @throws IllegalArgumentException if initialCapacity ≤ 0 or cartId is empty
     * @throws NullPointerException if cartId is null
     */
    public ShoppingCart(int initialCapacity, String cartId) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        if (cartId == null) {
            throw new NullPointerException("Cart ID cannot be null");
        }
        if (cartId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be empty");
        }
        
        this.cartId = cartId;
        this.cart = new RetailItem[initialCapacity];
        this.top = 0;
    }

    /**
     * Gets the unique identifier of this shopping cart.
     * 
     * @return the cart ID (never null)
     */
    public String getCartId() {
        return cartId;
    }

    /**
     * Checks if the cart contains no items.
     * 
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Adds a retail item to the top of the cart.
     * 
     * @param <T> the type of retail item
     * @param item the retail item to add (cannot be null)
     * @return the added item
     * @throws NullPointerException if item is null
     * @throws IllegalStateException if the cart is full
     */
    public <T extends RetailItem> T push(T item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        if (top == cart.length) {
            throw new IllegalStateException("Cart is full");
        }
        
        cart[top++] = item;
        return item;
    }

    /**
     * Retrieves but does not remove the item at the top of the cart.
     * 
     * @param <T> the type of retail item
     * @return the item at the top of the cart
     * @throws EmptyStackException if the cart is empty
     */
    @SuppressWarnings("unchecked")
    public <T extends RetailItem> T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) cart[top - 1];
    }

    /**
     * Removes and returns the item at the top of the cart.
     * 
     * @param <T> the type of retail item
     * @return the removed item
     * @throws EmptyStackException if the cart is empty
     */
    public <T extends RetailItem> T pop() {
        T item = peek();  // Will throw EmptyStackException if empty
        cart[--top] = null;  // Clear the reference
        return item;
    }

    /**
     * Returns the number of items currently in the cart.
     * 
     * @return the number of items in the cart
     */
    public int size() {
        return top;
    }

    /**
     * Returns the current capacity of the cart (maximum number of items it can hold).
     * 
     * @return the current capacity of the cart
     */
    public int capacity() {
        return cart.length;
    }

    /**
     * Returns a string representation of the cart's contents.
     * 
     * @return a string showing the cart ID and items
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShoppingCart[ID=").append(cartId).append(", Items=[");
        for (int i = 0; i < top; i++) {
            if (i > 0) builder.append(", ");
            builder.append(cart[i].getName());
        }
        builder.append("]]");
        return builder.toString();
    }
}
