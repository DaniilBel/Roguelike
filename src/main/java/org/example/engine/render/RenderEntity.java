package org.example.engine.render;

import org.example.entity.Entity;

import java.awt.*;

public class RenderEntity {
    /**
     * Кастомная отрисовка персонажа
     * @param entity - Объект, который присутствует на карте
     * @param g - на что отрисовываем
     */
    public static void render(Entity entity, Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(entity.getX(), entity.getY(), 32, 32);
    }
}
