package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class Checkout {
    private final HashSet<CheckoutCounter> counters = new HashSet<>();
    
    public HashSet<CheckoutCounter> getCounters() {
        return new HashSet<>(counters); // Return a copy for encapsulation
    }

    public void addCounters(Collection<CheckoutCounter> checkoutCounters) {
        if (checkoutCounters != null && !checkoutCounters.isEmpty()) {
            counters.addAll(checkoutCounters);
            System.out.println("Added " + checkoutCounters.size() + " checkout counters");
        } else {
            System.out.println("No valid checkout counters provided");
        }
    }
    
    
    /**
     * Finds the counter with the most customers waiting
     */
    public CheckoutCounter findBusiest() {
        if (counters.isEmpty()) {
            return null;
        }
        
        Iterator<CheckoutCounter> it = counters.iterator();
        CheckoutCounter busiest = it.next();
        
        while (it.hasNext()) {
            CheckoutCounter current = it.next();
            if (current.getLineSize() > busiest.getLineSize()) {
                busiest = current;
            }
        }
        return busiest;
    }
    
    
    /**
     * Finds the counter with the fewest customers waiting
     * Prioritizes available counters (those not currently serving)
     */
    public CheckoutCounter findQuickest() {
        if (counters.isEmpty()) {
            return null;
        }
        
        Iterator<CheckoutCounter> it = counters.iterator();
        CheckoutCounter quickest = it.next();
        
        while (it.hasNext()) {
            CheckoutCounter current = it.next();
            if (current.getLineSize() < quickest.getLineSize()) {
                quickest = current;
            }
        }
        return quickest;
    }
    
    /**
     * Helper method to find counter using a comparator
     */
    @SuppressWarnings("unused")
	private CheckoutCounter findCounterByComparator(Comparator<CheckoutCounter> comparator) {
        if (counters.isEmpty()) {
            return null;
        }
        return counters.stream()
            .min(comparator)
            .orElse(null);
    }

    /**
     * Prints a detailed breakdown of all counters
     */
    public void printBreakdown() {
        System.out.println("\n=== Checkout Counter Status ===");
        System.out.printf("%-10s %-10s %-10s %-15s%n", 
            "Counter", "Status", "In Line", "Current Customer");
        
        counters.forEach(counter -> {
            String status = counter.isAvailable() ? "Available" : "Busy";
            String customer = counter.getCurrentCustomer() != null ? 
                counter.getCurrentCustomer().getName() : "None";
            
            System.out.printf("%-10s %-10s %-10d %-15s%n",
                counter.getCounterId(),
                status,
                counter.getLineSize(),
                customer);
        });
    }
}
