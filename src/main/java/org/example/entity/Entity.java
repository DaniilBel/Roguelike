package org.example.entity;

import org.example.engine.Tile;

public class Entity extends Tile {

    protected int hitPoints;
    protected int strength;
    protected int defense;

    private int motionOffsetX;
    private int motionOffsetY;

    /**
     * Каждая сущность будет иметь тэг для классификации монстр или игрок
     * У каждой этой сущности помимо расположения есть хп и тп
     * @param tag - имя метки, к которой обращаемся для проверки
     * @param x - координата x
     * @param y - координата y
     * @param hitPoints - количество хп
     */
    public Entity(String tag, int x, int y, int hitPoints) {
        super(tag, x, y);
        this.hitPoints = hitPoints;
    }

    /**
     * Новая позиция сущности
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

    /**
     * Задание хп в зависимости от нанесенного урона или восполнененного хп
     * @param amount - количество отнимаемого/добавляемого хп
     */
    public void setHitPoints(int amount) {
        hitPoints += amount;
    }

    public int getMotionOffsetX() {
        return motionOffsetX;
    }

    public int getMotionOffsetY() {
        return motionOffsetY;
    }

    public void setMotionOffset(int motionOffsetX, int motionOffsetY) {
        this.motionOffsetX = motionOffsetX;
        this.motionOffsetY = motionOffsetY;
    }

    public void decreaseMotionOffset() {
        if(motionOffsetX > 0)
            motionOffsetX-=2;
        else if(motionOffsetX < 0)
            motionOffsetX+=2;

        if(motionOffsetY > 0)
            motionOffsetY-=2;
        else if(motionOffsetY < 0)
            motionOffsetY+=2;
    }
}
