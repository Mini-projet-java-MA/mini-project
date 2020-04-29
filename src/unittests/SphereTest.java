package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class SphereTest {
    /**
     * Test method for {@link geometries.Sphere}.
     *
     * @author aaron
     */

    @Test
    public void get_center() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:we test the get center of sphere by comparing the result of the func and the expected result:
        try {
            Coordinate x = new Coordinate(2);
            Coordinate y = new Coordinate(3);
            Coordinate z = new Coordinate(4);
            Point3D centerPoint = new Point3D(x, y, z);
            double radius = 2;
            Sphere mySphere = new Sphere(radius, centerPoint);
            Point3D result = mySphere.getCenter();
            Point3D expResult = new Point3D(centerPoint);

            assertEquals(result, expResult);
        } catch (IllegalArgumentException e) {
        }
    }//if the test worked it means the result and expected results are equal and then get center works

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        //TC01:we test the get normal of sphere the same way than the other func(result and expected result)
        try {
            Sphere sp = new Sphere(1.0, new Point3D(0, 0, 1));
            assertEquals(new Vector(0, 0, 1), sp.getNormal(new Point3D(0, 0, 2)));
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 no intersections ray is outside of the sphere
        Ray ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        Sphere sphere = new Sphere(1, new Point3D(0,2,-4));
        List<Point3D> intersectionsList= sphere.findIntersections(ray);
        assertNull("must be empty",intersectionsList);

        //TC02 ray start before and cross the sphere twice: 2 intersections
        //(si ca ne marche pas penser a changer l'ordre des points)
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,0.5,-4));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must not be empty",intersectionsList);
        assertEquals("must be equal to 2",2,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,-4.84),intersectionsList.get(0));
        assertEquals("must be equal",new Point3D(0,0,-3.11),intersectionsList.get(0));

        //TC03 ray start inside sphere:1 intersection
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,-1,-1));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,-5.9),intersectionsList.get(0));

        //TC04 ray starts outside sphere after it, due to direction- no intersection
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(2, new Point3D(0,1,4));
        intersectionsList=sphere.findIntersections(ray);
        assertNull("must be empty",intersectionsList);

        // =============== Boundary Values Tests ==================

        //TC10 ray start at the center of the sphere
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,0));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,-5),intersectionsList.get(0));

        //TC11 ray starts on sphere surface, to the outside: 1 intersection
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,5));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,0),intersectionsList.get(0));

        //TC12 ray starts on sphere surface, to the inside: 2 intersections
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,-5));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",2,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,0),intersectionsList.get(0));
        assertEquals("must be equal",new Point3D(0,0,0),intersectionsList.get(1));

        //TC13 ray starts outside of the sphere to the outside but on a line aligned with the center: no intersections
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,6));
        intersectionsList=sphere.findIntersections(ray);
        assertNull("must be empty",intersectionsList);

        //TC14 ray starts outside of the sphere to the inside cross twice the sphere and pass by center: 2 intersections
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,-6));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",2,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,-1),intersectionsList.get(0));
        assertEquals("must be equal",new Point3D(0,0,-11),intersectionsList.get(1));

        //TC15 ray starts on sphere surface, to the outside on the same line that the center: 1 intersection
        ray= new Ray(new Point3D(0,0,1), new Vector(0,0,1));
        sphere = new Sphere(5, new Point3D(0,0,0));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,5),intersectionsList.get(0));

        //TC16 ray is on the tangent line- ray starts before intersection: 1 intersection
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,-1));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,-1),intersectionsList.get(0));

        //TC17 ray is on the tangent line- ray starts on intersection: 1 intersection
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,0));
        intersectionsList=sphere.findIntersections(ray);
        assertNotNull("must be not empty",intersectionsList);
        assertEquals("must be equal to one",1,intersectionsList.size());
        assertEquals("must be equal",new Point3D(0,0,0),intersectionsList.get(0));

        //TC18 ray is on the tangent line- ray starts after intersection: no intersections
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,1));
        intersectionsList=sphere.findIntersections(ray);
        assertNull("must be empty",intersectionsList);

        //TC18 ray is on a line that vertical to radius- ray starts on the radius line
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,2,0));
        intersectionsList=sphere.findIntersections(ray);
        assertNull("must be empty",intersectionsList);


















        
    }
}