package primitives;

/**
 *
 */
public class Point3D {
    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;

    /**
     * This is point in center of Cartesian coordinate system - point (0,0,0)
     */
    public static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * this is the constructor of a point in cartesian coordinate system with three coordinate x,y,z
     * @param x 1st coordinate
     * @param y 2nd coordinate
     * @param z 3rd coordinate
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    /**
     * this is the constructor of a point in cartesian coordinate system with three numbers x,y,z that will become coordinate
     * @param x 1st coordinate
     * @param y 2nd coordinate
     * @param z 3rd coordinate
     */
    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    /**
     * this is the copy constructor that allows us to build a point from another one with the same parameters
     * @param other this is the point we copy to the new point
     */
    public Point3D(Point3D other) {
        this._x = new Coordinate(other._x);
        this._y = new Coordinate(other._y);
        this._z = new Coordinate(other._z);
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }

    public double distanceSquared(Point3D a) {
        return ((this._x._coord - a._x._coord) * (this._x._coord - a._x._coord))
                + ((this._y._coord - a._y._coord) * (this._y._coord - a._y._coord)
                + (this._z._coord - a._y._coord) * (this._z._coord - a._y._coord));
    }

    public double distance(Point3D a) {
        return Math.sqrt(this.distanceSquared(a));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return _x.equals(oth._x) && _y.equals(oth._y) && _z.equals(oth._z);
    }

    public Point3D add(Vector v) {
        return new Point3D(this.getX()._coord + v.getHead().getX()._coord,
                this.getY()._coord + v.getHead().getY()._coord,
                this.getZ()._coord + v.getHead().getZ()._coord);
    }

    public Vector subtract(Point3D other) {
        return new Vector(this._x._coord - other.getX()._coord,
                this._y._coord - other.getY()._coord,
                this._z._coord - other.getZ()._coord);
    }
}