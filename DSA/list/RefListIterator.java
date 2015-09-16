package list;

/**
 * a reference list iterator
 * 
 * @author SEan Mulhall
 * @version (2015)
 */
public class RefListIterator<E>
extends RefIterator<E>
implements ListIterator<E>
{
    /**
     * cursor now refers to last element returned
     * by a call to next() or previous()
     * it now actually sits on the element is refering to
     */
    boolean forward = true; //same as ArrayListIterator

    RefListIterator(LinkedList<E>theList)
    {
        super(theList);
    }

    RefListIterator(LinkedList<E>theList, int ndx)
    {
        super(theList);
        cursor = theList.head;
        for (int i = 0; i < ndx; i++)
        {
            cursor = cursor.next;
        }
    }

    public boolean hasNext()
    {
        if (forward)
        {
            return cursor.next != theList.tail;
        }
        else 
        {
            return theList.size() > 0; 
        }
    }

    public boolean hasPrevious()
    {
        if (!forward)
        {
            return cursor.prev != theList.head;
        }
        return theList.size() > 0;
    }

    /**
     * next() and previous() are both dependant on hasnext() and hasprevious() being true, respectively.
     */
    public E next()
    {
        if(forward)
        {
            cursor = cursor.next;
        }
        forward = true;
        return cursor.value;
    }

    public E previous()
    {
        if (!forward)
        {
            cursor = cursor.prev;
        }
        forward = false;
        return cursor.value; 
    }

    /**
     * remove will ALWAYS remove the node that cursor is refering to, regardless of whether we're moving forward or backwards.
     * the tricky part is moving the cursor AFTER we remove the node; which way should we move?
     * since the remove method in the super class moves the node to whatever.previous, we need to change it if we're already moving backwards, hence
     * the cursor = cursor.next below.
     * 
     */
    public void remove()
    {
        super.remove();//call to the super class(RefIterator) to use the remove method
        if(!forward)
        {
            cursor = cursor.next;
        }
    }

    /**
     * this was even trickier than remove! basically its almost the same as the add
     * but you have to take a couple of things into consideration, mainly; are you moving forward
     * or backwards? if forward, youre inserting the element AFTER the node that the cursor is sitting
     * on. if backwards, it goes BEFORE the node where cursor is at. basically, this method
     * will set the cursor so that Previous() will return the new node that we just added
     * where as next() would remain unchainged.
     * 
     */
    public void add(E value)
    {

        if(forward)
        {    
            Node <E> temp = new Node <E> (value, cursor.next, cursor);
            cursor.next = temp;
            temp.next.prev = temp;
            cursor = temp;
            theList.size++;
        }
        else
        {
            Node <E> temp = new Node <E> (value, cursor, cursor.prev);
            cursor.prev = temp;
            temp.prev.next = temp;
            theList.size++;
        }

    }

}

