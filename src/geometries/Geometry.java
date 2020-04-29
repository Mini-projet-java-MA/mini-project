package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 *
 */
public interface Geometry extends Intersectable {
    /**
     *
     * @param p
     * @return
     */
    List<Intersectable> rrg;
    Vector getNormal(Point3D p) ;
}