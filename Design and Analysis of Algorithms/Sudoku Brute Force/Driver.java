
/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables - replace the example below with your own
    public static void main(String[] args)
    {
        for (String s: args)
        {
            System.out.println("Testing user board\n");
            Game game0 = new Game(s);
        }

    }
}
