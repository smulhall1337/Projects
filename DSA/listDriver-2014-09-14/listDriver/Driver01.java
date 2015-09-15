package listDriver;
import list.*;

/**
 * Lab 1
 * Test methods added to the List interface
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class Driver01
{
/**
 *  This main method tests the List classes
 *  for lab 1, Data Structures and Algorithms
 */
    public static void main ( )
    {   List<String> friends = new ArrayList <String> ();

    friends.add ("joe");
    friends.add ("mary");
    friends.add ("jim");
    friends.add ("joe");                            // Lists may contain duplicate elements
    friends.add (2, "sally");                       // Insert at position 2
    friends.remove (0);                             // Remove joe at position 0 
    System.out.println (friends.size());            // should be 4

    String s1 = "sal";
    String s2 = "ly";       // s1 + s2 is "sally"
    System.out.println ("sally is at position " + friends.indexOf(s1 + s2));  // should be 1
    if  (friends.contains ("Jim"))
        System.out.println ("Not correct"); 
    if (!friends.contains ("jim"))
        System.out.println ("Not correct"); 
        
        
//////////// Uncomment the following when ready for problem 2 
    System.out.println ("\n\n Testing problem 2");   
    System.out.println ("The list of friends is " + friends);
    List <String> enemies = null;
    System.out.println (enemies);                   // should be null
//     if (friends.equals (enemies))
//         System.out.println ("Not correct");
//     enemies = new ArrayList <String> ();
//     System.out.println (enemies);                   // should be an empty list
//     if (friends.equals (enemies))
//         System.out.println ("Not correct");
//     enemies.add ("mary");
//     enemies.add ("sally");
//     enemies.add ("jim");
//     if (friends.equals (enemies))
//         System.out.println ("Not correct");
//     enemies.add ("joe");    
//     System.out.println ("enemies are: " + enemies); 
//     if (!friends.equals (enemies))
//         System.out.println ("Not correct");
//     if (!enemies.equals (friends))
//         System.out.println ("Not correct");
//     enemies.add ("jim");
//     if (friends.equals (enemies))
//         System.out.println ("Not correct");


//////////// Uncomment the following when ready for problem 3 
//         System.out.println ("\n\n Testing problem 3");    
//        friends = new LinkedList <String> ();
//        friends.add ("joe");
//         friends.add ("mary");
//         friends.add ("jim");
//         friends.add ("joe");                            // Lists may contain duplicate elements
//         friends.add (2, "sally");                       // Insert at position 2
//         friends.remove (0);                             // Remove joe at position 0 
//         System.out.println (friends.size());            // should be 4
//         
//     
//         System.out.println ("sally is at position " + friends.indexOf(s1 + s2));  // should be 1
//         
//         if  (friends.contains ("Jim"))
//             System.out.println ("Not correct"); 
//         if (!friends.contains ("jim"))
//             System.out.println ("Not correct"); 
//             
//         System.out.println ("friends: " + friends);
//         System.out.println ("enemies: " + enemies);
//         if (friends.equals (enemies))
//             System.out.println ("Not correct");
//         friends.add ("jim");
//         if (!friends.equals (enemies))
//             System.out.println ("Not correct");
//         if (!enemies.equals (friends))
//             System.out.println ("Not correct");
//             
    
        
    }
}

