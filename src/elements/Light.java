package elements;

import primitives.Color;

/**
 * this class represent the light in our moodel of 3D
 */
public abstract class Light {
    protected Color _intensity;

    /**
     * default contractor for abstract and the default color his black
     */
    public Light()
    {
        _intensity=Color.BLACK;
    }

    /**
     * corrector for the abrtrac class light to initialize Lights color intensity
     * @param intensity his the color of the light in this abstrac class
     */
    public Light(Color intensity)
    {
        _intensity=intensity;
    }
    /**
     *the getter of the intensity of the light
     * @return color Intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
    /**
     *this function is the constructor it allows us to set an ambient light in our scene
     * @param ia-refill light
     * @param ka-promotes light refill
     */
    public  Light(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }

}
