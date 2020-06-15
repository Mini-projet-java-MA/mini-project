package elements;

import primitives.Color;

/**
 * The class represents ambient lighting in the scene - the fill color which is added to all objects of 3D model
 */
public class AmbientLight extends Light  {

    /**
     *this function is the constructor it allows us to set an ambient light in our scene
     * @param ia-refill light
     * @param ka-promotes light refill
     */
    public  AmbientLight(Color ia, double ka) {
        super(ia, ka);
    }

}
