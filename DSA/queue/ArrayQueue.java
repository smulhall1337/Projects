package queue;
import list.*;

/**
 * An implementation of a queue using an array list.
 * 
 * @author Sean Mulhall
 * @version 9.25.2014
 * 
 * Stolen from our Queue class:
 *  //it is possible to use an ArrayList, but theres a few things we need to keep track of: We would need to 
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
 */
public class ArrayQueue<E>
implements QueueADT<E>
{
    int front = 0;//next to be removed
    int back = 0;//next available to add
    int size = 0; //size of queue, NOT size of arraylist
    List <E> values = new ArrayList<E>();

    /**
     * !!!!!!!!!!!!!!!!!!!
     * THIS SHIT GETS CONFUSING. BE SMART AND DRAW A DIAGRAM OF WHATS GOING ON
     * !!!!!!!!!!!!!!!!!!!
     */

    public void add(E value)
    {
        if (size != values.size())//theres still room
        {
            values.set(back,value);//we're not adding yet because the list isnt full. instead set the value that back
            //is pointing to to what we're adding
            //back = (back+1)%values.size();//this prevents back from going into null pointer territory.
            //when it reaches the end of the array, it will wrap around to the beginning of the array
            //commented both of them out since we added it to the end of the method, but im gonna keep them here with the notes
            //incase I or someone else (god forbid) needs them

        }
        else //the array is full, now we are adding
        {
            values.add(back, value);
            front = (front+1)%values.size();//move front because we just added an element in front of it in the array and we want to
            //keep front pointing to the same element
            //backback = (back+1)%values.size();//as long as we keep adding, we'll have to keep moving front and back at the same time
        }
        size++; //size is incrementing no matter what happens, this prevents code duplication (BAD) 
        back = (back+1)%values.size();//same with this
    }

    public E remove()
    {
        //we're going to return whatever front is pointing to, since thats whats being removed
        E result = values.get(front);
        front = (front+1)%values.size();
        size--;
        return result;
    }

    public E peek()
    {
        if (size == 0)
        {
            return null;
        }
        else
        {
            return values.get(front);
        }
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
            return false;
    }

    public String toString()
    {
        return null;
    }

    public boolean isEmpty()
    {
        return false;
    }
}
