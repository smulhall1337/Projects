
/**
 * Write a description of class List here.
 * 
 * @author Sean Mulhall
 * @version (a version number or a date)
 */
package list;
public interface List <E>
{   
     /**return size of list
     * 
     */
    int size(); 
    
     /**
     * add at end of lsit
     */
    void add (E value);
    
    /**
     * insert element at given index
     */
    void add (int ndx, E value);
    
    //return value at given index
    E get (int ndx);
    
    //stores valjue at given index
    E set (int ndx, E value);
     /**
     * remove element at given index
     */
    //removes element at given index
    E remove (int ndx);
     /**
     * removes all elements from array
     */
    //removes all elements
    void clear();
     /**
     * searches array list for first occourence of object and returns position of that object
     */
    int indexOf (Object value);
    /**
     * return true if given object is in list
     */
    boolean contains (Object value);
    
    /**
     * returns elements of this list as a string enclosed in square brackets
    */
   String toString();
    
   /**
    * @return true only if this list is equal to the parameter
    */
   boolean equals(Object object);
   
   /**return true only if this list has a duplicate entry*/
   boolean hasDuplicate();
   
   /**
    * @return true if the list is empty, otherwise return false
    */
   boolean isEmpty();
   
   Iterator<E> iterator();
   
   ListIterator<E> listIterator();
   ListIterator<E> listIterator(int ndx);

}
