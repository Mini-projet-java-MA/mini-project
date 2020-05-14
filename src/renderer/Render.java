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
        /***
         * psodo code from cours
         * Camera camera = _scene.getCamera();
         * Intersectable geometries = _scene.getGeometries();
         * java.awt.Color background = _scene.getBackground().getColor();
         * int nX = _imageWriter.getNX();
         * …
         * for each point (i,j) in the view plane // i is pixel row number and j is pixel in the row number
         * Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
         * List<Point3D> intersectionPoints = geometries.findIntersections(ray);
         * if intersectionPoints is empty
         * _imageWriter.writePixel(j, i, background);
         * else
         * Point3D closestPoint = getClosestPoint(intersectionPoints);
         * _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor())
         */

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
    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {return  null;}

}
