/**
 *Quiz 3 Problem 2
 * 
 * @author (Sean Mulhall) 
 * @version (10.15.2014)
 */
public class Quiz3
{
    /** 
    @return the remainder of a/b    

    @param a and b are positive

     */
    public int mod (int a, int b)
    {
        if (a < b)
        {
            return a; 
        }
        return mod(a-b, b);
    }
}
