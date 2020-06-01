package geometries;

import primitives.Ray;
import primitives.Vector;

import java.util.List;
import primitives.Color;


/**
 *this is abstract class represent a shapes geometry that contain radius
  */
public abstract class RadialGeometry implements Geometry {
    protected double _radius;
    protected Color _emission;

    /**
     * this is simple constructor build the shapes witch radius
     * @param radius
     * @param  color
     */
    public RadialGeometry(double radius ,Color emission) {

        _radius = radius;
        this._emission = new Color(emission);

    }

    /**
     * this is simple constructor build the shapes witch radius
     * @param radius
     * @param  color-black
     */
    public RadialGeometry(double radius ) {

        _radius = radius;
        this._emission = new Color(java.awt.Color.black);

    }


    /**
     * a simple func that return radius
     * @return reduis
     */
    public double getRadius() {
        return _radius;
    }

    public String toString() {
        return "RadialGeometry {radius=" + this._radius;
    }

}