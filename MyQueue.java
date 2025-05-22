package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * A generic queue interface representing a first-in-first-out (FIFO) data structure.
 * This interface defines the basic operations that all queue implementations should provide.
 *
 * @param <T> the type of elements held in this queue
 * @author Nigel Barreras
 */
public interface MyQueue<T> {

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions.
     *
     * @param element the element to add
     * @return true if the element was added to this queue, else false
     * @throws IllegalStateException if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element prevents it from being added
     * @throws NullPointerException if the specified element is null and this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element prevents it from being added
     */
    boolean add(T element);

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    T remove();

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    T element();

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    int getSize();
}
