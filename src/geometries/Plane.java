package geometries;

import primitives.Point3D;
import primitives.Vector;
public class Plane {
    Point3D _p;
    Vector _normal;

    public Plane(Point3D _p1,Point3D _p2, Point3D _p3) {
        _p=new Point3D(_p1);
        _normal=new Vector(getNormal(_p1,_p2,_p3));


    }

    public Plane(Point3D p, Vector normal) {
        _p = new Point3D(p);
        _normal = new Vector(normal);
    }

    public Vector getNormal(Point3D _p1,Point3D _p2, Point3D _p3)
    {

        Vector V1=new Vector(_p2.subtract(_p1));
        Vector V2=new Vector(_p3.subtract(_p1));
        return  new Vector( V1.crossProduct(V2).normalize());


    }
    public Vector getNormal(Point3D p)
    {
        return _normal;
    }
    public Vector getNormal()
    {
        return _normal;
    }


}