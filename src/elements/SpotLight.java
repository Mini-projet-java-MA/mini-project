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
     *
     * @param intensity
     * @param position location  of the light
     * @param direction of light
     * @param kC   coefficient of quadratic attenuation of the light in the distance
     * @param kL   coefficient of linear weakening of the light in the distance
     * @param kQ   coefficient of exponential weakening of the light at a distance
     */
    public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(intensity, position, kC,kL, kQ);
        this._direction = new Vector(_direction).normalized();
    }


}
