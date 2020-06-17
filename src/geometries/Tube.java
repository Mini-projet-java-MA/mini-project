package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * tube class represents a tube in 3D cartesian coordinate and he define it with a radius and ray
 * @author aaron
 */
public class Tube extends RadialGeometry {
    private Ray _ray;

    /**
     * this is the basic constructor for a tube:
     * he receive ray and radius
     * @param radius
     * @param ray
     * @throws IllegalArgumentException when the radius is equal or smaller to zero so we don't have a tube
     */
    public Tube(Ray ray, double radius) {
        super(radius,Color.BLACK);
        if (radius <= 0)
            throw new IllegalArgumentException("It's not possible to have radius equals to 0");
        this._ray = new Ray(ray);
    }
    /**
     * this is the basic constructor for a tube:
     * he receive ray and radius
     * @param radius
     * @param ray
     * @param emission the emission light of the tube
     * @throws IllegalArgumentException when the radius is equal or smaller to zero so we don't have a tube
     */
    public Tube(Ray ray, double radius,Color emission) {
        super(radius,emission);
        if (radius <= 0)
            throw new IllegalArgumentException("It's not possible to have radius equals to 0");
        this._ray = new Ray(ray);
    }

    /**
     * simple function get the ray of the tube
     * @return the ray
     */
    public Ray getRay() {
        return _ray;
    }

    /**
     * the function should return the normal to the tube in size one
     * @param point- he receive a point on cycle tube
     * @return a normal of the tube normalize
     */
    @Override
    public Vector getNormal(Point3D point) {
        // distance from axis start to the projection of the point onto axis
        double scaleNumber = _ray.getDirection().dotProduct(point.subtract(_ray.getP0()));
        // he found the point on the border of the tube
        Point3D o = _ray.getP0().add(_ray.getDirection().scale(scaleNumber));
        // the normal of tube his the point on the border of the tub minus the central
        Vector normal_tube = point.subtract(o);
        //rerun normal tube vector in size one
        return normal_tube.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public String toString() {
        return "ray: " + _ray +
                ", radius: " + _radius;
    }
}