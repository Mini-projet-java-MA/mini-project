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
     * The function looks for intersection points between a ray and a geometry. <br/>
     * If there are no intersections, the function returns null.
     * @param ray the ray that intersects the object
     * @return a list of all intersection points
     */
    List<Point3D> findIntersections(Ray ray);

}

