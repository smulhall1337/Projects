import java.util.Scanner;
import java.io.*;
import java.util.*;//ArrayLists and HashSet
import java.lang.Math;
/**
 * Game is our main class that represents a user specified sudoku board with a set number of blanks, and attempts to solve the puzzle by use of brute force. 
 * We use a two-dimensional array that acts as our sudoku board. As we read in the sudoku board from a file, our class inspects the first two numbers, and sets that to the boxes width and height, respectively. 
 * Originally, our group had our sudoku board represented as an ArrayList of ArrayLists. We ran into issues when we attempted to copy our sudoku board into a temporary board. We discovered that each board was refrencing the same 
 * memory object, and was giving us incorrect results and exceptions. We solved this problem by using standard arrays, which ended up being much more simpple and effective than ArrayLists.
 *<br>
 *<br>
 * To store possible possible answers, we used an ArrayList that represented the possible entries for each blank cell. We would initialize our answerArray to be filled with as many '1's as there were blanks in the board.
 * As we tried each combination, we would increment the values in the answerArray by one (starting with least-significant, moving up if the values were to "roll-over"). We achieved this by creating a recursive method
 * that would increment the least-significant digit (the digit all the way to the "right" end of answerArray), then calling the function on itself if that digit equals the highest possible number a cell can be.
 *<br>
 *<br>
 * so...if we have a 3 x 3, highest possible number for a cell is 6
 *<br>
 * so when we have
 *<br>
 * 111...115
 *<br>
 * the next call would increment it to
 *<br>
 * 111...121
 *<br>
 *<br>
 * we continue this until we find an answer, or until we have exhausted all possible combinations.
 *<br>
 *<br>
 * To check possible answers, we would examine each individual column, row, and box for duplicates. If a duplicate is found, we know that answer can't be right.
 * We would first examine the columns. To do this, we would simply move across the top row, and examine each element "below" the element in the top row we we're inspecting.
 * Testing the rows was pretty much the same. We looked at the left-most column and examined all of the elements to the right.
 * Testing the boxes was tricky, but we found a solution using the boxes width as an index point, and then iterated over those specific cells.
 * 
 * 
 * @author Sean Mulhall & Dyona Tate 
 * @version 09/23/2015
 */
public class Game
{

    //Game variable declarations
    static int width, height, ctr; //the width, height, and number of zeros this board contains
    //static int attempts = 0;
    static int [][] board;//the original unmodified board
    static int [][] temp;//temporary board we will edit
    static ArrayList<Integer> answerArray;//array that stores our answer combination

    public Game(String args)
    {
        // declare instance of Timer object & start timer
        Timer timer = new Timer();
        timer.start();

        File infile = new File(args);

        try
        {
            Scanner scanner = new Scanner(infile);

            //read first two lines of input file
            // initialize width and height values
            width = scanner.nextInt();
            scanner.nextLine();
            height = scanner.nextInt();

            // initialize new instance of original board
            // and temporary board used to input possible answers
            // initialize ctr to count blank spaces in board
            board = new int [width*height][width*height];
            temp = new int [width*height][width*height];
            ctr = 0;

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
                        if (index == 0) //if current value = 0
                        {
                            ctr++;//increment ctr
                        }

                    }
                    System.out.print("\n");
                }

                answerArray = new ArrayList<Integer>(ctr);

                //initialize each entry in the answer array to 1
                for(int i = 0; i < ctr; i++) {
                    answerArray.add(1);
                }

                resetBoard();//initialize temp

                // Attempt to Solve board and output appropriate answer
                if(Solve(temp) == false)
                {
                    System.out.println("This board cannot be solved!");
                }

