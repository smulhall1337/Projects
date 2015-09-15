
/**
 * class for the node elements of linked list   
 * 
 * @author Sean Mulhall 
 * @version 9.10.2014
 */
package list;
class Node <E>
{
   E value;
   Node <E> next;
   Node <E> prev;
   
   Node (E value, Node <E> next, Node <E> prev)
   {
       this.value = value;
       this.next = next;
       this.prev = prev;
    }
    
}
