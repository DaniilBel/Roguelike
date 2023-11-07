package org.example.person;

/**
 * Характеристики персонажа и методы, связанные с его перемещением
 */
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

    /**
     * Новая позиция персонажа
     * @param newX - новая координата x
     * @param newY - новая координата y
     */
    public void setPos(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public void setHitPoints(int amount) {
        hitPoints -= 1;
    }

}
