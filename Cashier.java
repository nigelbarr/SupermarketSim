package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class creates a cashier that takes and checks out a ready customer 
 * who is done shopping
 * @author Nigel Barreras cocs2436 1PM - 2:45PM
 *
 */
public class Cashier {
	private final String name;
	public Checkout checkout;
	public CheckoutCounter counter;
	public Supermarket market;
	public Cashregister register;
	
	/**
	 * Constructor for a cashier
	 * @param name name of the Cashier
	 */
	public Cashier(String name, Cashregister register, CheckoutCounter counter) {
		this.name = name;
		this.register = register;
		this.counter = counter;
	}

	/**
	 * @return the name
	 */
	public String getName() { 
		return name;
	}
	
	/**
	 * @param market the market to set
	 */
	public void setMarket(Supermarket market) {
		this.market = market;
	}
	
	/**
	 * get counter from the cashier
	 */
	public CheckoutCounter getCounter() {
	    return counter;
	}

	/**
	 * Processes the checkout for the current customer at the counter
	 * Handles scanning items, applying promotions, and completing the transaction
	 */
	 /**
     * Cashier processes customers at their assigned counter
     */
    public void handleCheckout() {
        System.out.println(name + " is checking for customers at counter " + counter.getCounterId());

        // If not serving, attempt to get next customer
        if (!counter.isServing() && !counter.serveNextCustomer()) {
            System.out.println("No customer currently being served at counter " + counter.getCounterId());
            return;
        }

        Customer customer = counter.getCurrentCustomer();
        if (customer == null) {
            System.out.println("No valid customer at counter " + counter.getCounterId());
            return;
        }

        ShoppingCart cart = customer.getCart();
        if (cart == null) {
            System.out.println("ERROR: " + customer.getName() + " has no shopping cart");
            counter.completeCheckout();
            return;
        }

        if (cart.isEmpty()) {
            System.out.println("NOTICE: " + customer.getName() + "'s cart is empty");
            counter.completeCheckout();
            return;
        }

        System.out.println("\nStarting checkout for " + customer.getName());
        register.startTransaction();

        try {
            while (!cart.isEmpty()) {
                RetailItem item = cart.pop();
                System.out.println("Scanning: " + item.getName());
                register.scanItem(item);
                Thread.sleep(200);
            }

            System.out.println("\nCheckout summary:");
            System.out.println(register.printBreakdown());

        } catch (Exception e) {
            System.err.println("Error during checkout: " + e.getMessage());
        } finally {
            counter.completeCheckout();
            System.out.println("Checkout process completed for " + customer.getName());

            // Continue to next customer if available
            if (counter.serveNextCustomer()) {
                handleCheckout();  // Tail-recursive OK for limited queue sizes
            } else {
                System.out.println(name + " is now idle at counter " + counter.getCounterId());
            }
        }
    }

}
