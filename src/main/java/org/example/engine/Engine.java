package org.example.engine;

import org.example.engine.utils.Resources;
import org.example.entity.Entity;
import org.example.entity.behaviour.Agressive;
import org.example.entity.behaviour.Passive;
import org.example.entity.monster.Monster;
import org.example.entity.Person;
import org.example.entity.PersonAttack;
import org.example.entity.monster.MonsterFactory;
import org.example.entity.monster.ReplicationMonster;
import org.example.map.Levels;
import org.example.map.level.Floor;

import java.util.ArrayList;
import java.util.Iterator;
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
    private static ArrayList<MonsterFactory> monsters;
    private static ArrayList<ReplicationMonster> replicators;
    private static Iterator<MonsterFactory> monsterIterator;
    private static boolean behaviourIsChanged = false;
    private static Thread thread;
    private static Floor currentFloor;
    private static Levels levels;
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

        levels = Levels.builder().setStartPos(2, 1).setPath("level").build();
        currentFloor = levels.nextLevel();

        System.out.println("Engine: Init level");

        running = true;
        thread = new Thread(Engine.this);
        thread.start();
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
//        int updates = 0;
//        int frames = 0;
        long timer = System.currentTimeMillis();

        random = new Random();

        person = new Person(2, 1, 2000);
        monsters = currentFloor.getMonsters();
        replicators = new ArrayList<>();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                movePerson(person.getVelX(), person.getVelY());
//                updates++;
                delta--;
            }

