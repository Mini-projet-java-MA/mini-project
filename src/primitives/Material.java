package primitives;

/**
 * a class to represent material
 */
public class Material {
    double _kD;
    double _kS;
    int _nShiniess;

    /**
     * a simple contreator for Material
     * @param kD the distanse
     * @param ks
     * @param nShiniess
     */
    public Material(double kD, double ks, int nShiniess)
    {
        _kD=kD;
        _kS=ks;
        _nShiniess=nShiniess;
    }

    /**
     *
     * @return
     */
    public double getKd(){return _kD}
    public
}
