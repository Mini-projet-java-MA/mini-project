package renderer;

import primitives.Color;
import primitives.Point3D;
import scene.Scene;

/**
 *th object of this class his to creat matrix of picture
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    public void renderImage() {
    }

    /**
     * function to draw a grid on our image by pixsel
     * @param interval number that the pixels are multiple of this number, are part of the grid.
     */
    public void printGrid(int interval) {
        Color white = new Color(255, 255, 255);
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, white.getColor());
            }
        }
    }

    /**
     * @param point the point that we want to color
     * @return the color intensity
     */
    private Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }

}
