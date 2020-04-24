package primitives;

// In Java:
// protected == C# internal protected --- which means it's available for any class in the same package as
// well as in any child class in other packages
// w/o access (default) == C# internal --- it's called package friendly in Java

public class Ray {
    private Point3D _p0;
    private Vector _direction;

    public Ray(Point3D p, Vector v) {
        this._direction = v.normalized();
        this._p0 = new Point3D(p);
    }

    public Ray(Ray other) {
        this._direction = new Vector(other._direction);
        this._p0 = new Point3D(other._p0);
    }

    public Vector getDirection() {
        return _direction;
    }

    public Point3D getP0() {
        return _p0;
    }

    @Override
    public String toString() {
        return "Ray{" + "P1= " + _p0 + ", _direction=" + _direction + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _direction.equals(ray._direction);
    }
}
