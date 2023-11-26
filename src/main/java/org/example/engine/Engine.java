package org.example.engine;

import org.example.engine.utils.Resources;
import org.example.entity.Monster;
import org.example.entity.Person;
import org.example.map.level.Floor;

import javax.swing.*;

/**
 * Запускает бесконечный цикл с игрой
 * Здесь же идёт управление и отрисовка персонажа
 */
public class Engine {

    private static Person person;
    private static Monster monster;
    private static Timer timer;

    private static Floor currentFloor;
    private static boolean onStart;

    /**
     * Ставится персонаж и подключается управление им
     * После запуска этого метода можно играть
     */
    public static void start() {
        System.out.println("Start engine");
        Resources.init();
        System.out.println("Init resources");
        onStart = true;

        person = new Person(2, 1, 20);
        //monster = new Monster(Monster.Type.BAT, 200, 200, 10);

        currentFloor = new Floor(new String[] {
                "########",
                "#...#..#",
                "#...#..#",
                "##.##..#",
                "#...#..#",
                "#......#",
                "#...#..#",
                "########"
        });

        timer = new Timer(200, new ControlListener());
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

    public static void moveMonster(int dx, int dy) {
        monster.setPos(monster.getX() + dx, monster.getY() + dy);
    }

    public static Monster getMonster() {
        return monster;
    }
    public static Floor getCurrentFloor() {
        return currentFloor;
    }
    public static boolean isOnStart() {
        return onStart;
    }
}