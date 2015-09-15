package tree;

/**
 * Division Operation
 * 
 * @author (Sean Mulhall) 
 * @version (10.29.2014)
 */
public class Quotient
implements Expr
{
    Expr left, right;

    public Quotient(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public int eval()
    {
        return left.eval() / right.eval();
    }

    public Expr simplify()
    {
        //well, we could do what we have in the Expr interface
        //(a+b)/(b+a) = 1
        left = left.simplify();
        right = right.simplify();
        Constant result; 
        if ((right instanceof Expr)&&(left instanceof Expr))
        {
            if (right.equals(left))
            {
                //since this methos is returning and Expr, and not an int, we cant just say "return 1"
                //we have to make a new Constant, and have it return that
                result = new Constant(1);
                return result;
            }

            if (right.eval() == 1)
            {
                return left;
            }
        }
        //if we cant perform any simplifications
        return this;
    }

    public String toString()
    {
        return"("+ left.toString() + " / " + right.toString() +")";
    }

    public boolean equals(Object obj)
    {
        //does this sum equal some other difference
        if (!(obj instanceof Quotient))
        {
            return false;
        }
        Quotient q = (Quotient) obj;
        if (left.equals(q.left)&&(right.equals(q.right)))
        {
            return true;
        }
        return false;
    }

}
