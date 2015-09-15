package tree;

/**
 * Write a description of class Difference here.
 * 
 * @author (Sean Mulhall) 
 * @version (11.5.2014)
 */
public class Difference
implements Expr
{
    Expr left, right;

    public Difference(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public int eval()
    {
        return left.eval() - right.eval();
    }

    public Expr simplify()
    {
        //not a whole lot we can do to simplify subtraction 
        //but we can do a-0=a
        left = left.simplify();
        right = right.simplify();
        if ((right instanceof Constant)&&(right.eval() == 0))
        {
            return left;
        }

        if((right instanceof Expr)&&(left instanceof Expr))
        {
            if (left.equals(right))
            {
                return new Constant(0);
                //if we cant perform any simplifications
            }
        }
        return this;

    }

    public String toString()
    {
        return"("+ left.toString() + " - " + right.toString() +")";
    }

    public boolean equals(Object obj)
    {
        //does this sum equal some other difference
        if (!(obj instanceof Difference))
        {
            return false;
        }
        Difference d = (Difference) obj;
        //since subtraction isn't commutitive, both children have to be in the same position
        if((left.equals(d.left)&&(right.equals(d.right)))) 
        {
            return true;
        }
        return false;
    }

}
