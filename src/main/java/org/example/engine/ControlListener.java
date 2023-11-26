package org.example.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlListener extends KeyAdapter implements ActionListener {

    /**
     * Событие на нажатие кнопки
     * <b>step</b> = 5 для более быстрого перемещения
     * @param e событие, которое необходимо обработать (нажание кнопки)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed");
        final int step = 16;
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

    /**
     * Без этого метода перемещение не работает
     * @param e the event to be processed
     */
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
