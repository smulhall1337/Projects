package map;
import hash.*;
import set.*;
import list.Iterator;
/**
 *Implement a Map using a HashTable
 * 
 * @author (SEan Mulhall) 
 * @version (11.24.2014)
 */
public class HashMap<K,V>
implements Map<K,V>
{
    HashTable<Entry<K,V>> table = new HashTable<Entry<K,V>>();

    public boolean containsKey(K key)
    {
        return table.containsKey(new Entry<K,V>(key, null));
    }

    public V get(K key)
    {
        Entry<K,V> entry = new Entry<K,V>(key, null);
        entry = table.get(entry);
        if (entry == null)
        {
            return null;
        }
        return entry.value;
    }

    public V put(K key, V value)
    {
        Entry<K,V> newEntry = new Entry<K,V> (key, value);
        Entry<K,V> oldEntry = table.get(newEntry);
        if (oldEntry == null)
        {
            table.put(newEntry);
            return null;
            //size doesnt change
        }
        V result = oldEntry.value;
        oldEntry.value = value; 
        return result; 
    }

    public V remove(K key)
    {
        Entry<K,V> entry = new Entry<K,V>(key, null);
        entry = table.get(entry);
        if (entry == null)
        {
            return null;
        }
        table.remove(entry);
        return entry.value;
    }

    public int hashCode(K key)
    {
        return key.hashCode();
    }

    public boolean equals(Object object)
    {
        if (!(object instanceof Entry))
        {
            return false;
        }
        Entry<K,V> otherentry = (Entry<K,V>) object;
        return otherentry.key.equals(this);
    }

    public String toString()
    {
        return table.toString();
    }

    public Set<K> keySet()
    {
        //this one took a while but was real simple in the end
        //I wasn't sure what type to use for the iterator but
        //found out from Bergman that it was Entry, from there it was cake
        Set<K> set = new HashSet<K>();
        Iterator<Entry<K,V>> itty = table.iterator();
        while (itty.hasNext())
        {
            set.add(itty.next().key);
        }
        return set;      
        //be sure to check out the average() method in the Driver for Lab 11
    }

    public void clear()
    {
        table.clear();
    }

    public int size()
    {
        return table.size();
    }

    public boolean isEmpty()
    {
        return table.size() == 0;
    }
}
