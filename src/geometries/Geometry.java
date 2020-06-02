package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * we do interface for geometry
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;

    /**
     * getNormal function to get the normal vector (unit vector - length=1) from a point on a geometry surface
     * @param p a point on geometry surface
     * @return the normal vector
     */
    abstract public Vector getNormal(Point3D p);
}