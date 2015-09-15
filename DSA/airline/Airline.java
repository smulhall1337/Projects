package airline;
import list.*;
import map.*;
import java.util.Scanner;
import java.io.*;
/**
 * An airline has a list of direct flights, it can find a path of direct flights 
 * from and airport to any other airport
 * 
 * @author (Sean Mulhall) 
 * @version (11.2014)
 */
public class Airline
{
    List <Flight> flights;//Direct flights
    Map <String, Airport> map;

    public static void main()
    {
        Airline airline = new Airline();

        airline.getFlights();
        System.out.println("Direct flights: " + airline.flights);
        System.out.println("Map is: " + airline.map);

        System.out.println("Path from PHL to SFO");
        System.out.println(airline.path("PHL","SFO"));

        System.out.println("Path from JFK to LAX");
        System.out.println(airline.path("JFK", "LAX"));

        System.out.println("Path from LAX to SFO");
        System.out.println(airline.path("LAX", "SFO"));

        System.out.println("Path from SFO to Paris");
        System.out.println(airline.path("SFO", "Paris"));

    }

    //read direct flights from the file "flights.txt"
    //into the list flights, also the map of the airports
    private void getFlights()
    {
        File infile = new File("flights.txt");
        map = new HashMap<String, Airport>();
        flights = new LinkedList<Flight>();
        try{
            Scanner scanner = new Scanner(infile);
            String origin, dest, flight;

            while (scanner.hasNextLine())
            {
                flight = scanner.nextLine();
                origin = flight.split(" ")[0];
                dest   = flight.split(" ")[1];
                map.put(origin, new Airport (origin));
                map.put(dest, new Airport(dest));
                flights.add(new Flight(new Airport(origin), new Airport(dest)));
            }
            scanner.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
    }

    private List <Airport> path (String origin, String dest)
    {
        Airport o = map.get (origin);
        Airport d = map.get (dest);
        clearAirports();
        return path (o,d);
    }
    
    //return a path from the origin to dest
    private List <Airport> path (Airport origin, Airport dest)
    {
        List <Airport> result = new LinkedList <Airport>();
        origin.visited = true; 

        //base case
        if (origin.equals (dest))
        {
            result.add(origin);
            return result;    
        }
        
        Iterator<Airport> itty = getDirect(origin).iterator();
        while (itty.hasNext())
        {
            Airport port = itty.next();
            result = path (port, dest);
            if(! (result.isEmpty()))
            {
                result.add(0, origin);
                return result;
            }
        }
        //can not find path
        return result;
    }

    //return a list of all airports which are a direct destinations 
    //of a given origin
    private List <Airport> getDirect (Airport origin)
    {
        List <Airport> result = new LinkedList<Airport>();
        Iterator <Flight> itty = flights.iterator();
        Flight flight;
        
        while (itty.hasNext())
        {
            flight = itty.next();
            if (flight.origin.equals(origin) && !( flight.dest.visited))
            {
                flight.origin.visited = true;
                flight.dest.visited = true;
                result.add(flight.dest);
            }
        }
        return result;
    }

    /** Clear all the visited flags */
    private void clearAirports()
    {
        Flight flight;
        Airport port;
        Iterator <Flight> itty = flights.iterator();
        while (itty.hasNext())
        {  flight = itty.next();
            flight.origin.visited = false;
            flight.dest.visited = false;
        }
    }
}
