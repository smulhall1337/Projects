package sort;
import list.*;

/**
 * Sorts a list by turning the list into a heap, and using heapsort
 * 1. make the list into a heap(heapify)
 * 2. last is last position in list
 * 3. repeat while last > 0
 * a. swap root with last
 * b. decriment last
 * c. percDown the list until the list is a heap
 * 
 * a heap is a list in which for each position, its value is greater than or equal to both its children
 * 
 *          15
 *          /\
 *         10 7
 *        /\  /\
 *      9  10 6 5
 * 
 * @author (Sean Mulhall) 
 * @version (10/30/2014)
 */
public class HeapSort<E extends Comparable>
{
    List <E> list;

    public HeapSort(List<E> list)
    {
        this.list = list;
    }

    public void sort()
    {
        heapify(0);//make list into heap
        int last = list.size() - 1;
        while (last > 0)
        {
            swap (0, last);
            last--;
            percDown(0, last);
        }
    }

    /**
     * in a list, the left child of position i is at 2i+1
     * the right child is at 2i+2
     * 
     * this makes a tree out of a list, NOT A HEAP
     * heapify makes that tree into a heap
     * ...its not really a tree either, but whatever
     * 
     * for example
     * we have a list 
     * 5 10 3 2 0 1 4 8 6
     * our "tree" would look like
     * 
     *              5
     *             / \     
     *            10  3
     *           / \  /\
     *          2  0 1  4
     *          /\
     *         8  6
     */

    private void heapify(int root)
    {
        if (root*2+1 > list.size()-1)//current root has no children
        {
            return;//done
        }
        heapify(2*root+1);//left child
        heapify(2*root+2);//right child
        percDown(root, list.size()-1);
    }

    private void percDown(int root, int max)
    {
        int bc = biggerChild(root, max);
        while((2*root+1 <= max) && (greater(bc,root)))
        {
            swap(root, bc);//bc = bigger child
            root = bc;
            bc = biggerChild(root, max);
        }
    }

    private void swap(int x, int y)
    {
        E temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }

    private int biggerChild(int root, int max)
    {
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (right > max)//no right child
        {
            return left;
        }

        if (greater(left,right))
        {
            return left;
        }
        return right;
    }
    
    private boolean greater(int i, int j)
    {
        return list.get(i).compareTo(list.get(j)) > 0; 
    }
}