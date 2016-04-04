package controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Textures {

    public static BufferedImage background;

    public static void load() {
        background = loadImage("/res/background.png");
    }

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Textures.class.getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
