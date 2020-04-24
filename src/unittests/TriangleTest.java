package unittests;

import geometries.*;
import primitives.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void testConstructor() {
        try {//wiht two same points and so it's not a triangle it's a vector
            new Triangle(new Point3D(0, 0, 0), new Point3D(0, 0, 0), new Point3D(1, 2, 3));
            fail("Constructed a vector not a triangle ");
        } catch (IllegalArgumentException e) {
        }
    }
}