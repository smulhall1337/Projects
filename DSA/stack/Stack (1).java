package stack;
import list.*;

/**
 * implements the StackADT
 * 
 * @author Sean Mulhall 
 * @version 9.23.2014
 */
public class Stack <E>
implements StackADT<E>
{
    List <E> values;

    public Stack()
    {
        values = new ArrayList<E>();//we might want to change this later to a linked list, we'll see
        //but since they're essentially the same, we can use the same methods in List
    }

    public E push(E value)
    {
        values.add(value);
        return value;
    }

    public E pop()
    {
        return values.remove(values.size()-1);
    }

    public E peek()
    {
        return values.get(values.size()-1);
    }

    /** @return true only if this Stack is empty */
    public boolean isEmpty()
    {
        return (values.size()==0);
    }

    /** Clear this stack, to make it an empty stack */

    public void clear()
    {
        values.clear();
    }

    public String toString()
    {
        String result = "[";
        for (int i = 0; i<values.size(); i++)
        { 
            if (i == values.size()-1)
            {
                result = result + values.get(i); 
            }
            else
            {
                result = result + values.get(i) +",";
            }
        }

        result = result + "]";
        return result;
    }

    public boolean equals (Object object)
    {
        //retuen values.equals(object);
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof StackADT))
        {
            return false;
        }
        Stack<E> otherStack = (Stack<E>)object;
        if (values.size() != otherStack.values.size())
        {
            return false;
        }
        for (int i=0;i<values.size();i++)
        {
            if (!(values.get(i).equals(otherStack.values.get(i))))
            {
                return false;
            }
        }
        return true; 
    }

}

