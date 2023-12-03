package org.example.entity;

/**
 * Характеристики персонажа и методы, связанные с его перемещением
 */
public class Person extends Entity {

    private double velX = 0;
    private double velY = 0;

    public Person(int x, int y, int hitPoints) {
        super("person", x, y, hitPoints);
    }

    public void smoothMoving() {
        x += velX;
        y += velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }


    /**
     * Конкретная реализация ХП для персонажа
     */
    @Override
    public void setHitPoints(int amount) {
        hitPoints += amount;
    }

}
