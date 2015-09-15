package recursion;
import java.util.*;

/** Maintain email addresses, with aliases.
 * @author (sdb)
 * @author (Sean Mulhall)
 */
public class EmailAddresses
{
    private Map <String, Set <String>>  book;

    public EmailAddresses ()
    {  book = new HashMap <String, Set <String>> ();  }

    /** Associate the given alias with the given people */
    public void add (String alias, Set <String> people)
    // Add this entry to the map
    { 
        book.put(alias, people);   
    }

    /** @return All the email addresses corresponding to
     * the given alias, in a Set.
     */
    public Set <String> expandAlias (String alias)
    { 
        Set <String> result = new HashSet <String> ();
        Set <String> newSet;
        if (!(book.containsKey(alias)))
        {
            result.add(alias);
            return result;
        }
        newSet = book.get(alias); 
        for (String str: newSet)
        {
          result.addAll(expandAlias(str));
        }
        return result;
    }
}
