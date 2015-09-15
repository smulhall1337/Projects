package sort.sortDriver;
import sort.*;
import list.*;
import java.util.Random;

/**
 * Test the QuickSort algorithm.
 * 
 * @author (sdb)  
 * @version (Oct 2014)
 */
public class Driver05
{   static final int MAX = 10;
    
    public static void main()
    {
        Random rand = new Random();         // random number generator
        List <Integer> numbers = new ArrayList <Integer> ();
        QuickSort<Integer>  sorter;
        sorter = new QuickSort <Integer> (numbers);
        
        // Test QuickSort with random input
        for (int i=0; i<MAX; i++)
            numbers.add (rand.nextInt(50));   // random int in [0..49]
        sorter = new QuickSort <Integer> (numbers);
        System.out.println ("Before sorting:");
        System.out.println (numbers);
        sorter.sort ( );
        System.out.println ("After sorting:");
        System.out.println (numbers);
        System.out.println (sorter.count);
        
        
         // Test QuickSort with ascending input
        numbers.clear();
        for (int i=0; i<MAX; i++)
            numbers.add (i * 10);           // initially in ascending order
         sorter = new QuickSort <Integer> (numbers);
        System.out.println ("Before sorting:");
        System.out.println (numbers);
        sorter.sort ( );
        System.out.println ("After sorting:");
        System.out.println (numbers);
        System.out.println (sorter.count);
    }
}
