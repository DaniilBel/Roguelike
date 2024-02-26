package org.example.entity.behaviour;

public class Agressive implements State {
    @Override
    public void doAction() {
        System.out.println("Monster recovered");
    }
}
