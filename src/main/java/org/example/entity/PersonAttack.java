package org.example.entity;

import org.example.entity.monster.Monster;

import java.util.Random;

public class PersonAttack extends Person {
    Person person;
    Random random;
    public PersonAttack(Person person) {
        super(person.getX(), person.getY(), person.getHitPoints());
        this.person = person;
        random = new Random();
    }

    public void attack(Monster monster) {

        if (monster.getDefense() - person.getStrength() >= 0)
            monster.setHitPoints(-1);
        else
            monster.setHitPoints(-person.getStrength() + monster.getDefense());

        switch(random.nextInt(4)) {
            case 0:
                monster.setPos(monster.getX()+10, monster.getY());
            case 1:
                monster.setPos(monster.getX()-10, monster.getY());
            case 2:
                monster.setPos(monster.getX(), monster.getY()+10);
            case 3:
                monster.setPos(monster.getX(), monster.getY()-10);
        }
    }
}
