package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.Iterator;

/**
 * Represents a customer in a supermarket system, handling shopping and checkout processes.
 * The customer can shop for items, manage a shopping cart, and complete checkout procedures.
 */
public class Customer {
    private final String name;
    private Supermarket market;
    private ShoppingCart cart;
    private final Checkout checkout;
    
    /**
     * Constructs a new Customer with the specified name and checkout system.
     * 
     * @param name The name of the customer (cannot be null or empty)
     * @param checkout The checkout system to be used by this customer (cannot be null)
     * @throws IllegalArgumentException if name is null or empty, or checkout is null
     */
    public Customer(String name, Checkout checkout) {
        this.name = name;
        this.checkout = checkout;
    }
    
    /**
     * Gets the name of the customer.
     * 
     * @return The customer's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the supermarket reference for this customer.
     * 
     * @param market The supermarket instance where the customer will shop (cannot be null)
     */
    public void setMarket(Supermarket market) {
        this.market = market;
    }
    
    /**
     * Sets the shopping cart for this customer.
     * 
     * @param cart The shopping cart to be used by the customer (cannot be null)
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    
    /**
     * Gets the customer's current shopping cart.
     * 
     * @return The current shopping cart, or null if no cart is assigned
     */
    public ShoppingCart getCart() {
        return cart;
    }
    
    /**
     * Begins the shopping process with a given shopping list in a specified supermarket.
     * 
     * @param <T> The type of shopping list entries
     * @param list The shopping list containing items to purchase (cannot be null)
     * @param market The supermarket where the customer will shop (cannot be null)
     * @throws OutofStockException if any requested items are unavailable
     * @throws IllegalArgumentException if list or market is null
     * @throws IllegalStateException if the shopping cart becomes full
     */
    public <T extends ShoppingListEntry> void shop(MyList<T> list, Supermarket market) throws OutofStockException {
        System.out.println(getName() + " getting a shopping cart");
        this.market = market;
        this.cart = market.getCartForCustomer();
        
        System.out.println(getName() + " needs to buy:");
        list.forEach(element -> 
            System.out.println(element.getName() + " from " + element.getShelfName()));
        System.out.println();
        
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T entry = it.next();
            IShelfCustomer<? extends RetailItem> shelf = market.lookUpShelf(entry.getShelfName());
            
            if (shelf != null) {
                System.out.println(getName() + " getting " + entry.getName() + " from " + shelf.getName());
                
                try {
                    RetailItem item = shelf.findAndTake(entry.getName(), entry.getType());
                    cart.push(item);
                    System.out.printf("%s got: %s from %s (Cart now has %d items)%n%n",
                    getName(), 
                    item.getName(), 
                    shelf.getName(),
                    cart.size());
                    it.remove();
                } catch (OutofStockException e) {
                    System.out.println("Out of stock: " + entry.getName() + " on " + shelf.getName());
                } catch (IllegalStateException e) {
                    System.out.println("Cart is full! Couldn't add " + entry.getName());
                    break;
                }
            } else {
                System.out.println("Shelf not found: " + entry.getShelfName());
            }
        }
        
        System.out.println(getName() + " finished shopping. Cart contains " + cart.size() + " items");
    }
    

    /**
     * Initiates the checkout process by finding the quickest available counter.
     * The customer will join the queue of the selected counter.
     */
    public void checkout() {
        CheckoutCounter counter = checkout.findQuickest();
        if (counter != null) {
            boolean wasIdle = counter.isAvailable(); // Check before joining
            counter.waitInLine(this);

            if (!wasIdle) {
                System.out.println(name + " is waiting in line at counter " + counter.getCounterId());
            }
        } else {
            System.out.println("No available checkout counters");
        }
    }
    
    /**
     * Completes the checkout process by printing the receipt and returning the cart.
     * 
     * @throws MissingItemException if required items are missing during checkout
     * @throws MissingCategoryException if required categories are missing during checkout
     * @throws IllegalStateException if no market or cart is assigned
     */
    public void takeReceipt() throws MissingItemException, MissingCategoryException {
        if (market != null && cart != null) {
            checkout.printBreakdown();
            market.returnCart(cart); // Return the cart properly
            System.out.println(getName() + " has returned cart " + cart.getCartId());
            cart = null;
        }
    }
}