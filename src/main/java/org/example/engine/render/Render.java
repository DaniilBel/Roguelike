package org.example.engine.render;

import org.example.engine.gui.GUI;
import org.example.engine.utils.Resources;
import org.example.entity.Entity;
import org.example.entity.Monster;
import org.example.entity.Person;
import org.example.map.level.Floor;
import org.example.engine.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    private int zoom;

    public Render() {
        this.zoom = 2;
    }

    /**
     * Персонаж помещается на центр карты
     * @param g - для отрисовки контекста
     */
    public void renderPlayer(Entity personData, Graphics g) {
        BufferedImage sprite = Resources.getSprite("player");
        int drawPosX = ((GUI.WIDTH * GUI.SCALE)/2) - (sprite.getWidth()/2)*zoom;
        int drawPosY = ((GUI.HEIGHT * GUI.SCALE)/2) - (sprite.getHeight()/2)*zoom;
        g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoom, sprite.getHeight()*zoom, null);
    }

    /**
     * На вход подается сетка с условными обознчениями на карте
     * По ней строится местность со стенами, полом и тп
     * @param floorData - сетка с условными обозначениями
     * @param person - расположение игрока. Относительно него двигается камера
     * @param g - для отрисовки контекста
     */
    public void renderLevel(Floor floorData, Person person, Graphics g) {
        for (int y = 0; y < floorData.getSizeY(); y++) {
            for (int x = 0; x < floorData.getSizeX(); x++) {
                BufferedImage sprite = Resources.getSprite(floorData.getTileAt(x, y).getTag());
//                int drawPosX = floorData.getEntityAt(x, y).getX()*zoom + ((Window.HEIGHT/2) - player.getX()*zoom - (sprite.getWidth()/2)*zoom);
//                int drawPosY = floorData.getEntityAt(x, y).getY()*zoom + ((Window.HEIGHT/2) - player.getY()*zoom - (sprite.getHeight()/2)*zoom);
                int drawPosX = offsetX(sprite, floorData.getTileAt(x, y), person);
                int drawPosY = offsetY(sprite, floorData.getTileAt(x, y), person);
                g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoom, sprite.getHeight()*zoom, null);
            }
        }
    }

    public void renderMonsters(Monster[] monsters, Person person, Graphics g) {
        if (monsters == null) return;

        for (Monster m : monsters) {
            BufferedImage sprite = Resources.getSprite(m.getTag());
            int drawPosX = offsetX(sprite, m, person) - m.getMotionOffsetX()*zoom;
            int drawPosY = offsetY(sprite, m, person) - m.getMotionOffsetY()*zoom;
            if (m.getHitPoints() > 0)
                g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoom, sprite.getHeight()*zoom, null);
        }
    }

    private int offsetX(BufferedImage sprite, Tile tile, Person person) {
//        int a = tile.getX()*sprite.getWidth()*zoom;
//        int b = (GUI.WIDTH/2);
//        int c = person.getX()*sprite.getWidth()*zoom;
//        int d = (sprite.getWidth()/2)*zoom;
//        int e = person.getMotionBoundaryX()*zoom;
//        return a + (b-c-d)+e;
        return tile.getX()*zoom + (((GUI.WIDTH * GUI.SCALE)/2) - person.getX()*zoom - (sprite.getWidth()/2)*zoom);
    }

    private int offsetY(BufferedImage sprite, Tile tile, Person person) {
//        return tile.getY()*sprite.getHeight()*zoom + ((GUI.HEIGHT/2)-person.getY()*sprite.getHeight()*zoom-(sprite.getHeight()/2)*zoom)+person.getMotionBoundaryY()*zoom;
        return tile.getY()*zoom + (((GUI.HEIGHT * GUI.SCALE)/2) - person.getY()*zoom - (sprite.getHeight()/2)*zoom);
    }
}
