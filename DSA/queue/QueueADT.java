package queue;
import list.*;

/**
 * A queue is a first-in-first-out ADT. think of it as a line; whoever goes in first, comes out first
 * Works almost the same way as a stack, except the remove() method will remove the
 * front most element.
 * @author Sean Mulhall 
 * @version 9.25.2014
 */
public interface QueueADT<E>
{
    /**
     * add a value to the end(back) of the queue
     */
    void add(E value);
    
    /**
     * remove the value at the front of the queue
     * @return the value that is removed
     * @Pre the queue is not empty
     */
    E remove();
    
    /**
     * return the value at the front of this queue
     * @return the value at the front of the queue
     */
    E peek();
    
    /**
     * returns the size if this queue
     * @return the size of the queue
     */
    int size();
    
    /**
     * removes all elements from the stack
     * @return void
     */
    void removeAll();
    
    /**
     * returns true if this queue equals the other queue 
     * @param the other queue
     * @returns true if the other queue equals this queue
     */
    boolean equals(Object object);
    
    /**
     * returns the string representation of this queue
     * @returns this queue as a string
     */
    String toString();
    
    /**
     * tells us whether or not the queue is empty
     * @return true if the queue is empty, otherwise returns false
     */
    boolean isEmpty();
    
}   


