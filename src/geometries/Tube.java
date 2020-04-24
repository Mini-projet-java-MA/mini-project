package geometries;

import primitives.*;


public class Tube extends RadialGeometry {
    private Ray _ray;



    public Tube(Ray ray, double radius){
        super(radius);
     if (radius ==0)
    {
        throw new IllegalArgumentException("It's not possible to have radius equals to 0");
    }
     this._ray = ray;

    }


    public Ray getRay() {
        return _ray;
    }




    public Vector getNormal(Point3D point) {
        double scaleNumber= _ray.getDirection().dotProduct(point.subtract(_ray.getP0()));
        Point3D O = _ray.getP0().add(_ray.getDirection().scale(scaleNumber));
        Vector normalVector = point.subtract(O);
        return normalVector.normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + _radius +
                "} " + super.toString();
    }
}