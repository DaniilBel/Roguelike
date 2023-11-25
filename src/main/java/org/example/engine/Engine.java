package org.example.engine;

import org.example.entity.Person;

import javax.swing.*;

/**
 * Запускает бесконечный цикл с игрой
 * Здесь же идёт управление и отрисовка персонажа
 */
public class Engine {

    private static Person person;
    private static Timer timer;

    /**
     * Ставится персонаж и подключается управление им
     * После запуска этого метода можно играть
     */
    public static void start() {
        System.out.println("Start engine");

        person = new Person(100, 200, 20);

        timer = new Timer(1000, new ControlListener());
        timer.start();
    }

    /**
     * Передвинуть персонажа на некоторое количество пикселей
     * @param dX - перемещение по x
     * @param dY - перемещение по y
     */
    public static void movePerson(int dX, int dY) {
        person.setPos(person.getX() + dX, person.getY() + dY);
    }

    public static Person getPerson() {
        return person;
    }
}