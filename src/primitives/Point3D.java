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
     * @param x
     * @param y
     * @param z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    public Point3D(Point3D other) {
        this._x = new Coordinate(other._x);
        this._y = new Coordinate(other._y);
        this._z = new Coordinate(other._z);
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return new Coordinate(_y);
    }

    public Coordinate getZ() {
        return new Coordinate(_z);
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