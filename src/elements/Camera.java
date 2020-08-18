package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * camera class represents camera in 3D Cartesian coordinate
 *
 * @author aaron
 */
public class Camera {
    private Point3D _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;
    // number of rows and columns in one pixel for supersampling
    private static final int SUPERSAMPLING_NUM = 5;

    /**
     * a simple constuctor for camera
     *
     * @param p0-the  place of the camera
     * @param vTo     where the vector point outgoing from the camera
     * @param vUp-the vector vertical to vto
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        //if the vectors are not orthogonal, throw exception.
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("the vector must be orthogonal");

        this._p0 = p0;
        this._vTo = vTo.normalized();
        this._vUp = vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();
    }

    /**
     * @return the position of the camera
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * @return the position of vector camera
     */
    public Vector getVto() {
        return _vTo;
    }

    /**
     * @return the vector up of camera
     */
    public Vector getVup() {
        return _vUp;
    }

    /**
     * @return the vector right of camera
     */
    public Vector getVright() {
        return _vRight;
    }

    /**
     * the func should create ray witch point
     *
     * @param nX             number         of pixels in the x axis
     * @param nY             number         of pixels in the y axis
     * @param j              horizontal index of pixel (from left to right)
     * @param i              vertical        index of pixel (from up to down)
     * @param screenDistance the distance between the _p0 and pc where the image are located
     * @param screenWidth    width of the screen
     * @param screenHeight   height of the screen
     * @return ray where outgoing construct Ray Through Pixel
     */
    public Ray constructRayThroughPixel(double nX, double nY, int i, int j, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) throw new IllegalArgumentException("distance cannot be 0");
        //image center
        Point3D screenCenter = _p0.add(_vTo.scale(screenDistance));
        //ratio (pixel height&width)
        double ry = screenHeight / nY;
        double rx = screenWidth / nX;
        //pixel[i,j] center
        //multiplying of x value of pixel with the pixel width. and adding half of the width to get the distance till the center.
        double xPixel = (i - nX / 2.0) * rx + rx / 2.0;
        double yPixel = (j - nY / 2.0) * ry + ry / 2.0;
        Point3D pij = screenCenter;
        if (xPixel != 0) pij = pij.add(_vRight.scale(xPixel));
        if (yPixel != 0) pij = pij.add(_vUp.scale(-yPixel));
        //direction vector to pixel center
        Vector direction = pij.subtract(_p0);
        return new Ray(_p0, direction);
    }
    /**
     * super sampling method-- we make a group of rays in the pixel
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @param screenDistance
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public List<Ray> constructBeamThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth,
                                               double screenHeight) {
        List<Ray> beam = new LinkedList<>();

        if (isZero(screenDistance)) throw new IllegalArgumentException("error,the distance is 0");

        // get the image center (Center = P0 + distance*Vto)
        Point3D Center = _p0.add(_vTo.scale(screenDistance));

        // pixel_center
        Point3D P_center = getPixelCenter(Center, nX, nY, j, i, screenWidth, screenHeight);

        // pixel height and width
        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        // subpixel height and width
        double Sry = Ry / (SUPERSAMPLING_NUM - 1);
        double Srx = Rx / (SUPERSAMPLING_NUM - 1);

        // Move P_center to the pixel top left corner
        double X0 = ((- (SUPERSAMPLING_NUM - 1) / 2d) * Srx);
        double Y0 = ((- (SUPERSAMPLING_NUM - 1) / 2d) * Sry);

        P_center = P_center.add(_vRight.scale(X0));
        P_center = P_center.add(_vUp.scale(-Y0));

        // pIJS is moving on grid
        Point3D pIJS = P_center;

        for (i = 0; i < SUPERSAMPLING_NUM ; ++i) {
            for (j = 0; j < SUPERSAMPLING_NUM; ++j) {

                // Create an Adding Ray to the beam
                Vector vIJ = pIJS.subtract(_p0);
                beam.add(new Ray(_p0, vIJ.normalize()));

                // Next point on i
                pIJS = pIJS.add(_vRight.scale(Srx));
            }
            // Next Point on j
            pIJS = P_center.add(_vUp.scale(- Sry * (j + 1)));
        }
        return beam;
    }

    public Point3D getPixelCenter(Point3D center ,int nX, int nY, int j, int i, double screenWidth, double screenHeight) {
        // pixel height and width
        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double Yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double Xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D pIJ = new Point3D(center);  // pIJ is the point on the middle of the given pixel

        if (!isZero(Xj)) pIJ = pIJ.add(_vRight.scale(Xj));
        if (!isZero(Yi)) pIJ = pIJ.add(_vUp.scale(-Yi));
        return pIJ;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vUp +
                ", _vTo=" + _vTo +
                ", _vRight=" + _vRight +
                '}';
    }
}
