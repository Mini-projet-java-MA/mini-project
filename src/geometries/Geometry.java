package geometries;

import elements.Camera;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * interface for geometry
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;

    /**
     * constructor
     * @param emission the emission light of the geometry
     */
    public Geometry(Color emission){
        this._emission=emission;
    }

    /**
     * default constructor
     * initiate the emission light with the Color.Black
     */
    public Geometry(){
        this._emission=Color.BLACK;
    }

    /**
     * the getter for the emission light on the geometry
     * @return the emssion light
     */
    public Color getEmission() {
        return _emission;
    }



    /**
     * getNormal function to get the normal vector (unit vector - length=1) from a point on a geometry surface
     * @param p a point on geometry surface
     * @return the normal vector
     */
    abstract public Vector getNormal(Point3D p);
}