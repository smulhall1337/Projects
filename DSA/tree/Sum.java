package tree;
/**
 * Represents the expression that is the sum of two expressions 
 * every Sum will have a left and right Operand of type Expr
 * 
 * @author (Sean Mulhall) 
 * @version (10.28.2014)
 */
public class Sum
implements Expr
{
    Expr left, right;

    public Sum(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public int eval()
    {
        //since we dont know what the child expressions are, this can be tricky. We want to make sure they're
        //a Constant first, it'll be different if one or both of them are variables
        //or another operator
        /**
         * so if we have 
         *          [+]
         *          /\
         *         /   \
         *        [+]   [6]
         *        /\
         *       /  \ 
         *     [2]  [5]
         *     + = sum
         *     
         *     we'll want to call eval on both the left and the right
         */
        return left.eval() + right.eval();
    }

    public Expr simplify()
    {
        /**
         * this is interesting, theres a few conditions to look out for, for example if we have 
         * a + 0
         * we just want to return the left operand
         * 
         */
        left = left.simplify();
        right = right.simplify();
        if ((right instanceof Constant)&&(right.eval() == 0))
        {
            return left;
        }
        //for the opposite condition
        //0 + a
        if ((left instanceof Constant)&&(left.eval() == 0))
        {
            return right;
        }
        
        if ((right instanceof Difference)&&(left instanceof Difference))
        {
            //for this, we're simplifying (a-b)+(b-a) = 0
            //but since we cant call left.left or left.right
            //we had to cast the left and the right child (which happen to be Differences)
            //as a Difference so we could refrence each of their children and
            //perform the conditional check.
            //it seems redundant(and dirty for the most part) but it works
            Difference dLeft  = (Difference) left;
            Difference dRight = (Difference) right;
            
            if((dLeft.left.equals(dRight.right))&&(dLeft.right.equals(dRight.left)))
            {
                return new Constant(0);
            }
        }
            

        /**
         * but what if we had something like
         * 
         *           [+]
         *           /\
         *          /  \
         *        [a]  [+]
         *              /\
         *             /  \
         *            [b]  [0]
         *      we wanna go down until we cant anymore
         *      so we add those two statements at the top
         *      left = left.simplify
         *      right = right.simplify
         */            
        //if we cant perform any simplifications
        return this;

        //in the future, we might want a + a = 2*a
    }
    
    public String toString()
    {
        return"("+ left.toString() + " + " + right.toString() +")";
    }

    public boolean equals(Object obj)
    {
        //does this sum equal some other sum?(hehe)
        if (!(obj instanceof Sum))
        {
            return false;
        }
        Sum s = (Sum) obj;
        //MAKE SURE THIS LOGIC WORKS
        if((left.equals(s.left)&&(right.equals(s.right))) || ((left.equals(s.right)&&(right.equals(s.left)))))
        {
            return true;
        }
        return false;
    }
}
