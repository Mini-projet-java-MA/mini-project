package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane {
    private Point3D _p;
    private Vector _normal;

    public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
        _p = new Point3D(_p1);
        _normal = getNormal(_p1, _p2, _p3);
    }

    public Plane(Point3D p, Vector normal) {
        _p = new Point3D(p);
        _normal = normal.normalized();
    }

    /**
     * Produce normal vector (orthogonal unit vector) from 3 points in the plane)
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

    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Vector getNormal() {
        return _normal;
    }

}