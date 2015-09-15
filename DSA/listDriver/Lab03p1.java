package listDriver;

import list.*;

/**
 * Lab 3, problem 1
 * Test the ListIterators
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class  Lab03p1
{
    public static void main()
    {   
    List <String> names;
    System.out.println ("Testing ListIterarors for ArrayLists");
    testItty (new ArrayList<String> ());
    System.out.println ("\nTesting ListIterarors for LinkedLists");
    testItty (new LinkedList<String> ());
}
   private static void testItty (List<String> names)
   {
   names.add ("jim");
   names.add ("mary");
   names.add ("joe");
   names.add ("sue");
   System.out.println (names);
    
   System.out.println ("Print the list backwards:");
    ListIterator<String> itty = names.listIterator(names.size());
    
    while (itty.hasPrevious())
        System.out.print (itty.previous() + " ");
    System.out.println();
    itty = names.listIterator();            // reset iterator
    System.out.println (itty.next());       // "jim"
    System.out.println (itty.next());       // "mary"
    System.out.println (itty.previous());       // "mary"
    System.out.println (itty.next());       // "mary"
    System.out.println (itty.previous());       // "mary"
    itty.remove();
    System.out.println (names);             // jim, joe, sue
    System.out.println (itty.next());       // "joe"
    itty.add ("harry");
    System.out.println (names);             // jim, joe, harry, sue
    System.out.println (itty.previous() + "\n");       // "harry"
    itty.add ("barry");                      
    while (itty.hasPrevious())
        System.out.println (itty.previous());   // barry, joe, jim

    }   


}
