package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Exception class that is called when there is already a pricing category for a retail item made
 * @author Nigel Barreras 
 *
 */

public class DuplicateCategoryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -734234716331205128L;

	/**
	 * Throw when there is already a pricing category for a retail item made
	 * @param soap Retail item type
	 */
	public DuplicateCategoryException(RetailItemType soap) {
		System.out.println(soap + " is already in a pricing category");
	}

}
