package org.example.engine;

import org.example.engine.utils.Resources;
import org.example.entity.Entity;
import org.example.entity.Monster;
import org.example.entity.Person;
import org.example.map.Levels;
import org.example.map.level.Floor;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

/**
 * Запускает бесконечный цикл с игрой
 * Здесь же идёт управление и отрисовка персонажа
 */
public class Engine implements Runnable {
    private static final int TILE_SIZE = 32;
    private static Random random;
    private static Person person;
    private static Monster[] monsters;
//    private static Timer timer;
    private static Thread thread;
    private static Floor currentFloor;
    private static boolean running = false;

    /**
     * Ставится персонаж и подключается управление им
     * После запуска этого метода можно играть
     */
    public synchronized void start() {
        if (running) {
            System.out.println("Running is true");
            return;
        }

        System.out.println("Engine: Start engine");
        Resources.init();
        System.out.println("Engine: Init resources");
        currentFloor = Levels.LEVEL_1;
        System.out.println("Engine: Init level");

        running = true;
        thread = new Thread(Engine.this);
        thread.start();

//        timer = new Timer(200, new ControlListener());
//        timer.start();
    }

    /**
     * Остановка или вылет игры
     */
    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    /**
     * В отдельном потоке происходит весь игровй процесс
     * Слушатель клавиш подключается в классе GameWindow
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ticks = 60.0; // Игра будет ограничиваться 60 тиками
        double ns = 1_000_000_000 / ticks;
        double delta = 0; // Нужно для обновления внутренней игрвой логики (персонаж, монстры и тп)
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        random = new Random();

        person = new Person(2, 1, 20);
        monsters = currentFloor.getMonsters();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                movePerson(person.getVelX(), person.getVelY());
                updates++;
                delta--;
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Передвинуть персонажа на некоторое количество пикселей
     * @param dX - перемещение по x
     * @param dY - перемещение по y
     */
    public static void movePerson(double dX, double dY) {
        switch (getFrontTile(person, dX + (double)TILE_SIZE/2, dY + (double)TILE_SIZE/2).getTag()) {
            case "floor":
                person.smoothMoving();
                break;
            case "wall":
                System.out.println("A wall");
                break;
        }
        moveMonsters();
    }

    private static void moveMonsters() {
        for(Monster monster : monsters) {
            if(monster.getHitPoints() <= 0)
                continue;

            if(!monster.shouldChasePlayer()) {
                switch(random.nextInt(4)) {
                    case 0:
                        if(currentFloor.monsterIsHere(monster.getX()+1, monster.getY())) {
                            return;
                        }
                        else if(monster.getX()+1 == person.getX() && monster.getY() == person.getY()) {
                            message();
                            break;
                        }
                        if(Objects.equals(getFrontTile(monster, 1, 0).getTag(), "floor")) {
                            monster.setPos(monster.getX()+1, monster.getY());
                            break;
                        }
                    case 1:
                        if(currentFloor.monsterIsHere(monster.getX()-1, monster.getY())) {
                            return;
                        }
                        else if(monster.getX()-1 == person.getX() && monster.getY() == person.getY()) {
                            message();
                            break;
                        }
                        if(Objects.equals(getFrontTile(monster, -1, 0).getTag(), "floor")) {
                            monster.setPos(monster.getX()-1, monster.getY());
                            break;
                        }
                    case 2:
                        if(currentFloor.monsterIsHere(monster.getX(), monster.getY()+1)) {
                            return;
                        }
                        else if(monster.getX() == person.getX() && monster.getY()+1 == person.getY()) {
                            message();
                            break;
                        }
                        if(Objects.equals(getFrontTile(monster, 0, 1).getTag(), "floor")) {
                            monster.setPos(monster.getX(), monster.getY()+1);
                            break;
                        }
                    case 3:
                        if(currentFloor.monsterIsHere(monster.getX(), monster.getY()-1)) {
                            return;
                        }
                        else if(monster.getX() == person.getX() && monster.getY()-1 == person.getY()) {
                            message();
                            break;
                        }
                        if(Objects.equals(getFrontTile(monster, 0, -1).getTag(), "floor")) {
                            monster.setPos(monster.getX(), monster.getY()-1);
                            break;
                        }
                }
            } else {
                float angCoeff = -((float)person.getY()-(float)monster.getY())/((float)person.getX()-(float)monster.getX());

                if(angCoeff>-1 && angCoeff<1 && person.getX()>monster.getX()) {
                    if(monster.getX()+1 == person.getX() && monster.getY() == person.getY()) {
                        message();
                    }
                    else if(Objects.equals(getFrontTile(monster, 1, 0).getTag(), "floor")) {
                        monster.setPos(monster.getX()+1, monster.getY());
                    }
                }
                else if(angCoeff>-1 && angCoeff<1 && person.getX()<monster.getX()) {
                    if(monster.getX()-1 == person.getX() && monster.getY() == person.getY()) {
                        message();
                    }
                    else if(Objects.equals(getFrontTile(monster, -1, 0).getTag(), "floor")) {
                        monster.setPos(monster.getX()-1, monster.getY());
                    }
                }
                else if((angCoeff>1 || angCoeff<-1) && person.getY()>monster.getY()) {
                    if(monster.getX() == person.getX() && monster.getY()+1 == person.getY()) {
                        message();
                    }
                    else if(Objects.equals(getFrontTile(monster, 0, 1).getTag(), "floor")) {
                        monster.setPos(monster.getX(), monster.getY()+1);
                    }
                }
                else if((angCoeff>1 || angCoeff<-1) && person.getY()<monster.getY()) {
                    if(monster.getX() == person.getX() && monster.getY()-1 == person.getY()) {
                        message();
                    }
                    else if(Objects.equals(getFrontTile(monster, 0, -1).getTag(), "floor")) {
                        monster.setPos(monster.getX(), monster.getY()-1);
                    }
                }
            }
        }
    }

    private static void message() {
        System.out.println("You have been attacked!");
    }

    /**
     * Нужно для определения тега тайла
     * @param entity - какой объект
     * @param x - координата по x
     * @param y - координата по y
     * @return тег тайла (wall, floor и тп)
     */
    private static Tile getFrontTile(Entity entity, double x, double y) {
        return currentFloor.getTileAt((entity.getX() + (int)x)/TILE_SIZE, (entity.getY() + (int)y)/TILE_SIZE);
    }

    /**
     * Используется в управлении персонажем и тп
     * @return персонаж со всеми полями и инвентарём
     */
    public static Person getPerson() {
        return person;
    }

    /**
     *
     * @return все монстры на этаже
     */
    public static Monster[] getMonsters() {
        return monsters;
    }

    /**
     * Текущий уровень со всеми тайлами
     * @return возвращает текущий уровень
     */
    public static Floor getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Запущем ли основной игровой цикл
     * @return запущена ли игра с возможностью управления персонажем
     */
    public static boolean isRunning() {
        return running;
    }
}