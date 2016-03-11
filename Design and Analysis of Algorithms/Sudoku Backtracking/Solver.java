import java.util.Scanner;
import java.io.*;
import java.util.*;//ArrayLists and HashSet
import java.lang.Math;
/**
 * Solver basically takes in the boards as command line arguments and passes all the work
 * to game. IT contains all the checks to make sure the board is valid as well as handle a few exceptions 
 * 
 * @author Sean Mulhall & Dyona Tate 
 * @version fall 2015
 */
public class Solver
{
    public static void main(String[] args)
    {
        // declare instance of Timer object & start timer
        Timer timer = new Timer();
        timer.start();

        ArrayList<Integer> rowArray; //array that stores row values
        ArrayList<Integer> colArray; // array that stores column values

        for (int j = 0; j <= args.length-1; j++)
        {
            File infile = new File(args[j]);

            if(j > 0){
                System.out.println("next board\n");//make multiple boards look pretty
            }

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

                    Game game = new Game(board, width, height, ctr);
                    game.createEmptyCells(ctr, rowArray, colArray);
                    // Attempt to Solve board and output appropriate answer
                    if(game.Solve(0) == false) {
                        System.out.println("This board cannot be solved!");
                    }

                    else {
                        //Output completed board
                        System.out.println("\nBoard Solved!\n");
                        for(int r = 0; r < (height*width); r++){
                            for(int c = 0; c < (width*height); c++){
                                if(r == ((width*height)-1) && c == ((width*height)-1) && game.emptyCells[ctr-1].row == r && game.emptyCells[ctr-1].col == c){
                                    //some real dirty code to print out the last blank value
                                    //instead of a zero when the very last cell in the board is
                                    //a zero. I plan on fixing this in the future
                                    //
                                    //update 3/10/16: STILL plan on fixing this in the future. ughhh
                                    System.out.println(game.emptyCells[ctr-1].possVals.get(0)+ " ");
                                    break;
                                }
                                System.out.print(game.board[r][c]+" ");
                            }
                            System.out.print("\n");
                        }
                        System.out.print("\n");
                    }

                }
            }

            catch(FileNotFoundException fnfe)
            {
                System.out.println("File not found!");
            }
        }
        // stop Timer object
        timer.stop();
        System.out.println("Computation time: " + timer.getDuration() + " milliseconds");
    }
}
