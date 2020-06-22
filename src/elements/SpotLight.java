package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this class spot light in of light in cartesian 3D coordinate system
 */
public class SpotLight extends PointLight {
    private Vector _direction;
    //protected Vector _direction - i dont understand  if the variable should be in protected or in private
    //***************** Constructors **********************//

    /**
     * constructor for spot light
     * @param intensity
     * @param position location  of the light
     * @param direction of light
     * @param kC   coefficient of quadratic attenuation of the light in the distance
     * @param kL   coefficient of linear weakening of the light in the distance
     * @param kQ   coefficient of exponential weakening of the light at a distance
     */
    public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(intensity, position, kC,kL, kQ);
        this._direction = new Vector(direction).normalized();
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

        return (_intensity.scale(Math.max(0,_direction.dotProduct(vector)))
                .reduce(_kC + _kL * d + _kQ * dSquared));

    }
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }


}
