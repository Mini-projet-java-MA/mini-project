package elements;

import primitives.Color;

/**
 * The class represents ambient lighting in the scene - the fill color which is added to all objects of 3D model
 */
public class AmbientLight {
    private Color _intensity;

    /**
     *the func refill light
     * @param ia-refill light
     * @param ka-promotes light refill
     */
    public  AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }

    /**
     *the pususense vof the color
     * @return
     */
    public Color getIntensity() {
        return _intensity;
    }
}
