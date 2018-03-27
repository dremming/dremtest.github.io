package com.laba1;

import javafx.geometry.Point3D;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point3d
{
    /**
     * A two-dimensional point class.
     */
    /**
     * X ,Y, Z coordinate of the point
     */
    private double xCoord;
    private double yCoord;
    private double zCoord;

    /**
     * Constructor to initialize point to (x, y) value.
     */
    public Point3d(double x, double y, double z)
    {
        xCoord = x;
        yCoord = y;
        xCoord = z;
    }
    /**
     * No-argument constructor:  defaults to a point at the origin.
     */
    public Point3d()
    {
        // Call two-argument constructor and specify the origin.
        xCoord=0.0;
        yCoord=0.0;
        zCoord=0.0;
    }
    /**
     * Return the X coordinate of the point.
     */
    public double getX()
    {
        return xCoord;
    }
    /**
     * Return the Y coordinate of the point.
     */
    public double getY()
    {
        return yCoord;
    }

    public double getZ()
    {
        return zCoord;
    }

    /**
     * Set the X coordinate of the point.
     */
    public void setX(double a)
    {
        xCoord = a;
    }

    /**
     * Set the Y coordinate of the point.
     */
    public void setY(double b)
    {
        yCoord = b;
    }

    /**
     * Set the Z coordinate of the point.
     */
    public void setz(double c)
    {
        zCoord = c;
    }
    public boolean comparisonofxyz(Point3d a)
    {
        if (a.getX() == xCoord && a.getY() == yCoord && a.getZ() == zCoord)
            return true;
        else
            return false;
    }
    public double distanceTo(Point3d val)
    {
        return(sqrt(pow(xCoord-val.getX(),2)+pow(yCoord-val.getY(),2)+pow(zCoord-val.getZ(),2)));
    }
}
