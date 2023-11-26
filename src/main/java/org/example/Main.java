package org.example;
import org.example.engine.Engine;
import org.example.engine.gui.GUI;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Main: Init Starting");

            GUI.createWindow();
            Engine.start();

            System.out.println("Main: Init Started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}