/**
 * Write a description of class ArrayList here.
 * 
 * @author Sean Mulhall
 * @version 9.10.2014
 */
package list;
public class ArrayList <E>
implements List <E>
{
    E [] values; 
    int size = 0;

    //constructors
    public ArrayList()
    {
        values = (E[]) new Object [10];
    }

    /**
     * initialize with given capacity
     */

    public ArrayList(int capacity)
    {
        values = (E[]) new Object [capacity];
    }

    /**return size
     * 
     */
    public int size()
    {
        return size;
    }

    /**
     * add at given index
     */
    public void add (int ndx, E value)
    {
        if (size==values.length)
        {
            //no room to add
            alloc();
        }
        for (int i=size-1; i>=ndx; i--)
        {
            values[i+1] = values[i];//move everything over
        }
        values[ndx] = value;//replace 
        size ++;
    }

    /**
     * add at end of array
     */
    public void add(E value)
    {
        add (size, value);
    }

    /**
     * adds space to array
     */
    private void alloc()
    {
        E[] temp = (E[]) new Object [2 * values.length];
        for(int i =0; i<values.length; i++)
            temp[i] = values[i];
        values = temp;
    }

    /**
     * returns value at given index
     */
    public E get (int ndx)
    {
        return values[ndx];
    }

    /**
     * set element at given index to given value
     */
    public E set(int ndx, E value)
    {
        E result = values[ndx]; //save old value
        values [ndx] = value;
        return result; 
    }

    /**
     * remove element at given index
     */
    public E remove(int ndx)
    {
        E result = values[ndx];
        for (int i = ndx; i < size-1; i++)
        {
            values[i] = values[i+1];
        }
        size--;
        return result;
    }

    /**
     * removes all elements from array
     */
    public void clear()
    {
        size = 0;
    }

    /**
     * searches array list for first occourence of object and returns position of that object
     */
    public int indexOf(Object value)
    {
        for (int i=0; i<=size-1; i++)
        {
            if (values[i].equals(value))
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
            if (i == size-1)
            {
                result = result + values[i]; 
            }
            else
            {
                result = result + values[i] +",";
            }
        }

        result = result + "]";
        return result;
    }

    public boolean equals (Object object)
    {
        if (!(object instanceof List))
        {
            return false;
        }
        List otherList = (List) object; 
        if(otherList.size() != size)
        {
            return false;
        }
        for (int i=0;i<size-1;i++)
        {
            if (!(values[i].equals(otherList.get(i))))
            {
                return false;
            }
        }
        return true; 
    }

    /**returns true if this list has at least two elements which are equal*/
    public boolean hasDuplicate()
    {
        for (int i=0; i<size-1;i++)
        {
            int count = 0;
            Object temp = values[i];
            for(int j=0; j<size; j++)
            {

                if (temp.equals(values[j]))
                {
                    count++;
                }
            }
            if (count > 1)
            {
                return true;
            }
        }        
        return false;
    }

    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    /**returns an iterator for this list*/
    public Iterator<E> iterator()
    {
        return new ArrayIterator<E>(this);
    }

    public ListIterator<E> listIterator()
    {
        return new ArrayListIterator<E> (this);
    }

    public ListIterator<E> listIterator(int ndx)
    {
        return new ArrayListIterator<E>(this, ndx);
    }

}