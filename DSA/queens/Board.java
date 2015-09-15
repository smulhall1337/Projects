package queens;
import java.util.*;
/**
 * a chess board specifically made for the Queens problem.
 * each board stores a list of positions at which we have placedd a Queen
 * 
 * @author (Sean Mulhall) 
 * @version (11.20.2014)
 */
public class Board
{
    List <Position> queens;//positions at which queens have been placed on this board;

    public Board()
    {
        queens = new LinkedList <Position> ();
    }

    //copy constructor
    public Board(Board b)
    {
        queens = new LinkedList <Position> (b.queens);
        //we'll need to set up a constructor in linked list to copy all values from our previous list 
        //into a new list(or just use java.util)(which is what I did)
    }

    /**
     * return true if some queen is attacking some other queen on this board
     */
    public boolean check()
    {
        //Check every pair of positions in the list queens 
        Iterator <Position> itty1 = queens.iterator();
        Iterator <Position> itty2;
        while (itty1.hasNext())
        {
            Position p = itty1.next();
            itty2 = queens.iterator();
            while (itty2.hasNext())
            {
                Position other = itty2.next();
                if(! p.equals(other))
                {
                    if (p.isAttacking (other))
                    {
                        System.out.println(toString());
                        return true;
                    }
                }
            }
        }
        System.out.println(toString());
        return false;
    }

    public String toString()
    {
        String result = queens.toString() + "\n";

        for (int row = 0; row < Game.SIZE; row++)
        {
            result += "\n";
            for (int col = 0; col < Game.SIZE; col++)
            {
                if (queens.contains (new Position (row, col)))
                {
                    result += " Q ";
                }
                else 
                {
                    result += " . ";
                }
            }
        }
        return result;
    }
}
