package hash;
import hash.*;
import list.*;
import set.*;
import sort.*;

/**
 * Test the HashTable
 * 
 * @author (sdb) 
 * @version (Nov 2014)
 */
public class Lab10Day
{
    public static void main()
    {   
        HashTable <Student> students = new HashTable <Student> (10);

        if (! students.isEmpty())
            System.out.println ("Not correct");
            
        students.put (new Student ("jim", "423"));
        students.put (new Student ("mary", "433"));
        students.put (new Student ("joe", "422"));
        students.put (new Student ("jim", "421"));
        
        System.out.println ("The students are: " + students);
        if (students.size() != 4)
            System.out.println ("Not correct");
                 
        if (students.get (new Student ("mary", "433")) == null) // mary(433) is in the table
            System.out.println ("Not correct");
        
        System.out.println ();
        // Uncomment the following when ready for problem 2
        
//         Set <Integer> numbers = new TreeSet <Integer> ();
//         numbers.add (17);
//         numbers.add (18);
//         numbers.add (-17);
//         numbers.add (17);
//         numbers.add (23);
//         
//         if (numbers.size() != 4)
//             System.out.println ("Not correct");
//         System.out.println (numbers);
//         if (! numbers.contains (23))
//             System.out.println ("Not correct");
//         numbers.clear();
//         if (! numbers.isEmpty())
//             System.out.println ("Not correct");
}
  
}