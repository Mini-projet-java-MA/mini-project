package unittests;
import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class OurImageTest {
    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void OurImageTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //z
                new Sphere(new Color(java.awt.Color.blue), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)),
                new Sphere(new Color(java.awt.Color.black), new Material(0.2, 0.2, 60, 0.5, 0.2), // )
                        28, new Point3D(-50, 30, 50)),
                new Sphere(new Color(java.awt.Color.magenta), new Material(0.2, 0.2, 20, 0.1, 0.2), // )
                        10, new Point3D(-50, 30, 20)),
                new Triangle(new Color(0,128,0), new Material(0.5, 0.5, 60), //
                        new Point3D(-45, 35, 80), new Point3D(-10, 35, 105), new Point3D(35, -75, 55)), //
                new Sphere(new Color(java.awt.Color.red), new Material(0.2, 0.2, 90, 0, 0.6), // )
                        33, new Point3D(-50, -50, 50)));

        scene.addLights(

                new SpotLight(new Color(800, 400, 400), //
                        new Point3D(50, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7),
                new PointLight(new Color(127, 255, 212), new Point3D(-400, 120, 50),1,1,1),
                new SpotLight(new Color(100, 250, 100),
                        new Point3D(-20, 20, 100), new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("OurImage", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void OurImageTestAdvace() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //z
                new Sphere(new Color(java.awt.Color.blue), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)),
                new Sphere(new Color(java.awt.Color.black), new Material(0.2, 0.2, 60, 0.5, 0.2), // )
                        28, new Point3D(-50, 30, 50)),
                new Sphere(new Color(java.awt.Color.magenta), new Material(0.2, 0.2, 20, 0.1, 0.2), // )
                        10, new Point3D(-50, 30, 20)),
                new Triangle(new Color(0,128,0), new Material(0.5, 0.5, 60), //
                        new Point3D(-45, 35, 80), new Point3D(-10, 35, 105), new Point3D(35, -75, 55)), //
                new Sphere(new Color(java.awt.Color.red), new Material(0.2, 0.2, 90, 0, 0.6), // )
                        33, new Point3D(-50, -50, 50)));

        scene.addLights(

                new SpotLight(new Color(800, 400, 400), //
                        new Point3D(50, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7),
                new PointLight(new Color(127, 255, 212), new Point3D(-400, 120, 50),1,1,1),
                new SpotLight(new Color(100, 250, 100),
                        new Point3D(-20, 20, 100), new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("OurImageAdv", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImageAdvanced();
        render.writeToImage();
    }
    @Test
    public void OurImageNewTest() {
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(10000);
            scene.setBackground(new Color(0, 0, 204));
            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

            scene.addGeometries(
                    new Sphere(new Color(0, 100, 0), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                    new Sphere(new Color(0, 20, 80), new Material(0.25, 0.25, 40), 200, new Point3D(-950, 900, 1000)),
                    new Sphere(new Color(153, 0, 204), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-300, -500, 1000)),
                    new Sphere(new Color(255, 255, 0), new Material(0.25, 0.25, 40), 200, new Point3D(-300, -500, 1000)),
                    new Sphere(new Color(43, 255, 244), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(750, 900, 1000)),
                    new Sphere(new Color(244, 168, 53), new Material(0.25, 0.25, 40), 200, new Point3D(750, 900, 1000)),
                    new Triangle(new Color(0,0,0), new Material(0, 0, 30, 0, 0.5), new Point3D(1500, 1500, 1500),
                            new Point3D(0, -1400, 1000), new Point3D(-1500, 1500, 2000)),
                    new Triangle(new Color(64,0,128), new Material(0, 0, 30, 0, 0.5), new Point3D(5000, 1500, 1500),
                            new Point3D(0, -1400, 1000), new Point3D(-1500, 1500, 2000)),
                    new Triangle(new Color(64,0,128), new Material(0, 0, 30, 0, 0.5), new Point3D(-5000, 1500, 1500),
                             new Point3D(0, -1400, 1000), new Point3D(-1500, 1500, 2000)));



            scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150),
                    new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
            scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-200, -1000, 150),
                new Vector(1, -1, 4), 1, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter("OurIMG", 2500, 2500, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }
    }


