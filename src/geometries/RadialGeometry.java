package geometries;

import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.awt.Color;

/**
 *this is abstract class represent a shapes geometry that contain radius
  */
public abstract class RadialGeometry implements Geometry {
    protected double _radius;
    /**
     * this is simple constructor build the shapes witch radius
     *
     * @param radius
     */
    public RadialGeometry(double radius) {
        _radius = radius;
    }

    /**a simple func that return radius
     *
     * @return reduis
     */
    public double getRadius() {
        return _radius;
    }

    public String toString() {
        return "RadialGeometry {radius=" + this._radius;
    }

}