package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this class represents a point in of light in cartesian 3D coordinate system
 */
public class PointLight extends Light implements LightSource {
    private Point3D _position;
    private double _kC, _kL, _kQ;

    /**
     * Constructor to build a Point light
     *
     * @param intensity light intensity
     * @param position  localisation of the light
     * @param kC        coefficient of quadratic attenuation of the light in the distance
     * @param kL        coefficient of linear weakening of the light in the distance
     * @param kQ        coefficient of exponential weakening of the light at a distance
     */
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = position;
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    public Point3D getPosition() {
        return _position;
    }

    public double getKC() {
        return _kC;
    }

    public double getKL() {
        return _kL;
    }

    public double getKQ() {
        return _kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = Math.sqrt(dSquared);
        return _intensity.reduce(_kC + _kL * d + _kQ * dSquared);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalize();
    }
}
