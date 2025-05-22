package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.ArrayList;

/**
 * Represents an employee who manages retail items in a supermarket system.
 * Employees can place items on shelves, sort them, and add new items while maintaining shelf organization.
 * 
 * @author Nigel Barreras
 */
public class Employee {
	private final String name;
	
    /**
     * Constructs an Employee with the specified name.
     * 
     * @param name The name of the employee (cannot be null or empty)
     * @throws IllegalArgumentException if name is null or empty
     */
	public Employee(String name) {
		this.name = name;
	}


	  /**
     * Places and sorts a collection of retail items onto a specified shelf.
     * The items will be placed using the shelf's sorting mechanism (typically quicksort).
     * 
     * @param <T> The type of retail items being placed, must extend RetailItem
     * @param shelf The shelf interface where items will be placed (cannot be null)
     * @param unsortedItems ArrayList of items to be placed and sorted (cannot be null)
     * @throws IllegalArgumentException if shelf or unsortedItems is null
     */
	 public <T extends RetailItem> void placeItems(IShelfEmployee<T> shelf,ArrayList<T> unsortedItems) {
		System.out.println( name + " placing on " + shelf.getName());
		for (T retailitem : unsortedItems) {
			System.out.println(retailitem.getName());
		}
		System.out.println();
		System.out.println(name + " sorted the " + shelf.getName());
		shelf.placeItems(unsortedItems);
	}
	
	    /**
	     * Adds a single retail item to a shelf while maintaining the shelf's sorted order.
	     * 
	     * @param <T> The type of retail item being added, must extend RetailItem
	     * @param shelf The shelf interface where the item will be added (cannot be null)
	     * @param retailitem The retail item to be added to the shelf (cannot be null)
	     * @throws IllegalArgumentException if shelf or retailitem is null
	     */
		 public <T extends RetailItem> void addItem(IShelfEmployee<T> shelf,T retailitem) {
			System.out.println( name+ " adding to " + shelf.getName() + ": " + retailitem.getName());
			shelf.addItem(retailitem);
		}


	

	
}
