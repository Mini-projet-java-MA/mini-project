package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * tube class for represents tube tube define witch a radius and ray
 *
 * @author aaron
 */
public class Tube extends RadialGeometry {
    private Ray _ray;
    /**
     * this is the basic constructor for a ray:
     * it receive ray and raduis
     * @param radius
     * @param ray
     * @throws  if the radius equal on small to zero so we don't have a tube so his return IllegalArgumentException
     */
    public Tube(Ray ray, double radius) {
        super(radius);
        if (radius <=0)
            throw new IllegalArgumentException("It's not possible to have radius equals to 0");
        this._ray = new Ray(ray);
    }

    /**
     * simple function get
     * @return the ray
     */
    public Ray getRay() {
        return _ray;
    }

    /** the fucnction should return the normal of tube in size one
     *
     * @param point- he recive a point on cycle tube
     * @param
     * @return
     */
    public Vector getNormal(Point3D point) {
        // distance from axis start to the projection of the point onto axis
        double scaleNumber = _ray.getDirection().dotProduct(point.subtract(_ray.getP0()));
        // he find the point on the border of the tube
        Point3D o = _ray.getP0().add(_ray.getDirection().scale(scaleNumber));
        // he rerun a new vector in size one
        return point.subtract(o).normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + _radius +
                "} " + super.toString();
    }
}