import java.util.*;
/**
 * represents a blank cell
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cellInfo
{
    int row;
    int col;
    int value = 0; 
    ArrayList<Integer> possVals;
    
    public cellInfo(int row, int col, int dim)
    {
        this.row = row;
        this.col = col;
        possVals = new ArrayList<Integer>();
        
        //set initial values of possVals ArrayList
        for(int i = 1; i <= dim; i++) {
            possVals.add(i);
        }
    }
   //10.20.2015: removed all methods below here as we never use them

}
