package org.example.engine.gui;

import org.example.engine.ControlListener;
import org.example.engine.Engine;
import org.example.engine.render.RenderEntity;

import javax.swing.*;
import java.awt.*;

/**
 * Инициализирует начало игры и отрисовывает все объекты на поле
 */
public class GameWindow extends JPanel {

    /**
     * Инициализирует игру
     */
    public GameWindow() {
        super();
        this.setFocusable(true);

        Engine.start();
        this.addKeyListener(new ControlListener());

        System.out.println("GameWindow: Create game window");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GUI.WIDTH, GUI.HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(15, 15, GUI.WIDTH-50, GUI.HEIGHT-75);

        RenderEntity.render(Engine.getPerson(), g);

        repaint();
    }
}
