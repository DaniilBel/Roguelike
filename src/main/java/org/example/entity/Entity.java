package org.example.entity;

public abstract class Entity implements Behaviour {
    protected int x;
    protected int y;
    protected int hitPoints;
    protected int strength;
    protected int defense;

    protected Entity(int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;
        this.hitPoints = hitPoints;
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
    public abstract void setPos(int newX, int newY);

    public int getHitPoints() {
        return hitPoints;
    }

    public abstract void setHitPoints(int amount);
}
