package tree;
/**
 * represents a variable, an integer that could change
 * 
 * @author (Sean Mulhall) 
 * @version (10.28.2014)
 */
public class Variable
implements Expr
{
    char name; //dont ant long variable names, just a,b,c...etc
    int varval;     //if the Assign operator is ever used on a variable, we want
    //variable to keep track of what it was assigned 
    boolean assigned;

    public Variable(char name)
    {
        this.name = name;
        varval = 0;
        assigned = false;
    }

    public int eval()
    {   
        if (assigned == true)
        {
            return varval;
        }
        throw new IllegalArgumentException();
    }

    public Expr simplify()
    {
        return this;
        //like Constant, we cant simplify
    }

    public String toString()
    {
        //same as in constant
        return name + " ";
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Variable))
        {
            return false;
        }
        Variable v = (Variable) obj;
        return this.name == v.name;
    }
}
