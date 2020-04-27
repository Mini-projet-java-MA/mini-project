package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;
//test

/**
 * Unit tests for primitives.Vector class
 * @author aaron
 */

import primitives.Point3D;
import primitives.Vector;

import java.awt.event.ItemEvent;

public class VectorTest {
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */

    @org.junit.Test
    public void crossProduct() {


            /*
            we need to do test for this next this case//GOOD
            1. when the vectors have the same diretion//GOOD
            2. when the vectors have the oposite diretion//GOOD
            4. when the vector have the orthogonal diretion//good
            5. the test from the main//GOOD
            6. just regular test//GOOD
            7. when the vectors with a sharp angle between//GOOD
            8. when the vectors with a blunt angle between//GOOD
             */
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        Vector v1;
        Vector v2;
        Vector v3;
        // TC01: cross product witch the same direction


        try {
            v1 = new Vector(4, 8, 12);
            v2 = new Vector(2, 4, 6);
            Vector temp = new Vector(v1.crossProduct(v2));
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail like supose  !");
        }
        //the second test -opposite diretion
        try {
            System.out.println("\t" + "witch the vectors with opposite directions:");
            v1 = new Vector(4, 8, 12);
            v2 = new Vector(-4, -8, -12);
            Vector temp = new Vector(v1.crossProduct(v2));
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail like suppose  !");
        }

        //the third test- the ortogonal test

        try {
            System.out.println("\t" + "witch the vectors with otogonal  directions:");
            v1 = new Vector(1, 5, 6);
            v2 = new Vector(-10, 2, -0);
            Vector temp = new Vector(-12, -60, 52);
            Vector succe = new Vector(v1.crossProduct(v2));

            assertEquals(succe, temp, "\t+he third  succes");

        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail !");
        }


        //  the fourth test -the main test
        try {
            // test zero vector
            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-2, -4, -6);
            v3 = new Vector(0, 3, -2);
            v1.crossProduct(v2);
            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(-2, -4, -6);
        v3 = new Vector(0, 3, -2);
        Vector vr = new Vector(v1.crossProduct(v3));
        if (!isZero(vr.length() - v1.length() * v3.length()))
            out.println("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            out.println("ERROR: crossProduct() result is not orthogonal to its operands");
        // the fifth- a normal test
        try {
            System.out.println("\t" + "witch the vectors with normal tset :");
            v1 = new Vector(0, 3, 4);
            v2 = new Vector(1, 5, 2);
            Vector temp = new Vector(-14, 4, -3);
            Vector sucs = v1.crossProduct(v2);
            assertEquals(sucs, temp, "the normal test success");
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail !");
        }
        try {
            v1 = new Vector(1, 2, -5);
            v2 = new Vector(0, 2, 1);
            v3 = new Vector(12, -1, 2);
            assertEquals(v3, v1.crossProduct(v2), "the normal test was accuses");

        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail!");

        }
        // when the vectors with a sharp angle between
        try {
            v1 = new Vector(5, -2, 3);
            v2 = new Vector(-4, 5, 7);
            v3 = new Vector(-29, -47, 17);
        } catch (IllegalArgumentException e) {
            System.out.print("the test was fail!");

        }

        // when the vectors with a blunt angle between
        try {
            v1 = new Vector(-2, 2, 2);
            v2 = new Vector(0, 2, 2);
            v3 = new Vector(0, 4, -4);
            assertEquals(v1.crossProduct(v2), v3, "the of blunt angle was success");
        } catch (IllegalArgumentException e) {
            System.out.print("the test was for blunt angle fail!");
        }
    }

    @org.junit.Test
    public void lengthSquared() {
            /*
            test for lengthSquared
            1.when the vector normal vector
            2. when the vector equal to zero
             */
        //=============the first test
        Vector v1 = new Vector(0, 3, 4);
        double expected = 25;
        double result = v1.lengthSquared();
        assertEquals(result, expected);
        ///======================the second test
        try {
            v1 = new Vector(0, 0, 0);
            expected = 0;
            result = v1.lengthSquared();
            assertEquals(result, expected);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail like suppose");

        }
        // the test from the main

    }

    @org.junit.Test
    public void length() {
                    /*
            test for length
            1.when the vector normal vector
            2. when the vector equal to zero
            3. test from the main
             */
        //=============the first test
        Vector v1 = new Vector(0, 3, 4);
        double expected = 5;
        double result = v1.length();
        assertEquals(result, expected);
        ///======================the second test
        try {
            v1 = new Vector(0, 0, 0);
            expected = 0;
            result = v1.length();
            assertEquals(result, expected);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail like suppose");

        }
        //test from the main
        v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");

        // test Dot-Product
        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

    }

    @org.junit.Test
    public void scale() {
            /* we do a tests for opertion scale witch the next case
            1. when we mule by wero
            2. a simple test
             */
        //
        // ============ Equivalence Partitions Tests ==============
        //this test should try the boundary of vector scale be double
        //a simple test

        Vector v1 = new Vector(4, 5, 6);
        double num = 5.68852;
        Vector parm1;
        Vector parm2;
        try {


            parm1 = new Vector((4 * num), (5 * num), (6 * num));
            parm2 = v1.scale(num);
            //return true if he is the good answer
            assertEquals(parm1, parm2);
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test was fail!");

        }
        //when we mult by zero
        try {


            num = 0;
            parm1 = new Vector((4 * num), (5 * num), (6 * num));
            parm2 = v1.scale(num);
            assertEquals(parm1, parm2);
            fail();
        } catch (IllegalArgumentException error) {
            System.out.println("\t" + "teh test was sucsse so he fail!");
        }
    }

    @org.junit.Test
    public void dotProduct() {
        //we need to do tests for this next test

        //1. a normal test

        //2.test for orthogonal vectors

        //3.test for vectors with opposite directions

        //4. test for vectors with the same direction

        // 5. the vectors with a blunt angle between

        //6. the test for
        //for more explain see wiki https://en.wikipedia.org/wiki/Dot_product or see this video  https://www.youtube.com/watch?v=lVhCbjmpkN8

        //a normal test
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 1, 1);
        try {
            double temp = 6;
            v1.dotProduct(v2);
            assertEquals(v1.dotProduct(v2), temp, "this normal test was succsec");

        } catch (IllegalArgumentException e) {
            System.out.println("this normal test wa fail!");
        }

        //assertTrue(Util.usubtract(expected, result) == 0);


        try {


            //test for orthogonal vectors
            v1 = new Vector(3, 2, -7);
            v2 = new Vector(1, 2, 1);
            double temp = 0;
            ;
            assertEquals(v1.dotProduct(v2), temp, "the test was succsec ");
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the orthogonal test was  fail! ");
        }
        //test for vectors with opposite directions
        try {

            v1 = new Vector(1, 2, 3);
            v2 = new Vector(-1, -2, -3);
            double temp = -14;
            assertEquals(v1.dotProduct(v2), temp, "the test was sוcces");
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the test opposite directions was fail ");
        }

        //test for vectors with the same direction
        try {
            v1 = new Vector(1, 2, 4);
            v2 = new Vector(2, 4, 8);
            double temp = 42;
            assertEquals(v1.dotProduct(v2), temp, "the test of the same diretion was succsec ");

        } catch (IllegalArgumentException e) {
            System.out.println("the test of the same direction was fail!");
        }
        // the test from the maim

        v1 = new Vector(1, 2, 3);
        v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

        // test the vectors with a sharp angle(so dot product his negative )
        try {
            v1 = new Vector(5, -2, 3);
            v2 = new Vector(-4, 5, 7);
            double temp = -9;
        } catch (IllegalArgumentException e) {
            System.out.print("the test was fail!");

        }

        // test the vectors with a blunt angle (so dot product his positive )
        try {
            v1 = new Vector(-2, 2, 2);
            v2 = new Vector(0, 2, 2);
            double temp = 8;
            assertEquals(v1.dotProduct(v2), temp, "the of dot product witch blunt angle was success");
        } catch (IllegalArgumentException e) {
            System.out.print("the test was for blunt angle fail!");
        }
    }

