package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * scene class create a scene to work on
 * contain 6 fields:
 * _name name of scene
 * _background color to paint the background of scene
 * _ambientLight light/color that lights the environment
 * _geometries objects/shapes that exist in scene
 * _camera point of view, that from there we look on the scene, through an imagine view plane
 * _distance distance from camere to the imagine view plane
 *
 * @author moshe
 */
public class Scene {
    private List<LightSource> _lights = new LinkedList<LightSource>();
    private String _name;
    private Color _background = Color.BLACK;
    private AmbientLight _ambientLight = new AmbientLight(Color.BLACK, 0d);
    private Geometries _geometries = new Geometries();
    private Camera _camera;
    private double _distance;

    /**
     * constructor with only the name as a parameter
     *
     * @param name name of the scene
     */
    public Scene(String name) {
        this._name = name;
    }

    /**
     * a simple geter of list of light souce
     *
     * @return list of light
     */
    public List<LightSource> getLight() {
        return _lights;
    }

    /**
     * @param lights function to add in the list point of light
     */
    public void addLights(LightSource... lights) {
        for (LightSource ls : lights)
            _lights.add(ls);
    }

    public String getName() {
        return _name;
    }

    public Color getBackground() {
        return _background;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public double getDistance() {
        return _distance;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public void setGeometries(Geometries _geometries) {
        this._geometries = _geometries;
    }

    /**
     * function to add geometries to scene
     *
     * @param geometries one or more geometries, such sphere or triangle
     */
    public void addGeometries(Intersectable... geometries) {
        _geometries.add(geometries);
    }
}
