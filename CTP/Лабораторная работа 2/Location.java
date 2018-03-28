/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
    public boolean equals(Location val)
    {
        return(xCoord==val.xCoord && yCoord==val.yCoord);
    }
    public int hashcode(Location val)
    {
        final int intchislo=95;
        int result = 1;
        result=result*intchislo+xCoord;
        result=result*intchislo+yCoord;
        return result;
    }
}
