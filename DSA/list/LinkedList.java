
/**
 * Create a basic Linked List object
 * 
 * @author Sean Mulhall
 * @version 9.10.2014
 */
package list; 
public class LinkedList <E>
implements List <E>
{   
    int size = 0;
    Node <E> head = new Node <E> (null, null, null); //our dummy head
    Node <E> tail = new Node <E> (null, null, head);//and dummy tail
    private Node <E> ref;//handy dandy reference node

    /**initializes the linked list*/
    public LinkedList()
    {
        head.next = tail;
    }

    public int size()
    {
        return size;
    }

    /**adds the element to the end of the list*/
    public void add(E value)
    {
        Node <E> temp = new Node<E> (value,tail,tail.prev);
        tail.prev.next = temp;
        tail.prev = temp;
        size++;
    }

    /**Adds the given element at the specified position*/
    public void add(int ndx, E value)
    {
        setRef(ndx);
        Node <E> temp = new Node <E> (value,ref,ref.prev);
        ref.prev.next = temp;
        ref.prev = temp;
        size++;
    }

    /**
     * used to set a reference node. basically used to reduce code duplication
     */
    private void setRef(int ndx)
    {
        if (ndx < size/2)
        {
            ref = head.next;
            for (int i=0; i<ndx; i++)
            {
                ref = ref.next;
            }
        }

        else 
        {
            ref = tail.prev;
            for (int i=size-1; i>ndx; i--)
            {
                ref = ref.prev;
            }
        }

    }

    public E get(int ndx)
    {
        setRef(ndx);
        return ref.value;
    }

    public E set(int ndx, E value)
    {
        setRef(ndx);
        E result = ref.value;
        ref.value = value;
        return result;
    }

    public E remove(int ndx)
    {
        setRef(ndx);
        ref.prev.next = ref.next;
        ref.next.prev = ref.prev;
        size--;
        return ref.value;
    }

    public void clear()
    {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * searches list for first occourence of object and returns position of that object
     */
    public int indexOf(Object value)
    {
        for (int i=0; i<size; i++)
        {
            setRef(i);
            if(ref.value.equals(value))
            {
                return i;
            }
        }
        return -1; 
    }

    /**
     * searches list to see if given element is contained
     */
    public boolean contains (Object value)
    {
        int i = indexOf(value);
        if (i != -1)
        {
            return true; 
        }
        return false;
    }

    public String toString()
    {
        String result = "[";
        for (int i = 0; i<size; i++)
        { 
            setRef(i);
            if (i == size-1)
            {
                result = result + ref.value; 
            }
            else
            {
                result = result + ref.value +",";
            }
        }

        result = result + "]";
        return result;
    }

    public boolean equals (Object object)
    {
        Node temp = head.next;
        if (!(object instanceof List))
        {
            return false;
        }
        LinkedList otherList = (LinkedList) object; //had to cast as a linkedlist to avoid the compiler yelling about incompatible types
        if(otherList.size() != size)
        {
            return false;
        }
        Node temp2 = otherList.head.next;//this temporary node eliminated the need for the get method. this whole method works like an iterator
        for (int i=0;i<size-1;i++)       //where the temporary node "remembers" where it is in our linked list. this meant the computer didn't have to return to the first element
        {                                //in our list as it did with the set() (and getRef() through that) 
            if (!(temp.value.equals(temp2.value)))     //note to self: see if theres a way to do this without the  get
            {                                          // it seems to be bottlenecking here but im not sure if its 
                return false;                          //because of the setRef method
            }                                          
            temp = temp.next;
            temp2 = temp2.next;
        }
        return true; 
    }

    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    public boolean hasDuplicate()
    {
        return false;
    }

    public Iterator<E> iterator()
    {
        return new RefIterator<E>(this);
    }

    public ListIterator<E> listIterator()
    {
        return new RefListIterator<E>(this);
    }

    public ListIterator<E> listIterator(int ndx)
    {
        return new RefListIterator<E>(this, ndx);
    }

    /**
     * arrange the elements of this list in ascending order
     * @pre E is comparable 
     */
    public void bubbleSort()
    {
        Node <E> ref;
        int cmp;

        for(int i = 0; i < size-1;i++)
        {
            ref = head.next;
            for (int j = 0; j < size-i-1; j++)
            {
                cmp = ((Comparable)(ref.value)).compareTo(ref.next.value);
                //have to cast here because we might have a linked list of things that cant be compared
                if (cmp > 0)
                {
                    swap(ref);
                }
                ref = ref.next;
            }
        }
    }

    private void swap(Node<E> ref)
    {
        E temp = ref.value;
        ref.value = ref.next.value;
        ref.next.value = temp;
    }

}
