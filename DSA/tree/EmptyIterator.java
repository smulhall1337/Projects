package tree;
import list.Iterator;

/**
 * Iterates over an empty binary search tree. used as to make the compiler happy
 * 
 * @author (Sean Mulhall) 
 * @version (Fall 2014)
 */
public class EmptyIterator<E>
implements Iterator<E>
{
    //2ez
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
