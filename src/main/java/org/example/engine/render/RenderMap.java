package org.example.engine.render;

import javax.swing.*;

public class RenderMap extends JPanel {
//    private final JPanel jPanel;
    private final JFrame jFrame;
    private static int x;
    private static int y;
    public RenderMap(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void render(int xPos, int yPos) {
        x = xPos;
        y = yPos;
        System.out.println("Дошло?");
        jFrame.setContentPane(this);
//        jFrame.add(new RenderPerson());
        System.out.println("Вышло");
    }

//    static class RenderPerson extends JComponent {
//        @Override
//        protected void paintComponent(Graphics g) {
//            System.out.println("Дошло");
//            Graphics2D g2 = (Graphics2D) g;
//            Ellipse2D el = new Ellipse2D.Double(10, 10, x, y);
//            g2.setBackground(Color.WHITE);
//            g2.draw(el);
//        }
//    }
}
