package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


/**
 * camera class represents camera in 3D Cartesian coordinate
 *
 * @author aaron
 */


public class Camera {
    private Point3D _p0;
    private Vector _vto;
    private Vector _vup;
    private Vector _vright;

    /**
     * @param p0-the  place of the camera
     * @param vto-    where the vector point outgoing from the camera
     * @param vup-the vector vertical to vto
     */
    public Camera(Point3D p0, Vector vto, Vector vup) {


        //if the two vectors are not orthogonal throw exception
        if (vto.dotProduct(vup) != 0)
            throw new IllegalArgumentException("the vto not orthogonal to vup ");
        _p0 = new Point3D(p0);
        _vto = vto.normalized();
        _vup = vup.normalized();
        _vright = _vto.crossProduct(_vup).normalize();


    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getVto() {
        return _vto;
    }

    public Vector getVup() {
        return _vup;
    }

    public Vector getVright() {
        return _vright;
    }

    /**
     * the func should creat ray witch point
     *
     * @param nX-number         of pixels in the x axis
     * @param nY-number         of pixels in the y axis
     * @param j-                horizontal index of pixel (from left to right)
     * @param i-vertical        index of pixel (from up to down)
     * @param screenDistance-   the distance between the _p0 and pc where the image are located
     * @param screenWidth-width of the screen
     * @param screenHeight-     height of the screen
     * @return ray where outgoing construct Ray Through Pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int i, int j, double screenDistance, double screenWidth, double screenHeight) {
        //image center
        Point3D screenCenter = _p0.add(_vto.scale(screenDistance));
        //ratio (pixel height&width)
        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;
        //pixel[i,j] center
        //multiplying of x value of pixel with the pixel width. and adding half of the width to get the distance till the center.
        double Xpixsel = (i - nX / 2.0) * Rx + Rx / 2.0;
        double Ypixsel = (j - nY / 2.0) * Ry + Ry / 2.0;
        Point3D Pij = screenCenter;
        if (Xpixsel != 0) Pij = Pij.add(_vright.scale(Xpixsel));
        if (Ypixsel != 0) Pij = Pij.add(_vup.scale(-Ypixsel));
        //direction vector to pixel center
        Vector direction = Pij.subtract(_p0);
        return new Ray(_p0, direction);
    }


    /**
     * @return in string data of camera
     */
    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vup +
                ", _vTo=" + _vto +
                ", _vRight=" + _vright +
                '}';
    }
}
