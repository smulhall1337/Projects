package set;
import list.Iterator;
import tree.*;

/**
 * an Iterator that iterates through a tree set
 * 
 * @author (Sean Mulhall) 
 * @version (11.11.14)
 */
public class TreeSetIterator<E extends Comparable>
extends TreeIterator<E>
implements Iterator<E>
{
    TreeSet<E> set;

    TreeSetIterator(TreeSet<E> set)
    {
           super(set.tree);
           this.set = set;
    }
    
    public void remove()
    {
        super.remove();
        set.size--; 
    }

}
