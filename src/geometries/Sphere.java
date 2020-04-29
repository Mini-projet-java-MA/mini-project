package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        List<Point3D> insertion = null;
        // 𝑢 = 𝑂 − 𝑃0
        double u = _center.subtract(ray.getP0()).length();
        //tm=v*u
        double tm = ray.getDirection().length() * u;
        //d=root of (u^2+tm^2)
        double d = Math.sqrt((u * u) - (tm * tm));
        // if (d>r) there are no intersections
        if (d>_radius)return  null;
        //th=radius*radius-d*d
        double th = Math.sqrt((_radius * _radius) - (d * d));
        //t1=tm+th
        double t1 = tm + th;
        //t1=tm-th
        double t2 = tm + th;
        if (t1>0)
        insertion.add(ray.getP0().add((ray.getDirection().scale(t1))));
        if (t2>0)
        insertion.add(ray.getP0().add((ray.getDirection().scale(t2))));
        return insertion;
    }
}