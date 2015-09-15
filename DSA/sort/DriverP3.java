package sort;
import list.*;

/**
 * Test the sort algorithm for a list of students
 * 
 * @author (sdb) 
 * @version (Oct 2014)
 */
public class DriverP3
{
   
    public static void main()
    {   List <Student> roster = new ArrayList <Student> ();
        
        roster.add (new Student ("jim", "252"));   // name and ssn, gpa = 0.0
        roster.add (new Student ("joe", "144"));
        roster.add (new Student ("al", "543"));
        roster.add (new Student ("jim", "950"));
        roster.add (new Student ("mary", "333"));
        roster.add (new Student ("jim", "303"));
        
        System.out.println (roster);
        
        SelectionSort <Student> sorter = new SelectionSort <Student> (roster);
        sorter.sort();
       
        System.out.println (roster);
        
//         System.out.println ();
//         Student s = new Student ("jim", "100");
//         System.out.println (s.compareTo ("mary"));  
    }
        
}
