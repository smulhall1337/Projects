package set;
import list.Iterator;

/**
 * Set ADT:
 * 1. No Explicit Order
 * 2. Cannot contain duplicates
 * 
 * @author (Sean Mulhall) 
 * @version (11.11)
 */
public interface Set<E>
{
    /**
     * add the value to this set
     * @return true if added
     */
    boolean add(E value);

    /**
     * return true if this set already contains the given value
     * @return true if the value already exists
     */
    boolean contains(Object object);

    /**
     * remove the given value from this set
     * @return true if the element was removed
     */
    boolean remove(Object object);

    /**
     * creates an iterator for this set
     * @return a new iterator
     */
    Iterator<E> iterator();

    /**
     * returns the size
     */
    int size();

    /**
     * clears this set
     */
    boolean isEmpty();

    /**
     * prints a string of this set
     */
    String toString();

    /** @return true only if obj is a Set and this Set is equal to the given Set, obj */

    boolean equals (Object obj);

    /** Add all elements of otherSet to this Set.

     *  @return true only if this Set was changed

     */

    boolean addAll (Set <E>  otherSet);

}

