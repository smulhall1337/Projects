package set;
import hash.*;
import list.Iterator;

/**
 * Implements the Set interface using a HashTable
 * 
 * @author (Sean Mulhall) 
 * @version (11.13.2014)
 */
public class HashSet<E>
implements Set<E>
{
    HashTable<E> table = new HashTable<E>();

    public boolean add(E value)
    {
        if (table.containsKey(value))
        {
            return false;
        }
        table.put(value);
        return true;
    }

    public boolean contains(Object object)
    {
        //since containsKey is already defined in HashTable, we don't need to do any extra work here
        return table.containsKey(object);
    }

    public boolean remove(Object object)
    {
        //same as above
        return table.remove(object);
    }

    public Iterator<E> iterator()
    {
        return table.iterator();
    }

    public boolean isEmpty()
    {
        return table.isEmpty();
    }

    public int size()
    {
        return table.size();
    }

    public String toString()
    {
        return table.toString();  
    }

    public boolean equals (Object obj)
    {
        if (obj instanceof Set)
        {
            Set<E> otherset = (Set<E>) obj;
            boolean found = false;
            if (table.size() == otherset.size())
            {
                Iterator<E> itty = iterator();
                while (itty.hasNext())
                {
                    if (otherset.contains(itty.next()))
                    {
                        found = true;
                    }
                    else
                    {
                        found = false;
                        break;
                    }
                }               
            }
            return found;
        }
        return false;
    }
    //for Quiz 6
    public boolean addAll(Set <E> otherSet)
    {
        Set <E> set = (Set<E>) otherSet;
        Iterator<E> itty = set.iterator();
        boolean changed = false;
        while (itty.hasNext())
        {
            E next = itty.next();
            if (! (contains(next)))
            {
                add(next);
                changed = true;
            }
        }
        return changed; 
    }

}
