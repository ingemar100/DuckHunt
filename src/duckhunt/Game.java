package duckhunt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game {

    private static final Dimension SIZE = new Dimension(600, 600);
    private static AnimationPanel panel = new AnimationPanel();
    private static final int TIMER_DELAY = 20;
    private static final String GAME_NAME = "DuckDuckHunt";
    private static final int FPS = 60;
    private static DuckManager dm;

    private static void createAndShowUI() {
        JFrame frame = new JFrame(GAME_NAME);
        frame.getContentPane().add(panel);
        frame.getContentPane().setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Sound.BACKGROUND.loop();

        dm = new DuckManager(panel);

    }

    public static void main(String[] args) {
        int dt = 1 / FPS;
        Thread threadForInitGame = new Thread() {
            public void run() {
                createAndShowUI();
                while (true) {
//                    processInput();
                    update();
                    render();

                    try {
                        Thread.sleep(TIMER_DELAY);
                    } catch (Exception e) {

                    }
                }
            }
        };
        threadForInitGame.start();
    }

    public static void update() {
        dm.update();
    }

    public static void render() {
        panel.repaint();
        dm.render();
    }
}
