 

import list.*;

/**
 * Data Structures, Quiz 1, Problem 2
 * Do  not change this Driver
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class Quiz1
{
    public static void main ()
    {
    ArrayList <Integer> grades = new ArrayList <Integer> ();
    
    System.out.println (grades);
    System.out.println ("Has duplicate: " + grades.hasDuplicate() + "\n");
    
    grades.add(17);
    grades.add(27);
    grades.add(37);
    grades.add(7);
    
    System.out.println (grades);
    System.out.println ("Has duplicate: " + grades.hasDuplicate() + "\n");
    
    grades.add (17);
    System.out.println (grades);
    System.out.println ("Has duplicate: " + grades.hasDuplicate() + "\n");
    
    grades.add (0);
    System.out.println (grades);
    System.out.println ("Has duplicate: " + grades.hasDuplicate() + "\n");
    
    grades.add (0, 75);
    System.out.println (grades);
    System.out.println ("Has duplicate: " + grades.hasDuplicate() + "\n");
    
        
    }
    
    
        



}
