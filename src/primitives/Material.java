package primitives;

/**
 * a class to represent material
 */
public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;

    /**
     * a simple constructor for Material
     *
     * @param kD         the factor for diffusive light
     * @param kS         the factor for specular light
     * @param nShininess level of the object's shininess
     */
    public Material(double kD, double kS, int nShininess) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /**
     * getter for the factor for diffusive light
     *
     * @return the factor for diffusive light
     */
    public double getKd() {
        return _kD;
    }

    /**
     * getter for the factor for specular light
     *
     * @return the factor for specular light
     */
    public double getKs() {
        return _kS;
    }

    /**
     * getter fot the level of the object's shininess
     *
     * @return the level of the object's shininess
     */
    public int getNshininess() {
        return _nShininess;
    }


}
