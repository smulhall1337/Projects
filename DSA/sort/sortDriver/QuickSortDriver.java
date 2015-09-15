package sort.sortDriver;
import sort.*;
import list.*;
import java.util.Random;
/**
 * Write a description of class QuickSortDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuickSortDriver
{
    public static void main()
    {
        List <Integer> numbers = new ArrayList <Integer> ();
        QuickSort<Integer>  sorter;
        sorter = new QuickSort <Integer> (numbers);
        Random random = new Random();
//         numbers.add(3);
//         numbers.add(5);
//         numbers.add(1);
//         numbers.add(4);
//         numbers.add(2);
//         numbers.add(7);
//         numbers.add(6);
//         numbers.add(12);
//         numbers.add(9);
//         numbers.add(10);
//         numbers.add(8);
//         numbers.add(11);
// //         this was for a personal experiment...
        int n = 10000;
        for (int i = 1; i <= n; i++)
        {
            numbers.add(random.nextInt(10000));
        }
        
        sorter = new QuickSort <Integer> (numbers);
        System.out.println ("Before sorting:");
        System.out.println (numbers);
        sorter.sort ( );
        System.out.println ("After sorting:");
        System.out.println (numbers);
        System.out.println (sorter.count);
    }
}
