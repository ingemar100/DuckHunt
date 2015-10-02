package duckhunt;

import java.awt.*;
import javax.swing.*;

public class Game {

    private static final Dimension SIZE = new Dimension(600, 600);
    private static AnimationPanel panel;
    private static final int TIMER_DELAY = 20;
    private static final String GAME_NAME = "DuckDuckHunt";
    private static final double FPS = 60;
    private static UnitManager um;

    private static void createAndShowUI() {
        panel = new AnimationPanel();
        um = new UnitManager(panel);
        panel.setManager(um);
        
        JFrame frame = new JFrame(GAME_NAME);
        frame.getContentPane().add(panel);
        frame.getContentPane().setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Sound.BACKGROUND.loop();


    }

    public static void main(String[] args) {
        Thread threadForInitGame = new Thread() {
            public void run() {
                createAndShowUI();
                double t = 0.0;

                final double dt = 1 / FPS;

                double currentTime = timeInSeconds();
                double accumulator = 0.0;

                boolean running = true;

                while (running) {

                    double newTime = timeInSeconds();
                    double frameTime = newTime - currentTime;
                    currentTime = newTime;

                    accumulator += frameTime;

                    while (accumulator >= dt) {
                        update();
                        accumulator -= dt;
                        t += dt;
                    }
                    render();

                }
            }
        };
        threadForInitGame.start();
    }

    private static double timeInSeconds() {
        return (double) System.nanoTime() / 1000000000.0;
    }

    public static void update() {
        um.update();
    }

    public static void render() {
        um.render();
    }
}
