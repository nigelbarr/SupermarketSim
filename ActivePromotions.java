package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class takes promotions and adds them to a list of active promotions that will discount retail items.
 * Promotions can be added or removed entierly even based on their expiration date. This class also
 * finds the best promo for an item to give the highest dicount
 * @author  Nigel Barreras 
 *
 */
public class ActivePromotions {
	private static List<Promotions> promotions = new ArrayList<>();
	
	/**
	 * Adds promotions into the promotions list
	 * @param promotion new promotion to be added
	 */
	public void addPromotion(Promotions promotion) {
		promotions.add(promotion);
	}
	
	/**
	 * Removes a promotion from the list using an iterator to move throught the list
	 * and then remove a promotion if it equals the taken promotion
	 * @param toRemove promotion to remove
	 */
	public void removeItemsIter(String toRemove) { 
		Iterator<Promotions> iter = promotions.iterator();
		while (iter.hasNext()) {
			Promotions element = iter.next();
			if(element.getName().equals(toRemove)) {
				iter.remove();
			}
		}
	}
	
	/**
	 * Method to remove any expired Promotions
	 */
	public void removeExpiredIter() { 
		Iterator<Promotions> iter = promotions.iterator();
		while (iter.hasNext()) {
			Promotions element = iter.next();
			if(element.getExpireDate().isBefore(LocalDate.now())) {
				iter.remove();
			}
		}
	}
	
	/**
	 * Method that takes an item and determines the best/highest discount for it
	 * @param <T> item type T for any subclass of Retailitem
	 * @param item Object to take
	 * @return best discount for item
	 */
	public <T extends RetailItem> double findBest(T item) {
		double ret = 0;
		for (Promotions element : promotions) {
			if(element.getDiscount() > ret && promotions.contains(element)) {
				ret = element.getDiscount();
			}
		}
		return ret;
	}
	
	
}
