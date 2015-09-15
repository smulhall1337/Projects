package Lab04;

import java.util.Random;

/**
 * Simulate print queues and print jobs assigned to queues
 * 
 * @author (sdb) 
 * @version (Feb 2012)
 */
public class PrintQueues
{
    private Printer printer1;
    private Printer printer2;
    private static double DOC_CREATION_PROBABILITY = 0.7;
    private static int MAX_DOC_SIZE = 25;
    private int step = 0;
    
    Random rand = new Random();
    
    public PrintQueues()
    {   printer1 = new Printer (5, "ROB 312");           // print speed is 5
        printer2 = new Printer (3, "Advanced Lab");      // print speed is 3
    }
    
    public void simulateOneStep()
    {   Doc doc;
        if (rand.nextDouble() < DOC_CREATION_PROBABILITY)
            {   doc = new Doc (1 + rand.nextInt(MAX_DOC_SIZE));
                if (printer1.size() > printer2.size())
                    printer2.add (doc);             // add to smaller queue
                else
                    printer1.add (doc);
            }
         printer1.print();  
         printer2.print();
         step++;
         System.out.println ("Step " + step + ": " + printer1 + ", " + printer2 + '\n');
    }
    
    public void run (int n)
    {   for (int i=0; i<n; i++)
            simulateOneStep();
        }
        
    public void runLong ()
    {   run (500);  }
}