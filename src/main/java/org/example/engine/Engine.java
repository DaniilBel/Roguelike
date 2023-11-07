package org.example.engine;

import org.example.person.Person;

import javax.swing.*;

public class Engine {

//    private final int framesPerSecond = 60;
//    private final int timePerLoop = 1_000_000_000 / framesPerSecond;
    private static Person person;
    private static Timer timer;

    public static void start() {

        person = new Person(100, 200, 20);

        timer = new Timer(100, new ControlListener());
        timer.start();
    }

    public static void movePerson(int dX, int dY) {
        person.setPos(person.getX() + dX, person.getY() + dY);
    }

    public static Person getPerson() {
        return person;
    }
}