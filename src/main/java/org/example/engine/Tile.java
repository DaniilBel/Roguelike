package org.example.engine;

public class Tile {

    private String tag;
    protected int x;
    protected int y;

    /**
     * Отвечает за глобальное расположение и имя тайла
     * Дальше используется для более конкретных целей
     * Например указание местоположения стены или персонажа
     * @param tag
     * @param x
     * @param y
     */
    public Tile(String tag, int x, int y) {
        this.tag = tag;
        this.x = x;
        this.y = y;
    }

    public String getTag() {
        return tag;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
