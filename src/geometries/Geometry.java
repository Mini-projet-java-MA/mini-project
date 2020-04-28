package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 *
 */
public interface Geometry extends Intersectable {
    /**
     *
     * @param p
     * @return
     */
    Vector getNormal(Point3D p) ;
}