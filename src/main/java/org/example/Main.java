package org.example;
import org.example.engine.Engine;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = initScreen();
        Engine.initWindow(jFrame);
    }

    private static JFrame initScreen() {
        JFrame jFrame = new JFrame("Roguelike");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2, dimension.height/2, 720, 480);
        return jFrame;
    }

    public static int add(int x, int y) {
        return x + y;
    }
}