package tree;
import list.Iterator;

/**
 * Write a description of class EmptyIterator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EmptyIterator<E>
implements Iterator<E>
{
    //this is easy
    public boolean hasNext()
    {
        return false;
    }
    
    public E next()
    {
        return null;
    }
    
    public void remove()
    {
    }
    
    public void remove2()
    {
    }
    
    public boolean hasTwoMore()
    {
        return false;
    }
}
