package org.example.map.level;

import org.example.engine.Tile;
import org.example.entity.monster.Monster;
import org.example.entity.monster.MonsterFactory;
import org.example.entity.monster.Monsters;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final Tile[][] floor;
    private final ArrayList<MonsterFactory> monsters;

    /**
     * Создает уровень на основе поданной сетки. Потом передается в Render для отрисовки
     * @param levelData - сетка с условными обозначениями на карте
     */
    public Floor(String[] levelData, ArrayList<MonsterFactory> monsters) {
        floor = new Tile[levelData.length][];

        for (int y = 0; y < levelData.length; y++) {
            floor[y] = new Tile[levelData[y].length()];

            for (int x = 0; x < levelData[y].length(); x++) {
                switch (levelData[y].charAt(x)) {
                    case '#':
                        floor[y][x] = new Tile("wall", x, y);
                        break;
                    case '.':
                        floor[y][x] = new Tile("floor", x, y);
                        break;
                    case 'd':
                        floor[y][x] = new Tile("wall_hole", x, y);
                        break;
                }
            }
        }

        this.monsters = monsters;
//        Collections.addAll(this.monsters, monsters);
    }

    public ArrayList<MonsterFactory> getMonsters() {
//        MonsterFactory[] other = new MonsterFactory[monsters.size()];
//        return monsters.toArray(other);
        return monsters;
    }

    public List<MonsterFactory> getMonstersList() {
        return monsters;
    }

    public MonsterFactory getMonsterAt(int x, int y) {
        for (MonsterFactory m : monsters) {
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

    public boolean monsterIsHere(int x, int y) {
        for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getX() == x && monsters.get(i).getY() == y)
                return true;

        return false;
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
