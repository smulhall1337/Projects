package recursion;
import list.*;
/**
 * Write a description of class Palindrome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
