package org.example.map.level;

import org.example.engine.Tile;

public class Floor {
    private final int TILE_SIZE = 32;
    private Tile[][] floor;

    /**
     * Создает уровень на основе поданной сетки. Потом передается в Render для отрисовки
     * @param levelData - сетка с условными обознчениями на карте
     */
    public Floor(String[] levelData) {
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
