package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.*;

import java.awt.*;

/**
 * class Triangle represents a triangle in 3D cartesian coordinate system
 */
public class Triangle extends Polygon {
    /**
     * this is a basic triangle constructor that get 3 points and build a triangle from them
     * in did 3 points are already a triangle
     * this constructor uses the constructor of the parent class Polygon
     *
     * @param p1 1st point
     * @param p2 2nd point
     * @param p3 3rd point
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
}
