package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 *  this interface for all geometries that are able to intersect from a ray to their entity
 * @ auther aaron
 */
public interface Intersectable {
    /**
     * @param ray- he his the ray that insert the object
     * @return a list of all intersection
     */
    List<Point3D> findIntersections(Ray ray);

}

