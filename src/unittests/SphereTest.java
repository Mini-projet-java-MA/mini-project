package unittests;

import org.junit.Test;

import static org.junit.Assert.*;

import geometries.Sphere;
import primitives.*;

public class SphereTest {
    // ============ Equivalence Partitions Tests ==============

    @Test
    public void get_center() {
        //here we test the get center of sphere by comparing the result of the func and the expected result
        Point3D centerPoint = new Point3D(2, 3, 4);
        double radius = 2;
        Sphere mySphere = new Sphere(radius, centerPoint);
        Point3D result = mySphere.getCenter();
        Point3D expResult = new Point3D(centerPoint);
        assertEquals(result, expResult);
    }//if the test worked it means the result and expected results are equal and then get center works

    @Test
    public void getNormal() {
        //we test the get normal of sphere the same way than the other func(result and expected result)
        Sphere sp = new Sphere(1.0, new Point3D(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sp.getNormal(new Point3D(0, 0, 2)));
    }
}