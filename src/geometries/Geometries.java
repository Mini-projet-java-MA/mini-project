package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;
/**
 * class with list of shape.
 */
public class Geometries {
    List<Intersectable> intersectableList = new ArrayList<>();

    /**
     * constructor received many geometries and adding them to the list
     *
     * @param geometries geometries to add the list
     */
    /* *********constructor*******/
    public Geometries(Intersectable... geometries) {
        for (Intersectable g : geometries)
            intersectableList.add(g);
    }

    public List<Intersectable> getIntersectableList() {
        return intersectableList;
    }

    /**
     * function who adding geometry shape to list of the class
     *
     * @param geometry geometry shape
     */
    public void add(Intersectable... geometry) {
        for (Intersectable g : geometry)
            intersectableList.add(g);
    }

}
