package geometries;

import primitives.Color;


/**
 *this is abstract class represent a shapes geometry that contain radius
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * this is simple constructor build the shapes witch radius
     * @param radius
     */
    public RadialGeometry(double radius) {
         this(radius,Color.BLACK);
    }
    public RadialGeometry(double radius,Color emission) {
        super(emission);
        _radius = radius;
    }

    /**
     * a simple func that return radius
     * @return radius
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * this func return the radius in string
     * @return radius
     */
    public String toString() {
        return "RadialGeometry {radius=" + this._radius;
    }

}