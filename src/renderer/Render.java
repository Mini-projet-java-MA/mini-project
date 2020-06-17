package renderer;

import primitives.*;
import geometries.*;
import elements.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.*;

/**
 * th object of this class is to create pixel matrix of picture basinc on scene with 3D model
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    /**
     * Build Render object with a scene and image writer
     * @param imgWr Image Writer object
     * @param sc    scene object
     */
    public Render(ImageWriter imgWr, Scene sc) {
        _scene = sc;
        _imageWriter = imgWr;
    }

    /**
     *this func creat image
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and nY are the width and height of the image.
        int nX = _imageWriter.getNx(); //columns
        int nY = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < nY; ++row) {
            for (int column = 0; column < nX; ++column) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, column, row, distance, width, height);
                List<Intersectable.GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }
    /**
     * Finding the closest point to the P0 of the camera.
     * @param  intersectionPoints list of points, the function should find from
     * this list the closet point to P0 of the camera in the scene.
     * @return  the closest point to the camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Intersectable.GeoPoint result = null;
        double mindist = Double.POSITIVE_INFINITY;

        Point3D p0 = this._scene.getCamera().getP0();

        for (Intersectable.GeoPoint pt: intersectionPoints ) {
            double distance = p0.distance(pt._point);
            if (distance < mindist){
                mindist= distance;
                result = pt;
            }
        }

        return  result;
    }

    /**
     * function to draw a grid on our image by pixel
     * @param interval number that the pixels are multiple of this number, are part of the grid.
     */
    public void printGrid(int interval, java.awt.Color color) {
        int nY =  _imageWriter.getNy();
        int nX =  _imageWriter.getNx();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Calculate the color intensity in a point
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection) {
        Color color = _scene.getAmbientLight().getIntensity();
        color = color.add(intersection._geometry.getEmission());
        return color;
    }

    /**
     * Create the image file in jpeg format
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

}
