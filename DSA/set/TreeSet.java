package set;
import list.Iterator;
import tree.*;

/**
 * Use a Binary Tree to implement the set interface
 * 
 * @author (Sean Mulhall) 
 * @version (11.11.14)
 */
public class TreeSet<E extends Comparable>
implements Set<E>
{
    BinaryTree<E> tree = new EmptyBinarySearchTree<E>();
    int size = 0;//binary trees dont keep track of their size, so we need to do it here

    public boolean add(E value)
    {
        if(tree.containsKey(value))
        {
            return false;
        }
        tree = tree.add(value);
        size++;
        return true;
    }

    public boolean contains(Object object)
    {
        try
        {
            E value = (E) object;
            return tree.containsKey(value);
        }
        catch(ClassCastException cce)
        {
            //object is not of type E, so it couldnt be in this set
            return false;
        }
    }

    public boolean remove(Object object)
    {
        try
        {
            E value = (E) object;
            if (tree.containsKey(value))
            {
                tree = tree.remove(value);
                size--;
                return true;
            }
        }

        catch(ClassCastException cce)
        {
            return false;
        }
        //shouldnt get here
        return false;
    }

    public Iterator<E> iterator()
    {
        if (size==0)
        {
            return new EmptyIterator<E>();
        }
        return new TreeSetIterator<E>(this);
    }

    public int size()
    {
        return size;
    }

    public void clear()
    {
        tree = new EmptyBinarySearchTree<E>();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public String toString()
    {
        return "["+tree.toString()+"]";
        //displaying the contents of this tree in INORDER
        //return tree.left.toString() +" "+tree.value+" "+tree.right.toString();
    }

    public boolean equals (Object obj)
    {

        if (obj instanceof Set)
        {
            Set<E> otherset = (Set<E>) obj;
            if (size == otherset.size())
            {
                boolean found = false;//going to get rid of this at some point
                //if it finds something thats not found, I'll just return
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
                return found;
            }
        }
        return false;
    }
    //for quiz 6
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