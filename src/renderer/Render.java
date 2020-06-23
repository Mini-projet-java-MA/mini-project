package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * the object of this class is to create pixel matrix of picture basic on scene with 3D model
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

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
     * this func create an image
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        double width = (int) _imageWriter.getWidth();
        double height = (int) _imageWriter.getHeight();

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
     *
     * @param val
     * @return boolean value if double and val>0
     * @auther eliezergensburger
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
     * Calculate the color intensity in a point aproch phong model
     *
     * @return the color intensity
*/
    private Color calcColor(GeoPoint intersection ) {
        Color color = _scene.getAmbientLight().getIntensity();
        color = color.add(intersection._geometry.getEmission());
        Vector v = intersection.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        Vector n = intersection._geometry.getNormal(intersection.getPoint());
        Material material = intersection._geometry.getMaterial();
        int nShininess = material.getNshininess();
        double kD = material.getKd();
        double kS = material.getKs();
        if(_scene.getLight()!=null)
        for (LightSource lightSource : _scene.getLight()) {
            Vector l = lightSource.getL(intersection.getPoint());
            if (sign(Util.alignZero(n.dotProduct(l))) == sign(Util.alignZero(n.dotProduct(v)))) {
                Color lightIntensity = lightSource.getIntensity(intersection.getPoint());
                color = color.add(calcDiffusive(kD, l, n, lightIntensity),
                        calcSpecular(kS, l, n, v, nShininess, lightIntensity));
            }
        }
        return  color;
    }

    /**
     *
     * @param kS factor reduces the specular light.
     * @param l direction vector from light source to intersection point on geometry.
     * @param n normal vector from geometry.
     * @param v direction vector
     * @param nShininess level of shininess (for calculate the specular light)
     * @param lightIntensity color of light from light source
     * @return specular light (color).
     */

    private Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        double p = nShininess;

        Vector r = l.add(n.scale(-2 *Util.alignZero(l.dotProduct(n)))); // nl must not be zero!
        double minusVr = - Util.alignZero(r.dotProduct(v));
        if (minusVr <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return lightIntensity.scale(kS* Math.pow(minusVr, p));
    }
    /**
     *this function calculate the diffusive ligh
     * @param kD the factor of the diffusive light
     * @param l the vector of the light source
     * @param n the normal vector to the object
     * @param lightIntensity the intensity of the light
     * @return the diffusive light
     */
        private Color calcDiffusive(double kD, Vector l,Vector n,  Color lightIntensity) {
            return lightIntensity.scale(kD * Math.abs(l.dotProduct(n)));
        }
        /**
         * Create the image file in jpeg format
         */
        public void writeToImage() {
            _imageWriter.writeToImage();
        }

}

