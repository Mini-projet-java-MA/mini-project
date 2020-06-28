package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the object of this class is to create pixel matrix of picture basic on scene with 3D model
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    /**
     * constant for the size of the ray sources moving for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * Build Render object with a scene and image writer
     *
     * @param imgWr Image Writer object
     * @param sc    scene object
     */
    public Render(ImageWriter imgWr, Scene sc) {
        _scene = sc;
        _imageWriter = imgWr;
    }

    /**
     * this function is used to create an image
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        double width = (double) _imageWriter.getWidth();
        double height = (double) _imageWriter.getHeight();

        //Nx and nY are the width and height of the image.
        double nX = _imageWriter.getNx(); //columns
        double nY = _imageWriter.getNy(); //rows
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
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Intersectable.GeoPoint result = null;
        double minDist = Double.POSITIVE_INFINITY;

        Point3D p0 = this._scene.getCamera().getP0();

        for (Intersectable.GeoPoint pt : intersectionPoints) {
            double distance = p0.distance(pt._point);
            if (distance < minDist) {
                minDist = distance;
                result = pt;
            }
        }

        return result;
    }
    /**
     * unshaded function check if specific ray from light source to geometry passes through other geometry
     *
     * @param l        vector from light source to point on geometry
     * @param n   a unit vector from, vertical to intersection point.
     * @param geopoint current geoPoint (the intersection point)
     * @return true if there is no hindrance, and false otherwise
     */
    private boolean unshaded(Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint._point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        return intersections == null;
    }


    /**
     * @param val the tested value
     * @return boolean value if double and val>0
     */
    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * function to draw a grid on our image by pixel
     *
     * @param interval number that the pixels are multiple of this number, are part of the grid.
     */
    public void printGrid(int interval, java.awt.Color color) {
        int nY = _imageWriter.getNy();
        int nX = _imageWriter.getNx();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Calculate the color intensity in a point approach phong model
     *
     * @return the color intensity
     */

    private Color calcColor(GeoPoint geopoint) {
        Color color = _scene.getAmbientLight().getIntensity();
        color = color.add(geopoint._geometry.getEmission());
        Vector v = geopoint.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        Vector n = geopoint._geometry.getNormal(geopoint.getPoint());
        Material material = geopoint._geometry.getMaterial();
        int nShininess = material.getNshininess();
        double kD = material.getKd();
        double kS = material.getKs();
        double nv = alignZero(n.dotProduct(v));
        if (nv != 0) return color;//ne to check if is good
        for (LightSource lightSource : _scene.getLight()) {
            Vector l = lightSource.getL(geopoint.getPoint());
            if (n.dotProduct(l) * n.dotProduct(v) > 0)
                if (unshaded(l, n, geopoint)){
                Color lightIntensity = lightSource.getIntensity(geopoint.getPoint());
                color = color.add(calcDiffusive(kD, l, n, lightIntensity),
                        calcSpecular(kS, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * @param kS             factor reduces the specular light.
     * @param l              direction vector from light source to intersection point on geometry.
     * @param n              normal vector from geometry.
     * @param v              direction vector
     * @param nShininess     level of shininess (for calculate the specular light)
     * @param lightIntensity color of light from light source
     * @return specular light (color).
     */

    private Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        double p = nShininess;

        Vector r = l.add(n.scale(-2 * l.dotProduct(n))); // nl must not be zero!
        double minusVr = -alignZero(r.dotProduct(v));
        if (minusVr <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return lightIntensity.scale(kS * Math.pow(minusVr, p));
    }

    /**
     * this function calculate the diffusive ligh
     *
     * @param kD             the factor of the diffusive light
     * @param l              the vector of the light source
     * @param n              the normal vector to the object
     * @param lightIntensity the intensity of the light
     * @return the diffusive light
     */
    private Color calcDiffusive(double kD, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kD * Math.abs(l.dotProduct(n)));
    }

    /**
     * Create the image file in jpeg format
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

}

