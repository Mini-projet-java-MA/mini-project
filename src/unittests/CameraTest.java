package unittests;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;

/**
 * this is a class test for the java class camera
 */
public class CameraTest {

    /**
     * this is a test for the constructor of camera
     */
    @Test
    public void constructorTest() {
        Point3D location = new Point3D(3, 3, 3);
        Vector up = new Vector(0, 0, 3);
        Vector to = new Vector(4, 0, 0);
        Camera result = new Camera(location, to, up);
        assertEquals(new Vector(1, 0, 0), result.getVto());
        assertEquals(new Vector(0, 0, 1), result.getVup());
        assertEquals(new Vector(0, -1, 0), result.getVright());
    }

    /**
     * this is the test for the function construct ray trough pixel
     * we are testing on different dispositions to see if the ray is good
     */
    @Test
    public void constructRayThroughPixel() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: 3X3 on the Corner (0,0)
        assertEquals("Bad ray",
                new Ray(Point3D.ZERO, new Vector(-2, -2, 10)), camera.constructRayThroughPixel(3, 3, 0, 0,
                        10, 6, 6));


        // =============== Boundary Values Tests ==================

        // TC11: 3X3 in the Center (1,1)
        assertEquals("Bad ray",
                new Ray(Point3D.ZERO, new Vector(0, 0, 10)), camera.constructRayThroughPixel(3, 3, 1, 1,
                        10, 6, 6));

        // TC12: 3X3in the Center of the Upper Side (0,1)
        assertEquals("Bad ray",
                new Ray(Point3D.ZERO, new Vector(0, -2, 10)), camera.constructRayThroughPixel(3, 3, 1, 0,
                        10, 6, 6));

        // TC13: 3X3 in the Center of the Left Side (1,0)
        assertEquals("Bad ray",
                new Ray(Point3D.ZERO, new Vector(-2, 0, 10)), camera.constructRayThroughPixel(3, 3, 0, 1,
                        10, 6, 6));

    }
}