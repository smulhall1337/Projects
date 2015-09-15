package map;
import set.*;

/**
 * A map is a data structure storing Entries. Each entry has a key and a value
 * Access is provided via the key. Keys cannot be duplicated 
 * 
 * @author (Sean Mulhall) 
 * @version (11.13.2014)
 */
public interface Map<K,V>
{

    /**
     * @return true only if the given key is in this map
     */
    boolean containsKey(K key);

    /**
     * add the given key value pair into this map. If key is already in the map, 
     * replace its value with given value
     * @return the existing value or null if the key was not in the map
     */
    V put(K key, V value);

    /**
     * @return the value for the given key
     */
    V get(K key);

    /**
     * @return ttrue if this map is empty
     */
    boolean isEmpty();

    /**
     * remove the entry with the given key from this map
     * @return the value of the entry eremoved or null if not in this map
     */
    V remove(K key);

    /**
     * @return the number of entries in this map
     */
    int size();

    /**
     * @return a set consisting of all the keys in this map
     */
    Set<K> keySet();

    /**
     * clears this map
     */
    void clear();

    /**
     * @return the String representation of this map
     */
    String toString();
}
