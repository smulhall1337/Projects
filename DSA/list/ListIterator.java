package list;

/**
 * creates an iterator for a list type
 * 
 * @author Sean Mulhall
 * @version 9.24.2014
 */
public interface ListIterator<E>
extends Iterator<E>
{
    /**returns true onli if there is an element before the cursor*/
    /**List iterator operates with a cursor that sits between elements
     * so we can have:
     * a b c d
     *  ^ ^ ^
     */
    boolean hasPrevious();

    /**
     * returns the previous element
     */
    E previous();

    /**
     * removes the lasdt element returnedby next() or previous()
     */

    void remove();

    /** Add the given value at the cursor position in the List 
     * being iterated through. A subsequent call to next( ) would be unaffected, 
     * and a subsequent call to previous( ) would return the new element.*/


    public void add (E value);

}
