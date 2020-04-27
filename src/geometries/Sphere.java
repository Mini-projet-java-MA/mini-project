package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * sphere class represents sphere in 3D Cartesian coordinate
 * for represents a sphere 3D Cartesian we need radius and point 3D
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * this is a basic constructor it receives two param radius and center
     *
     * @param radius- the radius of the sphere
     * @param center- point 3d that his the location of the sphere
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = center;
    }

    /**
     * a simple function that return the center a point 3d
     *
     * @return point 3d
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * the function find the normal vector og the sphere when he recive point 3d
     *
     * @param point3d
     * @return new vector normal of the sphere in size one
     */
    public Vector getNormal(Point3D p) {

//the normal of the vector the diff between the point p minus the center
        Vector normal_sphere = p.subtract(_center);
        //rerun normal sphere vector in size one
        return normal_sphere.normalize();
    }
}