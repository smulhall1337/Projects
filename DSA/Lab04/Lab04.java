package Lab04;
import queue.*;

/** Test the Queue class 
 *  @author (sdb)
 */
public class Lab04
{  public static void main()
   {    QueueADT<Character>  q = new Queue<Character>(), 
                             q2 = new Queue<Character>();
    setup(q);

    System.out.println ("q is " + q);
    if (q.size() != 3)
        System.err.println ("Error in size() or add()");

    q.removeAll();
    if (! q.isEmpty())
        System.err.println ("Error in removeAll() or isEmpty()");
        
    setup (q);
    
    // Testing equals(Object)
    if (q.equals (new Integer(3)))
        System.err.println ("Error in equals(Object)");
    q2.add ('b');
    q2.add ('c');
    q2.add ('d');
    if (q.equals (new Integer(3)))
        System.err.println ("Error in equals(Object)");
    q2.remove();
    if (q.equals (q2))
        System.err.println ("Error in equals(Object)");
    q2.add('e');
    System.out.println ("q2 is " + q2);
    if (!q.equals (q2))
        System.err.println ("Error in equals(Object)");
    q2.add('f');
    q2.remove();
    if (q.equals (q2))
        System.err.println ("Error in equals(Object)");
    
    System.out.println ("Testing complete");
    }
    
    private static void setup (QueueADT<Character> q)
    {   // put characters into the given queue
    q.add('a');
    q.add('b');
    q.add('c');
    q.remove();
    q.add('d');
    q.remove();
    q.add('e');
}
}