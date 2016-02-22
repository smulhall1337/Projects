import list.
/**
 * a fibonachi generator. it was fun seeing how hot this could make my laptop
 * 
 * @author Sean Mulhall
 * 
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
