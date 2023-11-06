package org.example.person;

import javax.swing.*;
import java.awt.*;

public class Person {
    private int hitPoints;

    private int x;
    private int y;

    public Person(int hitPoints, int x, int y) {
        this.hitPoints = hitPoints;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    private int getHitPoints() {
        return hitPoints;
    }
    private void setHitPoints(int amount) {
        hitPoints -= 1;
    }

    public void move(JPanel map, int dx, int dy) {
        x += dx;
        y += dy;


    }
}
