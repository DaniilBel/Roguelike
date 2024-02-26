package org.example.entity.monster;

import org.example.entity.Behaviour;
import org.example.entity.Entity;
import org.example.entity.behaviour.Coward;
import org.example.entity.behaviour.State;

import java.util.Random;

public class Monster extends Entity implements Behaviour, MonsterFactory, State {

    private State state;
    private final Type type;
    private boolean chasePlayer;
    private final Random random = new Random();
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
    public ReplicationMonster createReplicator(ReplicationMonster.Type type, int x, int y) {
        return null;
    }

    @Override
    public Monster createMonster(Type type, int x, int y) {
        return null;
    }

    public void setState(State state) {
        chasePlayer = !chasePlayer;
        this.state = state;
    }

    @Override
    public void doAction() {
        if (state != null) {
            state.doAction();
            if (getHitPoints() <= 5 && random.nextInt(100) >= 80) {
                int hp = getHitPoints()+1;
                setHitPoints(hp);
            }
        }
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
