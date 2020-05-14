package unittests;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * we test the Intersection between package geometry and ray to the camera
 * @author aaron
 */
public class IntersectionTest {
    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    /**
     * the fucn fund intersection between the sphere and camera
     * @param sph
     * @param cam
     * @return list of all point intesection between sphere and camera on screen 3*3
     */
    private int intersectionsSphere(Sphere sph, Camera cam) {
        List<Point3D> results = null;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                ;
                if (results != null)
                    count += results.size();
            }
        }
        return count;
    }

    /**
     * the func should find the all intersection between plane and came on screen 3*3
     * @param plane
     * @param cam
     * @return
     */
    private int intersectionsSphere(Plane plane, Camera cam) {
        List<Point3D> results = null;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = plane.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                ;
                if (results != null)
                    count += results.size();
            }
        }
        return count;
    }

    /**
     * we test the intersection between camera and sphere
     */
    @Test
    public void constructRayThroughPixelWithSphere() {
        // TC01 test of intersection with sphere when all the sphere are out from the screen 3*3
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));
        assertEquals("Too bad ", 2, intersectionsSphere(sph, cam1));
        // TC02 test of intersection with sphere when the all screen are in sphere
        sph = new Sphere(2.5, new Point3D(0, 0, 2.5));
        assertEquals("not good", 18, intersectionsSphere(sph, cam2));
        //TC03:part of screen are in the sphere
        sph = new Sphere(2, new Point3D(0, 0, 2));
        assertEquals("not good", 10, intersectionsSphere(sph, cam2));
        //TC04:the sphere around the screen
        sph = new Sphere(4, new Point3D(0, 0, 2));
        assertEquals("not good", 9,  intersectionsSphere(sph, cam2));
        //TC05: the sphere are behind the screen
        sph = new Sphere(0.5, new Point3D(0, 0, -1));
        assertNull("not good", intersectionsSphere(sph, cam2));
    }
    @Test
    public void constructRayThroughPixelWithPlane() {

    }
}
