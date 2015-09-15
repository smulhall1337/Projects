package stack;

/**
 * a representation of a Stack abstract data type. Last in first out
 * 
 * @author Sean Mulhall 
 * @version 9.23.14
 */
public interface StackADT<E>
{
    /**
     * puts a value on top of the stack
     * @return the values "pushed"
     */

    E push(E value);

    /**
     * removes and returns the top value of the stack
     * Pre: The stack is not empty (duh)
     * @returns the top value that is removed
     */

    E pop();

    /**
     * return the value of the top element of the stack
     * @return the top value of the stack
     * @pre: the stack is not empty
     */

    E peek();
    

    /** @return true only if this Stack is empty */

    boolean isEmpty();

    /** Clear this stack, to make it an empty stack */

    void clear();

    /** @return This Stack as a String */

    String toString();

    /** @return true only if this Stack is equal to the given obj */
    boolean equals (Object obj);
}
