package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.awt.Color;

/**
 *
 */
public abstract class RadialGeometry implements Geometry {
    protected double _radius;

    public RadialGeometry(double radius) {
        _radius = radius;
    }


    public double getRadius() {
        return _radius;
    }

    public String toString() {
        return "RadialGeometry {radius=" + this._radius;
    }

}