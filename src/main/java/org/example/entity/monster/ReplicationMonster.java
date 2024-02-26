package org.example.entity.monster;

import org.example.entity.Entity;

public class ReplicationMonster extends Entity implements MonsterFactory{

    private final Type type;

    public ReplicationMonster(Type type, int x, int y) {
        super(type.getName(), x, y, type.getHp());
        this.type = type;
        this.hitPoints = type.getHp();
        this.strength = type.getStrength();
        this.defense = type.getDefense();
    }

    public ReplicationMonster copy(int x, int y) {
        return new ReplicationMonster(this.type, x, y);
    }

    @Override
    public ReplicationMonster createReplicator(Type type, int x, int y) {
        return null;
    }

    @Override
    public Monster createMonster(Monster.Type type, int x, int y) {
        return null;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        MOLD("mold", 110, 0, 10);

        private final String name;
        private final int hp;
        private final int strength;
        private final int defense;

        Type(String name, int hp, int strength, int defense) {
            this.name = name;
            this.hp = hp;
            this.strength = strength;
            this.defense = defense;
        }

        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public int getStrength() {
            return strength;
        }

        public int getDefense() {
            return defense;
        }
    }
}
