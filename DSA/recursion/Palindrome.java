package recursion;
import list.*;
/**
 * checks to see if a given string is a palindrome
 * 
 * @author (Sean Mulhall) 
 * @version (2014)
 */
public class Palindrome
{

    /** @return true only if str is a palindrome */

    public boolean isPalindrome (String str)
    {
        if ((str.length() == 0) || (str.length() == 1))
        {
            return true;
        }
        
        else if (str.charAt(0) != str.charAt(str.length()-1))
        {
            return false;
        }
        
        String newString = str.substring(1, str.length()-1);
        return isPalindrome (newString);
    }

}
