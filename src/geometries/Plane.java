package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * class plane represents a plane in 3D cartesian coordinate system
 */
public class Plane extends Geometry {
    private Point3D _p;
    private Vector _normal;

    /**
     * this is a basic constructor who build a plane from 3 points int cartesian coordinates system
     * @param p1 1 st point
     * @param p2 2nd point
     * @param p3 3rd point
     * @param emission the emission light of the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3,Color emission) {
        super(emission);
        _p = new Point3D(p1);
        _normal = getNormal(p1, p2, p3);
    }

    /**
     * this constructor build a plane from a single point and a vector that will be normalized
     * @param p      the point in the plane
     * @param normal the vector normalized we'll use to build the plane
     * @param emission the emission light of the plane
     */
    public Plane(Point3D p, Vector normal,Color emission) {
        super(emission);
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

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    /**
     * this function returns the normal vector to the plane
     * @return the vector normal to the plane
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Vector pq0;
        try {
            pq0 = _p.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return null;
        }

        double nv = alignZero(_normal.dotProduct(ray.getDirection()));
        //if the ray are paralle to the plan so is not intersections
        if (nv == 0)
            return null;

        // intersections point equal 𝑃 = 𝑃0 + 𝑡 ∙ 𝑣, 𝑡 ≥ 0
        double t = alignZero(_normal.dotProduct(pq0) / nv);
        //t>=0, and hence:
        if (t <= 0)
            return null;
        return List.of(new GeoPoint(this,ray.getTargetPoint(t)));
    }
}