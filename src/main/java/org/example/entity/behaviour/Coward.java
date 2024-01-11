package org.example.entity.behaviour;

public class Coward implements State {

    @Override
    public void doAction() {
        System.out.println("Set state");
    }
}
