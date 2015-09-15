package set;
import set.*;
import list.Iterator;

/**
 * Test both TreeSet and HashSet.
 * 
 * @author (sdb) 
 * @version (Nov 2014
 */
public class SetDriver
{
  
    
    public static void main()
    {  
        Set <String> friends = new TreeSet <String> ();
        initialize (friends);
        
        Set <String> relatives = new HashSet <String> ();
        if (! relatives.isEmpty())
            System.out.println ("Not Correct");
        initialize (relatives);
        if (relatives.isEmpty())
            System.out.println ("Not Correct");
        System.out.println ("We have " + friends.size() + " friends");
        System.out.println ("We have " + relatives.size() + " relatives");
        System.out.println ("friends are " + friends);
        System.out.println ("relatives are " + relatives);
        
        if (! friends.equals (relatives))
            System.out.println ("Not Correct");
        if (! relatives.equals (friends))
            System.out.println ("Not Correct");
        friends.add ("susie");
        if (friends.equals (relatives))
            System.out.println ("Not Correct");
        if (relatives.equals (friends))
            System.out.println ("Not Correct");
        if (friends.equals ("oops"))
            System.out.println ("Not Correct");
    }
    
    
    
    private  static void initialize(Set<String> set)
    {   set.add ("sue");
        set.add ("jim");
        set.add ("sue");
        set.add ("harry");
        set.add ("sue");
        set.add ("mary");
        
    }
}