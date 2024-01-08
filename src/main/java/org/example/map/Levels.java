package org.example.map;

import org.example.entity.Monster;
import org.example.map.level.Floor;

import java.io.*;
import java.util.*;

public class Levels {

    private String path = "";
    private int x, y;
    private int startPosX, startPosY;

    private Floor level;
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
            level = new Floor(new String[] {
                    "###########",
                    "#....#....#",
                    "#....#....d",
                    "##..##....#",
                    "#....#....#",
                    "#.........#",
                    "#.........#",
                    "#....#....#",
                    "###########"
            }, 2, 1,
                    new Monster(Monster.Type.GHOST, 3, 2),
                    new Monster(Monster.Type.RAT, 2, 6));
        } else {
            // Строятся уровни из папки
            File folder = new File(path);

            for (File file : Objects.requireNonNull(folder.listFiles())) {
                // Читаем файл построчно
                ArrayList<String> lines = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }

                    // Конвертируем ArrayList в String[]
                    String[] floor = lines.toArray(new String[0]);

                    // Добавляем уровень в очередь, чтобы при переходе не следующий удалять текущий
                    levels.add(new Floor(floor, startPosX, startPosY,
                            new Monster(Monster.Type.GHOST, 3, 2),
                            new Monster(Monster.Type.RAT, 2, 6)));

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

    public static final Floor LEVEL_1 = new Floor(new String[] {
            "###########",
            "#....#....#",
            "#....#....d",
            "##..##....#",
            "#....#....#",
            "#.........#",
            "#.........#",
            "#....#....#",
            "###########"
    }, 2, 1,
            new Monster(Monster.Type.GHOST, 3, 2),
            new Monster(Monster.Type.RAT, 2, 6));

    public static final Floor LEVEL_2 = new Floor(new String[] {
            "###########",
            "#....#....#",
            "#....#....#",
            "#....##..##",
            "#....#....#",
            "#.........#",
            "#.........#",
            "#....#....#",
            "###########"
    }, 2, 1,
            new Monster(Monster.Type.GHOST, 3, 2));


}
