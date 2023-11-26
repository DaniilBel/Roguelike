package org.example.entity;

public class Monster extends Entity implements Behaviour {

    private Type type;
    private boolean chasePlayer;
    public Monster(Type type, int x, int y, int hitPoints) {
        super("monster", x, y, hitPoints);
        this.type = type;
        this.hitPoints = type.getHp();
//        this.strength = type.getStrength();
//        this.defense = type.getDefense();
        this.chasePlayer = type.isChase();
    }

    @Override
    public void setPos(int newX, int newY) {
        x = newX;
        y = newY;
    }

    @Override
    public void setHitPoints(int amount) {
        hitPoints += amount;
    }

    @Override
    public boolean shouldChasePlayer() {
        return chasePlayer;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        BAT("bat", 7, 2, 0, false),
        RAT("rat", 11, 2, 0, true);

        private String name;
        private int hp;
        private int strength;
        private int defense;
        private boolean chase;

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
