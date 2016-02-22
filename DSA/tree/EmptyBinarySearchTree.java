package tree;
import list.*;
/**
 * A BinaryTree that stores nothing
 * ...??
 * 
 * @author (Sean Mulhall) 
 * @version (10.21.2014)
 */
public class EmptyBinarySearchTree<E extends Comparable>
implements BinaryTree<E>
{
    public E get(E value)
    {
        //will never get anything because its empty...but we need to have it 
        //do something so the compiler is happy
        return null;
    }

    public E getValue()
    {
        return null;
    }

    public BinaryTree<E> getLeft()
    {
        //same here
        return null;
    }

    public BinaryTree<E> getRight()
    {
        //..and here
        return null;
    }

    public void setValue(E value)
    {
        //empty
    }

    public void setRight(BinaryTree<E> right)
    {
        //empty
    }

    public void setLeft(BinaryTree<E> left)
    {
        //empty
    }

    public BinaryTree<E> add(E value)
    {
        return new BinarySearchTree(value);
    }

    public BinaryTree<E> remove(Object value)
    {
        return this;
    }

    public boolean containsKey(E value)
    {
        return false;
        //will alwahys return false since the tree is empty
    }

    public Iterator<E> iterator()
    {
      return new EmptyIterator();
      //seems useless, but...
    }

    public String toString()
    {
        return "";
    }

    public boolean isEmpty()
    {
        return true;
    }

    public boolean equals(Object object)
    {
        if (object instanceof EmptyBinarySearchTree)
        {
            return true;
        }
        return false;
    }

}
