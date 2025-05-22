package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * Interface for implementing a generic linked list
 * @author Nigel Barreras cocs2436 1PM - 2:45PM
 *
 */
public interface MyList <T> extends Iterable<T> {
	void addFirst(T element);
	void addLast(T element);
	boolean isEmpty();
	boolean remove(T element);
	
}
