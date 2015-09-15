package queue;
import queue.*;
import list.*;

/**
 * Test the PriorityQueue.
 * 
 * @author (sdb) 
 * @version (Dec 2014)
 */
public class PriorityDriverLab13Part1
{
   public static void main()
   {    
       QueueADT <Integer> nums = new PriorityQueue<Integer> ();
       nums.add (20);
       nums.add (15);
       nums.add (17);
       nums.add (18);
       nums.add (18);
       nums.add (22);
       
       System.out.println (nums);           //  [22,18,20,15,18,17]
       
       System.out.println (nums.remove ());
       
       System.out.println (nums);           //  [20,18,17,15,18]
       System.out.println (nums.remove ());
       System.out.println (nums);           //  [18,18,17,15]
       nums.add (3);
       nums.add (22);
       nums.add (20);
       
       System.out.println (nums);           //   [22,18,20,15,3,17,18]
       System.out.println (nums.remove ());
       System.out.println (nums);           //   [20,18,18,15,3,17]
       System.out.println (nums.remove ()); 
       System.out.println (nums);           //   [18,18,17,15,3]
       System.out.println (nums.remove ());
       System.out.println (nums);           //   [18,15,17, 3]
       
       System.out.println (nums.peek());    //   18
       
       for (int i=0; i<4; i++)
            {   System.out.println (nums.remove ());
                System.out.println (nums);
            }
       
    }

}