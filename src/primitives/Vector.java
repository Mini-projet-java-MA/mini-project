package primitives;

import java.util.Objects;
/* pbm every contractor in class vector need to use the func equal excluding the cortactaor that recive vector*/
public class Vector  {
    private Point3D _head_vector;

    public Vector (Coordinate x, Coordinate y,Coordinate z) {
        Point3D temp =new Point3D(x,y,z);;

        if (temp.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
        }


        _head_vector= new Point3D(x,y,z);
    }

    public Vector(double x, double y, double z) {
        Point3D temp =new Point3D(x,y,z);;

        if (temp.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
        }
        _head_vector=new Point3D(x,y,z);

    }

    public Vector(Point3D a) {
        Point3D temp =new Point3D(a.get_x(),a.get_y(),a.get_z());
        if (temp.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("It's not possible to have point head (0,0,0)");
        }
        _head_vector=new Point3D(a.get_x(),a.get_y(),a.get_z());
    }

    public  Vector (Vector a)
    {

        _head_vector=new Point3D(a._head_vector.get_x(),a._head_vector.get_y(),a._head_vector.get_z());
    }

    public Vector get_head_Vector() {
        return new Vector(_head_vector.get_x(),_head_vector.get_y(),_head_vector.get_z());
    }
    public  Coordinate get_head_x()
    {
        return  _head_vector.get_x();
    }
    public  Coordinate get_head_y()
    {
        return  _head_vector.get_y();
    }
    public  Coordinate get_head_z()
    {
        return  _head_vector.get_z();
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head_vector=" + _head_vector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(_head_vector, vector._head_vector);
    }



    public Vector  crossProduct (Vector b)
    {
            /*we send to contractor a new vector kinds of double
    we are going to do now cross product between two vector and get back an new vector vertical two vector
    c.x= a.y*b.z − a.z*b.y
    c.y = a.z*b.x − a.x*b.z
    c.z = a.x*b.y − a.y*b.x
     */
        return new Vector(((this._head_vector.get_y()._coord*b._head_vector.get_z()._coord) -(this._head_vector.get_z()._coord*b._head_vector.get_y()._coord))
                ,((this._head_vector.get_z()._coord*b._head_vector.get_x()._coord) -(this._head_vector.get_x()._coord*b._head_vector.get_z()._coord))
                ,(this._head_vector.get_x()._coord*b._head_vector.get_y()._coord)-(this._head_vector.get_y()._coord*b._head_vector.get_x()._coord));
    }
    public double lengthSquared()
    {
        return  ((this._head_vector.get_x()._coord*this._head_vector.get_x()._coord)
                +(this._head_vector.get_y()._coord*this._head_vector.get_y()._coord)
                +(this._head_vector.get_z()._coord*this._head_vector.get_z()._coord));
    }
    public  double length()
    {
        return  Math.sqrt(this.lengthSquared());
    }
    public  Vector scale (double multe)
    {
        return new Vector(
                _head_vector.get_x()._coord*multe,
                _head_vector.get_y()._coord*multe,
                _head_vector.get_z()._coord*multe);
    }
    public double  dotProduct(Vector b)
    {
        return  ((this._head_vector.get_x()._coord*b._head_vector.get_x()._coord)
                +(this._head_vector.get_y()._coord*b._head_vector.get_y()._coord)
                +(this._head_vector.get_z()._coord*b._head_vector.get_z()._coord));
    }
    public Vector normalize()
    {
        double temp =this.length();//the length of this vector
        this._head_vector = new Point3D(_head_vector.get_x()._coord/temp,_head_vector.get_y()._coord/temp,_head_vector.
                get_z()._coord/temp);
        return this;
    }
    public Vector  normalized()
    {
        return new Vector (this.normalize());
    }
    public Vector add(Vector a)
    {
        return  new Vector(this._head_vector.add(a));
    }
    public Vector subtract (Vector a)
    {
        return new Vector(this._head_vector.subtract(a._head_vector));

    }
}
