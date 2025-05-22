package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.NoSuchElementException;

/**
 * A circular array implementation of the {@link MyQueue} interface.
 * This implementation provides dynamic resizing with configurable growth parameters
 * and supports all standard queue operations.
 *
 * @param <T> the type of elements held in this queue
 * @author Nigel Barreras
 * @see MyQueue
 */
public class MyArrayQueue<T> implements MyQueue<T> {
    /** Default initial capacity when not specified in constructor */
    private static final int DEFAULT_CAPACITY = 10;
    
    /** Default growth increment when not specified in constructor */
    private static final int DEFAULT_INCREMENT = 10;
    
    /** Default maximum capacity when not specified in constructor */
    private static final int DEFAULT_MAX_CAPACITY = 10;
    
    /** The amount to grow the array when capacity is reached */
    private final int increment;
    
    /** The maximum capacity this queue can grow to */
    private final int maxCapacity;
    
    /** Index of the front element in the queue */
    private int front;
    
    /** Index where the next element will be added */
    private int rear;
    
    /** The backing array for queue storage */
    private Object[] arr;
    
    /** Current number of elements in the queue */
    private int size;

    /**
     * Constructs a queue with specified initial capacity, maximum capacity, and growth increment.
     * 
     * @param initialCapacity the initial size of the backing array (must be > 0)
     * @param maxCapacity the maximum size the queue can grow to (must be >= initialCapacity)
     * @param increment the amount to grow the array when needed (must be > 0)
     * @throws IllegalArgumentException if parameters are invalid
     */
    public MyArrayQueue(int initialCapacity, int maxCapacity, int increment) {
        if (initialCapacity <= 0 || maxCapacity < initialCapacity || increment <= 0) {
            throw new IllegalArgumentException("Invalid capacity parameters");
        }
        this.arr = new Object[initialCapacity];
        this.increment = increment;
        this.maxCapacity = maxCapacity;
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    /**
     * Constructs a queue with default capacity parameters.
     */
    public MyArrayQueue() {
        this(DEFAULT_CAPACITY, DEFAULT_MAX_CAPACITY, DEFAULT_INCREMENT); 
    }

    /**
     * Adds an element to the rear of the queue.
     * 
     * @param element the element to add (cannot be null)
     * @return true (as per collection interface requirements)
     * @throws IllegalStateException if queue is at maximum capacity
     * @throws NullPointerException if element is null
     */
    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null elements");
        }
        growIfNeeded();
        arr[rear++] = element;
        if (rear == arr.length) {
            rear = 0;
        }
        size++;
        return true;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if queue is empty
     */
    @Override
    public T remove() {
        T ret = element();
        arr[front++] = null;
        if (front == arr.length) {
            front = 0;
        }
        size--;
        return ret;
    }

    /**
     * Retrieves but does not remove the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if queue is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (T) arr[front];
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Expands the backing array if capacity is reached.
     * 
     * @throws IllegalStateException if queue is at maximum capacity
     */
    private void growIfNeeded() {
        if (size == arr.length) {
            if (size == maxCapacity) {
                throw new IllegalStateException("Queue has reached maximum capacity");
            }
            int newCapacity = size + increment >= maxCapacity ? maxCapacity : size + increment;
            Object[] newArr = new Object[newCapacity];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = remove();
            }
            size = arr.length;
            arr = newArr;
            front = 0;
            rear = size;
        }
    }

    /**
     * Returns a string representation of the queue contents.
     * 
     * @return a string showing queue elements in order from front to rear
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        for (int i = 0, idx = front; i < size; i++) {
            builder.append(arr[idx++]).append(" ");
            if (idx == arr.length) {
                idx = 0;
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Returns the current number of elements in the queue.
     * 
     * @return the number of elements in the queue
     */
    @Override
    public int getSize() {
        return size;
    }
}