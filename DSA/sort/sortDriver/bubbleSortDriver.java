package sort.sortDriver;
import sort.*;
import list.*;


/**
 * test the bubblesort algorithm against case defined in lab 06
 * 
 * @author (Sean Mulhall) 
 * @version (10.15.2014)
 */
public class bubbleSortDriver
{
    public static void main()
    {
        List <Integer> numbers = new ArrayList <Integer> ();
        BubbleSort<Integer>  sorter;
        sorter = new BubbleSort <Integer> (numbers);

        int n = 10;
        for (int i = n; i > 0; i--)
        {
            numbers.add(i);
        }
        
        sorter = new BubbleSort <Integer> (numbers);
        System.out.println ("Before sorting:");
        System.out.println (numbers);
        sorter.sort ( );
        System.out.println ("After sorting:");
        System.out.println (numbers);
        System.out.println (sorter.count);
    }
        

}
