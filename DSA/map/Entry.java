package map;
/**
 * Stores a key-value pair
 * 
 * @author (Sean Mulhall) 
 * @version (11.13.2014)
 */
public class Entry<K,V>
implements Comparable<Entry<K,V>>
{
    K key;
    V value;
    Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object object)
    {
        Entry entry = (Entry) object;
        return key.equals(entry.key);
    }

    public int compareTo(Entry<K,V> entry)
    {
        Comparable thiskey = (Comparable) key;
        Comparable otherkey = (Comparable) entry.key;
        return thiskey.compareTo(otherkey);
    }
    
    public String toString()
    {
        return key+"="+value;
    }

    public int hashCode()
    {
        return key.hashCode();
    }
    
}
