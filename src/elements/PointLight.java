package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *  this class represents a point in of light in cartesian 3D coordinate system
 */
public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    protected   double _kC, _kL, _kQ;
    /**
     * Contractor to build a Point light
     *
     * @param intensity
     * @param position  localisation of the light
     * @param kC   coefficient of quadratic attenuation of the light in the distance
     * @param kL   coefficient of linear weakening of the light in the distance
     * @param kQ   coefficient of exponential weakening of the light at a distance
     */
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = new Point3D(position);
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * dSquared));
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalized();
    }
}
