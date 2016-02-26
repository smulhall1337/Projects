import java.util.*;
public class Table 
{
    static ArrayList<Diner> temp; //temporary list for all diners
    static ArrayList<Diner> temp2; //temporary list for sorting
    static ArrayList<Diner> table; //permanent table list

    public static void main(String[] args)
    {   
        temp = new ArrayList<Diner>(); 
        temp2 = new ArrayList<Diner>();
        table = new ArrayList<Diner>();

        Diner diner1 = new Diner('a', 'b', 'c', 'd', 'e');
        Diner diner2 = new Diner('d', 'b', 'e', 'a', 'c');
        Diner diner3 = new Diner('a', 'c', 'd', 'e', 'b');
        Diner diner4 = new Diner('c', 'd', 'b', 'a', 'e');
        Diner diner5 = new Diner('a', 'c', 'b', 'd', 'e');
        Diner diner6 = new Diner('c', 'b', 'd', 'e', 'a');

        temp.add(diner1);
        temp.add(diner2);
        temp.add(diner3);
        temp.add(diner4);
        temp.add(diner5);
        temp.add(diner6);
    }

    public void sort()
    {
        while (temp.size() != 0)
        {
            char dish = 'a';
            for (Diner d : temp)//move through all diners on temp
            {
                if (d.get(0) == dish)
                {
                    temp2.add(d);
                    temp.remove(d);
                } 
            }
            if (temp2.size() != 0)
            {
                sortTable();
            }
            dish++;
        }
    }

    public void sortTable()
    {
        if(temp2.size() == 1)
        {
            table.add(temp2.get(0));
            temp2.remove(0);
        }
        else 
        {
            int highest = 0;//(NOT ASCII VALUE, whatever will be inserted first into the table
            while (temp2.size() != 0)
            {
                for (int cmp = 1; cmp <= temp2.size()-1; cmp++)
                {  
                    for(int rank = 0; rank <= temp2.get(highest.size()-1); rank++)
                    {
                        if(temp2.get(highest.get(rank)) != (temp2.get(cmp).get(rank)))
                        {
                            if (compareRank(temp2.get(highest, cmp, rank)) == true) //d1 is "higher" than d2, from aascii, d2 will become the new highest
                            {
                                highest = cmp;
                            }
                        }
                        //increment rank only if they are the rankings for the diners we're comparing are the same???
                    }
                    //end of the cmp loop
                    //move onto next diner
                }

            }
        }
    }

    public Boolean compareRank(Diner d1, Diner d2, int rank)//return true if d1 > d2, in terms of the ascii table
    {
        char c1 = d1.get(rank);
        char c2 = d2.get(rank);

        if (c1 > c2)
        {
            return true;
        }
        return false;
    }

}


