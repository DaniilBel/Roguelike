package org.example.entity.behaviour;

public class Passive implements State {
    @Override
    public void doAction() {
        System.out.println("Monster has low hp");
    }
}
