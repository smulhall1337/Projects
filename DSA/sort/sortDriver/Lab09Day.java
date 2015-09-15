package sort.sortDriver;
import tree.*;
import list.Iterator;

/**
 * Lab 8.
 * 
 * @author (sdb) 
 * @version (Oct 2014)
 */
public class Lab09Day

{
   public static void main()
   {    
       
       Expr e1 = new Sum (new Variable('x'), new Variable('y'));       //  x + y
        Expr e2 = new Sum (new Variable('y'), new Variable('x'));       //  y+x
        e2 = new Difference (e1, e2);                                   // (x+y) - (y+x)
        System.out.println (e2);
        System.out.println ("Simplifies to " + e2.simplify());                             // 0
        System.out.println();
        
        Expr e = new Quotient (new Variable('x'), new Variable('x'));       // x / x
        e = new Product ( new Variable('y'), e);                    //   y * (x/x)
        e = new Difference (e, new Variable('y'));                   //   y * (x/x) - y
        e = new Sum (e, new Constant(1));                             //   (y * (x/x) - y) + 1
        e = new Mod (new Variable ('z'), e);                          //   z % ((y * (x/x) - y) + 1)
        System.out.println (e);
        System.out.println ("Simplifies to " + e.simplify());        //    0
            e1 = new Difference (new Variable ('a'), new Variable ('b'));
            e2 = new Difference (new Variable ('b'), new Variable ('a'));
            if (e1.equals (e2))
                System.out.println ("Not correct");
            e1 = new Quotient (new Variable ('a'), new Variable ('b'));
            e2 = new Quotient (new Variable ('b'), new Variable ('a'));
            if (e1.equals (e2))
                System.out.println ("Not correct");
            e1 = new Mod (new Variable ('a'), new Variable ('b'));
            e2 = new Mod (new Variable ('b'), new Variable ('a'));
            if (e1.equals (e2))
                System.out.println ("Not correct");
            System.out.println ("\n  Lab 9");
              
          Expr a = new Variable ('a');
          Expr b = new Variable ('b');

//// If you uncomment the followintg line, an Exception should be thrown
          System.out.println (new Sum(a,b).eval());                    // Throws an Exception

          e1 = new Sum (new Assign(a, new Constant(5)),
                            new Assign (b, new Constant (7)));          // e1 = (a=5) + (b=7)
          System.out.println ("e1 is " + e1);
          System.out.println ("The value of e1 is " + e1.eval());
          
          
          System.out.println ("a is " + a.eval());
          System.out.println ("b is " + b.eval());
         
    }
}