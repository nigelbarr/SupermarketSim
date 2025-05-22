package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom implementation of a singly linked list that implements the {@link MyList} interface.
 * This implementation provides standard linked list operations and supports iteration.
 *
 * @param <T> the type of elements held in this collection
 * @author Nigel Barreras
 * @see MyList
 */
public class MyLinkedList<T> implements MyList<T> {
    /** Reference to the first node in the list */
    private Node<T> first = null;
    
    /** Reference to the last node in the list */
    private Node<T> last = null;
    
    /** The number of elements in the list */
    private int size = 0;

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * Inserts the specified element at the beginning of this list.
     * 
     * @param element the element to add
     * @throws NullPointerException if the specified element is null and this list does not permit null elements
     */
    @Override
    public void addFirst(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements not allowed");
        }
        
        Node<T> node = new Node<>(element);
        node.setNext(first);
        first = node;
        
        if (last == null) {
            last = node;
        }
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param element the element to add
     * @throws NullPointerException if the specified element is null and this list does not permit null elements
     */
    @Override
    public void addLast(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements not allowed");
        }
        
        Node<T> node = new Node<>(element);
        node.setNext(null);
        
        if (last != null) {
            last.setNext(node);
        }
        last = node;
        
        if (first == null) {
            first = node;
        }
        size++;
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * 
     * @param element the element to be removed
     * @return true if this list contained the specified element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException("Cannot remove null element");
        }
        
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (element.equals(it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of this list. The string representation
     * consists of a list of the list's elements in the order they are stored,
     * enclosed in square brackets ("[]").
     * 
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (T element : this) {
            builder.append(element).append(" ");
        }
        if (!isEmpty()) {
            builder.setLength(builder.length() - 1); // Remove trailing space
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * An iterator over the linked list that supports element removal.
     */
    private class MyIterator implements Iterator<T> {
        /** The current node being examined */
        private Node<T> current;
        
        /** The node before the current node */
        private Node<T> previous;
        
        /** Flag indicating if remove operation is allowed */
        private boolean canRemove;

        /**
         * Constructs a new iterator positioned before the first element.
         */
        public MyIterator() {
            Node<T> dummy = new Node<>(null);
            dummy.setNext(first);
            
            current = dummy;
            previous = dummy;
            canRemove = false;
        }

        /**
         * Returns true if the iteration has more elements.
         * 
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.getNext();
            
            canRemove = true;
            return current.getValue();
        }

        /**
         * Removes from the list the last element returned by next().
         * 
         * @throws IllegalStateException if next() has not been called or remove()
         *         has already been called after the last call to next()
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            
            // Update first reference if needed
            if (first == current) {
                first = current.getNext();            
            }
            
            // Update last reference if needed
            if (last == current) {
                last = (first == null) ? null : previous;
            }
            
            previous.setNext(current.getNext());
            current = previous;
            canRemove = false;
            size--;
        }
    }

    /**
     * A node in the linked list that holds an element and a reference to the next node.
     * 
     * @param <T> the type of element held in the node
     */
    private static class Node<T> {
        /** The data held in this node */
        private final T value;
        
        /** Reference to the next node in the list */
        private Node<T> next;

        /**
         * Constructs a new node with the specified value.
         * 
         * @param value the value to be stored in this node
         */
        public Node(T value) {
            this.value = value;
        }

        /**
         * Returns the next node in the list.
         * 
         * @return the next node
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * Sets the next node in the list.
         * 
         * @param next the node to set as next
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        /**
         * Returns the value held in this node.
         * 
         * @return the value held in this node
         */
        public T getValue() {
            return value;
        }
    }
}
