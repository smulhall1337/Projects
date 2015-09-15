package tree;
import list.*;

/**
 * Abstract data type for binary tree
 * 
 * @author (Sean Mulhall) 
 * @version (10.21.2014)
 */
public interface BinaryTree<E>
{
    /**
     * gets the value at a particular spot in the tree
     * @returns <E> the value
     */
    E getValue();

    /**
     * returns the left and right child, respectively 
     * @return the left child and its children
     */
    BinaryTree<E> getLeft();

    BinaryTree<E> getRight();

    /**
     * change the value of this BinaryTree
     */
    void setValue(E value);

    /**
     * change the left child
     */
    void setLeft(BinaryTree<E> left);

    /**
     * change the right child
     */
    void setRight(BinaryTree<E> right);

    /**
     * insert a given value somewhere into this tree
     * Where?  How? this is left up to the implementing class. Different
     * Binary Trees will insert differently 
     * @return a ref to the updated tree
     */
    BinaryTree<E> add(E value);

    /**
     * Remove the given value from this BinaryTree
     * !!!!
     * Keep in mind that when we remove something, we have to "fix" some of the leaves.
     * If we remove something from the middle of the tree, how is the implementing class going to fix it?
     * Again, left up to the implementing class./
     * !!!!
     * 
     * @return a ref to the updated tree
     */
    BinaryTree<E> remove(Object value);

    /**
     * returns true only if the given value exists in this Binary Tree
     * @return true if this Binary Tree contains the given value
     */
    boolean containsKey(E value);

    /**
     * returns the value stored in the BinaryTree with the given value(...what?), or null if not found
     * 
     * so why would we have a method that returns the value we give it? Its returning a ref to an object, and
     * if we were only returning a value by say a name, we could have two names that are the same but ref DIFFERENT
     * objects. So we compare against objects to make sure we return what the user wants.
     */
    E get(E value);

    /**
     * @return an Iterator for this BinaryTree
     * lots of ways this could work, left up to implementing class.
     * 
     * Could decide to go pre order, in order, or post order. 
     */
    Iterator<E> iterator();

    /**
     * @return a String representation of this BinaryTree
     * How? In what order? Could we use an Iterator?
     * again, left up to implementation
     */
    String toString();

    /** @return true only if this BinaryTree is empty */
    boolean isEmpty();

    /** @return true only if this BinaryTree is equal to the given obj */
    boolean equals (Object object);
}   
