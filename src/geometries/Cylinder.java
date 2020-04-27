package geometries;

import primitives.*;

import static primitives.Util.*;

/**
 * the cylinder the tube with height so he exsqlty like tube and he have one more param height
 */
public class Cylinder extends Tube {
    private double _height;

    /**  this is the basic constructor for a tube :
     * it receive ray and raduis,
     * @param ray -the ray
     * @param radius- the raduis
     * @param height- the height of the (cylinder he limited with height)
     * @throws if the radius equal on small to zero so we don't can't have a cylinder so his return IllegalArgumentException
     */

    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        if (height <= 0)    //if the radius equal on small to zero so we don't can't have a cylinder so his return IllegalArgumentException
        throw new IllegalArgumentException("height must be equal or superior to zero.");
        this._height = height;
    }

    /**
     * simple function get
     * @return the ray
     */
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

    /**
     *
     * @param point- he recive a point on cylinder
     * @return a normal of the cylinder normalize (new vector in size one)
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return  super.getNormal(point3D);

    }



}