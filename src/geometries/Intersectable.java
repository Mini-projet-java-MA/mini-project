package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 *  this interface for all geometries that are able to intersect from a ray to their entity
 * @ auther aaron
 */
public interface Intersectable {
    /**
     * @param ray- he his the ray that insert the object
     * @return a list of all intersection
     */
    List<GeoPoint> findIntersections(Ray ray);

    /**
     * class include intersection point and its geometry
     * this is a static helping class
     */
    public static class GeoPoint {

        public Geometry _geometry;
        public Point3D _point;

        /**
         * constructor for GeoPoint
         * @param geometry the geometry we work with
         * @param point the point we work with
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this._geometry = geometry;
            this._point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) &&
                    Objects.equals(_point, geoPoint._point);
        }

    }

}

