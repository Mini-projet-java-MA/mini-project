package primitives;

/**
 * a class to represent material
 */
public class Material {
    double _kD;
    double _kS;
    int _nShininess;

    /**
     * a simple constructor for Material
     * @param kD the factor for diffusive light
     * @param ks the factor for specular light
     * @param nShininess level of the object's shininess
     */
    public Material(double kD, double ks, int nShininess)
    {
        _kD=kD;
        _kS=ks;
        _nShininess=nShininess;
    }

    /**
     * getter for the factor for diffusive light
     * @return the factor for diffusive light
     */
    public double getKD() {
        return _kD;
    }

    /**
     * getter for the factor for specular light
     * @return the factor for specular light
     */
    public double getKS() {
        return _kS;
    }

    /**
     * getter fot the level of the object's shininess
     * @return the level of the object's shininess
     */
    public int getNShininess() {
        return _nShininess;
    }
}
