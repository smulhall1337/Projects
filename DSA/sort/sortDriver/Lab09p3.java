package sort.sortDriver;
import sort.*;
import list.*;

/**
 * Test HeapSort by sorting a list of Students.
 * 
 * 
 * @author (sdb) 
 * @version (Nov 2014)
 */
public class Lab09p3
{
    public static void main ()
    {
   List <Student> students = new ArrayList <Student> ();
   students.add (new Student ("jim", "232"));
   students.add (new Student ("mary", "242"));
   students.add (new Student ("al", "262"));
   students.add (new Student ("jim", "235"));
   students.add (new Student ("harry", "272"));
   students.add (new Student ("jim", "231"));
   students.add (new Student ("james", "232"));
   
   System.out.println ("Before sorting, students = " + students);
   
   HeapSort<Student> sorter = new HeapSort <Student> (students);
   sorter.sort ();
   
   System.out.println ("After sorting, students = " + students);
}
   
}