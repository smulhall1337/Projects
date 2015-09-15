package tree;
import tree.*;

/**Test the Expression tree classes.
 * Quiz 5, Problem 2
 * 
 * @author (sdb) 
 * @version (Nov 2014)
 */
public class Quiz5p2Day
{
  public static void main()
  {   
      Expr a = new Variable ('a');
      Expr e = new Assign (a, new Constant (5));
      e.eval();
      
      Expr first = new Product (new Constant(1), a);                            // 1*a
      Expr diff1 = new Difference (first, new Variable ('b'));                  // 1*a - b
      Expr diff2 = new Difference (new Variable ('b'), a);                      // b - a
      Expr result = new Sum (diff1, diff2);
      
      System.out.println ("Result is " + result);
      System.out.println ("Simplified result is " + result.simplify());
      
      
  }
  
}