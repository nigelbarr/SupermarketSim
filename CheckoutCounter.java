package edu.collin.cocs2436.nbarreras.SuperMarketSim;


/**
 * Class that creates a checkout counter that has its own register and takes a ready
 * customer that has finished shopping
 * @author Nigel Barreras cocs2436 1PM - 2:45PM
 *
 */
public class CheckoutCounter implements Comparable<CheckoutCounter> {
    private final MyQueue<Customer> queue = new MyArrayQueue<>();
    private final Cashregister register;
    private final String counterId;  // Changed to lowercase for convention
    private Customer currentCustomer;  // Tracks who is currently being served
    private boolean isServing = false;

    public CheckoutCounter(Cashregister register, String counterId) {
        this.register = register;
        this.counterId = counterId;
        this.currentCustomer = null;
    }

    /**
     * Moves the next customer from queue to be served
     * @return the customer now being served or null if queue is empty
     */
    public Customer nextInLine() {
        if (!queue.isEmpty()) {
            this.currentCustomer = queue.remove();
            System.out.println(currentCustomer.getName() + " is now being served at counter " + counterId);
            return currentCustomer;
        }
        this.currentCustomer = null;
        System.out.println("No customers in line at counter " + counterId);
        return null;
    }

    public void waitInLine(Customer customer) {
        if (customer == null) return;
        
        queue.add(customer);
        System.out.println(customer.getName() + " joined line at counter " + counterId);
        
        // Automatically serve if counter is available
        if (!isServing) {
        	System.out.println(customer.getName() + " is waiting in line at counter " + counterId);
        } else {
        	serveNextCustomer();
        }
    }

    /**
     * Moves next customer from queue to be served
     * @return true if customer was served
     */
    public boolean serveNextCustomer() {
        if (isServing || queue.isEmpty()) {
            return false;
        }
        
        currentCustomer = queue.remove();
        isServing = true;
        System.out.println(currentCustomer.getName() + " is now being served at counter " + counterId);
        return true;
    }

    // Modified getCurrentCustomer to be more accurate
    public Customer getCurrentCustomer() {
        return isServing ? currentCustomer : null;
    }

    /**
     * Completes the current customer's checkout
     */
    public void completeCheckout() {
        if (currentCustomer != null) {
            System.out.println(currentCustomer.getName() + " completed checkout at counter " + counterId);
        }

        currentCustomer = null;
        isServing = false;
        serveNextCustomer(); // Chain to next customer
    }

    public Cashregister getRegister() {
        return register;
    }

    public String getCounterId() {
        return counterId;
    }

    public int getLineSize() {
        return queue.getSize();
    }

    public boolean isAvailable() {
        return currentCustomer == null;
    }
    
    public boolean isServing() {
        return isServing;
    }
    
    public boolean hasCustomers() {
        return isServing || !queue.isEmpty();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + counterId.hashCode();
        result = 31 * result + register.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CheckoutCounter)) return false;
        CheckoutCounter other = (CheckoutCounter) obj;
        return counterId.equals(other.counterId) && 
               register.equals(other.register);
    }

    @Override
    public int compareTo(CheckoutCounter other) {
        return Integer.compare(this.getLineSize(), other.getLineSize());
    }
}