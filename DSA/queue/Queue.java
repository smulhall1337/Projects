package queue;
import list.*;

/**
 * Implementation of the QueueADT interface which will use some kind of list to store the values
 * using a LinkedList
 * 
 * @author Sean Mulhall
 * @version 9.25.2014
 */
public class Queue<E>
implements QueueADT<E>
{
    List <E> values = new LinkedList <E>(); //what kind of list do we want to use?
    //Array would be good due to the fact that the Add() method in array will add right to the end of 
    //the list. But when we to remove an element from the front, we will need to move everything over
    //using our loop which may take a lot of time depending on the list size.
    //However: if we use a LinkedList, add and remove are pretty straight forward. Add works the same
    //where it adds to the end. But when we remove, there is no loop involved, all we do
    //is change which the head.next is refrencing, making it much more efficiant. 

    //it is possible to use an ArrayList, but theres a few things we need to keep track of: We would need to 
    //re-use positions after a remove, that is to say, make it so that we dont have ot move everything over. And we 
    //would need to remember the positions of the front and back locally(as in this class)
    //you could have a variable Back, which keeps track of the last position in line(assuming we have a set 
    //array list length), everytime you add, Back points to the next element(respectively) that would be the
    //new "back" of the line.
    //we could also have a Front variable, that points to the front of the line, and when you call remove(),
    //you remove the element that Front is pointing to, and move front to the next element in the array.
    //in short, back would move whenever we add something, front would move whenever we remove something.
    //if front and back are pointing to the same element, then our queue is full, and if we were to call add()
    //again, we would need to increase the size of our Array and move both Front and Back up. We wouldnt set Back 
    //to the new position that we added because Back points to where we WOULD LIKE TO ADD a new element, NOT
    //the element we just added(if the list is full, if empty, it will point to whatever empty position is after
    //the one we just added)

    public void add(E value)
    {
        values.add(value);//calling straight from the linkedlist class
        //automagically adds to the end of the list and increments the size

    }

    public E remove()
    {
        return values.remove(0);//always remove the 0th value in a queue
    }

    public E peek()
    {
        if(values.isEmpty())
        {
            return null;
        }
        return values.get(0);
    }

    public int size()
    {
        return values.size();
    }

    public void removeAll()
    {
        values.clear();
    }

    public boolean equals(Object object)
    {
        if (!(object instanceof Queue))
        {
            return false;
        }
        Queue otherQueue = (Queue) object;
        return values.equals(otherQueue.values);
    }

    public String toString()
    {
        return values.toString();
    }

    public boolean isEmpty()
    {
        if (values.size() == 0)
        {
            return true;
        }
        return false;
    }

}
