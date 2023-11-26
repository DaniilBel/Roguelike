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
    private static final int offset = 4;

    private static JFrame window;
    private static GameWindow gameWindow;

    public static void createWindow() {
        window = new JFrame("Roguelike");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(dimension.width/offset, dimension.height/offset, WIDTH, HEIGHT);
        window.setResizable(false);

        gameWindow = new GameWindow();
        window.add(gameWindow);

        System.out.println("GUI: Created window");
    }
}
