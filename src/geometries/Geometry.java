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
    /**
     * getNormal function to get the normal vector from a point on a geometry
     * @param point3D a point on geometry
     * @return the normal vector
     */
    protected Color _emission;

    Vector getNormal(Point3D point3D);
}