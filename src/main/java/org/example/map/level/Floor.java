package org.example.map.level;

import org.example.engine.Tile;

public class Floor {
    private Tile[][] floor;

    public Floor(String[] levelData) {
        floor = new Tile[levelData.length][];

        for (int y = 0; y < levelData.length; y++) {
            floor[y] = new Tile[levelData[y].length()];

            for (int x = 0; x < levelData[y].length(); x++) {
                switch (levelData[y].charAt(x)) {
                    case '#':
                        floor[y][x] = new Tile("wall", x*32, y*32);
                        break;
                    case '.':
                        floor[y][x] = new Tile("floor", x*32, y*32);
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

    public Tile getEntityAt(int x, int y) {
        return floor[y][x];
    }
}
