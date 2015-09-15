package sort;
import list.*;

/**
 * Arrange the elements of the list in ascending order
 * @pre list SHOULD BE AN ARRAY LIST
 * (lots of gets and sets, linked list will be SLOOOOW)
 * BUT....we may be able to do it without get and set...hmmmmm
 * (spoiler alert, we can)
 * 
 * how Bubble sort works
 * 1. start at position p = 0
 * 2. compare value at position p with value at position p+1. swap IF NECESSARY
 * 3. continue from step 2 for next value of p, which is p+1 until next to last 
 * element has  been reached.
 * 
 * Big O analysis:
 * if our size ==5
 *1. inner loop repeates 4 times
 *2. " 3 times
 *3. " 2 times
 *4. " 1 time
 *total = 10 times
 *so i = (size*(size-1))/2
 *same as seleciton sort: O(n^2)
 * 
 * @author (Sean Mulhall) 
 * @version (10/9/14)
 */
public class BubbleSort<E extends Comparable>
{
    List <E> list;
    public int count = 0;
    public BubbleSort(List <E> list)
    {
        this.list = list;
    }

    public void sort()
    {
        int cmp; //compare
        for (int i = 0; i < list.size()-1; i++)
        {
            for (int j = 0; j < list.size()-i-1; j++)
            {
                //want to compare j to j+1
                cmp = list.get(j).compareTo(list.get(j+1));
                if (cmp > 0)
                {
                    swap(j, j+1);
                }
            }
        }
    }

    private void swap(int x, int y)
    {
        count++;
        E temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}
