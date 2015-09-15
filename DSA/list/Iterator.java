package list;

/**
 * implements a basic iterator for lists
 * 
 * @author Sean Mulhall
 * @version 9.17.2014
 */
public interface Iterator <E>
{

    
    /**
     * returns true if there are more elements
     * 
     * 
     * @return        true if there are more elements in the list
     */
    boolean hasNext();

    /**
     * Returns the next element in the list
     * 
     * 
     * @return  the next element
     */
    E next();

    /**
     * Remove the last value obtained
     * 
     * 
     * @return  The last value obtained 
     */
    void remove();

    /**
     * returns true if there are at least two more elements left in the collection
     * @return true only if there are at least two more elements in the List being iterated through
     */
    boolean hasTwoMore();

    /** Remove two elements from the List,
     *  the most recent obtained by a call to
     *  next() and the element following that.
     *  Pre: There are two such elements which 
     *  can be removed.
     */    
    void remove2();

}
