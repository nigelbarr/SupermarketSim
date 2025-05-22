package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class represents chips. Cereal items can only be put into a cereal shelf.
 * @author Nigel Barreras 
 *
 */
public class Cereal extends RetailItem {
	private static final RetailItemType TYPE = RetailItemType.CEREAL;
	private final String name;
	
	/**
	 * Constructs chips with a given name
	 * 
	 * @param name  The name of the chips
	 */
	public Cereal(String name) {
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
