package Lab04;

import queue.*;

/**
 * A Printer has a speed, in blocks of printing per step, and a queue of Docs to be printed
 * 
 * 
 * @author (Sean Mulhall)
 */
public class Printer
{
    private int speed;      // speed of this printer, blocks per step
    private QueueADT <Doc> docs;    // Queue of documents waiting to be printed
    private int blocksRemaining;    // blocks remaining to be printed on the active doc
    private Doc doc;                // the active document
    private String id;      // name of this Printer

    /**
     * Constructor for objects of class Printer
     */
    public Printer(int speed, String id)
    {
        this.speed = speed;
        this.id = id;
        blocksRemaining = 0; //I'm assuming that a newley created printer will have no jobs(knock on wood)
        docs = new Queue();
        System.out.println(id+", speed: "+speed+", Docs in queue: "+docs.size());
    }

    /** Add a document to this printer's queue */
    public void add (Doc doc)
    {   
        if (this.doc == null)
        {
            this.doc = doc;
            docs.add(doc);//I didnt have this earlier because I thought that if it was the printers active document(this.doc)
            //then I didnt necesarilly have to add it into docs. However, I found that documents if a document 
            //was created exactly one step afer, the first printer would recieve both documents. not exactly what I wanted
            blocksRemaining += doc.size();
        }
        else 
        {
            docs.add(doc);
        }
        System.out.println("Doc of size "+doc.size()+" added to "+id+", speed: "+speed+", docs in queue: "+docs.size());
    }

    /** 
     * Print several blocks, if necessary, determined by this printer's speed.
     */
    public void print()
    {   
        if ((blocksRemaining <= 0)&&(docs.size() == 0))
        { 
            return; //nothing to print, go idle
        }
        if (blocksRemaining > speed)//still printing the current document
        {
            blocksRemaining -= speed;
            return;
        }
        else//finished current document
        {
            System.out.println("Print task completed on "+id+" for: Doc of size "+doc.size());
            this.doc = docs.remove();//get next document in docs
            blocksRemaining = doc.size();
            //in previous versions, blocksRemaining was the sum of all the documents sizes in the queue
            //which is why nothing ever finishing printing
            //so, to fix that, blocksRemaining always gets set to the active documents(this.doc) size.
            //after the current document has finished, we set this.doc to the next document in the queue
            //and thus blocksRemaining would get set to doc.size()
        }
    }

    /** @return the number of documents in this Printer's queue */
    public int size()
    {  
        return docs.size();
    }

    /** Include the id, speed, and number of docs in the queue */
    public String toString()
    {   
        return id+", speed: "+speed+", Docs in queue: "+docs.size();
    }

}
