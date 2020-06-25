package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for improve the spot light (sharp spot beam)
 */
public class SpotLightImproved extends SpotLight {
    private double _concentration;
    /**
     * a simp;le contrator
     * @param intensity colorIntensity
     * @param position       position
     * @param direction      direction
     * @param kC             kc
     * @param kL             kl
     * @param kQ             kq
     */
    public SpotLightImproved(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(intensity, position, direction, kC, kL, kQ);
        _concentration = concentration;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        Vector vector;
        if(p.subtract(_position).normalized() == null)
            vector = new Vector(_direction);
        else
            vector = p.subtract(_position).normalized();
        return (_intensity.scale(Math.max(0,Math.pow(_direction.dotProduct(vector),_concentration)))
                .reduce(_kC + _kL * d + _kQ * dSquared));
    }
}