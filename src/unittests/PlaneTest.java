package unittests;


import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

public class PlaneTest {
    @Test
    public void testConstructor() {
        try {//wrong plane with two identical points
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0));
            fail("Constructed a plane with two same vertices");
        } catch (IllegalArgumentException e) {}

        try {//wrong plane with three identical points
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1),
                    new Point3D(0, 0, 1));
            fail("Constructed a plane with three same vertices");
        } catch (IllegalArgumentException e) {}

    }


    @Test
    public void getNormal() {//we test the plane normalization with expected result and computed result with a delta of 0.00001 for more accuracy
        Point3D receivedPoint = new Point3D(2, 2, 2);
        Point3D p1 = new Point3D(5, 4, 0);
        Point3D p2 = new Point3D(5, 6, 0);
        Point3D p3 = new Point3D(7, 8, 0);
        Plane plane = new Plane(p1, p2, p3);
        assertEquals( 1, plane.getNormal(receivedPoint).length(), 0.00001);
        assertEquals( 0, p1.subtract(p2).dotProduct(plane.getNormal(receivedPoint)), 0.00001);
        assertEquals( 0, p1.subtract(p3).dotProduct(plane.getNormal(receivedPoint)), 0.00001);
    }
}