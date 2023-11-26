package org.example.engine.render;

import org.example.engine.gui.GUI;
import org.example.engine.utils.Resources;
import org.example.entity.Entity;
import org.example.entity.Person;
import org.example.map.level.Floor;
import org.example.map.level.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private int zoom;

    public Renderer() {
        this.zoom = 2;
    }

    /**
     *
     * @param g
     */
    public void renderPlayer(Entity personData, Graphics g) {
        BufferedImage sprite = Resources.getSprite("player");
        int drawPosX = (GUI.WIDTH/2) - (sprite.getWidth()/2)*zoom;
        int drawPosY = (GUI.HEIGHT/2) - (sprite.getHeight()/2)*zoom;
        g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoom, sprite.getHeight()*zoom, null);
    }

    /**
     *
     * @param floorData
     * @param person
     * @param g
     */
    public void renderLevel(Floor floorData, Entity person, Graphics g) {
        for (int y = 0; y < floorData.getSizeY(); y++) {
            for (int x = 0; x < floorData.getSizeX(); x++) {
                BufferedImage sprite = Resources.getSprite(floorData.getEntityAt(x, y).getTag());
//                int drawPosX = floorData.getEntityAt(x, y).getX()*zoom + ((Window.HEIGHT/2) - player.getX()*zoom - (sprite.getWidth()/2)*zoom);
//                int drawPosY = floorData.getEntityAt(x, y).getY()*zoom + ((Window.HEIGHT/2) - player.getY()*zoom - (sprite.getHeight()/2)*zoom);
                int drawPosX = offsetX(sprite, floorData.getEntityAt(x, y), (Person) person);
                int drawPosY = offsetY(sprite, floorData.getEntityAt(x, y), (Person) person);
                g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoom, sprite.getHeight()*zoom, null);
            }
        }
    }

    private int offsetX(BufferedImage sprite, Tile tile, Person person) {
//        int a = tile.getX()*sprite.getWidth()*zoom;
//        int b = (GUI.WIDTH/2);
//        int c = person.getX()*sprite.getWidth()*zoom;
//        int d = (sprite.getWidth()/2)*zoom;
//        int e = person.getX()*zoom;
//        return a + (b-c-d)+e;
        return tile.getX()*zoom + ((GUI.WIDTH/2) - person.getX()*zoom - (sprite.getWidth()/2)*zoom);
    }

    private int offsetY(BufferedImage sprite, Tile tile, Person person) {
//        return tile.getY()*sprite.getHeight()*zoom + ((GUI.HEIGHT/2)-person.getY()*sprite.getHeight()*zoom-(sprite.getHeight()/2)*zoom)+person.getY()*zoom;
        return tile.getY()*zoom + ((GUI.HEIGHT/2) - person.getY()*zoom - (sprite.getHeight()/2)*zoom);
    }

    /**
     * Кастомная отрисовка персонажа
     * @param entity - Объект, который присутствует на карте
     * @param g - на что отрисовываем
     */
//    public static void render(Entity entity, Graphics g, Color color) {
//        g.setColor(color);
//        g.fillOval(entity.getX(), entity.getY(), 32, 32);
//    }
}
