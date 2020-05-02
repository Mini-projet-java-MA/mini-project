package unittests;

import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

//test

/**
 * Unit tests for primitives.Vector class
 *
 * @author aaron
 */

public class VectorTest {
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */

    @org.junit.Test
    public void crossProduct() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1;
        Vector v2;
        Vector v3;
        Vector result;
        Vector excepted;

        // TC01: cross product witch the same diretion
        try {
            v1 = new Vector(4, 8, 12);
            v2 = new Vector(2, 4, 6);
            Vector temp = new Vector(v1.crossProduct(v2));
            fail("cross product witch the same diretion");
        } catch (IllegalArgumentException e) {
        }
        // TC02: cross product when the vectors have the opposite direction
        try {
            v1 = new Vector(4, 8, 12);
            v2 = new Vector(-4, -8, -12);
            result = new Vector(v1.crossProduct(v2));
            fail("cross product when the vectors have the opposite direction");
        } catch (IllegalArgumentException e) {
        }
        // TC03:cross product when the vector orthogonal
        try {
            v1 = new Vector(1, 5, 6);
            v2 = new Vector(-10, 2, 0);
            excepted = new Vector(-12, -60, 52);
            result = new Vector(v1.crossProduct(v2));
            assertEquals(excepted, result);
        } catch (IllegalArgumentException e) {
        }
//TC04:test from the main
        //  TC04.1:test zero vector

        try {
            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-2, -4, -6);
            result = v1.crossProduct(v2);
            fail("test zero vector");
        } catch (IllegalArgumentException e) {
        }
        //  TC04.2 test cross product witch zero
        try {

            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-2, -4, -6);
            v3 = new Vector(0, 3, -2);
            Vector vr = v1.crossProduct(v3);
            assertEquals(0, alignZero(vr.length() - v1.length() * v3.length()));
        } catch (IllegalArgumentException e) {
        }
        //TC04.3: test from the main
        try {

            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-2, -4, -6);
            v3 = new Vector(0, 3, -2);

            Vector vr = v1.crossProduct(v3);
            if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
                assertEquals(0, 0);

        } catch (IllegalArgumentException e) {
        }
        //TC05: test of cross product witch sharp angle between
        try {
            v1 = new Vector(5, -2, 3);
            v2 = new Vector(-4, 5, 7);
            excepted = v1.crossProduct(v2);
            result = new Vector(-29, -47, 17);
            assertEquals(excepted, result);
        } catch (IllegalArgumentException e) {
        }
        //TC06: test of cross product witch blunt angle between
        try {
            v1 = new Vector(-2, 2, 2);
            v2 = new Vector(0, 2, 2);
            excepted = v1.crossProduct(v2);
            result = new Vector(0, 4, -4);
            assertEquals(excepted, result);
        } catch (IllegalArgumentException e) {
        }
    }


    @org.junit.Test
    public void lengthSquared() {

        // ============ Equivalence Partitions Tests ==============
//TC01:when the vector normal vector
        Vector v1;
        double expected;
        double result;
        try {
            v1 = new Vector(0, 3, 4);
            expected = 25;
            result = v1.lengthSquared();
            assertEquals(result, expected);
        } catch (IllegalArgumentException e) {
        }

//TC02:when the vector equal to zero
        try {
            v1 = new Vector(0, 0, 0);
            expected = 0;
            result = v1.lengthSquared();
            fail("id not possible vector zero");
        } catch (IllegalArgumentException e) {

        }
        //TC03:test from the main
        try {
            v1 = new Vector(1, 2, 3);
            assertEquals(v1.lengthSquared() - 14, 0);
        } catch (IllegalArgumentException e) {
        }
    }

    @org.junit.Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1;
        Vector v2;
        Vector v3;
        double expected;
        double result;
        //TC01:when the vector equal to zero
        try {
            v1 = new Vector(0, 0, 0);
            v1.length();
        } catch (IllegalArgumentException e) {
        }
        //TC02:te test fronm the main
        try {
            assertEquals(new Vector(0, 3, 4).length(), 5);

        } catch (IllegalArgumentException e) {
        }
        //TC03: normal test
        try {
            assertEquals(new Vector(3, 4, 0).length(), 5);
        } catch (IllegalArgumentException e) {
        }

    }

    @org.junit.Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: normal test
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 1, 1);
        try {
            double temp = 6;
            v1.dotProduct(v2);
            assertEquals(v1.dotProduct(v2), temp);

        } catch (IllegalArgumentException e) {
        }
        //TC02:test for orthogonal vectors
        try {
            v1 = new Vector(3, 2, -7);
            v2 = new Vector(1, 2, 1);
            double temp = 0;
            ;
            assertEquals(v1.dotProduct(v2), temp);
        } catch (IllegalArgumentException e) {
        }
        //TC03:test for vectors with opposite directions
        try {

            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-1, -2, -3);
            double temp = -14;
            assertEquals(v1.dotProduct(v2), temp);
        } catch (IllegalArgumentException e) {
        }

        //TC04:test for vectors with the same direction
        try {
            v1 = new Vector(1, 2, 4);
            v2 = new Vector(2, 4, 8);
            double temp = 42;
            assertEquals(v1.dotProduct(v2), temp);

        } catch (IllegalArgumentException e) {
        }
        // TC05: test from the maim

        v1 = new Vector(1, 2, 3);
        v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // TC05.1: test from the maim
        try {
            assertEquals(v1.dotProduct(v3), 0);
        } catch (IllegalArgumentException e) {
        }

