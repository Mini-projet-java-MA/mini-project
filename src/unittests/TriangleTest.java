 package unittests;

import geometries.*;
import org.junit.Test;
import primitives.*;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;


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
        assertArrayEquals(intersectionsList, );

        //TC02 outside against edge
        ray = new Ray(new Point3D(5, 6, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);


        //TC02 outside against vertex
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);



// =============== Boundary Values Tests ==================
        //TC10 on edge- start at the plane
        ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);



    }
}