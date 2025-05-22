package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class represents chips. Chips items can only be put into a chips shelf.
 * @author  Nigel Barreras 
 *
 */
public class Chips extends RetailItem {
	private static final RetailItemType TYPE = RetailItemType.CHIPS;
	private final String name;
	
	/**
	 * Constructs chips with a given name
	 * 
	 * @param name  The name of the chips
	 */
	public Chips(String name) {
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
