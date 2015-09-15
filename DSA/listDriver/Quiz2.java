package listDriver;

import list.*;

/**
 * Test the List ADT.
 * 
 * @author (sdb) 
 * @version (Sep 2012)
 */
public class Quiz2
{
    public static void main ()
    {

        System.out.println ("\nTesting remove2 on a LinkedList");
        List <Integer> grades;
        grades = new LinkedList <Integer> ();
        
        grades.add(17);
        grades.add(27);
        grades.add(37);
        grades.add(7);

        System.out.println (grades);

        Iterator <Integer> itty = grades.iterator();
        itty.next();        // 17
        System.out.println ("Remove the " + itty.next() + " and the one following it");

        itty.remove2();
        System.out.println (grades);            // 17, 7

        System.out.println (itty.next());       // 7
    }

}
