package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class represents soda. Soda items can only be put into a soda shelf.
 * @author Nigel Barreras 
 *
 */
public class Soda extends RetailItem {
	private static final RetailItemType TYPE = RetailItemType.SODA;
	private final String name;
	
	/**
	 * Constructs chips with a given name
	 * 
	 * @param name  The name of the chips
	 */
	public Soda(String name) {
		super(name,TYPE);
		this.name = name;
	}
	
	/**
	 * An overridden toString implementation
	 */
	@Override
	public String toString() {
		return name;
	}
}
