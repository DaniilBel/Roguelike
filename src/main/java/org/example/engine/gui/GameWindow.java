package org.example.engine.gui;

import org.example.engine.ControlListener;
import org.example.engine.Engine;
import org.example.engine.render.Render;

import javax.swing.*;
import java.awt.*;

/**
 * Инициализирует начало игры и отрисовывает все объекты на поле
 */
public class GameWindow extends JPanel {

    private Render render;

    /**
     * Инициализирует игру
     */
    public GameWindow() {
        super();
        this.setFocusable(true);
        this.addKeyListener(new ControlListener());

        render = new Render();

        System.out.println("GameWindow: Create game window");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GUI.WIDTH, GUI.HEIGHT);

            if (Engine.isOnStart()) {
                render.renderLevel(Engine.getCurrentFloor(), Engine.getPerson(), g);
                render.renderPlayer(Engine.getPerson(), g);
            }

        } catch (Exception e) {
            System.err.println("Exception in render system");
            e.printStackTrace();
        }

//        revalidate();
        repaint();
    }
}
