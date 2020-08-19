package unittests;
import geometries.*;
import org.junit.Test;

import elements.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class miniProjct1 {/**
 * Produce a picture of a sphere lighted by a spot light
 */

@Test
    public void finalTest1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(
                new Point3D(0, 250, -80),
                new Vector(0, -250, 80),
                new Vector(0,-250,80).crossProduct(new Vector(-1,0,0))));
        scene.setDistance(200);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.1));


        scene.addGeometries(new Plane(  new Point3D(0,0,50),
                        new Vector(0,0,1),
                Color.BLACK,
                new Material(0.4,0.6,150,0.2,0.4)));



        scene.addGeometries(new Plane(new Point3D(0,0,50),
                        new Vector(0,0,1), Color.BLACK, new Material(0.4,0.6,150,0.2,0.4)));
//************************************ Sphere***************************************//

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLACK),
                        new Material(0, 0.5, 100, 0.2, 0.8), 20,
                        new Point3D(45, 15, 30)));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLACK),
                        new Material(0, 0.5, 100, 0.2, 0.8), 30,
                        new Point3D(60, 70, 20)));

//*******************************************cylinder**************************************//
        scene.addGeometries(
                new Cylinder( new Ray(
                        new Point3D(-50, -20, 12), new Vector(0,0,1)),25, 100, new Color(0,255,255), new Material(0.3,0.3,150,0.3,0.3) ));


//**********************************  Polygons ************************************//
        scene.addGeometries(new geometries.Polygon
                        (new Color(java.awt.Color.BLUE),
                                new Material(0.5, 0.5, 150, 0.8, 0.2),
                                new Point3D(0, -40, 0),
                                new Point3D(20, -20, 50),
                                new Point3D(20, -60, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.BLUE),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(20, -20, 50),
                                new Point3D(3.52, -36.88, 50),
                                new Point3D(0, -40, 0)),
                new geometries.Polygon
                        (new Color(java.awt.Color.BLUE),
                                new Material(0, 1, 150, 0.7, 0.3),
                                new Point3D(3.52, -36.88, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(-3.65, -36.76, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.BLUE),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(-3.65, -36.67, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(-20.1, -20, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.BLUE),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(-20.1, -20, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(-20, -60, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.YELLOW),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(-20, -60, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(-3.78, -43.85, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.pink),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(-3.78, -43.85, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(3.39, -43.51, 50)),
                new geometries.Polygon
                        (new Color(java.awt.Color.GREEN),
                                new Material(0, 0.5, 150, 0.7, 0.3),
                                new Point3D(3.39, -43.51, 50),
                                new Point3D(0, -40, 0),
                                new Point3D(20, -60, 50)));
        scene.addLights(new PointLight(
                new Color(255, 0, 0),
                new Point3D(-100, -100, 34),
                1, 0.00001, 0.000001));

        scene.addLights(new SpotLight(new Color(0, 255, 0), new Point3D(41.68, -67.52, 28.84), new Vector(34.29, 9.93, 0), 2, 0.001, 0.000001));

        scene.addLights(new SpotLight(new Color(0, 0, 255), new Point3D(-100, 100, -500),  new Vector(-1, 1, 2), 2, 0.001, 0.000001 ));


        ImageWriter imageWriter = new ImageWriter("mimi 1", 150, 150, 500, 500 );
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


    }

}
