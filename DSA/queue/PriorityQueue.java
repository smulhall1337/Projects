package queue;
import list.*;
/**
 * A priority Queue is a queue in which each item has a PRIORITY.
 * The priority determines its position in the queue
 * 
 * uses heap properties
 * 
 * @author (Sean Mulhall) 
 * @version (11.2.14)
 */
public class PriorityQueue<E extends Comparable>
implements QueueADT<E>
{
    List<E> values = new ArrayList<E>();
    int size = 0;//size of PRIORITY QUEUE, not the list

    /**
     * Removes the first element in the queue
     * Remove must preserve the heap properties
     */
    public E remove()
    {
        E result = values.get(0);
        size--;
        int avail = 0;//available slot in list
        int bigger = biggerChild(0);
        while((2*avail+1 < size) && (greater(bigger, size)))
        {
            //find the bigger child, put that into the position we are removing
            values.set(avail, values.get(bigger));
            //set the available slot to the one we just moved bigger out of
            avail = bigger;
            //move the next biggest child into that spot;
            bigger = biggerChild(avail);
        }
        //move the last child into the last posssible position
        values.set(avail, values.get(size));
        //this next line isn't the best idea, but it simplified my toString method
        //theres probably a better way to do this by using the toString method and the Priority Queues size
        //I'll work on at some point
        values.remove(size);
        return result;
    }

    /**
     * add at the end
     * swap with parent until the added value is at the correct spot
     */
    public void add(E value)
    {
        //is the list full?
        if (size == values.size())
        {
            values.add(value);
        }
        else
        {
            values.set(size,value);
        }

        size++;
        int added = size-1;//position of added value
        int parent = (added - 1)/2;
        while ((added > 0)&&greater(added,parent))
        {
            swap (added,parent);
            added = parent;
            parent = (added - 1)/2;
        }
    }

    private int biggerChild(int parent)
    {
        int left = 2*parent+1;
        int right = 2*parent+2;

        if (right > size)
        {
            return left;//wont have a right child if its bigger than size
        }

        if(greater(left,right))
        {
            return left;
        }
        return right;
    }

    private void swap(int x, int y)
    {
        E temp = values.get(x);
        values.set(x, values.get(y));
        values.set(y, temp);
    }

    public boolean greater(int x, int y)
    {
        return values.get(x).compareTo(values.get(y)) > 0;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void removeAll()
    {
        values.clear();
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public E peek()
    {
        return values.get(0);
    }

    public String toString()
    {
        return values.toString();
    }

}
