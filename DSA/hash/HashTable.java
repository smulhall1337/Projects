package hash;
import list.*;

/**
 * Stores Values for quick retrieval
 * 
 * 
 * @author (Sean Mulhall) 
 * @version (11.6.2014)
 */
public class HashTable<K>
{
    List<List<K>> list;//so we have a list, filled with lists, filled with generic objects
    int keyCount = 0; 

    /**
     * Constructs a new hash table with given size
     * @param size of this table
     */
    public HashTable(int size)
    {
        list = new ArrayList<List<K>>();
        for (int i = 0; i < size; i++)
        {
            list.add(new LinkedList<K>());
        }
    }

    /**
     * constructs a new hash table with default size of 10
     */
    public HashTable()
    {
        this(10);
    }

    /**
     * enter the given key  into this hash table
     * Duplicates are OK
     * @param the key to be added
     */
    public void put(K key)
    {
        //when we add something to this hash table, we want the items to go into a specific predetermined linked list
        //we do that with a hashcode
        int code = getCode(key);
        list.get(code).add(key);//get the reference to the linkedlist, and add this key to that LL
        keyCount++;
    }

    private int getCode(K key)
    {
        return  Math.abs(key.hashCode() % list.size());
        //we use mod here because we want whatever the list size is -1, or size()-1;
        //x % y is in the range [0,1,2,...y-1]
    }

    /**
     * see if the given key is in this hash table
     * @param the key we are searching for
     * 
     */
    public boolean containsKey(Object object)
    {
        try{
            K key = (K) object;
            int code = getCode(key);
            return list.get(code).contains(key);
        }
        //if this doesnt work, we want to catch the ClassCast exception
        catch(ClassCastException cce)
        {
            return false;
        }
    }

    /**
     * Returns the given key, or null if the key isnt found
     * @return the given key, or null if not found
     */
    public K get(K key)
    {
        int code = getCode(key);
        List<K> lst = list.get(code);
        int ndx = lst.indexOf(key);
        if (ndx < 0)
        {
            return null;
        }
        return lst.get(ndx);
    }

    /**
     * remove the given object if found
     * @return reu if removed
     */
    public boolean remove(Object obj)
    {
        try {
            K key = (K) obj;
            int code = getCode(key);
            List<K> lst = list.get(code);
            int ndx = lst.indexOf(key);
            if(ndx < 0) 
            {
                return false;
            }
            lst.remove(ndx);//remove the item at the index(in LinkedList) and throw away the result
            keyCount--;
            return true;
        }
        catch (ClassCastException cce)
        {
            return false;
        }
    }

    /**
     * clears this hashtable
     */
    public void clear()
    {
        //we need to go into each reference and clear the individual linked lists
        //we do NOT clear the hashtable by removing those references
        for(int i =0; i < list.size();i++)
        {
            list.get(i).clear();
        }
        keyCount = 0;
    }

    public Iterator<K> iterator()
    {
        return new TableIterator<K>(this);
    }

    /** @return The number of keys in this HashTable */
    public int size()
    {
        return keyCount;
    }

    /** @return true only if this HashTable is empty */
    public boolean isEmpty()
    {
        if (keyCount == 0)
        {
            return true;
        }
        return false;
    }

    /** @return The keys of this HashTable as a String.

    Example:  [mary, jim, joe]   */

    public String toString ()
    {
        Iterator<K> itty = iterator();
        String result = "[";

        while(itty.hasNext())
        {
            result += itty.next();
            if (itty.hasNext())
            {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

}
