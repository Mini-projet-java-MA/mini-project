package elements;

import primitives.Color;
import primitives.Vector;

/**
 * this class direction light of light in cartesian 3D coordinate system
 */
public class DirectionalLight extends  Light {
    private Vector  _direction;

    /**
     *a simple contractor for directional light with it's intensity and direction,
     * @param intensity intensity of the light
     * @param direction      direction vector
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this._direction = new Vector(direction.normalized());
    }

}
