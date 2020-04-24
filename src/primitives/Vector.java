package primitives;

/**
 * Class Vector represents Euclidean 3D vector in Cartesian coordinate system
 */
public class Vector {
    private Point3D _head;

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
    }

    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
    }

    public Vector(Point3D p) {
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
        _head = new Point3D(p);
    }

    /**
     * Copy constructor for Point3D
     *
     * @param other source Vector to copy from
     */
    public Vector(Vector other) {
        _head = new Point3D(other._head);
    }

    public Point3D getHead() {
        return _head;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head_vector=" + _head +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector vector = (Vector) obj;
        return _head.equals(vector._head);
    }

    /**
     * The function performs Cross Product (vector multiplication) operation
     * on this vector with another one: this X v
     * and creates new vector which is the result of the operation
     *
     * @param v 2nd vector for the operation
     * @return new vector with cross-product operation result
     * @throws IllegalArgumentException when the vectors are co-directed (the angle is either 0 or 180)
     */
    public Vector crossProduct(Vector v) {
        double x1 = this._head.getX()._coord;
        double y1 = this._head.getY()._coord;
        double z1 = this._head.getZ()._coord;
        double x2 = v._head.getX()._coord;
        double y2 = v._head.getY()._coord;
        double z2 = v._head.getZ()._coord;
        return new Vector(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2);
    }

    public double lengthSquared() {
        double x = this._head.getX()._coord;
        double y = this._head.getY()._coord;
        double z = this._head.getZ()._coord;
        return x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector scale(double mult) {
        return new Vector(
                _head.getX()._coord * mult,
                _head.getY()._coord * mult,
                _head.getZ()._coord * mult);
    }

    public double dotProduct(Vector v) {
        return ((this._head.getX()._coord * v._head.getX()._coord)
                + (this._head.getY()._coord * v._head.getY()._coord)
                + (this._head.getZ()._coord * v._head.getZ()._coord));
    }

    /**
     *
     * @return
     */
    public Vector normalize() {
        double mult = 1d / this.length();
        this._head = new Point3D(
                _head.getX()._coord * mult,
                _head.getY()._coord * mult,
                _head.getZ()._coord * mult);
        return this;
    }

    public Vector normalized() {
        return new Vector(this.normalize());
    }

    public Vector add(Vector a) {
        return new Vector(this._head.add(a));
    }

    public Vector subtract(Vector a) {
        return this._head.subtract(a._head);
    }
}
