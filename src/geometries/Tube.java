package geometries;

import primitives.*;


public class Tube extends RadialGeometry {
    private Ray _ray;

    public Tube(Ray ray, double radius) {
        super(radius);
        if (radius == 0)
            throw new IllegalArgumentException("It's not possible to have radius equals to 0");
        this._ray = new Ray(ray);
    }

    public Ray getRay() {
        return _ray;
    }

    public Vector getNormal(Point3D point) {
        // distance from axis start to the projection of the point onto axis
        double scaleNumber = _ray.getDirection().dotProduct(point.subtract(_ray.getP0()));
        // the projection of the point onto axis
        Point3D o = _ray.getP0().add(_ray.getDirection().scale(scaleNumber));
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