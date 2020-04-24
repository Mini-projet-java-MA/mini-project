package geometries;

import primitives.*;

import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Cylinder extends Tube {
    private double _height;

    /* ********* Constructors ***********/


    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        if (height >= 0)
            this._height = height;
        else
            throw new IllegalArgumentException("height can't be zero (or almost zero).");
    }


    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _ray=" + getRay() +
                ", _radius=" + get_radius()+
                '}';
    }



    @Override
    public Vector getNormal(Point3D point) {
        Point3D a = getRay().get_P1();
        Vector b = getRay().get_direction();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(a).dotProduct(b));
        } catch (IllegalArgumentException e) { // P = O
            return b;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return b;

        a = a.add(b.scale(t));
        return point.subtract(a).normalize();
    }


}