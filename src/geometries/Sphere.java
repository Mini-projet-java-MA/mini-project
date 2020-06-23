package geometries;

import primitives.*;

import java.util.Collections;
import java.util.LinkedList;
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
     * @param radius - the radius of the sphere
     * @param center - point 3d that his the location of the sphere
     * @param emission the emission light of the sphere
     * @param material his the material that represent the objet
     */
    public Sphere(Color emission , Material material,double radius, Point3D center) {
            super(radius,emission, material);
            _center = center;
    }
    /**
     * this is a basic constructor it receives two param radius and center
     * @param radius - the radius of the sphere
     * @param center - point 3d that his the location of the sphere
     * @param emission the emission light of the sphere
     */
    public Sphere(Color emission,double radius, Point3D center) {
        this(emission, new Material(0, 0, 0),radius, center);
    }
    /**
     * this is a basic constructor it receives two param radius and center
     * @param radius - the radius of the sphere
     * @param center - point 3d that his the location of the sphere
     */
    public Sphere(double radius, Point3D center) {
        this( Color.BLACK,radius, center);
    }

    /**
     * a simple function that return the center a point 3d of the position of the sphere
     * @return point 3d of center
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * the function find the normal vector of the sphere at a specific point 3d
     * @param p the specified point
     * @return new vector normal of the sphere in size one
     */
    @Override
    public Vector getNormal(Point3D p) {
        //the normal of the vector the diff between the point p minus the center
        Vector normalSphere = p.subtract(_center);
        //rerun normal sphere vector in size one
        return normalSphere.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();
        Vector u;
        // 𝑢 = 𝑂 − 𝑃0
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            return new LinkedList<>(Collections.singletonList(new GeoPoint(this, p0.add(v.scale(_radius)))));
        }
        //tm=v*u
        double tm = alignZero(v.dotProduct(u));
        //d=u^2+tm^2
        double dSquared;
        if (tm == 0)
            dSquared = u.lengthSquared();
        else {
            dSquared = u.lengthSquared() - tm * tm;
        }
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;
        //th=radius*radius-d*d
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;

        if (t1 > 0 && t2 > 0)
            return List.of((new GeoPoint(this , ray.getTargetPoint(t1))), (new GeoPoint(this , ray.getTargetPoint(t2)))); //P1 , P2
        if (t1 > 0)
            return List.of(new GeoPoint( this , ray.getTargetPoint(t1)));
        if (t2 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
        return null;
    }

    @Override
    public String toString() {
        return String.format
                ("point: " + _center + ", radius: " + _radius);
    }
}