package org.example.entity;

/**
 * Характеристики персонажа и методы, связанные с его перемещением
 */
public class Person extends Entity {

    private double velX = 0;
    private double velY = 0;
    private double XP = 0;

    public Person(int x, int y, int hitPoints) {
        super("person", x, y, hitPoints);
        this.defense = 0;
        this.strength = 5;
    }

    public void smoothMoving() {
        x += (int) velX;
        y += (int) velY;
    }

    public void levelUp() {
        if (XP == 10) {
            System.out.println("Level up");
            defense++;
            strength++;
            XP = 0;
        }
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

    public double getXP() {
        return XP;
    }

    public void setXP(double XP) {
        this.XP += XP;
    }

    /**
     * Конкретная реализация ХП для персонажа
     */
    @Override
    public void setHitPoints(int amount) {
        hitPoints += amount;
    }

}
