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
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
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
     * this func create an image
     */
    public void renderImage() {
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        double distance = _scene.getDistance();

        Ray ray;
        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j) {
                //creating a new ray for every pixel
                ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                //List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                GeoPoint closestPoint = findClosestIntersection(ray);
                // if no have intersection on this ray so paint background
                if (closestPoint == null)
                    _imageWriter.writePixel(j, i, background);
                else {
                    //GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint, ray).getColor());
                }
            }
    }
    /**
     * the function should find the intercetion most close
     *
     * @param ray ray
     * @return the closest intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        GeoPoint closestPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        Point3D ray_p0 = ray.getP0();
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null)
            return null;
        for (GeoPoint gp : intersections) {
            double distance = ray_p0.distance(gp.getPoint());
            if (distance < closestDistance) {
                closestPoint = gp;
                closestDistance = distance;
            }
        }
        return closestPoint;
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
     */
    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * function to draw a grid on our image by pixel
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
     * unshaded function check if specific ray from light source to geometry passes through other geometry
     *
     * @param l        vector from light source to point on geometry
     * @param n   a unit vector from, vertical to intersection point.
     * @param geoPoint current geoPoint (the intersection point)
     * @return true if there is no hindrance, and false otherwise
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint._point, lightDirection, n);
        Point3D point = geoPoint._point;
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return true;
        double lightDistance = light.getDistance(point);
        for (GeoPoint gp : intersections) {
            double t = gp._point.distance(point) - lightDistance;
            if (alignZero(t) <= 0 && gp._geometry.getMaterial().getKt() == 0)
                return false;
        }
        return true;
    }

    /**
     * Calc the color intensity in a intersection point
     *
     * @param geoPoint geo point
     * @param inRay    ray
     * @return the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        return calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(
                _scene.getAmbientLight().getIntensity());
    }
    /**
     * Calculate the color intensity in a point approach phong model
     *
     * @return the color intensity
     */

    private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k ) {
        Color color = geopoint._geometry.getEmission();
        Vector v = geopoint.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        Vector n = geopoint._geometry.getNormal(geopoint.getPoint());
        Material material = geopoint._geometry.getMaterial();
        int nShininess = material.getNshininess();
        double kD = material.getKd();
        double kS = material.getKs();
        for (LightSource lightSource : _scene.getLight()) {
            Vector l = lightSource.getL(geopoint.getPoint());
            if (sign(n.dotProduct(l)) == sign(n.dotProduct(v))) {
                double ktr = transparency(lightSource, l, n, geopoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.getPoint()).scale(ktr);
                    color = color.add(calcDiffusive(kD, l, n, lightIntensity),
                            calcSpecular(kS, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        if (level == 1) return Color.BLACK;
        double kr = geopoint._geometry.getMaterial().getKr(), kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay( geopoint._point, inRay,n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
            double kt = geopoint._geometry.getMaterial().getKt(), kkt = k * kt;
            if (kkt > MIN_CALC_COLOR_K) {
                Ray refractedRay = constructRefractedRay(geopoint._point, inRay) ;
                GeoPoint refractedPoint = findClosestIntersection(refractedRay);
                if (refractedPoint != null)
                    color = color.add(calcColor(refractedPoint, refractedRay,level-1, kkt).scale(kt));
            }
        }
        return  color;
    }
    /**
     * func calc the level of transparency
     *
     * @param ls ls
     * @param l  l
     * @param n  n
     * @param gp gp
     * @return the level
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp._point, lightDirection, n);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return 1d;
        double lightDistance = ls.getDistance(gp._point);
        double ktr = 1d;
        for (GeoPoint geo : intersections) {
            double temp = geo._point.distance(gp._point) - lightDistance;
            if (alignZero(temp) <= 0) {
                ktr *= geo._geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
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

        Vector r = l.add(n.scale(-2 *l.dotProduct(n))); // nl must not be zero!
        double minusVr = - alignZero(r.dotProduct(v));
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
    /**
     * Returns reflected Ray
     *
     * @param point
     * @param ray
     * @param n
     * @return reflected ray
     */
    private Ray constructReflectedRay(Point3D point, Ray ray, Vector n){
        Vector v = ray.getDirection();
        double vNormal = v.dotProduct(n);
        if (vNormal == 0)
            return null;
        Vector r = v.subtract(n.scale(2 * vNormal));
        return new Ray(point,  n);
    }
    /**
     * this func calc the refracted ray
     *
     * @param point point
     * @param inRay ray
     * @return the ref ray
     */
    private Ray constructRefractedRay(Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDirection());
    }
}

