package primitives;

import java.io.PipedOutputStream;
import java.util.Objects;

public class Point3D {

    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;
    public static  Point3D ZERO=new Point3D(0,0,0);
    public Point3D (Coordinate x, Coordinate y, Coordinate  z)
    {

        this._x=new Coordinate(x);
        this._y=new Coordinate(y);
        this._z=new Coordinate(z);
    }


    public Point3D (double x, double y, double  z)
    {

        this._x=new Coordinate(x);
        this._y=new Coordinate(y);
        this._z=new Coordinate(z);
    }
public  Point3D(Point3D a)
{
    this._x=new Coordinate(a._x);
    this._y= new Coordinate(a._y);
    this._z=new Coordinate(a._z);
}

    public Coordinate get_x()
    {
        return new Coordinate(_x);
    }
    public Coordinate get_y()
    {
        return new Coordinate(_y);
    }
    public Coordinate get_z()
    {
        return new Coordinate(_z);
    }
public Point3D get_point3D()
{
    return new Point3D ( this.get_x(),this.get_y(), this.get_z() );
}
    public  double distanceSquared(Point3D a ) // the func receive two point and return scale in squared
    {
        return ((this._x._coord-a._x._coord)*(this._x._coord-a._x._coord))//
                +((this._y._coord-a._y._coord)*(this._y._coord-a._y._coord)
                *(this._z._coord-a._y._coord)*(this._z._coord-a._y._coord));
    }
    public double  distance (Point3D a)//the func recive two point and he return the distance
    {
        return Math.sqrt(this.distanceSquared(a));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return _x.equals(oth._x) && _y.equals(oth._y) && _z.equals(oth._z);

    }



    public Point3D add (Vector b)
    {
        return  new Point3D(this.get_x()._coord+b.get_head_x()._coord
                , this.get_y()._coord+b.get_head_y()._coord
                , this.get_z()._coord+b.get_head_z()._coord);
    }
    public Vector subtract(Point3D b)//receive two point and he return new vector
    {
        return new Vector
                (this._x._coord - b.get_x()._coord,
                        this._y._coord-b.get_y()._coord,
                        this._z._coord-b.get_z()._coord);
    }
}