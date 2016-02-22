package tree;


/**
 * defines a constant(an integer). used with the assignment class
 * 
 * @author (Sean Mulhall) 
 * @version (Fall 2014)
 */
public class Constant
implements Expr
{
    int value;
    
    public Constant(int value)
    {
        this.value = value;
    }
    
    public int eval()
    {
        return value;
        //nothing to evaluate
    }
    
    public Expr simplify()
    {
        //cannot simplify
        return this;
    }
    
    public String toString()
    {
        //cannot apply toString to a primitive, so just concatonate
        return value+" ";
    }
    
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Constant))
        {
            return false;
        }
        
        Constant c = (Constant) obj;
        //its a primitive, so we can use == on it
        return value == c.value;
    }
        
    
}
