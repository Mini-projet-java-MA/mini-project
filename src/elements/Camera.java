package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


/**
 *  camera class represents camera in 3D Cartesian coordinate
 *
 *
 *   @author aaron
 */
public class Camera {
    private Point3D _p0;
    private Vector _vto;
    private Vector _vup;
    private Vector _vright;

    /**
     *
     * @param p0-the place of the camera
     * @param vto- where the vector point outgoing from the camera
     * @param vtup-the vector vertical to vto
     */
    public Camera(Point3D p0, Vector vto, Vector vtup) {

        double check_vertical = vto.dotProduct(vtup);
        //if two vector vto are not vertical to vtup
        if (check_vertical != 0)
            throw new IllegalArgumentException("the vector vto are not parallel to vector vtup ");
        _p0=new Point3D(p0);
        _vto = new Vector(vto.normalize());
        _vup = new Vector(vtup.normalize());
        _vright = new Vector(_vup.crossProduct(_vto).normalize());


    }

    /**
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @param screenDistance- the distance between the _p0 and pc where the image are located
     * @param screenWidth-width  of the screen
     * @param screenHeight- height of the screen
     * @return ray where outgoing construct Ray Through Pixel
     */
    public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenHeight) || isZero(screenWidth) || isZero(screenDistance))
            throw new IllegalArgumentException("the screen Height or  screen Height or screen Distance can't be zero or negative ");
        //  Image center equal to Pc = P0 + d∙Vto
        Point3D pc = _p0.add(_vto.scale(screenDistance));
        //Ratio (pixel width & height),Ry = h/Ny,Rx = w/Nx
        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;
        //Pixel[i,j] center Pi,j = Pc + (xj∙vright – yi∙vup) ,yi = (i – Ny/2)∙Ry + Ry/2 , xj = (j – Nx/2)∙Rx + Rx/2
        double yi = ((i - nY / 2.0) * Ry + Ry / 2.0);
        double xj = ((j - nX / 2.0) * Rx + Rx / 2.0);
        Point3D pij=new Point3D(pc);
        if (!isZero(xj)) pij = pij.add(_vright.scale(xj));

        if (!isZero(yi)) pij.add(_vup.scale(-yi));
            Vector vij = pij.subtract(_p0);

        return new Ray(_p0, vij);

    }

    /**
     *
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
