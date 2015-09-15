package listDriver;

import list.*;
import java.util.Scanner;
import java.io.*;

/**
 * 
 * Data Structures and Algorithms
 * Set up data for lab 0
 * 
 * @author (sdb) 
 * @version (Sep 2014)
 */
public class WordList
{
    private List <String> words = new LinkedList();
    
    /** Generate a list of English words.
     */
    public WordList()
    {   
    try
    {
    String line;
    Scanner scan = new Scanner (new File ("words.txt"));
    while (scan.hasNextLine())
        {   line = scan.nextLine();
            words.add (line);
        }
    scan.close();
    }
    catch (FileNotFoundException fnfe)
    {   System.err.println (fnfe);  }
}

    /** @return The list of words generated
     *  by the constructor.
     */
    public List <String> getWordList()
    {   return words;  }
    
}
