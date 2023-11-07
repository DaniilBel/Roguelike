package org.example.engine.render;

import org.example.person.Person;

import java.awt.*;

public class RenderEntity {
    public static void render(Person person, Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(person.getX(), person.getY(), 32, 32);
    }
}