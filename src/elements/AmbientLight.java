package elements;

import primitives.Color;

/**
 * the class should represent
 */
public class AmbientLight {
    private Color _intensity;
    public  AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
    public Color getIntensity() {
        return _intensity;
    }
}
