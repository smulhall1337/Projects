package sort;
import list.*;

/**
 * Search a sorted list for he given target
 * big O analysis:
 * 
 * size     calls to binSearch
 * 1        1
 * 3        2
 * 7        3
 * 15       4  
 * 31       5
 * (2^c)-1  c
 * 
 * h = 2^c-1
 * n+1 = 2^c
 * log(n+1) = log(2^c) = c
 * 
 * for a list of size n, we'll have log(n+1) calls to binSearch
 * 
 * 
 * @author (Sean Mulhall) 
 * @version (10.16.2014)
 * @pre the list is sorted 
 */
public class BinarySearch <E extends Comparable>
{
    List<E> list;

    public BinarySearch(List <E> list)
    {
        this.list = list;
    }

    /**
     * @return the position of target in the list or -1 if not found
     */
    public int search(E target)
    {
        return binSearch(target, 0, list.size()-1);
    }

    /**
     * private helper method
     * @return
     */
    private int binSearch(E target, int start, int end)
    {
        if (start > end)
        {
            return -1;
        }
        int mid = (start+end)/2;
        int cmp = list.get(mid).compareTo(target);

        if (cmp == 0)
        {
            //found the target at mid
            return mid;
        }

        if (cmp < 0)
        {
            //mid is less than target
            //use BinarySearch on "right" half of list
            return binSearch(target, mid+1, end);
        }
        //already id first two comparisons, no need for an if or else
        //mid is greater than target, search left half
        return binSearch(target, start, mid-1);
    }
}
