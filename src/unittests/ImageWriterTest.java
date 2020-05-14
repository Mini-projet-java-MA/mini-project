package unittests;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

/**
 * in this class we test image writer
 */
public class ImageWriterTest {

    /**
     * we test to creat image 10*16 squared 1000*1600 unit light witch resolutions 500*800
     */
    @Test
    public void writeToImage() {
        Color Green = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);
        ImageWriter img = new ImageWriter("img1", 10, 16, 800, 500);
        for (int col = 0; col< 500; col++) {
            for (int row = 0; row < 800; row++) {
                if (col % 50 == 0 || row % 50 == 0)
                    img.writePixel(col, row, white.getColor());
                else
                    img.writePixel(row, row, Green.getColor());

            }
            img.writeToImage();

        }
        img.writeToImage();

    }
}