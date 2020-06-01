package primitives;

/**
 * class Point3d represents a point in cartesian coordinate system
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
     *
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
     *
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

    /**
     * this is the function that will allow us to calculate the square of the distance between two points
     *
     * @param p this is the second point we will receive in parameter
     * @return the square of the distance between our point and the point p
     */
    public double distanceSquared(Point3D p) {
        return ((this._x._coord - p._x._coord) * (this._x._coord - p._x._coord))
                + ((this._y._coord - p._y._coord) * (this._y._coord - p._y._coord)
                + (this._z._coord - p._y._coord) * (this._z._coord - p._y._coord));
    }

    /**
     * this function calculate the distance between two points
     *
     * @param p the second point
     * @return the distance between our points and the point p
     */
    public double distance(Point3D p) {
        return Math.sqrt(this.distanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return _x.equals(oth._x) && _y.equals(oth._y) && _z.equals(oth._z);
    }

    /**
     * thie function allows us to compute an addition between a point and a vector and it will return a point for result
     *
     * @param v the vector we add to our point
     * @return a point3d as a result of the addition
     */
    public Point3D add(Vector v) {
        return new Point3D(this.getX()._coord + v.getHead().getX()._coord,
                this.getY()._coord + v.getHead().getY()._coord,
                this.getZ()._coord + v.getHead().getZ()._coord);
    }

    /**
     * the function allows us to compute a subtraction of a point from another one the result will be a vector
     *
     * @param other the point we subtract to our current point
     * @return a new vector as the result of the subtraction
     */
    public Vector subtract(Point3D other) {
        return new Vector(this._x._coord - other.getX()._coord,
                this._y._coord - other.getY()._coord,
                this._z._coord - other.getZ()._coord);
    }
    @Override
    public String toString() {
        return "(" +
                _x +
                ", " + _y +
                ", " + _z +
                ')';
    }
}