package org.example.engine.gui;

import org.example.engine.ControlListener;
import org.example.engine.Engine;
import org.example.engine.render.RenderEntity;
import org.example.engine.render.Renderer;
import org.example.engine.utils.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * Инициализирует начало игры и отрисовывает все объекты на поле
 */
public class GameWindow extends JPanel {

    private Renderer renderer;

    /**
     * Инициализирует игру
     */
    public GameWindow() {
        super();
        this.setFocusable(true);
        this.addKeyListener(new ControlListener());

        renderer = new Renderer();

        System.out.println("GameWindow: Create game window");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GUI.WIDTH, GUI.HEIGHT);
//            g.setColor(Color.WHITE);
//            g.fillRect(15, 15, GUI.WIDTH-50, GUI.HEIGHT-75);

//            g.drawImage(Resources.getSprite("player"), 100, 200, 32, 32, null);

            if (Engine.isOnStart()) {
                renderer.renderLevel(Engine.getCurrentFloor(), Engine.getPerson(), g);
                renderer.renderPlayer(Engine.getPerson(), g);
            }

        } catch (Exception e) {
            System.err.println("Exception in render system");
            e.printStackTrace();
        }

//        RenderEntity.render(Engine.getPerson(), g, Color.GREEN);
        //RenderEntity.render(Engine.getMonster(), g, Color.RED);



//        revalidate();
        repaint();
    }
}
