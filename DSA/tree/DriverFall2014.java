package tree;

import tree.*;
import list.*;

/**
 * Test the binary search tree implementation.
 * 
 * @author (sdb) 
 * @version (Oct 2014)
 */
public class DriverFall2014
{
    
    public static void main( )
    {
    BinaryTree <String> kids = new EmptyBinarySearchTree <String> ();
    
    if (!kids.isEmpty ())
        System.out.println ("Not correct 1");
    
    kids = kids.add ("kimmy");
    kids = kids.add ("jimmy");
    kids = kids.add ("susie");
    kids = kids.add ("joe");
    kids = kids.add ("sue");

    // With an inorder traversal, names should appear in alphabetic order
      System.out.println (kids);
      
      kids.remove ("kimmy");
      System.out.println (kids);
      kids.add ("kimmy");
      System.out.println (kids);
      kids.remove ("susie");
      System.out.println (kids);
      
      if (! kids.get("sue").equals ("s" + "u" + "e"))
        System.out.println ("Not correct 1");

      // Iterator should also provide an inorder traversal
      Iterator <String> itty = kids.iterator();
      while (itty.hasNext())
        System.out.println (itty.next());
        
//Uncomment the following when ready to test Expression trees
      Expr sum1 = new Sum(new Variable ('a'), new Variable ('b'));      // a + b
      Expr sum2 = new Sum (new Variable ('b'), new Variable ('a'));     // b + a
      
      Expr q = new Quotient (sum1, sum2);
      System.out.println ("q is " + q);
      
      System.out.println ("q simplifies to " + q.simplify());
      
    }
}