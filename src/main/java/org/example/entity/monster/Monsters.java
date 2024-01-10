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
    public ReplicationMonster createReplicator(ReplicationMonster.Type type, int x, int y) {
        this.x = x;
        this.y = y;
        return new ReplicationMonster(type, x, y);
    }

    @Override
    public Monster createMonster(Monster.Type type, int x, int y) {
        this.x = x;
        this.y = y;
        return new Monster(type, x, y);
    }
}
