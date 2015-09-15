package tree;

/**
 * Modulous operation
 * 
 * @author (Sean Mulhall) 
 * @version (10.29.2014)
 */
public class Mod
implements Expr
{
    Expr left, right;

    public Mod(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public int eval()
    {
        return left.eval() % right.eval();
    }

    public Expr simplify()
    {
        //we can simplfy a%a=0, and a%1 = 0
        left = left.simplify();
        right = right.simplify();
        Constant result; 
        //this is the part I missed for the lab8(MAD ABOUT)
        //it didnt just have to be a constant...ANYTHING mod 0, or 1 = zero
        //so I had to change this conditional to see if the left hand was just an expression
        //might be a better way to do this...
        if ((right instanceof Constant)&&(left instanceof Expr))
        {
            if (right.equals(left))
            {
                result = new Constant(0);
                return result;
            }

            if (right.eval() == 1)
            {
                result = new Constant(0);
                return result;
            }
        }
        return this;
    }

    public String toString()
    {
        return"("+ left.toString() + " % " + right.toString() +")";
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Mod))
        {
            return false;
        }
        Mod m = (Mod) obj;
        if (left.equals(m.left)&&(right.equals(m.right)))
        {
            return true;
        }
        return false;
    }
}
