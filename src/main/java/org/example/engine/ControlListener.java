package org.example.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlListener extends KeyAdapter implements ActionListener {

    @Override
    public void keyPressed(KeyEvent e) {
        final int step = 5;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Engine.movePerson(0, -step);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Engine.movePerson(0, step);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Engine.movePerson(-step, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Engine.movePerson(step, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Key pressed: " + e.getActionCommand());
//
//        if (Control.isKeyDown(KeyEvent.VK_UP) || Control.isKeyDown(KeyEvent.VK_W))
//            Engine.move(0, -1);
//
//        if (Control.isKeyDown(KeyEvent.VK_DOWN) || Control.isKeyDown(KeyEvent.VK_S))
//            Engine.move(0, 1);
//
//        if (Control.isKeyDown(KeyEvent.VK_LEFT) || Control.isKeyDown(KeyEvent.VK_A))
//            Engine.move(-1, 0);
//
//        if (Control.isKeyDown(KeyEvent.VK_RIGHT) || Control.isKeyDown(KeyEvent.VK_D))
//            Engine.move(1, 0);
    }
}
