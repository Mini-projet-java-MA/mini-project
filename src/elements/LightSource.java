package elements;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this Interface represent thr actions of light
 */
public interface LightSource {
    /**
     * **********************i not sure
     * get the Intensity of the lamp
     * @param p the palce of the Intensity
     * @return the Intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * i dont know what i do here
     * @param p -place of light
     * @return the direction of the light point
     */
    public Vector getL(Point3D p);

}
