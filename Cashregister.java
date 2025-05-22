package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a cash register simulation that scans items within a
 * list. The retail item lookup is applied to all instances of this class
 * 
 * @author Nigel Barreras cocs2436 1PM - 2:45PM
 *
 */
public class Cashregister {
	private ArrayList<RetailItem> Purchaseditems;
	public static ActivePromotions activePromotions;
	public static RetailItemLookup retailItemLookup;
	DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Sets retail item lookup
	 * 
	 * @param ReatailItemLookup The pricing policy for the retail items and
	 *                      tax per item
	 */
	public void setRetailItemLookup(RetailItemLookup retailItemLookup) {
	Cashregister.retailItemLookup = retailItemLookup;	
	}
	
	/**
	 * Sets retail item lookup
	 * 
	 * @param ReatailItemLookup The pricing policy for the retail items and
	 *                      tax per item
	 */
	public void setActivePromotions(ActivePromotions activePromotions) {
	Cashregister.activePromotions = activePromotions;	
	}
	
	/**
	 * Creates a new instance of the purchased items ArrayList
	 */
	public void startTransaction() {
		Purchaseditems = new ArrayList<>();
	}

	public void addCashregister(RetailItem item) {
		Purchaseditems.add(item);
	}
	
	 /**
     * Scans an item and adds it to current transaction
     * @param item The retail item to scan
     * @throws IllegalArgumentException if item is null
     */
    public void scanItem(RetailItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot scan null item");
        }
        Purchaseditems.add(item);
        System.out.println("Added: " + item.getName());
    }
	
    /**
     * Calculates subtotal with promotions applied
     */
    public double calculateSubtotal() throws MissingCategoryException, MissingItemException {
        if (retailItemLookup == null || activePromotions == null) {
            throw new IllegalStateException("Pricing policy not configured");
        }
        
        double subTotal = 0;
        for (RetailItem item : Purchaseditems) {
            double basePrice = retailItemLookup.getPrice(item);
            double discount = basePrice / activePromotions.findBest(item);
            subTotal += basePrice - discount;
        }
        return subTotal;
    }
	
	/**
	 * Calculates the total tax for all items
	 * 
	 * @return The total tax
	 * @throws MissingItemException 
	 * @throws MissingCategoryException 
	 */
	public double calculateTax() throws MissingCategoryException, MissingItemException {
		double tax = 0;
		for (RetailItem item : Purchaseditems) { 
			tax += (retailItemLookup.calculateTax(item));  
		}
		return tax;
	}
	
	
	/**
	 * Prints the formatted recipt
	 * @throws MissingItemException 
	 * @throws MissingCategoryException 
	 */
	public String printBreakdown() throws MissingItemException, MissingCategoryException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(stream);
		Iterator<RetailItem> iter = Purchaseditems.iterator();
		while (iter.hasNext()) {
			RetailItem element = iter.next();
			double price = retailItemLookup.getPrice(element);
			out.println(element.getName() + " $" + price);
			double promo = (price / activePromotions.findBest(element));
			out.println("Promo " + " -$" + df.format(promo));
		}
		double subtotal = calculateSubtotal();
		double tax = calculateTax();
		out.printf("\n%-10s", "Subtotal");
		out.printf("$%5.2f\n", subtotal);
		out.printf("%-10s", "Tax");
		out.printf("$%5.2f\n", tax);
		out.printf("%-10s", "Total");
		out.printf("$%5.2f\n----------------\n", subtotal + tax);
		return stream.toString()
;	}

}