package set;

import set.*;
import list.*;
/**
 * Quiz 6
 * Test the addAll method for sets.
 * 
 * @author (sdb) 
 * @version (Nov 2014)
 */
public class Quiz6DriverAddAll
{
    public static void main()
    {
        Set <Integer> values, grades;
        values = new TreeSet <Integer> ();
        grades = new HashSet <Integer> ();
        
        grades.add (99);
        grades.add (98);
        grades.add (99);
        
        values.add (3);
        values.add (17);
        
        grades.addAll (values);
        System.out.println (grades);
        
        grades.add(100);
        grades.add (88);
        if (values.addAll (grades))
            System.out.println (values);
    }
}