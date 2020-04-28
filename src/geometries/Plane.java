package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class plane represents a plane in 3D cartesian coordinate system
 */
public class Plane {
    private Point3D _p;
    private Vector _normal;

    /**
     * this is a basic constructor who build a plane from 3 points int cartesian coordinates system
     *
     * @param _p1 1st point
     * @param _p2 2nd point
     * @param _p3 3rd point
     */
    public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
        _p = new Point3D(_p1);
        _normal = getNormal(_p1, _p2, _p3);
    }

    /**
     * this constructor build a plane from a single point and a vector that will be normalized
     *
     * @param p      the point
     * @param normal the vector normalized we'll use to build the plane
     */
    public Plane(Point3D p, Vector normal) {
        _p = new Point3D(p);
        _normal = normal.normalized();
    }

    /**
     * Produce normal vector (orthogonal unit vector) from 3 points in the plane)
     *
     * @param p1 1st point
     * @param p2 2nd point
     * @param p3 3rd point
     * @return normal vector
     * @throws IllegalArgumentException in case when the points are in the same line (maybe also collocated points)
     */
    private Vector getNormal(Point3D p1, Point3D p2, Point3D p3) {
        // Vector constructor from the subtract and crossProduct operations
        // will throw exception when a pair of points are collocated or
        // the points are in the same line
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        return v1.crossProduct(v2).normalize();
    }

    /**
     * this function allows us to compute the normal vector in a specific point in the plane
     *
     * @param p the point where we want to compute the normal vector
     * @return the normal vector computed in the specific point
     */
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    /**
     * this function returns the normal vector to the plane
     *
     * @return the vector normal to the plane
     */
    public Vector getNormal() {
        return _normal;
    }
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

}