//            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println(updates + " Ticks, Fps " + frames);
//                updates = 0;
//                frames = 0;
            }
        }
        stop();
    }

    /**
     * Передвинуть персонажа на некоторое количество пикселей
     *
     * @param dX - перемещение по x
     * @param dY - перемещение по y
     */
    public static void movePerson(double dX, double dY) {
        if (isPlayerDied())
            return;

        switch (getFrontTile(person, dX + (double) TILE_SIZE / 2, dY + (double) TILE_SIZE / 2).getTag()) {
            case "floor":
                person.smoothMoving();
                break;
            case "wall":
                System.out.println("A wall");
                break;
            case "wall_hole":
                person.smoothMoving();
                currentFloor = levels.nextLevel();
                monsters = currentFloor.getMonsters();
                break;
        }
        moveMonsters();
    }

    /**
     * Функция передвижения монстра. В зависимости от типа монстр может следовать за игроком
     * или ходить около своего места. При сближении монстра с игроком он атакует
     */
    private static void moveMonsters() {
        monsterIterator = monsters.iterator();

        while (monsterIterator.hasNext()) {
            MonsterFactory pickMonster = monsterIterator.next();

            if (pickMonster instanceof Monster monster) {

                if (monster.getHitPoints() <= 0)
                    continue;

                if (monster.getHitPoints() <= 5 && !behaviourIsChanged) {
                    monster.setState(new Passive());
                    behaviourIsChanged = !behaviourIsChanged;
                }

                if (monster.getHitPoints() >= monster.getType().getHp() && behaviourIsChanged) {
                    monster.setState(new Agressive());
                    behaviourIsChanged = !behaviourIsChanged;
                }

                monster.doAction();

                if (!monster.shouldChasePlayer()) {
                    switch (random.nextInt(4)) {
                        case 0:
                            if (currentFloor.monsterIsHere(monster.getX() + 1, monster.getY())) {
                                return;
                            } else if (checkCollision(monster.getX() + 1, monster.getY(), person.getX(), person.getY())) {
                                message();
                                attack(monster);
                                break;
                            }
                            if (Objects.equals(getFrontTile(monster, 1, 0).getTag(), "floor")) {
                                monster.setPos(monster.getX() + 1, monster.getY());
                                break;
                            }
                        case 1:
                            if (currentFloor.monsterIsHere(monster.getX() - 1, monster.getY())) {
                                return;
                            } else if (checkCollision(monster.getX() - 1, monster.getY(), person.getX(), person.getY())) {
                                message();
                                attack(monster);
                                break;
                            }
                            if (Objects.equals(getFrontTile(monster, -1, 0).getTag(), "floor")) {
                                monster.setPos(monster.getX() - 1, monster.getY());
                                break;
                            }
                        case 2:
                            if (currentFloor.monsterIsHere(monster.getX(), monster.getY() + 1)) {
                                return;
                            } else if (checkCollision(monster.getX(), monster.getY() + 1, person.getX(), person.getY())) {
                                message();
                                attack(monster);
                                break;
                            }
                            if (Objects.equals(getFrontTile(monster, 0, 1).getTag(), "floor")) {
                                monster.setPos(monster.getX(), monster.getY() + 1);
                                break;
                            }
                        case 3:
                            if (currentFloor.monsterIsHere(monster.getX(), monster.getY() - 1)) {
                                return;
                            } else if (checkCollision(monster.getX(), monster.getY() - 1, person.getX(), person.getY())) {
                                message();
                                attack(monster);
                                break;
                            }
                            if (Objects.equals(getFrontTile(monster, 0, -1).getTag(), "floor")) {
                                monster.setPos(monster.getX(), monster.getY() - 1);
                                break;
                            }
                    }
                } else {
                    float angCoeff = -((float) person.getY() - (float) monster.getY()) / ((float) person.getX() - (float) monster.getX());

                    if (angCoeff > -1 && angCoeff < 1 && person.getX() > monster.getX()) {
                        if (checkCollision(monster.getX() + 1, monster.getY(), person.getX(), person.getY())) {
                            message();
                            attack(monster);
                        } else if (Objects.equals(getFrontTile(monster, 1, 0).getTag(), "floor")) {
                            monster.setPos(monster.getX() + 1, monster.getY());
                        }
                    } else if (angCoeff > -1 && angCoeff < 1 && person.getX() < monster.getX()) {
                        if (checkCollision(monster.getX() - 1, monster.getY(), person.getX(), person.getY())) {
                            message();
                            attack(monster);
                        } else if (Objects.equals(getFrontTile(monster, -1, 0).getTag(), "floor")) {
                            monster.setPos(monster.getX() - 1, monster.getY());
                        }
                    } else if ((angCoeff > 1 || angCoeff < -1) && person.getY() > monster.getY()) {
                        if (checkCollision(monster.getX(), monster.getY() + 1, person.getX(), person.getY())) {
                            message();
                            attack(monster);
                        } else if (Objects.equals(getFrontTile(monster, 0, 1).getTag(), "floor")) {
                            monster.setPos(monster.getX(), monster.getY() + 1);
                        }
                    } else if ((angCoeff > 1 || angCoeff < -1) && person.getY() < monster.getY()) {
                        if (checkCollision(monster.getX(), monster.getY() - 1, person.getX(), person.getY())) {
                            message();
                            attack(monster);
                        } else if (Objects.equals(getFrontTile(monster, 0, -1).getTag(), "floor")) {
                            monster.setPos(monster.getX(), monster.getY() - 1);
                        }
                    }
                }
            } else if (pickMonster instanceof ReplicationMonster monster) {
                int p = random.nextInt(100);
                final int STEP = 4;
                if (p >= 90) {
                    replicators.add(monster);
                    ReplicationMonster rm = replicators.get(replicators.size() - 1);
                    switch (random.nextInt(4)) {
                        case 0:
                            if (Objects.equals(getFrontTile(rm, 1, 0).getTag(), "floor")) {
//                                    monsters.add(monster.copy(monster.getX() + STEP, monster.getY()));
                                rm.setPos(rm.getX() + STEP, rm.getY());
                                break;
                            }
                        case 1:
                            if (Objects.equals(getFrontTile(rm, -1, 0).getTag(), "floor")) {
//                                    monsters.add(monster.copy(monster.getX() - STEP, monster.getY()));
                                rm.setPos(rm.getX() - STEP, rm.getY());
                                break;
                            }
                        case 2:
                            if (Objects.equals(getFrontTile(rm, 0, 1).getTag(), "floor")) {
//                                    monsters.add(monster.copy(monster.getX(), monster.getY() + STEP));
                                rm.setPos(rm.getX(), rm.getY() + STEP);
                                break;
                            }
                        case 3:
                            if (Objects.equals(getFrontTile(rm, 0, -1).getTag(), "floor")) {
//                                    monsters.add(monster.copy(monster.getX(), monster.getY() - STEP));
                                rm.setPos(rm.getX(), rm.getY() - STEP);
                                break;
                            }
                    }
                    replicators.set(replicators.size() - 1, rm);

                }

                if (checkCollision(monster.getX() + 1, monster.getY(), person.getX(), person.getY())) {
                    message();
                    attack(monster);
                    break;
                }
            }
        }
    }

    private static boolean stateIsAlive = true;

    /**
     * Используется stateIsAlive для вывода сообщения единожды
     *
     * @return игрок умер или нет
     */
    private static boolean isPlayerDied() {
        if (person.getHitPoints() <= 0) {
            stateIsAlive = false;
            System.out.println("You died");
            return true;
        }
        return false;
    }

    /**
     * Проверяет коллизию монстра и игрока
     *
     * @param xM - позиция монства по x
     * @param yM - позиция монства по y
     * @param xP - позиция игрока по x
     * @param yP - позиция игрока по y
     * @return коллизию
     */
    private static boolean checkCollision(int xM, int yM, int xP, int yP) {
        final int EXPAND = 8;
        return (xM >= xP - EXPAND && xM <= xP + EXPAND) && (yM >= yP - EXPAND && yM <= yP + EXPAND);
    }

    /**
     * В случае коллизии игрок и монстр атакуют друг друга
     *
     * @param m - монстр, с кем произошла коллизия
     */
    private static void attack(MonsterFactory m) {
        PersonAttack personAttack = new PersonAttack(person);

        // Персонаж атакует монстра
        if (m instanceof Monster monster) {
            personAttack.attack(monster);

            // Монстр атакует персонажа
            if (person.getDefense() - monster.getStrength() >= 0)
                person.setHitPoints(-1);
            else
                person.setHitPoints(person.getDefense() - monster.getStrength());

            if (monster.getHitPoints() <= 0) {
                person.setXP(+5);
                person.levelUp();
            }
        } else if (m instanceof ReplicationMonster monster) {
            // Монстр атакует персонажа
            if (person.getDefense() - monster.getStrength() >= 0)
                person.setHitPoints(-1);
            else
                person.setHitPoints(person.getDefense() - monster.getStrength());
        }
    }

    private static void message() {
        System.out.println("You have been attacked!");
        System.out.println(person.getHitPoints());
    }

    /**
     * Нужно для определения тега тайла
     *
     * @param entity - какой объект
     * @param x      - координата по x
     * @param y      - координата по y
     * @return тег тайла (wall, floor и тп)
     */
    private static Tile getFrontTile(Entity entity, double x, double y) {
        return currentFloor.getTileAt((entity.getX() + (int) x) / TILE_SIZE, (entity.getY() + (int) y) / TILE_SIZE);
    }

    /**
     * Используется в управлении персонажем и тп
     *
     * @return персонаж со всеми полями и инвентарём
     */
    public static Person getPerson() {
        return person;
    }

    /**
     * @return все монстры на этаже
     */
    public static ArrayList<MonsterFactory> getMonsters() {
        return monsters;
    }

    public static ArrayList<ReplicationMonster> getReplicators() {
        return replicators;
    }

    /**
     * Текущий уровень со всеми тайлами
     *
     * @return возвращает текущий уровень
     */
    public static Floor getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Запущем ли основной игровой цикл
     *
     * @return запущена ли игра с возможностью управления персонажем
     */
    public static boolean isRunning() {
        return running;
    }
}