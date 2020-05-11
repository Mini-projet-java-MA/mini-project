package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import elements.*;
import org.junit.Test;

import java.util.List;

/**
 * we test the Intersection between package geometry and ray to the camera
 */
public class IntersectionTest {

    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    // TC01 a first test of intersection with sphere we then compare the result with the expected result
    @Test
    public void constructRayThroughPixelWithFirstSphere() {
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));
//Ray ray = cam1.constructRayThroughPixel(3,3,0,0,1,3,3);
// List<Point3D> results =  sph.findIntersections(ray);
        List<Point3D> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("not good", 2, count);

    }
    // TC02 a second test of intersection with sphere we then compare the result with the expected result
    @Test
    public void constructRayThroughPixelWithSecondSphere() {
        Sphere sph = new Sphere(2.5, new Point3D(0, 0, 2.5));

        List<Point3D> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("not good", 18, count);


    }
}
