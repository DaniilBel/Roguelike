package org.example.map.level;

import org.example.entity.Entity;

public class Tile extends Entity {

    private String tag;

    public Tile(String tag, int x, int y) {
        super(x, y, 0);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean shouldChasePlayer() {
        return false;
    }

    @Override
    public void setPos(int newX, int newY) {
        x = newX;
        y = newY;
    }

    @Override
    public void setHitPoints(int amount) {

    }
}
