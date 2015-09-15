package recursion;

import java.util.Random;
import list.LinkedList;
import sort.HeapSort;
/**
 * Test the List ADT.
 * 
 * @author (sdb) 
 * @version (Jan 2012)
 * @todo find heapsort library in JDK
 */
public class Driver
{
    public static void foo (String [] args)
 //   public static void main (String [] args)
    {
    LinkedList <Integer> grades = new LinkedList <Integer> ();
    Random rand = new Random();
    
    grades.bubbleSort();
    System.out.println (grades);
    
    grades.add (1);
    grades.bubbleSort();
    System.out.println (grades);
    
    
    
    for (int i=0; i<10; i++)
//         grades.add (rand.nextInt(150));
                grades.add (20 - i);
    
    System.out.println (grades);
    grades.bubbleSort();
    System.out.println (grades);
}
    public static void main (String [] args)
    {
    
    HeapSort <Integer> sorter = new HeapSort <Integer> ( );
    sorter.sort();
    
    Random rand = new Random();
    
    sorter.sort();
    System.out.println (sorter.list);
    
  //  grades.add (2);
    sorter.sort();
    System.out.println (sorter.list);
    
    
    for (int i=0; i<15; i++)
        sorter.list.add (rand.nextInt(150));
//                sorter.list.add (12 + i);
    System.out.println ("Before heapsort");
    System.out.println (sorter.list);
    sorter.sort( );
    System.out.println ("After heapsort");
    System.out.println (sorter.list);
}      

}
