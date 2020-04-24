package unittests;

import geometries.Polygon;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Tube;

import static org.junit.Assert.*;

public class TubeTest {


    @Test
    public void testConstructor() {
        try {//build a tube with a radius equals to zero which is irrelevant because it will build a point3d not a tube
            double radius=0;
            new Tube(new Ray(new Point3D(1,2,3) ,new Vector(1,1,1)),radius);
            fail("Constructed a tube with a null radius ");
        } catch (IllegalArgumentException e) {}
    }

    @Test//test the get ray by comparing the result of the func and the expected result
    public void getRay()  {
        Coordinate x= new Coordinate(2);
        Coordinate y=new Coordinate(3);
        Coordinate z=new Coordinate(4);
        Point3D p =new Point3D(x,y,z);
        Vector d = new Vector(1,1,1);
        double radius=3;
        Ray myRay = new Ray(p,d);
        Tube myTube= new Tube(myRay,radius);
        Ray result =myTube.getRay();
        Ray expResult = new Ray(p,d);
        assertEquals(result,expResult);
    }//if the test worked it means that the get ray works because the result and the expected result are equals
}