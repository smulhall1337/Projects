package queue;


/**
 * Test the Queue ADT.
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class DriverFall14
{

    public static void main ()
    {
        QueueADT <String> lunch = new Queue <String> ();
        
	
	System.out.println ("Testing Queue");
    testQueue (lunch);
    System.out.println ("\nTesting ArrayQueue");
    lunch = new ArrayQueue <String>();
    testQueue (lunch);
    }

    private static void testQueue (QueueADT <String> theQueue)
    {
    theQueue.add ("jim");
    theQueue.add ("mary");
    theQueue.add ("joe");
    
    System.out.println (theQueue);

    System.out.println (theQueue.remove());     // jim got his lunch
    System.out.println (theQueue.peek());       // mary is first in line
    theQueue.add ("harry");             // harry is at the end of the line
    System.out.println (theQueue);          // mary, joe, harry
    System.out.println (theQueue.remove());     // mary got her lunch
    System.out.println (theQueue);          // joe, harry
    
    }
}