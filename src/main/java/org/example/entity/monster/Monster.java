package org.example.entity.monster;

import org.example.entity.Behaviour;
import org.example.entity.Entity;

public class Monster extends Entity implements Behaviour, MonsterFactory {

    private final Type type;
    private boolean chasePlayer;
    public Monster(Type type, int x, int y) {
        super(type.getName(), x, y, type.getHp());
        this.type = type;
        this.hitPoints = type.getHp();
        this.strength = type.getStrength();
        this.defense = type.getDefense();
        this.chasePlayer = type.isChase();
    }

    /**
     * Метод из Behaviour. Нужен для того, чтобы определить должен ли монстр гнаться за персонажем
     * @return - гонется монстр за персонажем или нет
     */
    @Override
    public boolean shouldChasePlayer() {
        return chasePlayer;
    }

    /**
     * Тип монстра для указания его поведения и тп
     * @return - Тип монстра со всеми его храктеристиками
     */
    public Type getType() {
        return type;
    }

    @Override
    public ReplicationMonster createReplicator() {
        return null;
    }

    @Override
    public Monster createMonster(Type type, int x, int y) {
        return null;
    }

    /**
     * Указаны все типы монстров и их характеристики
     */
    public enum Type {
        GHOST("ghost", 11, 2, 0, false),
        RAT("rat", 11, 2, 0, true);

        private final String name;
        private final int hp;
        private final int strength;
        private final int defense;
        private final boolean chase;

        Type(String name, int hp, int strength, int defense, boolean chase) {
            this.name = name;
            this.hp = hp;
            this.strength = strength;
            this.defense = defense;
            this.chase = chase;
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

        public boolean isChase() {
            return chase;
        }
    }
}
