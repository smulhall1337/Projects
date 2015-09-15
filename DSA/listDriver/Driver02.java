package listDriver;
import list.*;

/**
 * Lab 2
 * Test the List and Iterator classes
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class Driver02
{
/**
 *  This main method tests the List classes
 *  for lab 2, Data Structures and Algorithms
 */
    public static void main ( )
    {   
        WordList wordListGenerator = new WordList();
        List<String> words1 = wordListGenerator.getWordList();

        System.out.println (words1.get (1000));          // These should take about the same time
        System.out.println (words1.get (113000));
        
        List <String> words2 = new LinkedList<String>();
        // Add all words from words1 to words2
        Iterator <String> itty = words1.iterator();
        while (itty.hasNext())
            words2.add (itty.next());
        
        if (! (words1.equals (words2)))
            System.out.println ("Not correct");
        
        if (! (words2.equals (words1)))
            System.out.println ("Not correct");
            
        words2.add ("foo");
        
        if (words1.equals (words2))
            System.out.println ("Not correct");
            
        if (words1.equals (words2))
            System.out.println ("Not correct");
        
        System.out.println ("Done with problem 1 \n");
        
        
//////////////////////////////////
// Uncommment the following when ready to attempt problem 2
  
        System.out.println ("Testing problem 2 \n");
        
        words1 = new ArrayList <String> ();
        words2 = new LinkedList <String> ();
        
        for (int i=0; i<5; i++)
            {    words1.add ("str" + i);
                 words2.add ("str" + i);
                }
        
        // Test ArrayList
        itty = words1.iterator();
        while (itty.hasTwoMore())
              System.out.print (itty.next() + " " + itty.next() + " ");
        
        System.out.println ();
        words1.add ("str5");
        itty = words1.iterator();
        while (itty.hasTwoMore())
              System.out.print (itty.next() + " " + itty.next() + " ");
        
        System.out.println ();
        
        // Test LinkedList
        itty = words2.iterator();
        while (itty.hasTwoMore())
              System.out.print (itty.next() + " " + itty.next() + " ");
        
        System.out.println ();
        words2.add ("str5");
        itty = words2.iterator();
        while (itty.hasTwoMore())
              System.out.print (itty.next() + " " + itty.next() + " ");
        
     }
        
}

