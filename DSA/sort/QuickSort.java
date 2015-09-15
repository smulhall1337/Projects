package sort;
import list.*;
/**
 * Partition the list around a "pivot" element
 * The "left" side of the pivot will contain values LESS than the pivot, and the "right side
 * wll contain those greater than the pivot. Both sides are unsorted. We sort them by recursively calling quicksort
 * on both partitions. 
 * 
 * @author (Sean Mulhall) 
 * @version (10.9.14)
 */
public class QuickSort<E extends Comparable>
{
    List<E> list; 
    public int count; //keeps track of the calls to partition, used for lab 6

    public QuickSort(List <E> list)
    {
        this.list = list;
        count = 0; 
    }

    public void sort()
    {
        qSort(0, list.size()-1);
    }

    private void qSort(int start, int end)
    {
        //base case
        if (start  >= end)
        {
            return;
        }

        int p = partition (start, end);
        qSort(start, p-1);
        qSort(p+1, end);
    }

    private int partition(int start, int end)
    {
        //changed this around to have the machine pick the position in the middle of
        //the list to use for pivot, not the start
        //         count++;
        //         int p = start; //pivot position
        //         E pivotValue = list.get(p);
        //         for (int i = start+1; i <= end; i++)
        // 
        //         {
        //             int cmp = pivotValue.compareTo(list.get(i));
        //             if (cmp > 0)
        //             {
        //                 list.set(p, list.get(i));
        //                 p++;
        //                 list.set(i, list.get(p));
        //             }
        //         }
        //         list.set(p, pivotValue);
        //         return p;

        //  this was all for a lab where we had to "improve" quicksort by having it select a pivot value in the middle of the list
        //  this really doesn't make a lot of sense now that I look at it, it only further complicates things by selecting a pivot value,
        //  but keeping p, and i the same as it is above. who knows...
        count++;
        int p = start; //pivot position
        E pivotValue = list.get((start+end)/2); //store the element in the middle of the list
        list.set((start+end)/2, list.get(p));//store the data from the start position in the middle of the partition
        {
            for (int i = start+1; i <= end; i++)
            {
                int cmp = pivotValue.compareTo(list.get(i));
                if (cmp > 0)
                {
                    list.set(p, list.get(i));
                    p++;
                    list.set(i, list.get(p));
                }
            }
            list.set(p, pivotValue);
            return p;
        }
    }

}

