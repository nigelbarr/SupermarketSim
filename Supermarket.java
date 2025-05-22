package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a supermarket that manages smart shelves and shopping carts.
 * This class handles shelf management, cart distribution, and provides
 * access to the checkout system.
 * 
 * @author Nigel Barreras
 * @see SmartShelf
 * @see ShoppingCart
 * @see Checkout
 */
public class Supermarket {
    /** Map of shelf names to SmartShelf instances */
    private final Map<String, SmartShelf<? extends RetailItem>> shelves = new HashMap<>();
    
    /** Stack of available shopping carts */
    private final MyStack<ShoppingCart> shoppingCartStack = new MyArrayStack<>();
    
    /** The checkout system for this supermarket */
    private final Checkout checkout;
    
    /** Counter for generating unique cart IDs */
    private int cartCounter = 0;

    /**
     * Constructs a new Supermarket with a checkout system.
     * 
     * @param checkout the checkout system to use (cannot be null)
     * @throws NullPointerException if checkout is null
     */
    public Supermarket(Checkout checkout) {
    	 if (checkout == null) {
             throw new NullPointerException("Checkout system cannot be null");
         }
         this.checkout = checkout;
    }

	/**
     * Gets the checkout system of this supermarket.
     * 
     * @return the checkout system (never null)
     */
    public Checkout getCheckout() {
        return checkout;
    }

    /**
     * Provides a shopping cart to a customer. Returns an available cart
     * from the stack or creates a new one if none are available.
     * 
     * @return a shopping cart (never null)
     */
    public ShoppingCart getCartForCustomer() {
        if (!shoppingCartStack.isEmpty()) {
            ShoppingCart cart = shoppingCartStack.pop();
            System.out.println("Provided cart " + cart.getCartId() + " to customer");
            return cart;
        }
        
        // Create new cart if none available
        String cartId = "CART-" + (++cartCounter);
        ShoppingCart newCart = new ShoppingCart(20, cartId); // Increased default capacity
        System.out.println("Created new cart " + cartId + " for customer");
        return newCart;
    }

    /**
     * Returns a used cart to the supermarket for reuse.
     * 
     * @param cart the cart to return
     * @throws NullPointerException if cart is null
     */
    public void returnCart(ShoppingCart cart) {
        if (cart == null) {
            throw new NullPointerException("Cart cannot be null");
        }
        shoppingCartStack.push(cart);
        System.out.println("Cart " + cart.getCartId() + " returned and ready for reuse");
    }

    /**
     * Initializes the supermarket with a number of shopping carts.
     * 
     * @param numberOfCarts the number of carts to create (must be positive)
     * @throws IllegalArgumentException if numberOfCarts is not positive
     */
    public void initializeCarts(int numberOfCarts) {
        if (numberOfCarts <= 0) {
            throw new IllegalArgumentException("Number of carts must be positive");
        }
        
        for (int i = 1; i <= numberOfCarts; i++) {
            String cartId = "CART-" + (++cartCounter);
            shoppingCartStack.push(new ShoppingCart(20, cartId)); // Consistent capacity
        }
        System.out.println("Initialized supermarket with " + numberOfCarts + " carts");
    }

    /**
     * Adds a smart shelf to the supermarket.
     * 
     * @param name the name of the shelf (cannot be null or empty)
     * @param shelf the smart shelf to add (cannot be null)
     * @throws IllegalArgumentException if name is empty
     * @throws NullPointerException if name or shelf is null
     */
    public void addShelf(String name, SmartShelf<? extends RetailItem> shelf) {
        if (name == null) {
            throw new NullPointerException("Shelf name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Shelf name cannot be empty");
        }
        if (shelf == null) {
            throw new NullPointerException("Shelf cannot be null");
        }
        
        shelves.put(name.trim().toLowerCase(), shelf);
        System.out.println("Added shelf '" + name + "' to supermarket");
    }

    /**
     * Removes a smart shelf from the supermarket.
     * 
     * @param name the name of the shelf to remove (cannot be null)
     * @return true if the shelf was removed, false if it didn't exist
     * @throws NullPointerException if name is null
     */
    public boolean removeShelf(String name) {
        if (name == null) {
            throw new NullPointerException("Shelf name cannot be null");
        }
        
        SmartShelf<? extends RetailItem> removed = shelves.remove(name.trim().toLowerCase());
        if (removed != null) {
            System.out.println("Removed shelf '" + name + "' from supermarket");
            return true;
        }
        return false;
    }

    /**
     * Looks up a smart shelf by name.
     * 
     * @param name the name of the shelf to find (cannot be null)
     * @return the found shelf, or null if not found
     * @throws NullPointerException if name is null
     */
    public SmartShelf<? extends RetailItem> lookUpShelf(String name) {
        if (name == null) {
            throw new NullPointerException("Shelf name cannot be null");
        }
        
        String key = name.trim().toLowerCase();
        SmartShelf<? extends RetailItem> shelf = shelves.get(key);
        if (shelf == null) {
            System.out.println("No shelf exists for: '" + name + "'");
        }
        return shelf;
    }

    /**
     * Gets the number of shelves in the supermarket.
     * 
     * @return the number of shelves
     */
    public int getShelfCount() {
        return shelves.size();
    }

    /**
     * Gets the number of available shopping carts.
     * 
     * @return the number of available carts
     */
    public int getAvailableCartCount() {
        return shoppingCartStack.getSize();
    }
}
