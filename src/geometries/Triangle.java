package geometries;

import primitives.Point3D;

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
}