    @org.junit.Test
    public void normalize() {
            /*
            we test the opeation normalize should chang the vector
            // test from the main
             */
        try {
            Vector v1 = new Vector(4, 3, 0);
            Vector temp = new Vector(0.8, 0.6, 0);
            assertEquals(v1.normalize(), temp, "the test was success ");
        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the was fail");
            Vector v = new Vector(1, 2, 3);
            Vector vCopy = new Vector(v);
            Vector vCopyNormalize = vCopy.normalize();
            if (vCopy != vCopyNormalize)
                out.println("ERROR: normalize() function creates a new vector");
            if (!isZero(vCopyNormalize.length() - 1))
                out.println("ERROR: normalize() result is not a unit vector");
        }


    }

    @org.junit.Test
    public void normalized() {
                    /*
            we test the opeation normalized should to get a new vector
            //the test fron the main
             */
        try {
            Vector v1 = new Vector(4, 3, 0);
            Vector v2 = new Vector(v1.normalized());
            Vector temp = new Vector(0.8, 0.6, 0);
            assertEquals(v2, v1, "the test was success ");

        } catch (IllegalArgumentException e) {
            System.out.println("\t" + "the was fail");
        }

        // test from the main
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");

    }

    @org.junit.Test
    public void add() {
        // 1.a normal test
        //2. a test from the main
        try {
            Vector v1 = new Vector(0, 3, 4);
            Vector v2 = new Vector(0, 5, 4);
            Vector expected = new Vector(0, 8, 8);
            Vector result = v1.add(v2);
            assertEquals(expected, result);
        } catch (IllegalArgumentException error) {
            System.out.println("\t" + "teh test was fail!");
        }
        Point3D p1 = new Point3D(1, 2, 3);
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            out.println("ERROR: Point + Vector does not work correctly");


        out.println("If there were no any other outputs - all tests succeeded!");
    }

    @org.junit.Test
    public void subtract() {
        // we do a normal test to check if the operaion subdo what he suppose to do
        //a
        try {
            Vector v1 = new Vector(1, 2, 4);
            Vector v2 = new Vector(1, 5, 2);
            Vector expected = new Vector(0, -3, 2);
            Vector result = v1.subtract(v2);
            assertEquals(expected, result);
        } catch (IllegalArgumentException e) {
            System.out.println("the test was fail!");
        }
        Point3D p1 = new Point3D(1, 2, 3);
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            out.println("ERROR: Point + Vector does not work correctly");
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
            out.println("ERROR: Point - Point does not work correctly");

        out.println("If there were no any other outputs - all tests succeeded!");


    }
}