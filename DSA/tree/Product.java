package tree;

/**
 * Reference to a multiplication operation
 * 
 * @author (Sean Mulhall) 
 * @version (10.29.2014)
 */
public class Product
implements Expr
{
    Expr left, right;

    public Product(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public int eval()
    {
        return left.eval() * right.eval();
    }

    public Expr simplify()
    {
        //a couple things we can do
        //a * 0 = 0, a * 1 = a, 
        left = left.simplify();
        right = right.simplify();
        if (right instanceof Constant)
        {
            if (right.eval() == 0)
            {
                return right;
            }
            if  (right.eval() == 1)
            {
                return left;
            }
        }
        if (left instanceof Constant)
        {
            if (left.eval() == 0)
            {
                return left;
            }
            if  (left.eval() == 1)
            {
                return right;
            }
        }       
        //if we cant perform any simplifications
        return this;
    }

    public String toString()
    {
        return"("+ left.toString() + " * " + right.toString() +")";
    }

    public boolean equals(Object obj)
    {
        //does this equal another product?
        if (!(obj instanceof Product))
        {
            return false;
        }
        Product p = (Product) obj;
        //multiplication is commutitiave! so same conditions as addition
        if((left.equals(p.left)&&(right.equals(p.right))) || ((left.equals(p.right)&&(right.equals(p.left))))) 
        {
            return true;
        }
        return false;
    }
}
