package map;
import tree.*;
import set.*;
/**
 * Store the entries of a map using a BinarySearchTree
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TreeMap<K,V>
implements Map<K,V>
{
    BinaryTree<Entry<K,V>> tree = new EmptyBinarySearchTree<Entry<K,V>>();
    int size = 0;

    public boolean containsKey(K key)
    {
        Entry<K,V> entry = new Entry<K,V>(key, null);
        return tree.containsKey(entry);
    }

    public V get(K key)
    {
        Entry<K,V> entry = new Entry<K,V>(key, null);
        entry = tree.get(entry);
        if (entry == null)
        {
            return null;
        }
        return entry.value;
    }

    public V put(K key, V value)
    {
        //so the way this works, if the value doesnt exist, we wil simply create it.
        //but if it does exist in the map, we will update the value.
        Entry <K,V> newEntry = new Entry<K,V>(key, value);
        Entry <K,V> oldEntry = tree.get(newEntry);
        if(oldEntry == null)
        {
            tree = tree.add(newEntry);
            size ++;
            return null;
        }
        V result = oldEntry.value; //dsave the old value so we can return it
        oldEntry.value = value; 
        return result; 
        //size does not change
    }

    public V remove (K key)
    {
        Entry<K,V> entry = new Entry<K,V>(key, null);
        entry = tree.get(entry);
        if (entry == null)
        {
            return null;
        }
        tree = tree.remove(entry);
        size--;
        return entry.value; 
       
    }
    
    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return (size == 0);
    }
    
    public String toString()
    {
        return tree.toString();
    }
    
    public void clear()
    {
        tree = new EmptyBinarySearchTree<Entry<K,V>>();
    }
    
    public Set<K> keySet()
    {
        return null;
    }
    
}
