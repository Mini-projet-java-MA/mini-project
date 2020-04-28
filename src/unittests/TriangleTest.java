package unittests;

import org.junit.Test;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;


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
        Ray ray = new Ray(new Point3D(2, 5, 0), new Vector(0, 0, -1));
        Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(7, 0, 0), new Point3D(0, 8, 0));
        List<Point3D> intersectionsList = triangle.findIntersections(ray);
        assertNotNull("Must be not empty", intersectionsList);
        assertEquals("Must be one point", 1, intersectionsList.size());
        assertEquals("Bad point", new Point3D(0,0,0), intersectionsList.get(0));

        //TC02 outside against edge
        ray = new Ray(new Point3D(5, 6, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);


        //TC02 outside against vertex
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull("Must be empty", intersectionsList);


// =============== Boundary Values Tests ==================
        //TC10 on edge- start at the plane
        ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);


    }
}