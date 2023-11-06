package org.example.engine;

import org.example.gamelogic.Map;
import org.example.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Engine {

    private final int framesPerSecond = 60;
    private final int timePerLoop = 1_000_000_000/framesPerSecond;

    private static JPanel jPanel;
    private static JFrame jFrame;

    private Map map;
    private Person person;
    private org.example.engine.render.RenderMap renderMap;

    public static void initWindow(JFrame jFrame) {
        Engine.jFrame = jFrame;
        JButton startBtn = new JButton("Start");
        jPanel = new JPanel();
        jFrame.add(jPanel);
        jPanel.add(startBtn);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //jFrame.add(new RenderMap());
                jPanel.remove(startBtn);
                jPanel.setBackground(Color.BLACK);
                Engine engine = new Engine();
                engine.start();
            }
        });

    }

    public void start() {

        person = new Person(100, 10, 10);
        person.move(jPanel, 0, 0);

        renderMap = new org.example.engine.render.RenderMap(jFrame);
        System.out.println("render entity");
        renderMap.render(50, 50);

//        while (true) {
//            long startTime = System.nanoTime();
//
//            long endTime = System.nanoTime();
//
//            long sleepTime = timePerLoop - (endTime - startTime);
//
//            if (sleepTime > 0) {
//                try {
//                    Thread.sleep(sleepTime/1_000_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    static class RenderMap extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
//            g2.setBackground(Color.BLACK);
//            Line2D l2 = new Line2D.Double(70, 70, 90, 90);
//            g2.draw(l2);
        }
    }
}
