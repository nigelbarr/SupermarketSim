package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * This class represents soap. Soap items can only be put into a soap shelf.
 * @author Nigel Barreras 
 *
 */
public class Soap extends RetailItem {
	private static final RetailItemType TYPE = RetailItemType.SOAP;
	private final String name;
	
	/**
	 * Constructs chips with a given name
	 * 
	 * @param name  The name of the chips
	 */
	public Soap(String name) {
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
