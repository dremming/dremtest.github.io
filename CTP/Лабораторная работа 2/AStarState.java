/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
import java.util.ArrayList;
import java.util.List;

public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private List<Waypoint> openPoints;
    private List<Waypoint> closedPoints;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            if (map == null)
                throw new NullPointerException("map cannot be null");
        this.map = map;
        openPoints = new ArrayList<Waypoint>();
        closedPoints = new ArrayList<Waypoint>();
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if(numOpenWaypoints()==0)
            return null;
        Waypoint minPoint = openPoints.get(0);
        for (int i=1; i<numOpenWaypoints();i++)
        {
            Waypoint p = openPoints.get(i);
            if (p.getTotalCost()<minPoint.getTotalCost())
                minPoint=p;
        }
        return minPoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        for (int i=0; i<numOpenWaypoints();i++)
        {
            Waypoint previousPoint=openPoints.get(i);
            Location previousLocation=previousPoint.getLocation();
            if (previousLocation==newWP.getLocation())
                if(previousPoint.getPreviousCost()<newWP.getPreviousCost())
                {
                    openPoints.remove(previousPoint);
                    openPoints.add(newWP);
                    return true;
                }
                else
                    return false;
        }
        openPoints.add(newWP);
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        // TODO:  Implement.
        return openPoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        {
            // TODO:  Implement.
            for (int i = 0; i < numOpenWaypoints(); i++) {
                Waypoint currentpoint = openPoints.get(i);
                if (loc.equals(currentpoint.getLocation())) {
                    openPoints.remove(currentpoint);
                    closedPoints.add(currentpoint);
                }
            }
        }
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
        {
            for (int i=0;i<closedPoints.size();i++)
            {
                if (loc.equals(closedPoints.get(i).getLocation()))
                    return true;
            }
            return false;
        }
}
