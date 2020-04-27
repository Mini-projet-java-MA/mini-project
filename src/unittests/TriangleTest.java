package unittests;

import geometries.*;
import primitives.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * most of the test for triangle can be taken from his parent class polygon
 */
public class TriangleTest {
    @Test
    public void testConstructor() {
        //with two same points and so it's not a triangle it's a vector
        try {
            new Triangle(new Point3D(0, 0, 0), new Point3D(0, 0, 0), new Point3D(1, 2, 3));
            fail("Constructed a vector not a triangle ");
        } catch (IllegalArgumentException e) {
        }
    }
}