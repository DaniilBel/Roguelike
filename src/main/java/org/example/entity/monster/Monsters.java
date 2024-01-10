package org.example.entity.monster;

public class Monsters implements MonsterFactory {

    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public ReplicationMonster createReplicator() {
        return null;
    }

    @Override
    public Monster createMonster(Monster.Type type, int x, int y) {
        this.x = x;
        this.y = y;
        return new Monster(type, x, y);
    }
}
