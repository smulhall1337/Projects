package airline;
/**
 * A flight cons\its of an origin airport and a 
 * destination airport
 * 
 * @author (Sean Mulhall) 
 * @version (11.25.2014)
 */
public class Flight
{
    Airport origin, dest;
    
    public Flight(Airport o, Airport d)
    {
        origin = o;
        dest = d;
    }
    
    public String toString()
    {
        return "(" +origin + "---->" + dest + ")";
    }
    
}
