package org.example.map.level;

import org.example.engine.Tile;
import org.example.entity.Monster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Floor {
    private final int TILE_SIZE = 32;
    private Tile[][] floor;
    private List<Monster> monsters;

    /**
     * Создает уровень на основе поданной сетки. Потом передается в Render для отрисовки
     * @param levelData - сетка с условными обознчениями на карте
     */
    public Floor(String[] levelData, Monster... monsters) {
        floor = new Tile[levelData.length][];

        for (int y = 0; y < levelData.length; y++) {
            floor[y] = new Tile[levelData[y].length()];

            for (int x = 0; x < levelData[y].length(); x++) {
                switch (levelData[y].charAt(x)) {
                    case '#':
                        floor[y][x] = new Tile("wall", x*TILE_SIZE, y*TILE_SIZE);
                        break;
                    case '.':
                        floor[y][x] = new Tile("floor", x*TILE_SIZE, y*TILE_SIZE);
                        break;
                }
            }
        }

        this.monsters = new ArrayList<>();
        Collections.addAll(this.monsters, monsters);
    }

    public Monster[] getMonsters() {
        Monster[] other = new Monster[monsters.size()];
        return monsters.toArray(other);
    }

    public List<Monster> getMonstersList() {
        return monsters;
    }

    public Monster getMonsterAt(int x, int y) {
        for (Monster m : monsters) {
            if (m == null)
                return null;

            if (m.getX() == x && m.getY() == y)
                return m;
        }
        return null;
    }

    public int getSizeX() {
        return floor[0].length;
    }

    public int getSizeY() {
        return floor.length;
    }

    /**
     * Берет конкретный Tile с его тэгом и координатами
     * @param x - координата по x
     * @param y - координата по y
     * @return - возвращает Tile на позиции x, y
     */
    public Tile getTileAt(int x, int y) {
        try {
            return floor[y][x];
        } catch (Exception ignored) {

        }
        assert floor != null;
        return floor[0][0];
    }
}
