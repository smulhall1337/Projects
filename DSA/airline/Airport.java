package airline;
/**
 *an airport has a name
 * 
 * @author (Sean Mulhall) 
 * @version (11.25.2014)
 */
public class Airport
{
    String name;
    boolean visited = false; //already visited this airport
    
    public Airport (String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    } 
    
    public boolean equals(Object object)
    {
        if (object instanceof Airport)
        {
            Airport port = (Airport) object;
            return name.equals(port.name);
        }
        return false;
    }
}
