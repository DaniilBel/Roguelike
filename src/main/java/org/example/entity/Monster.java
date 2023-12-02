package org.example.entity;

public class Monster extends Entity implements Behaviour {

    private Type type;
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

    /**
     * Указаны все типы монстров и их характеристики
     */
    public enum Type {
        GHOST("ghost", 11, 2, 0, false),
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
