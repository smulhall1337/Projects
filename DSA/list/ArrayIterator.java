package list;


/**
 * an iterator that works with Arrays
 * 
 * @author Sean Mulhall
 * @version 9.17.14
 */
public class ArrayIterator <E>
implements Iterator <E>
{
    int ndx = -1;//position of last value obtained
    List <E> theList; //list we're iterating over
    
    ArrayIterator(List <E> theList)
    {
        this.theList = theList;
    }
    
    ArrayIterator()
    {
    }//should not be called
    
    public boolean hasNext()
    {
        return ndx < theList.size();
    }
    
    public E next()
    {
        ndx++;
        return theList.get(ndx);
    }
    
    public void remove()
    {
        theList.remove(ndx);
        ndx--;
    }
    
    public boolean hasTwoMore()
    {
        if (ndx < theList.size()-2)
        {
            return true;
        }
        
        return false;
    }
    
    public void remove2()
    {
    }
    
}
