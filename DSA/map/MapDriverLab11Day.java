package map;

import map.*;
import set.*;
import list.Iterator;
import sort.Student;

/**
 * Test the HashMap
 * Kept this here because it was a particularly tricky problem that I like 
 * to refrence from time to time. 
 * 
 * @author (Sean Mulhall) 
 * @version (Mar 2012)
 */
public class MapDriverLab11Day
{
    public static void main()
    {   Map <String, Student> students = new HashMap <String, Student> ();

        assert students.isEmpty() ;

        students.put ("343", new Student ("jim", "343"));
        students.put ("444", new Student ("harry", "444"));
        students.put ("333", new Student ("mary", "333"));
        students.put ("211", new Student ("sarah", "211"));
        students.put ("333", new Student ("susie", "333"));
        System.out.println ("students are " + students);
        assert students.size() == 4 : "Error in size method for HashMap";

        assert students.get("333").equals (new Student ("susie", "333")) : "Error in get for HashMap";
        students.clear();
        students.put ("343", new Student ("jim", "343"));
        assert students.remove ("343").equals (new Student ("jim", "343"));
        assert students.isEmpty() ;
        assert students.size() == 0;

        ///////////////////////
        // Uncomment the following lines when ready for problem 3
        ///////////////////////
        System.out.println();
        Map <String, Integer> grades = new HashMap <String, Integer> ();
        grades.put ("jim", 95);
        grades.put ("susie", 100);
        grades.put ("harry", 88);
        grades.put ("sarah", 99);

        System.out.println ("grades: " + grades);
        System.out.println ("Average grade is " + average (grades));
        grades.clear();
        System.out.println ("Average, for an empty map: " + average(grades));

    }
    // Complete the following private method
    // PUT YOUR NAME HERE
    /** @return the average of all values in the given map */
    private static double average (Map <String, Integer> map)
    {
        //this was tricky
        //since its private, I cou;dn't really use anythign from the Entry 
        //class, or reference generic types. 
        //this was due to the nature of the lab. In reality I could just set it to public but that was deemed incorrect 
        //according to our professor. 
        //However, thanks to the keySet() method in HashMap, it made things simple
        if (! (map.size() == 0))
        {
            double avg = 0;
            double total = 0;
            Set<String> set = map.keySet();//call keySet() to recieve all the 
                                            //keys in our map
            Iterator itty = set.iterator();//set up the iterator
            while (itty.hasNext())
            {
                total += map.get((String)itty.next());
                //then just call the get() method using the keys recieved 
                //from the iterator
                //I casted it as (string) here because I forgot to 
                //declare the type when I instantiated the Iterator
                //I could change it, but then I'd forget what I did
            }
            avg = total/map.size();
            return avg;//BOOM!
        }
        return 0;//if theres no elements in our map, average is zero

    }

}
