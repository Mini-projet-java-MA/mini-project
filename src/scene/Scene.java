package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * scene class
 * contain 6 fields:
 * _name name of scene
 * _background color to paint the background of scene
 * _ambientLight light/color that lights the environment
 * _geometries objects/shapes that exist in scene
 * _camera point of view, that from there we look on the scene, through an imagine view plane
 * _distance distance from camere to the imagine view plane
 */
public class Scene {
    private String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;

    /**
     * constructor with only the name as a parameter
     * @param name name of the scene
     */
    public Scene(String name) {
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public Color getBackground() {
        return _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public double getDistance() {
        return _distance;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }
    /**
     * function to add geometries to scene
     *
     * @param geometries one or more geometries, such sphere or triangle
     */
    void addGeometries(Intersectable... geometries){
        _geometries.add(geometries);
    }
}
