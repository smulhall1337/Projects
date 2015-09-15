package list;

/**
 * an iterator that is to be used on Linked Lists
 * 
 * @author Sean Mulhall 
 * @version 9.17.2014
 */
public class RefIterator <E>
implements Iterator <E>
{
    LinkedList <E> theList;
    Node <E> cursor; //placeholder, will initialize in the constructor

    RefIterator(LinkedList<E> theList)
    {
        this.theList = theList; 
        cursor = theList.head;
    }

    RefIterator()
    {
    }//should not be called

    public boolean hasNext()
    {
        return cursor.next != theList.tail;//if the next element is the tail, you hit the end of the list
    }

    public E next()
    {
        cursor = cursor.next;
        return cursor.value;
    }

    public void remove()
    {
        cursor.next.prev = cursor.prev;
        cursor.prev.next = cursor.next;
        theList.size--;
        cursor = cursor.prev;
    }

    public boolean hasTwoMore()
    {
        if ((hasNext())&&(cursor.next.next != theList.tail))//this was tricky, all I had was the second conditional, that meant when that when it got to the element before tail, in other words 
        {                                                  //that element --> tail --> null. so .next.next didnt equal the tail. so we put in that first conditional to detect whether it was
            return true;                                   // on the tail and boot you out of the method
        }
        return false;
    }

    /** Remove two elements from the List,
     *  the most recent obtained by a call to
     *  next() and the element following that.
     *  Pre: There are two such elements which 
     *  can be removed.
     */
    public void remove2()
    {
        if (hasTwoMore())
        {
            remove();
            cursor = cursor.next;
            remove();
        }
    }
        
        
    }
