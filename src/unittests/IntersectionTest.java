package unittests;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * we test the Intersection between package geometry and ray to the camera in 3D Cartesian coordinate
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
    private int intersectionsTriangle(Triangle sph, Camera cam) {
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
     * @param plane the plane
     * @param cam the camera intersecting the plane
     * @return
     */
    private int intersectionsPlane(Plane plane, Camera cam) {
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
        //TC05: the sphere is behind the screen
        sph = new Sphere(0.5, new Point3D(0, 0, -1));
        assertEquals("must be equal to zero",0, intersectionsSphere(sph, cam2));
    }

    /**
     * we test all the intersection between camera and plane on screen 3*3
     */
    @Test
    public void constructRayThroughPixelWithPlane() {
        // i think that the pbm is because somme test in class plane test that don't work
        Plane plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, 1));
        //TC01:
        assertEquals(9 , intersectionsPlane(plane, cam1));

    }

    /**
     * we test all the intersection between camera and Triangle on screen 3*3
     */
    @Test
    public void constructRayThroughPixelWithTriangle() {
        Triangle triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        //TC01: when triangle are smaller than the screen
        assertEquals("Not bad ", 1, intersectionsTriangle(triangle, cam1));
        //TC02: when the triangle is bigger than the screen
        triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        assertEquals("Not bad ", 2, intersectionsTriangle(triangle, cam1));

    }
}
