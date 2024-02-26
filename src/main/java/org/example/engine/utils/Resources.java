package org.example.engine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Resources {
    // Путь, изображение
    private static HashMap<String, BufferedImage> sprites;
//    private static HashMap<String, BufferedImage[]> animationSprites;

    /**
     * Инициализирует HashMap текстурами, которые имеются в папке res
     * Вызывается в Engine и дальше используется при рендере карты
     */
    public static void init() {
        sprites = new HashMap<>();

        File folder = new File("res");

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            try {
                sprites.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
            } catch (Exception e) {
                System.err.println("Error in " + file.getName() + " " + e.getMessage());
            }
        }
    }

//    public static void initAnimation() {
//        animationSprites = new HashMap<>();
//
//        File folder = new File("res");
//
//    }

    /**
     * Возвращает спрайт из HashMap
     * @param name - имя соответствующего спрайта в папке res
     * @return - BufferedImage, который соответствует запрошенному спрайту
     */
    public static BufferedImage getSprite(String name) {
        return sprites.get(name);
    }
}
