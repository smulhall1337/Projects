import java.util.Scanner;
import java.io.*;
import java.util.*;//ArrayLists and HashSet
import java.lang.Math;
/**
 * Game handles all of the heavy lifting. Game takes the board passed from Solver,
 * and stores the height, width, and the number of blank cells the board contains.
 * 
 * From there, the game attempts to solve the puzzle by inspecting a blank cell, more specifically; 
 * what is already contained in the blanks row, column, and box. From there. we can drastically reduce the
 * number of possible combinations to try. Each blank is stored as a cellInfo object. The object stores
 * the possible entries a blank could be. As we move through the board, if we get to a blank possibility that is 
 * infeasible, and the algorithm "backtracks" to the last blank, we remove that possibility from the cellInfos 
 * possible combinations.
 * 
 * the process repeats until a working solution is found, or the program finds no
 * possible solution for the board. 
 * 
 * @author Sean Mulhall & Dyona Tate 
 * @version 10/21/2015
 */
public class Game
{

    //Game variable declarations
    int width, height, count; //the width, height, and number of zeros this board contains
    int [][] board;//the original unmodified board
    cellInfo [] emptyCells;//array of Empty Cells in this board

    public Game(int[][] board, int width, int height, int count){
        //store all variaables
        this.board = board;
        this.width = width;
        this.height = height;
        this.count = count;
    }

    public void setPossValues(cellInfo tempCell) {
        //this is what i was talking about
        //basically, we treat this almost as if we were creating a new
        //blankCell in tempcells place, but to avoid al lthat work,
        //we would just reinitialize its possVals array
        //and then compare it with the new dimensions around the blank
        if(tempCell.possVals.size() == 0){
            for(int i = 1; i <= width*height; i++){
                tempCell.possVals.add(i);
            }
        }

        //check row for values already filled in
        checkSingleRow(tempCell);

        //check col for values already filled in
        checkSingleCol(tempCell);

        //check box that the blank resides in
        checkSingleBox(tempCell);
    } //setPossVals

    //recursive backtracking solver
    public boolean Solve(int i) {
        cellInfo tempCell = emptyCells[i];//pull out the next cell
        setPossValues(tempCell);
        //base case: board is solved
        if(check()) {
            return true;
        }

        while(tempCell.possVals.size() != 0){//still possibile values 
            tempCell.value = tempCell.possVals.get(0);//pull out the first possVal
            board[tempCell.row][tempCell.col] = tempCell.value;//assign the possVal to the board
            if (i < count-1){//so we dont keep going over the amount of blanks
                i+=1;
            }

            if(Solve(i)){//solve on next blank cell
                return true;
            }

            else{
                board[tempCell.row][tempCell.col] = 0;//reset cell to zero 
                tempCell.possVals.remove(0);//remove that possVal
            }
            i--;//decrement i so we dont skip any of the blank cells due to a +1 error
            //try the next one if there is one
        }
        //if we tried all the possible values and it none of them worked...
        return false; //board cant be solved
    }

    //Creates an array of Cell objects that stores info about the empty cells
    public void createEmptyCells (int count, ArrayList<Integer> cellsRow, ArrayList<Integer> cellsCol){
        emptyCells = new cellInfo [count]; //create an array of Cell objects
        for(int i = 0; i < count; i++) {
            emptyCells[i] = new cellInfo(cellsRow.get(i), cellsCol.get(i), width*height);
            //emptyCells[i].setValue(board[cellsRow.get(i)][cellsCol.get(i)]);
        }
    }

    /**
     * Checks the current temp board for correctness by checking each row, column, and box for duplicates, in that order. 
     * 
     * @param theBoard the board we are checking 
     * @return true if the board is correct
     */
    public boolean check()
    {
        //check columns for duplicates (moving over and down)
        if (!checkColumn()){
            return false;
        }
        //check rows
        if (!checkRow()){
            return false;
        }
        //check boxes
        if (!checkBox(0)){
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
    public boolean checkBox(int boxIndex)
    {
        for (int i = 0; i < (width*height); i += height){//iterate over al lthe boxes starting from the
            //"leftmost" colmn of boxes and moving to the right
            HashSet<Integer> dupes = new HashSet<Integer>();// check for duplicates
            for (int r = 0; r < height; r++){//check individual rows in a box
                for (int e = boxIndex; e < width; e++){//check each element in the row
                    if (!dupes.add(board[r][e])|| board[r][e] == 0){
                        return false;
                    }
                }
            }
        }

        boxIndex += width; //move over right to next column of boxes

        if(boxIndex < (width*height)){
            if(checkBox(boxIndex) == false){
                return false;
            }
        }
        return true;
    }//checkBox

    public boolean checkRow(){
        //check rows for duplicates
        for(int r =0; r < (width*height); r++){//r =the row we're looking at
            for(int c = 0; c < (width*height); c++){//c = the number we dont want duplicates of
                for(int e=c+1; e < (width*height);e++){//c = the number we're comparing to in the row
                    //to the "right" of c
                    if (board[r][c] == board[r][e] || board[r][c] == 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }//checkRow

    public boolean checkColumn(){
        for(int c = 0; c < (width*height); c++){//column. will only iterate over the first row
            for(int r = 0; r < (width*height); r++){//row. iterates throgh the elements "under" [c]
                for(int e = r+1; e < (width*height); e++){//compare elements in "lower" rows
                    if(board[r][c] == board[e][c] || board[r][c] == 0){//compare the current element in the column to the ones below it
                        return false;
                    }
                }           
            }
        }
        return true;
    }//checkColumn()

    /**
     * checks the box that the blank resides in for values that are already present 
     * 
     * I'm gonna go extra heavy on the comments here just so I can remember how this works 
     * in the future.....cause this was a major pain to figure out 
     */
    public void checkSingleBox(cellInfo tempCell){
        int x = tempCell.row % height;//how many cells to move up
        int y = tempCell.col % width;//how many cells to move left
        int indexX = tempCell.row - x;//the row of the first element in the box
        int indexY = tempCell.col - y;//the column of the first element in the box
        for (int r = indexX; r < indexX + height; r++){//iterate over the row
            for (int c = indexY; c < indexY + width; c++){//check column
                if (board[r][c] != 0){
                    Integer num = new Integer(board[r][c]);//check the value at the given coordinates
                    tempCell.possVals.remove(num);//remove if possible
                }
            }
        }
    }//checkSingleBox

    public void checkSingleRow(cellInfo tempCell){
        //check row for values already filled in
        for(int j = 0; j < width*height; j++) {
            Integer num = new Integer (board[tempCell.row][j]);
            if(tempCell.possVals.contains(num)) {
                tempCell.possVals.remove(num);
            }
        }
    }//checkSingleRow

    public void checkSingleCol(cellInfo tempCell){
        //check col for values already filled in
        for(int j = 0; j < width*height; j++) {
            Integer num = new Integer (board[j][tempCell.col]);
            if(tempCell.possVals.contains(num)) {
                tempCell.possVals.remove(num);
            }
        }
    }//checkSingleCol
}//game
