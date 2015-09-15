package tree;
import list.*; 

/**
 * A BinarySearchTree is a BinaryTree in which All descendants
 * on left have values which are smaller than the roots value.
 * 
 * All descendants on right have values which are
 * greater than the roots value.
 * 
 * CANNOT HAVE DUPLICATES
 * 
 * @author (Sean Mulhall) 
 * @version (10.21.2014)
 */
public class BinarySearchTree<E extends Comparable >
implements BinaryTree<E>
{
    E value;
    BinaryTree<E> left = new EmptyBinarySearchTree<E>();
    BinaryTree<E> right = new EmptyBinarySearchTree<E>();

    public BinarySearchTree (E value)
    {
        this.value = value;   
    }

    public E getValue()
    {
        return value;
    }

    public BinaryTree<E> getLeft()
    {
        return left;
    }

    public BinaryTree<E> getRight()
    {
        return right;
    }

    public void setValue(E value)
    {
        this.value = value;
    }

    public void setLeft(BinaryTree<E> left)
    {
        this.left = left;
    }

    public void setRight(BinaryTree<E> right)
    {
        this.right = right;
    }

    public E get(E value)
    {
        int cmp = value.compareTo(this.value);
        if (cmp < 0)
        {
            return left.get(value);
        }
        if (cmp > 0)
        {
            return right.get(value);
        }
        //found
        return this.value;
    }

    public BinaryTree<E> add(E value)
    {
        //now heres where things change
        //how are we gonna decide where to insert this new object?
        //it has to be inserted in such a way to preserve the BinarySearchTree system
        int cmp = this.value.compareTo(value);
        if (cmp > 0)
        {
            left = left.add(value);
            //this needs to be recursive because we want this to be a new leaf on this tree
            //so we want to keep going down in this tree until we find the end, and add this 
            //value at the correct position
        }
        if (cmp < 0)
        {
            right = right.add(value);
            //same for this
        }
        return this;
        //since BinarySearchTree cannot contain duplicates, we dont do anything
        //if cmp = 0, because the value already exists in the tree
    }

    public BinaryTree<E> remove(Object object)
    {
        //this wil lbe tricky because we need to preserve the structure of the tree 
        //after we remove something, so we need to "rebuild" it so to speak after a removal
        //EXCEPTIONS
        //we'll use a try and catch method for this
        try 
        {
            E value = (E) object;
            //we'll try casting our current object as type E, if it cant cast, we'll catch the exception
            int cmp = value.compareTo(this.value);
            if (cmp == 0) //found what we want to remove
            {
                //now we need to look at the children, there could be 0, 1, or 2 children
                //we'll make a list of BinaryTrees representing all the children, call it kids
                List<BinaryTree<E>>kids = children(); //we'll have a method called "children" that will return the children of this binary tree
                if (kids.size()==0)
                {
                    //This binaryTree has no children
                    //nt much to do, all we do is return a new EBST
                    return new EmptyBinarySearchTree<E>();
                }

                if (kids.size() == 1)
                {
                    //one child tree
                    //we want to return a reference to the one child node, we move it "up" in the tree so to speak
                    return kids.get(0);
                }
                //we've come this far, we can assume the BT has two children
                //now what do we do with the children??
                //the smallest child in the RIGHT substree will be this BT's successor
                //so we go from the root: right, left, left, left, left....until we hit the end child
                BinaryTree<E> successor = ((BinarySearchTree)right).smallest();
                //we'll define a method called smallest that will return the smallest child in the right subtree
                BinaryTree<E> result = this.remove(successor.getValue());
                //result becomes successors value
                result.setValue(successor.getValue());
                //set the value of the node we're removing to successors value
                return result;
                //and return it
            }
            //search until we've found it
            if (cmp < 0)
            {
                left = left.remove(value);
            }

            if (cmp > 0)
            {
                right = right.remove(value);
            }
        }
        //try failed, could not cast
        //throw exception
        catch (ClassCastException cce)
        {
            //we wont do anything, but we caught the exception so the program does not crash
        }
        //could not find it
        return this;
    }

    /**
     * check to see if this BST has any children
     */
    private List<BinaryTree<E>> children()
    {
        List<BinaryTree<E>> result = new ArrayList<BinaryTree<E>>();
        if (left instanceof BinarySearchTree)
        {
            result.add(left);
        }
        if (right instanceof BinarySearchTree)
        {
            result.add(right);
        }
        return result;
    }

    /**
     * return the smallest child of this BST
     */
    private BinaryTree<E> smallest()
    {
        if (left instanceof BinarySearchTree)
        {
            //keep calling smallest() until we find the smalelst child
            return ((BinarySearchTree)left).smallest();
        }
        //we've found it
        return this;
    }

    public boolean containsKey(E value)
    {
        int cmp = this.value.compareTo(value);
        if (cmp > 0)
        {
            return left.containsKey(value);
        }
        if (cmp < 0)
        {
            return right.containsKey(value);
        }
        return true;
        //only returning true...why?
        //if one of the children is an EMPTYBinarySearchTree, containsKey returns only false
        //so we dont need to define it here
    }

    public Iterator<E> iterator()
    {
        return new TreeIterator<E> (this);
    }

    public boolean isEmpty()
    {
        return false;    
    }

    public boolean equals(Object object)
    {
        if (object instanceof BinarySearchTree)
        {
            BinarySearchTree<E> otherBST = (BinarySearchTree<E>) object;
            if (!(value.equals(otherBST.value)))
            {
                return false;
            }
            if(otherBST.left.equals(getLeft()) && (otherBST.right.equals(getRight())))
            {
                return true;
            }
            return false;
        }
        //not a BST, cant compare
        return false;
    }

    public String toString()
    {
        //how do we display a tree as a string??
        //3 ways to do it
        //Traversal of Binary Trees
        //1. Preorder
        //a. visit the root value
        //b. visit all the values in left child
        //c. visit all the values in the right child
        //
        //2. Inorder (Alphabetical...or least to greatest)(this is the one we'll use(for now))
        //a. visit all the values in the left child
        //b. visit the value in the root
        //c. visit all the values in the right child
        //
        //3. PostOrder
        //a. visit all the values in the left child
        //b. visit al lthe values in the right child
        //c. visit the root value
        if((left (!(instanceof (EmptyBinarySearchTree)))) && (right(!(instanceof (EmptyBinarySearchTree)))))
        {
            return left.toString()+""+value+", "+right.toString();      
        }

    }

