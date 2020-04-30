package unittests;


import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:test when the  plane with two identical points
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0));
            fail("Constructed a plane with two same vertices");
        } catch (IllegalArgumentException e) {
        }
        //TC02: test wrong plane with three identical points
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1),
                    new Point3D(0, 0, 1));
            fail("Constructed a plane with three same vertices");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:we test the plane normalization with expected result and computed result with a delta of 0.00001 for more accuracy this test allows us to verify if the function getNormal from the class plane is working like it is supposed to
        Point3D receivedPoint = new Point3D(2, 2, 2);
        Point3D p1 = new Point3D(5, 4, 0);
        Point3D p2 = new Point3D(5, 6, 0);
        Point3D p3 = new Point3D(7, 8, 0);
        Plane plane = new Plane(p1, p2, p3);
        assertEquals(1, plane.getNormal(receivedPoint).length(), 0.00001);
        assertEquals(0, p1.subtract(p2).dotProduct(plane.getNormal(receivedPoint)), 0.00001);
        assertEquals(0, p1.subtract(p3).dotProduct(plane.getNormal(receivedPoint)), 0.00001);
    }
    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane;
        Ray ray;
        List<Point3D> intersectionsList = null;
        //TC01: the ray not included parallel to the plane
        try {
            ray = new Ray(new Point3D(2, 6, 1), new Vector(3, 3, 0));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
           assertEquals("must be equal to 1", 1, intersectionsList.size());
            assertEquals("must be the same", new Point3D(3, 3, 0), intersectionsList.get(0));
        } catch (IllegalArgumentException e) {
        }
        //TC02: the ray included parallel to the plane
        try {
            ray = new Ray(new Point3D(2, 6, 0), new Vector(3, 3, 0));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            intersectionsList = plane.findIntersections(ray);
            //assertNotNull("must be not empty", intersectionsList);
        } catch (IllegalArgumentException e) {
        }

        //TC03:the ray orthogonal to plane, p0 before plane
        try {
            ray = new Ray(new Point3D(2, 6, 1), new Vector(0, 0, -1));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            assertEquals("must be equal to 1", 1, intersectionsList.size());
            assertEquals("must be the same", new Point3D(1, 3, 0), intersectionsList.get(0));
        } catch (IllegalArgumentException e) {
        }
        //TC04:the ray orthogonal to plane, p0 in plane
        try {

            ray = new Ray(new Point3D(2, 6, 0), new Vector(0, 0, 1));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            assertEquals("must be equal to 1", 1, intersectionsList.size());
            assertEquals("must be the same", new Point3D(2, 6, 0), intersectionsList.get(0));

        } catch (IllegalArgumentException e) {
        }
        // TC05:the ray orthogonal to plane, p0 after plane
        try {
            ray = new Ray(new Point3D(2, 6, 1), new Vector(0, 0, 1));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            assertNotNull("must be not empty", intersectionsList);

        } catch (IllegalArgumentException e) {
        }

        //TC06:Ray is neither orthogonal nor parallel to and begins at the plane (𝑃0 is in the plane, but not the ray)
        try {
            ray = new Ray(new Point3D(3, 3, 3), new Vector(-1, 0, -1));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            assertEquals("must be equal to 1", 1, intersectionsList.size());
            assertEquals("must be the same", new Point3D(0, 0.75, 0), intersectionsList.get(0));

        } catch (IllegalArgumentException e) {
        }
        //TC07:the Ray neither orthogonal nor parallel to the plane without intersection
        try {
            ray = new Ray(new Point3D(3, 3, 3), new Vector(-1, 0, -1));
            plane = new Plane(new Point3D(5, 4, 0), new Point3D(7, 8, 0), new Point3D(5, 6, 0));
            assertEquals("must be equal to 1", 1, intersectionsList.size());
            assertEquals("must be the same", new Point3D(0, -1.5, 0), intersectionsList.get(0));
        } catch (IllegalArgumentException e) {
        }
    }
}