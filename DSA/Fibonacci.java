import list.
/**
 * Write a description of class Fibonacci here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fibonacci
{
    public int fib(int n)
    {
        if (n < 2)
        {
            return 1;
        }
        System.out.println (n);
        return fib(n-1)+ fib(n-2);
    }

    public int betterFib(int n)
    {
        int num = 0;
        int num2 = 1;
        int fibonacci = 0;
        System.out.print(num2);
        for (int i = 1; i <= n; i ++)
        {
            fibonacci = num + num2;
            num = num2;
            num2 = fibonacci;
            System.out.print(" " + fibonacci +"\n");
        }
        return fibonacci;
    }

}
