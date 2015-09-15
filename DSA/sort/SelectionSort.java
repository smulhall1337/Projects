package sort;
import list.*;

/**
 *Selection sort works by comparing the element youre pointing at to the 
 *position of the first element it finds thats smaller than it, it then swaps those two elements
 *and continues until the end of the list is reached
 *
 *1. start at position zero, p = 0
 *2. find the POSITION of the smallest item begginning at position P, call it PS
 *3. swap position p and ps
 *4. continue until next to last element has been reached
 *
 *big O analysis:
 *n = size of the list
 *if we call sort, we'er using two loops. One from sort(), the other is posSmallest().
 *how many comparisons will be made in posSmallest()?
 *if list.size() == 5, first time posSmallest (position 0) is called, there will be 4 comparisons.
 *next time posSmallest is called, we start at position 1, and there are 3 comparisons
 *next, there are 2 comparisons.
 *and the last time there is one comparison. 
 *Total comparisons = 10;
 *
 *General:
 *size of list = n
 *n-1 comparisons
 *n-2 comparisons
 *n-3
 *n-4
 *.
 *.
 *.
 *+1 comparison. 
 *so tthe total comparisons = ((n-1)*n)/2 times as n becomes size - 1;
 *so our expression is:
 *O(((n-1)*n)/2) = O((n-1)*n)(two goes away since its negligable) = O(n^2-n)(through distribution)
 *and our final formula = O(n^2) for our runtime 
 * 
 * @author (Sean Mulhall) 
 * @version (10.7.2014)
 * @pre E implements Comparable. 
 * @pre List SHOULD be an arraylist. Linked list would take too much time
 */
public class SelectionSort <E extends Comparable>
{
    List <E> values;

    /**
     * constructor
     */
    public SelectionSort(List<E> values)
    {
        this.values = values;
    }

    /**
     * arrange the values in the given list from smallest to largest
     * uses compareTo from javas Comparable class
     * 
     * compareTo(E other)
     * {
     *     if this < other, returns a negative number
     *     if this = other, returns 0
     *     if this > other, returns a positive number
     *     }
     *     can be applied to any class that implements the interface Comparable.
     *     
     *     @pre E implements comparable
     */

    public void sort()
    {
        int ps; //position of smallest

        for (int p = 0; p < values.size()-1; p++)
        {
            ps = posSmallest(p);
            swap(p, ps);
            //we'll define these later
        }    
    }

    /**
     * return position of the smallest value begginning at a given start position
     * @retun the POSITION of the smallest value
     */
    private int posSmallest(int start)
    {
        int result = start;
        int cmp; //result of compareTo()

        for (int i = start+1; i < values.size(); i++)
        {
            cmp = ((Comparable) values.get(i)).compareTo(values.get(result));
            //values.get(i) is going to return an E(which could be anything).
            //the compilier isnt going to like us trying to use 
            //compareTo on an E. So we cast the result of the get as a
            //Comparable, which you can use compareTo on

            if (cmp < 0)
            {
                //values[i] < values[result]
                result = i;
            }
        }
        return result;    
    }
    
    /**
     * swaps the given positions in the list
     * 
     */
    public void swap(int x, int y)
    {
        E temp = values.get(x);//store our lesser element in a temporary object
        values.set(x, values.get(y));//x becomes y
        values.set(y, temp);//y becomes temp
    }
}

