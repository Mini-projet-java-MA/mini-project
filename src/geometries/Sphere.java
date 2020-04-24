package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    private Point3D _center;

    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = center;
    }

    public Point3D getCenter() {
        return _center;
    }

    public Vector getNormal(Point3D p) {
        return p.subtract(_center).normalize();
    }
}