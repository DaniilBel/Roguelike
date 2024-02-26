package org.example.entity.monster;

public interface MonsterFactory {
    int getX();

    int getY();

    ReplicationMonster createReplicator(ReplicationMonster.Type type, int x, int y);
    Monster createMonster(Monster.Type type, int x, int y);
}