// TC05.2: test from the maim

        try {
            assertEquals(v1.dotProduct(v2) + 28, 0);

        } catch (IllegalArgumentException e) {
        }

        //TC06:test vectors with a sharp angle(so dot product his negative )
        try {
            v1 = new Vector(5, -2, 3);
            v2 = new Vector(-4, 5, 7);
            double temp = -9;
            assertEquals(v1.dotProduct(v2), temp);
        } catch (IllegalArgumentException e) {

        }

        // TC07:test the vectors with a blunt angle (so dot product his positive )
        try {
            v1 = new Vector(-2, 2, 2);
            v2 = new Vector(0, 2, 2);
            double temp = 8;
            assertEquals(v1.dotProduct(v2), temp);
        } catch (IllegalArgumentException e) {
        }
    }

    @org.junit.Test
    public void normalize() {

        // ============ Equivalence Partitions Tests ==============
        //TC01:normal test
        try {
            Vector v1 = new Vector(4, 3, 0);
            Vector temp = new Vector(0.8, 0.6, 0);
            assertEquals(v1.normalize(), temp);
        } catch (IllegalArgumentException e) {
        }
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        //TC02:test from the main
        //TC02.1:test from the main

        Vector vCopyNormalize = vCopy.normalize();
        try {
            assertEquals(vCopy, vCopyNormalize);
        } catch (IllegalArgumentException e) {
        }
        //TC02.2:test from the main

        try {
            assertEquals(vCopyNormalize.length() - 1, 0);
        } catch (IllegalArgumentException e) {
        }

    }

    @org.junit.Test
    public void normalized() {

        // ============ Equivalence Partitions Tests ==============
//TC01: normal test
        try {
            Vector v1 = new Vector(4, 3, 0);
            Vector v2 = new Vector(v1.normalized());
            Vector temp = new Vector(0.8, 0.6, 0);
            assertEquals(v2, v1);

        } catch (IllegalArgumentException e) {
        }

        // TC02:test from the main
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        try {

            assertEquals(u, v);
        } catch (IllegalArgumentException e) {
        }
    }

    @org.junit.Test
    public void scale() {

        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(4, 5, 6);
        double num = 5.68852;
        Vector result;
        Vector excepted;
        // TC01:simple test
        try {


            result = new Vector((4 * num), (5 * num), (6 * num));
            excepted = v1.scale(num);
            assertEquals(excepted, result);
        } catch (IllegalArgumentException e) {

        }
        //TC02: mult be zero
        try {
            num = 0;
            result = new Vector((4 * num), (5 * num), (6 * num));
            excepted = v1.scale(num);
            assertEquals(excepted, result);
            fail();
        } catch (IllegalArgumentException error) {
        }
    }

    @org.junit.Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1;
        Vector v2;
        Vector expected;
        Vector result;
        //2. a test from the main
        //TC01: normal test
        try {
            v1 = new Vector(0, 3, 4);
            v2 = new Vector(0, 5, 4);
            expected = new Vector(0, 8, 8);
            result = v1.add(v2);
            assertEquals(expected, result);
        } catch (IllegalArgumentException e) {
        }
        //TC02:test from the main
        try {
            Point3D p1 = new Point3D(1, 2, 3);
            if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
                fail();
        } catch (IllegalArgumentException e) {
        }

    }

    @org.junit.Test
    public void subtract() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: normal test
        try {
            Vector v1 = new Vector(1, 2, 4);
            Vector v2 = new Vector(1, 5, 2);
            Vector expected = new Vector(0, -3, 2);
            Vector result = v1.subtract(v2);
            assertEquals(expected, result);
        } catch (IllegalArgumentException e) {
            System.out.println("the test was fail!");
        }
        //TC02:test from the main
        Point3D p1 = new Point3D(1, 2, 3);
        try {

            if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
                fail();

        } catch (IllegalArgumentException e) {
        }

    }
}