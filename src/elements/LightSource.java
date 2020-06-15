package elements;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this Interface represent thr actions of light
 */
public interface LightSource {
    /**
     * i dont know what i do here @aaron
     * @param p
     * @return
     */
    public Color getIntensity(Point3D p);

    /**
     * i dont know what i do here
     * @param p
     * @return
     */
    public Vector getL(Point3D p);

}
