package edu.collin.cocs2436.nbarreras.SuperMarketSim;

/**
 * A generic stack interface representing a last-in-first-out (LIFO) data structure.
 * This interface defines the fundamental operations for stack implementations.
 *
 * @param <T> the type of elements held in this stack
 * @author Nigel Barreras
 */
public interface MyStack<T> {

    /**
     * Tests if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the size (length) of the stack
     *
     * @return true if and only if this stack contains no items; false otherwise
     */
    int getSize();
       
    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element the item to be pushed onto this stack
     * @return the item argument (for chaining operations)
     * @throws ClassCastException if the class of the specified element prevents it from being added
     * @throws NullPointerException if the specified element is null and this stack does not permit null elements
     * @throws IllegalArgumentException if some property of the specified element prevents it from being added
     */
    T push(T element);

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    T peek();

    /**
     * Removes and returns the object at the top of this stack.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    T pop();

	 /**
     * Returns the current capacity of the cart (maximum number of items it can hold).
     * 
     * @return the current capacity of the cart
     */
  
}
