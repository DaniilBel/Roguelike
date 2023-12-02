package org.example.engine;

import org.example.entity.Person;

import java.awt.event.*;

public class ControlListener implements KeyListener {

    private void moveDirection(KeyEvent e, int step) {
        int key = e.getKeyCode();
        Person person = Engine.getPerson();

        if (key == KeyEvent.VK_UP) {
            person.setVelY(-step);
        }
        if (key == KeyEvent.VK_DOWN) {
            person.setVelY(step);
        }
        if (key == KeyEvent.VK_LEFT) {
            person.setVelX(-step);
        }
        if (key == KeyEvent.VK_RIGHT) {
            person.setVelX(step);
        }
    }

    /**
     * Событие на нажатие кнопки
     * <b>step</b> = 8 для более быстрого перемещения
     * @param e событие, которое необходимо обработать (нажание кнопки)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed");
        final int step = 4;
        moveDirection(e, step);
    }

    /**
     * Событие на отпускание кнопки
     * Персонаж останавливается
     * @param e событие, которое необходимо обработать (нажание кнопки)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released");
        final int step = 0;
        moveDirection(e, step);
    }

    /**
     * Событие на тык кнопки
     * Открытие инвентаря
     * @param e событие, которое необходимо обработать (нажание кнопки)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key typed");
        final int step = 4;
        moveDirection(e, step);
    }
}
