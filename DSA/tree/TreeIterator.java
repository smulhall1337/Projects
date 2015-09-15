package tree;
import list.Iterator;
import queue.*;

/**
 * Iterates over a binary search tree in order
 * 
 * @author (Sean Mulhall) 
 * @version (10.28.2014)
 */
public class TreeIterator<E>
implements Iterator<E>
{
    BinaryTree<E> tree;
    //since we're moving inorder, where moving to the left most child, and examining 
    //all of thats childs right child. (so from least to greatest)
    //we need to remember where we are in the tree and what comes next. We'll use a Queue for that
    QueueADT<E> queue = new Queue<E>();
    E last; //last value obtained by next

    public TreeIterator(BinaryTree<E> tree)
    {
        this.tree = tree;
        buildQ(tree);
    }

    /**
     * so if we have
     * 
     *          [m]
     *          /\
     *         /   \
     *        [d]   [s]
     *        /\
     *       /  \ 
     *     [b]  [g] 
     *     we would get queue = [b, d, g, m, s]
     *     (ASCII art becomes practical)
     */
    /**
     * for lab 8, We're iterating in PREorder
     * so it will be
     * [m, d, b, g, s]
     * i think...
     */
    private void buildQ(BinaryTree<E> tree)
    {
        queue.add(tree.getValue());
        if (tree.getLeft() instanceof BinarySearchTree)
        {
            buildQ(tree.getLeft());
        }
        if (tree.getRight() instanceof BinarySearchTree)
        {
            buildQ(tree.getRight());
        }
    }

    public boolean hasNext()
    {
        return !queue.isEmpty();
        //if our queue is empty, theres nothing else to return
    }

    public E next()
    {
        last = queue.remove();
        return last;
    }

    /**
     * removes from the tree the last element returned from next. BUT we have to remove it from the tree
     * while still retaining the properties of a BST
     * 
     * but what if we remove the element at the root?? then what?
     * 
     */
    public void remove()
    {
        //first off, we need to check if we're removing the root
        //and we're going to have to remember what the last value obtained by next was, we'll add a field
        //now we'll compare it to wahts at the root
        if (last.equals(tree.getValue()))
        {
            BinaryTree<E>temp = tree.remove(last);
            tree.setValue(temp.getValue());
            tree.setLeft(temp.getLeft());
            tree.setRight(temp.getRight());            
            //so we've removed the root, stored that into our temp
            //and set the new root value to whatevers on the LEFT of the old root
            //so we copy whatevers the left node, and its Left and Right and set that as the new root
            //yrash its really confusing...I'll get clarification
        }
        else
        {
            tree = tree.remove(last);
        }
    }
    
    //dont use these right now...
    public void remove2()
    {
        
    }
    
    public boolean hasTwoMore()
    {
        return false;
    }

}
