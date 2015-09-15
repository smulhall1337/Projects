package hash;
import list.*;

/**
 * creates an iterator specifically to be used with hash tables
 * 
 * @author (Sean Mulhall) 
 * @version (11.11.2014)
 */
public class TableIterator<K>
implements Iterator<K>
{
    HashTable<K> table;
    int ndx;
    Iterator<K> itty; //for our linked lists in our table

    public TableIterator(HashTable<K> table)
    {
        this.table = table;
        setItty(ndx);//helper method
    }

    private void setItty(int ndx)
    {
        itty = table.list.get(ndx).iterator();
    }

    public boolean hasNext()
    {
        if (itty.hasNext())
        {
            return true;
        }
        for (ndx++; ndx < table.list.size(); ndx++)
        {
            if (!(table.list.get(ndx).isEmpty()))
            {
                setItty(ndx);
                return true;
            }
        }
        return false;

    }

    public K next()
    {
        hasNext();//the code we used here before was almost identical to hte hasNext() code, 
        //so instead of duplicating code, we got lazy and just called hasnext() to do
        //all of the legwork;
        return itty.next();
    }

    public void remove()
    {
        itty.remove();
        table.keyCount--;
    }

    //extra methods from a previous lab that dont do anything and shouldnt be called
    //added here to make compiler happy
    public void remove2()
    {
        return;
    }

    public boolean hasTwoMore()
    {
        return false;
    }
}
