package tree;
/**
The assignment operator in java actually does two things:
1.  It changes the value of the variable which is the left operand (we call this a side effect of the operator).
2.  It returns a result which is the value being assigned.
Thus
x = (y = 2) + (z = 3);
would assignment the value 2 to y, the value 3 to z, and the value 5 to x, all in one statement.
For Expression Trees,  wish to be able to assign a value to a Variable. To assign the value 3 to the variable x, the client would use 
new Assign (x, new Constant (3)).   This means x = 3.
 *@author (Sean Mulhall)
 *@version (11.5.2014)
 */
public class Assign
implements Expr
{
    Variable left;
    Expr right;//I assume, since we're assigning something to a variable
    //the left part of this statement SHOULD always be a variable(I think)
    int value;//the simplified version of right           

    public Assign(Expr left, Expr right)
    {  
        if (left instanceof Variable)
        {
            this.left = (Variable)left;
            this.right = right;
        }
        //throw new IllegalArgumentException("Left Operand must be a variable");
    }

    public int eval()
    {
        value = right.eval();
        left.varval = value;
        left.assigned = true;
        return value;
    }

    public Expr simplify()
    {
        return new Constant(value);
    }

    public String toString()
    {
        return "("+left + " = " + right.simplify()+")";
    }

}
