package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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

    /**
     * @param ray - he his the ray that insert the object
     * @return list of Intersections
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();
//we creat the tree vector
        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double side1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(side1)) return null;
        double side2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(side2)) return null;
        double side3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(side3)) return null;

        if ((side1 > 0 && side2 > 0 && side3 > 0) || (side1 < 0 && side2 < 0 && side3 < 0)) return intersections;

        return null;
    }
}