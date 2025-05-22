package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.EmptyStackException;

/**
 * A dynamic array implementation of the {@link MyStack} interface.
 * This implementation provides automatic resizing with configurable growth parameters
 * and supports all standard stack operations (LIFO - Last In First Out).
 *
 * @param <T> the type of elements held in this stack
 * @author Nigel Barreras
 * @see MyStack
 */
public class MyArrayStack<T> implements MyStack<T> {
    /** Default initial capacity when not specified in constructor */
    private static final int DEFAULT_CAPACITY = 10;
    
    /** Default growth increment when not specified in constructor */
    private static final int DEFAULT_INCREMENT = 10;
    
    /** The amount to grow the array when capacity is reached */
    private final int increment;
    
    /** The backing array for stack storage */
    private Object[] arr;
    
    /** Index of the next available slot (also represents size) */
    private int top;

    /**
     * Constructs an empty stack with default initial capacity (10)
     * and default growth increment (10).
     */
    public MyArrayStack() {
        this(DEFAULT_CAPACITY, DEFAULT_INCREMENT);
    }
    
    /**
     * Constructs an empty stack with specified initial capacity and growth increment.
     * 
     * @param initialCapacity the initial capacity of the stack (must be > 0)
     * @param increment the amount to grow the stack when needed (must be > 0)
     * @throws IllegalArgumentException if parameters are invalid
     */
    public MyArrayStack(int initialCapacity, int increment) {
        if (initialCapacity <= 0 || increment <= 0) {
            throw new IllegalArgumentException("Capacity and increment must be positive");
        }
        this.increment = increment;
        this.arr = new Object[initialCapacity];
        this.top = 0;
    }

    /**
     * Tests if this stack is empty.
     * 
     * @return true if and only if this stack contains no items; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Pushes an item onto the top of this stack.
     * 
     * @param element the item to be pushed (may be null)
     * @return the item that was pushed
     */
    @Override
    public T push(T element) {
        growIfNeeded();
        arr[top++] = element;
        return element;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * 
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) arr[top - 1];
    }

    /**
     * Removes and returns the object at the top of this stack.
     * 
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    @Override
    public T pop() {
        T ret = peek();  // Will throw EmptyStackException if empty
        arr[--top] = null;  // Clear the reference to allow garbage collection
        return ret;
    }

    /**
     * Returns a string representation of this stack.
     * The string representation consists of a list of the stack's elements
     * in the order they would be popped (from top to bottom), enclosed in square brackets.
     * 
     * @return a string representation of this stack
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        for (int i = 0; i < top; ++i) {
            builder.append(arr[i]).append(" ");
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Returns the current size of the stack.
     * 
     * @return the number of items in this stack
     */
    public int size() {
        return top;
    }

    /**
     * Increases the capacity of the stack if needed.
     * When the current capacity is reached, creates a new array with additional
     * space specified by the increment value.
     */
    private void growIfNeeded() {
        if (top == arr.length) {
            System.out.println("Growing from " + arr.length + " to " + (arr.length + increment));
            Object[] newArr = new Object[arr.length + increment];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
    }

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
