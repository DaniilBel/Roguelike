package org.example.engine.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Инициализация окна
 * Имеет свойства <b>WIDTH</b> - ширина окна и <b>HEIGHT</b> - высота окна
 */
public class GUI {
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    public static final int SCALE = 2;

    private static JFrame window;
    private static GameWindow gameWindow;

    public static void createWindow() {
        window = new JFrame("Roguelike");

        window.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        window.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
//        window.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameWindow = new GameWindow();
        window.add(gameWindow);

        System.out.println("GUI: Created window");
    }
}
