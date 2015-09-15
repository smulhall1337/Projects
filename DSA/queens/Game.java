package queens;
/**
 * place n queens on an NxN chess board so that no two queens are attacking eachother 
 * 
 * @author (Sean Mulhall) 
 * @version (11.20.2014)
 */
public class Game
{
    public static final int SIZE = 16;
    
    public static void main()
    {
        Game game = new Game();
        Board b = new Board();
        b = game.placeQueens(0, b);
        System.out.println (b);
    }
    
    /**
     * @return a board where all queens are safely placed(if possible)
     * or null if not possible
     * @pre Queens have been safely placed on the given Board board in cols 0...col-1
     */
    private Board placeQueens(int col, Board b)
    {
        Board result;
        for (int row = 0; row < SIZE; row++)
        {
            result = new Board(b);
            result.queens.add (new Position (row, col));
            if (! (result.check()))
            {
                if (col == SIZE - 1)
                {
                    return result;//base case
                }
                else
                {
                    result = placeQueens(col+1, result);//recursive case
                }
                if (result != null)
                {
                    return result;
                }
            }
        }
        return null; //could not be solved with the given board
    }
}

