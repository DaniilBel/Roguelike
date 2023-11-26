package org.example.engine;

import org.example.engine.utils.Resources;
import org.example.entity.Entity;
import org.example.entity.Monster;
import org.example.entity.Person;
import org.example.map.level.Floor;

import javax.swing.*;

/**
 * Запускает бесконечный цикл с игрой
 * Здесь же идёт управление и отрисовка персонажа
 */
public class Engine {

    private static final int TILE_SIZE = 32;

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
        System.out.println("Engine: Start engine");
        Resources.init();
        System.out.println("Engine: Init resources");
        onStart = true;

        person = new Person(2*TILE_SIZE, 1*TILE_SIZE, 20); // *32
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
        switch (getFrontTile(person, dX, dY).getTag()) {
            case "floor":
                person.setPos(person.getX() + dX, person.getY() + dY);
                break;
            case "wall":
                System.out.println("The wall");
                break;
        }
    }

    private static Tile getFrontTile(Entity entity, int x, int y) {
        return currentFloor.getTileAt((entity.getX() + x)/TILE_SIZE, (entity.getY() + y)/TILE_SIZE);
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