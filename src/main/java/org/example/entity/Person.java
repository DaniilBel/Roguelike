package org.example.entity;

/**
 * Характеристики персонажа и методы, связанные с его перемещением
 */
public class Person extends Entity {

    public Person(int x, int y, int hitPoints) {
        super("person", x, y, hitPoints);
    }


    @Override
    public void setPos(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Конкретная реализация ХП для персонажа
     */
    @Override
    public void setHitPoints(int amount) {
        hitPoints += amount;
    }

}
