package geometries;

import elements.Camera;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
     * the function find the normal vector of the sphere at a specific point 3d
     *
     * @param p the specified point
     * @return new vector normal of the sphere in size one
     */
    @Override
    public Vector getNormal(Point3D p) {
        //the normal of the vector the diff between the point p minus the center
        Vector normal_sphere = p.subtract(_center);
        //rerun normal sphere vector in size one
        return normal_sphere.normalize();
    }

    /**
     * @param ray - he his the ray that insert the object
     * @return a list of all intersection
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();
        Vector u;
        // 𝑢 = 𝑂 − 𝑃0
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        //tm=v*u
        double tm = alignZero(v.dotProduct(u));
        //d=u^2+tm^2
        double dsquared;
        if (tm == 0)
            dsquared = u.lengthSquared();
        else {
            dsquared = u.lengthSquared() - tm * tm;
        }
        double thsquared = alignZero(_radius * _radius - dsquared);

        if (thsquared <= 0) return null;
        //th=radius*radius-d*d
        double th = alignZero(Math.sqrt(thsquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;

        if (t1 > 0 && t2 > 0)
            return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        if (t2 > 0)
            return List.of(ray.getTargetPoint(t2));
        return null;
    }

    @Override
    public String toString() {
        return String.format
                ("point: " + _center + ", radius: " + _radius);
    }
}