                if(Solve(temp) == true) 
                {
                    //Output completed board
                    System.out.println("\nBoard Solved!\n");
                    for(int r = 0; r < (height*width); r++)
                    {
                        for(int c = 0; c < (width*height); c++)
                        {
                            System.out.print(temp[r][c]+" ");
                        }
                        System.out.print("\n");
                    }
                    System.out.print("\n ");
                }

            }
        }

        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found!");
        }

        // stop Timer object
        timer.stop();
        System.out.println("Computation time: " + timer.getDuration() + " milliseconds");
    }

    /**
     * Attempts to solve the input board by brute force. This method traverses each element and replaces all zeros with the corrosponding elementt in our 
     * answerArray. If the current values in answerArray are incorrect, this method calls Increment() to increase the elements in answerArray by one, and tries again.
     * This continues until we have found the correct combination of digits, or until we try every possible combination of input.
     * 
     * @param theBoard the board we are solving
     * @returns true if this board is solved
     */
    public static boolean Solve(int[][] theBoard) {
        //made this method non-recursive because everytime we tried to run it, the stack 
        //would explode(overflow)

        //iterate elements in the board
        for (int attempts = 0; attempts < Math.pow((width*height), ctr); attempts++)
        {
            int pos = 0;//position in answerArray
            for(int r = 0; r < (width*height); r++) 
            {
                for(int c = 0; c < (width*height); c++) 
                {
                    if (board[r][c] == 0)
                    {
                        temp[r][c] =  answerArray.get(pos);
                        pos++;
                    }
                }
            }
            if (check(temp))
            {
                break;
            }
            Increment((answerArray.size())-1);//add one to our answer array
            resetBoard();//reset temp
        }

        if(check(temp) == false)
        {
            return false;
        }

        return true;
    }

    /**
     * This method increases our values stored in answerArray starting at the least significant (or "right-most") digit. 
     * If the least significant digit is the highest possible number that can be used for the board, this method returns that value back to one,
     * and increases the next most significant digit by one. This will repeat for all digits that need to roll over until we reach the end of the answerArray.
     * 
     * @param digit the current number in the answerArray that we are increasing. used to traverse through the answerArray recursively. should always be called from the outside as Increment(0)
     * @return void
     */
    public static void Increment(int digit)
    {
        //increase our answer 
        if(answerArray.get(digit) != (width*height))
        {
            answerArray.set(digit, answerArray.get(digit)+1);
        }

        else//rollover
        {
            answerArray.set(digit, 1);

            if(digit-1 >= 0)
            {
                Increment(digit-1);
            }
        }
    }

    /**
     * Checks the current temp board for correctness by checking each row, column, and box for duplicates, in that order. 
     * 
     * @param theBoard the board we are checking 
     * @return true if the board is correct
     */
    public static boolean check(int[][] theBoard)
    {
        //check columns for duplicates (moving over and down)
        for(int c = 0; c < (width*height); c++)//column. will only iterate over the first row
        {
            for(int r = 0; r < (width*height); r++)//row. iterates throgh the elements "under" [c]
            {
                for(int e = r+1; e < (width*height); e++)//compare elements in "lower" rows
                    if(theBoard[r][c] == theBoard[e][c])//compare the current element in the column to the ones
                    {   
                        return false;
                }
            }           
        }

        //check rows for duplicates
        for(int r =0; r < (width*height); r++)//r =the row we're looking at
        {
            for(int c = 0; c < (width*height); c++)//c = the number we dont want duplicates of
            {
                for(int e=c+1; e < (width*height);e++)//c = the number we're comparing to in the row
                {                                     //to the "right" of c
                    if (theBoard[r][c] == theBoard[r][e])
                    {
                        return false;
                    }
                }
            }
        }           

        if (checkBox(0) == false)//check boxes
        {
            return false;
        }
        return true;
    }

    /**
     * checks each individual "box" for duplicates. This method works by iterating over the width and height of each box, starting with the upperleft most box, moving down
     * through the left column of boxes. Once we hit the bottomleft most box, we move back up to the top and over to the next column to the right. We continue until we reach the
     * bottom right most box.
     * 
     * @param boxIndex starting position for checking the boxes in a specified column of boxes
     * @return true if the boxes do not have duplicates
     */
    public static boolean checkBox(int boxIndex)
    {
        for (int i = 0; i < (width*height); i += height)//iterate over al lthe boxes starting from the
        {                                               //"leftmost" colmn of boxes and moving to the right
            HashSet<Integer> dupes = new HashSet<Integer>();// check for duplicates
            for (int r = 0; r < height; r++)//check individual rows in a box
            {
                for (int e = boxIndex; e < width; e++)//check each element in the row
                {

                    if (!dupes.add(temp[r][e]))
                    {
                        return false;
                    }
                }
            }
        }

        boxIndex += width; //move over right to next column of boxes

        if(boxIndex < (width*height))
        {
            if(checkBox(boxIndex) == false)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * initializes and resets our temporary board after every attempt to solve
     * 
     * @param none
     * @return void
     */
    //returns a reset board containing zeros/initializes temp
    public static void resetBoard()
    {
        // resets temp back to the original board with zeros
        for(int r = 0; r < (width*height); r++)
        {
            //iterate the elements in each "row"
            for(int c = 0; c < (width*height); c++)
            {
                temp[r][c] = board[r][c];
            }
        }
    }
}