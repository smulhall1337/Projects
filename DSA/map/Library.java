package map;
import set.*;
/**
 * A Library has a Map in which the keys are Books, and the values are Integers.  
 * The value indicates the number of times the Book has been borrowed.
 * 
 * @author (Sean Mulhall) 
 * @version (12.10.2014)
 */
public class Library
{
    Map<Book,Integer> books = new HashMap<Book,Integer>();

    public void borrow(Book b)
    {
        if(books.containsKey(b))
        {
            Integer temp = books.get(b);
            temp++;
            books.put(b, temp);
            return;
        }
        books.put(b,1);
    }

    public String toString()
    {
        return books.toString();
    }
}
