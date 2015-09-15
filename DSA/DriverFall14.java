 
import list.*;

/**
 * Test the List ADT.
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class DriverFall14
{
          
    public static void main ()
    {
    List <Integer> grades = new ArrayList<Integer> ();
    
    for (int i=0; i<10; i++)
        grades.add (3 * i % 17);
    
    System.out.println (grades);
 
    grades.add (3, 0);                  // insert 0 at position 3
    System.out.println (grades);
    grades.remove (2);                  // remove position 2
    System.out.println (grades);
    grades.set (8, -3);                 // change position 8 to -3
    System.out.println (grades);
    System.out.println ("Position 4 is " + grades.get(4));  // Should be 12
    if (grades.isEmpty())
        System.out.println ("List is empty");
        
    grades = new LinkedList <Integer> ();
    
    for (int i=0; i<10; i++)
        grades.add (3 * i % 17);
    
    System.out.println (grades);
 
    grades.add (3, 0);                  // insert 0 at position 3
    System.out.println (grades);
    grades.remove (2);                  // remove position 2
    System.out.println (grades);
    grades.set (8, -3);                 // change position 8 to -3
    System.out.println (grades);
    System.out.println ("Position 4 is " + grades.get(4));  // Should be 12
    if (grades.isEmpty())
        System.out.println ("List is empty");
    
    }
}
