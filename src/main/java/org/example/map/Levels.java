package org.example.map;

import org.example.entity.monster.Monster;
import org.example.entity.monster.MonsterFactory;
import org.example.entity.monster.Monsters;
import org.example.entity.monster.ReplicationMonster;
import org.example.map.level.Floor;

import java.io.*;
import java.util.*;

public class Levels {

    private String path = "";
    private int x, y;
    private int startPosX, startPosY;
    private Queue<Floor> levels;

    private Levels(){
        x = 10;
        y = 10;
    }

    /**
     * Строит уровень в зависимости от того, передан ли путь к папке или нет
     */
    private void buildLevel() {
        levels = new LinkedList<>();

        if (path.isEmpty()) {
            levels.add(new Floor(new String[] {
                    "###########",
                    "#....#....#",
                    "#....#....#",
                    "##..##....#",
                    "#....#....#",
                    "#.........#",
                    "#.........#",
                    "#....#....#",
                    "#######d###"
            }, new ArrayList<>(Arrays.asList(
                    new Monsters().createMonster(Monster.Type.GHOST, 3, 2),
                    new Monsters().createMonster(Monster.Type.RAT, 2, 6)
            ))));

            levels.add(new Floor(new String[] {
                    "###########",
                    "#....#....#",
                    "#....#....#",
                    "#....##..##",
                    "#....#....#",
                    "#.........#",
                    "#.........#",
                    "#....#....#",
                    "###########"
            }, new ArrayList<>(List.of(
                    new Monsters().createMonster(Monster.Type.GHOST, 3, 2)
            ))));

        } else {
            // Строятся уровни из папки
            File folder = new File(path);

            for (File file : Objects.requireNonNull(folder.listFiles())) {
                // Читаем файл построчно
                ArrayList<String> lines = new ArrayList<>();

                // Монстры на уровне
                ArrayList<MonsterFactory> monsters = new ArrayList<>();

                // Уровень
                ArrayList<String> floor = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }

                    // Разделяем уровень и монстров
                    for (String str : lines) {
                        if (str.charAt(0) == '#' || str.charAt(0) == '.' || str.charAt(0) == 'd') {
                            floor.add(str);
                        } else {
                            String[] tmp = str.split(" ");
                            if (tmp[0].equals("GHOST")) {
                                monsters.add(new Monsters().createMonster(Monster.Type.GHOST,
                                        Integer.parseInt(tmp[1]),
                                        Integer.parseInt(tmp[2])
                                ));
                            }
                            if (tmp[0].equals("RAT")) {
                                monsters.add(new Monsters().createMonster(Monster.Type.RAT,
                                        Integer.parseInt(tmp[1]),
                                        Integer.parseInt(tmp[2])
                                ));
                            }
                        }
                    }

                    // Добавляем уровень в очередь, чтобы при переходе на следующий уровень удалять текущий
                    levels.add(new Floor(floor.toArray(new String[0]), monsters));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Игрок не может быть без начальной позиции
     * @return
     */
    private boolean check() {
        return startPosX > 0 && startPosY > 0;
    }

    public static Builder builder() {
        return new Levels().new Builder();
    }

    /**
     * Переход не следующий уровень
     * @return
     */
    public Floor nextLevel() {
        return levels.poll();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setPath(String path) {
            Levels.this.path = path;

            return this;
        }

        public Builder setSize(int x, int y) {
            Levels.this.x = x;
            Levels.this.y = y;

            return this;
        }

        public Builder setStartPos(int x, int y) {
            Levels.this.startPosX = x;
            Levels.this.startPosY = y;

            return this;
        }

        public Levels build() {
            if (check()) {
                buildLevel();
                return Levels.this;
            }
            System.err.println("Enter start position");
            return null;
        }
    }
}
