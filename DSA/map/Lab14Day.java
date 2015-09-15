package map;
import map.*;
import list.*;
import java.io.*;
import java.util.Arrays;
import sort.BubbleSort;
/**
 * Test the Map implementations
 * Lab 10
 * 
 * @author (sdb) 
 * @version (Nov 2012)
 */
public class Lab14Day
{
    public static void main()
    {   Library library = new Library();

        Book b = new Book ("Intro to Java", "Barnes", 332);
        library.borrow (b);      // borrow Intro to Java 3 times
        library.borrow (b);
        library.borrow (b);

        b = new Book ("Data Structures", "Weiss", 423);
        library.borrow (b);

        b = new Book ("Operating Systems", "Tanenbaum", 388);
        library.borrow (b);

        b = new Book ("Intro to Java", "Smith", 287);     // borrow Intro a 4th time
        library.borrow (b);
        // "Smith", 287
        System.out.println (library);
    }

    ////////  Uncomment the following when ready for problem 2
    private Map <String, List<String>> wordMap = new HashMap<String, List<String>> ();

    public static void problem2()
    {   Lab14Day lab14 = new Lab14Day();
        lab14.translate();
    }

    private void translate()
    {    
        buildMap();
        System.out.println (wordMap.get(sort("ptos")));
        System.out.println (wordMap.get(sort("rbag")));
    }

    /** @return a String consisting of the characters in the given String, s,
     *  in alphabetic order.
     */
    private String sort (String s)
    {  
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++)
        {
            list.add(s.charAt(i));
        }            
        BubbleSort sorter = new BubbleSort(list);
        sorter.sort();
        String result = "";
        for (int i = 0; i < list.size(); i++)
        {
            result += list.get(i);
        }      
        return result;

    }

    void buildMap()
    {   File inFile = new File ("Words.txt");
        String word,key;
        List <String> words;
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        try
        {   java.util.Scanner scanner = new java.util.Scanner (inFile);   
            while (scanner.hasNextLine())
            {   
                words = new ArrayList<String>();
                word = scanner.nextLine();
                key = sort(word);
                List<String> temp = map.get(key);
                if (temp != null)
                {
                    temp.add(word);
                    map.put(key, temp);
                }
                else
                {
                    words.add(word);
                    map.put(key, words);
                }
                System.out.println(key+", "+word);
            }
        }
        catch (IOException ioe)
        {   }
    }

}