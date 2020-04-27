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
     *function to find the normal of the cylinder
     * @param point to find the normal
     * @return a normal of the cylinder normalize (new vector in size one)
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = this.getRay().getP0();
        Vector direction = this.getRay().getDirection();
        Vector maybe_normal_cylinder= point.subtract(o);
        double scale_projection;
        //if normal he inside of the cylinder
        try {
            //if the projection he equal to zero because the vector are orthogonal it onle because p=0
            scale_projection=  maybe_normal_cylinder.dotProduct(direction);
        }
        catch (IllegalArgumentException e) {
            return direction.normalize();
        }
        // if one of the vector equal to zero or dot product he very close to height it on border of the height
        if (scale_projection == 0 || isZero(_height - scale_projection))
            //so normal he his diretion
            return direction.normalize();

        //if the point is outside
        o = o.add(direction.scale(scale_projection));
        Vector normal_cylinder= point.subtract(o);
        return normal_cylinder.normalize();
    }



}