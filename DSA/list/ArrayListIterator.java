package list;


/**
 * Write a description of class ArrayListIterator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArrayListIterator<E>
extends ArrayIterator<E>
implements ListIterator<E>
{
    boolean forward = true; //used for remove and add
    /**
     * since the cursor sits between two elements, it will be hard to know what element we have to remove
     *    A  B
     *      ^
     *  do we remove A or B?
     *  we'll set up this field to remember which way we are going and remove the appropriate method
     *  if forward, we'll remove the element in behind the cursor,
     *  if backwards, we'll remove the one in front of the cursor
     *  it works opposite the way we would think
     */
    ArrayListIterator(List<E>theList)
    {
        super(theList);//call to the super class's (ArrayIterator) to instantiate the Iterator
        ndx = 0;
    }

    /**
     * this second constructor will initialize the list as the one above does, but also sets the starting 
     * position for the cursor
     */
    ArrayListIterator(List <E> theList, int ndx)
    {
        super(theList);
        super.ndx = ndx;
    }
        
    
    public boolean hasNext()
    {
        return ndx<theList.size();//no need for an If statement
    }
    
    public E next()
    {
        forward = true; //we're going forwards, and we'll remove the element IN FRONT OF the cursor
        ndx++;
        return theList.get(ndx-1);//ndx has already been updated, so we want the previous value
    }
    
    public boolean hasPrevious()
    {
        return ndx>0;//if the ndx is set to zero, its sitting before the first element in the list, so there is no
                     // elements before it
    }
    
    public E previous()
    {
        forward = false; //we're going backwards and will remove the element BEHIND the cursor 
        ndx--;
        return theList.get(ndx);
    }
    
    
    public void remove()
    {
        if (forward)
        {
            ndx--;
            /**
             * decriment index first BEFORE removing because its going to work opposite what we think
             * 
             */
            theList.remove(ndx);
        }
        else
        {
            theList.remove(ndx);
        }
    
    }
    
    /**
     * easy-peasy. didnt need to update the size (as in RefListIterator) because thats
     * already taken care of in the add() method
     */
    public void add(E value)
    {
            ndx++;
            theList.add(ndx-1, value);   
       
        }
}
