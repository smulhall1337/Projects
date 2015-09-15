package queens;
/**
 * A position consists of a row and column
 * 
 * @author (Sean Mulhall) 
 * @version (11.20.2014)
 */
public class Position
{
   int row, col;
   
   public Position(int r, int c)
   {
       row = r;
       col = c;
    }
    
    public boolean equals(Object object)
    {
        if (! (object instanceof Position))
        {
            return false;
        }
        Position other = (Position) object;
        return row == other.row && col == other.col;
    }
    
    /**
     * return ture if the given position is attacking this position
     */
    public boolean isAttacking (Position p)
    {
        return sameCol(p) || sameRow(p) || sameMajDiag(p) || sameMinDiag(p);
    }
    
    public boolean sameCol (Position p)
    {
        return p.col == col;
    }
    
    public boolean sameRow(Position p)
    {
        return p.row == row;
    }
    
    /**
     * a minor diagonal(or any diagonal) is a combination of row and column
     * except minor diagonal goes "down" the grid from left to right
     * diagonal would be
     * (r,c)
     * (0,1)
     * (1,2)
     * (2,3)
     * etc...
     * if a position occupies the same minor diagonal, subtracting the two positions will yield the same result
     */
    public boolean sameMinDiag(Position p)
    {
        return p.row - p.col == row - col; 
    }
    
    /**
     * same as above, but going up the grid from left to right and adding 
     */
    public boolean sameMajDiag(Position p)
    {
        return p.row + p.col == row + col;
    }
    
    public String toString()
    {
        return "("+row+","+col+")";
    }
}
