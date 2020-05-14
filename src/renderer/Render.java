package renderer;
import primitives.Color;
import primitives.Point3D;
import renderer.ImageWriter;
import scene.Scene;

/**
 *th object of this class his to creat matrix of picture
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    /**
     * @param point the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }
}
