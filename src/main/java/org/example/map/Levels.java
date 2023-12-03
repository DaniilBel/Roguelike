package org.example.map;

import org.example.entity.Monster;
import org.example.map.level.Floor;

public class Levels {
    public static final Floor LEVEL_1 = new Floor(new String[] {
            "###########",
            "#....#....#",
            "#....#....#",
            "##..##....#",
            "#....#....#",
            "#.........#",
            "#.........#",
            "#....#....#",
            "###########"
    }, 2, 1,
            new Monster(Monster.Type.GHOST, 3, 2),
            new Monster(Monster.Type.RAT, 2, 6));
}
