package tree;

/**
 * Creates a tree expression of a prefix arithmetic expression, and if possible, 
 * calculate and return the value of the evaluation
 * 
 * so if we have 
 * a+b*c
 * prefix would be 
 * * + a b c
 * 
 * and that would look like
 * 
 *          [*]
 *          /\
 *         /   \
 *        [+]   [c]
 *        /\
 *       /  \ 
 *     [a]  [b] 
 * 
 * @author (Sean Mulhall) 
 * @version (10.29.2014)
 */
public interface Expr
{
    /**
     * evaluate and return the if possible
     */
    int eval();

    /**
     * simplyfy the  expression if possible
     * (a+b)/(b+a) = 1
     */
    Expr simplify();
    
    String toString();
    
    boolean equals(Object obj);
    
}
