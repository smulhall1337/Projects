
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;
import java.io.*;
import java.util.*;//ArrayLists and HashSet
import java.lang.Math;

/**
 * The test class GameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameTest
{
    Game game;
    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        File infile = new File("p2.txt");
        ArrayList<Integer> rowArray; //array that stores row values
        ArrayList<Integer> colArray; // array that stores column values

        try
        {
            Scanner scanner = new Scanner(infile);

            //initialize our row and column lists
            rowArray = new ArrayList<Integer>();
            colArray = new ArrayList<Integer>();

            //read first two lines of input file
            // initialize width and height values
            int width = scanner.nextInt();
            scanner.nextLine();
            int height = scanner.nextInt();

            // initialize new instance of original board
            // and temporary board used to input possible answers
            // initialize ctr to count blank spaces in board
            int[][] board = new int [width*height][width*height];
            int ctr = 0;

            // reads in board values from file
            while(scanner.hasNextLine())
            {
                scanner.nextLine();
                for(int r = 0; r < (width*height); r++)//interate over each row
                {
                    for (int c = 0; c < (width*height); c++)//add each element into the row
                    {
                        int index = scanner.nextInt();
                        board[r][c] = index;//current value we are looking at
                        System.out.print(index+" ");
                        if (index == 0) //if current value = 0, we know its a blank
                        {
                            ctr++;//increment ctr
                            rowArray.add(r); //record row & col of empty cell
                            colArray.add(c);
                        }

                    }
                    System.out.print("\n");
                }

                game = new Game(board, width, height, ctr);
                game.createEmptyCells(ctr, rowArray, colArray);
            }
        }

        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found!");
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testBlankPositions()
    {
        //test a few of the blank cells to make sure they have the correct row and column
        
        //p2.txt should have 10 blanks
        assertEquals(game.count, 10);
        
        //ensure that our emptyCells list isnt blank
        assertNotNull(game.emptyCells);
        
        //first blank is at row 0, col 1
        assertEquals(game.emptyCells[0].row, 0);
        assertEquals(game.emptyCells[0].col, 1);
        
        //sixth blank is at row 3, col 1 
        assertEquals(game.emptyCells[5].row, 3);
        assertEquals(game.emptyCells[5].col, 1);
    }
    
    @Test
    public void testPossVals()
    {
        //test a few blank cells to ensure that they have the correct possible values
        //the second blank should have 1 possible value after looking at the row, col and box
        game.setPossValues(game.emptyCells[1]);
        cellInfo tempCell = game.emptyCells[1];
        
        //test the possVal list size
        assertEquals(tempCell.possVals.size(), 1);
        //that possibility should only be a 2
        assertEquals((Object)tempCell.possVals.get(0), (Object)2);//had to cast these as objects to 
                                                                  //keep the compiler from getting confused 
        
        //lets try the eighth blank
        game.setPossValues(game.emptyCells[7]);
        tempCell = game.emptyCells[7];
        //ahould only hace one possibility that should be a 6
        assertEquals(tempCell.possVals.size(), 1);
        assertEquals((Object)tempCell.possVals.get(0), (Object)6);
    }
    
    @Test
    public void testCheckSingleRow()
    {
        //tests our checkSingleRow method, which looks at the values in the given
        //blank cells row and removes the values that are already in that row
        //we'll look at the fifth blank
        cellInfo tempCell = game.emptyCells[4];
        //check the size of possvals, should be 6 for this board
        assertEquals(tempCell.possVals.size(), 6);
        
        //run the checkSingleRow method on this cell
        game.checkSingleRow(tempCell);
        
        //recheck the possVals, should have a size of 2
        //and those values should be a 1 and a 6
        assertEquals(tempCell.possVals.size(), 2);
        assertEquals((Object)tempCell.possVals.get(0), (Object)1);
        assertEquals((Object)tempCell.possVals.get(1), (Object)6);
    }
    
    @Test
    public void testCheckSingleCol()
    {
        //tests our checkSinglecol method, which looks at the values in the given
        //blank cells col and removes the values that are already in that col
        //we'll look at the fifth blank
        cellInfo tempCell = game.emptyCells[4];
        //check the size of possvals, should be 6 for this board
        assertEquals(tempCell.possVals.size(), 6);
        
        //run the checkSingleRow method on this cell
        game.checkSingleCol(tempCell);
        
        //recheck the possVals, should have a size of 1
        //and that value should be a 6
        assertEquals(tempCell.possVals.size(), 1);
        assertEquals((Object)tempCell.possVals.get(0), (Object)6);
    }
    
    @Test
    public void testCheckSingleBox()
    {
        //tests our checkSingleBox method, which looks at the values in the given
        //blank cells box and removes the values that are already in that box
        //we'll look at the sixth blank
        cellInfo tempCell = game.emptyCells[5];
        //check the size of possvals, should be 6 for this board
        assertEquals(tempCell.possVals.size(), 6);
        
        //run the checkSinglebox method on this cell
        game.checkSingleBox(tempCell);
        
        //recheck the possVals, should have a size of 2
        //values should be 1, and 6
        assertEquals(tempCell.possVals.size(), 2);
        assertEquals((Object)tempCell.possVals.get(0), (Object)1);
        assertEquals((Object)tempCell.possVals.get(1), (Object)6);
    }
    
    @Test
    public void testSolveAndCheck()
    {
        //just checks the solve and check methods
        //solves the puzzle and runs the check() methods
        assertFalse(game.check());//test before solving
        assertTrue(game.Solve(0));
        assertTrue(game.check());//test after 
    }
}

