package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 *
 */
public interface Geometry extends Intersectable {

    List<Point3D> findIntersections(Ray ray);
    Vector getNormal(Point3D p) ;
}