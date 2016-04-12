package controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Textures {

    /**
     * the games background
     */
    public static BufferedImage background;

    /**
     * loads all textures
     */
    public static void load() {
        background = loadImage("/res/background.png");
    }

    /**
     * loads a texture
     *
     * @param path
     * @return
     */
    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Textures.class.getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
