package unittests;

import static org.junit.Assert.*;

import java.util.List;

import geometries.*;
import org.junit.Test;
import primitives.*;


public class TriangleTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:with two same points and so it's not a triangle it's a vector
        try {
            new Triangle(new Point3D(0, 0, 0), new Point3D(0, 0, 0), new Point3D(1, 2, 3));
            fail("Constructed a vector not a triangle ");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 inside triangle
        Ray ray = new Ray(new Point3D(-4, 4, 3), new Vector(4, -2, -1));
        Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(0, 6, 0), new Point3D(0, 0, 8));
        List<Point3D> intersectionsList = triangle.findIntersections(ray);
        assertNotNull("must be not empty", intersectionsList);
        assertEquals("must be equal to 1", 1, intersectionsList.size());
        assertEquals("must be the same", new Point3D(0, 2, 2), intersectionsList.get(0));

        //TC02 outside against edge
        ray = new Ray(new Point3D(5, 6, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull("must be empty", intersectionsList);


        //TC02 outside against vertex
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull("must be empty", intersectionsList);


        // =============== Boundary Values Tests ==================

        //ray starts "before" the plane
        //TC10 on edge
        ray = new Ray(new Point3D(-2, 0, 5), new Vector(1, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNotNull("must be not empty", intersectionsList);
        assertEquals("must be equal to 1", 1, intersectionsList.size());
        assertEquals("must be the same", new Point3D(0, 0, 1), intersectionsList.get(0));


        // TC12 in vertex
        ray = new Ray(new Point3D(6, 0, 1), new Vector(1, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNotNull("must be not empty", intersectionsList);
        assertEquals("must be equal to 1", 1, intersectionsList.size());
        assertEquals("must be the same", new Point3D(7, 0, 0), intersectionsList.get(0));

        // TC13 on edge continuation
        ray = new Ray(new Point3D(10, 0, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull("must be empty", intersectionsList);
    }
}