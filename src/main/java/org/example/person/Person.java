package org.example.person;

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

    public void setPos(int newX, int newY) {
        x = newX;
        y = newY;
    }

    private int getHitPoints() {
        return hitPoints;
    }
    private void setHitPoints(int amount) {
        hitPoints -= 1;
    }

}
