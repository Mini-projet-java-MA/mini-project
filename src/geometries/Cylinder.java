package geometries;

import primitives.*;

import static primitives.Util.*;

public class Cylinder extends Tube {
    private double _height;

    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        if (height <= 0)
            throw new IllegalArgumentException("height can't be zero (or almost zero).");
        this._height = height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _ray=" + getRay() +
                ", _radius=" + getRadius() +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D p0 = getRay().getP0();
        Vector v = getRay().getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = point.subtract(p0).dotProduct(v);
        } catch (IllegalArgumentException e) { // P = O - center of the 1st base
            return v;
        }

        // if the point is at a base
        if (isZero(t) || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        p0 = p0.add(v.scale(t));
        return point.subtract(p0).normalize();
    }

}