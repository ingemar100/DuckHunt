package duckhunt;

import java.awt.*;
import javax.swing.*;

public class Game {

    private static final Dimension SIZE = new Dimension(800, 800);
    private static AnimationPanel panel;
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
                double t = 0;

                final double dt = 1000000 / FPS;

                double currentTime = timeInMicroseconds();
                double accumulator = 0;

                boolean running = true;
                int upsCount = 0;
                int fpsCount = 0;
                double lastSecond = 0;
                
                while (running) {

                    double newTime = timeInMicroseconds();
                    double renderFrameTime = newTime - currentTime;
                    currentTime = newTime;

                    if ((newTime - lastSecond) > 1000 * 1000){
                        lastSecond = newTime;
                        System.out.println(upsCount);
                        System.out.println(fpsCount);
                        upsCount = 0;
                        fpsCount = 0;
                    }
                    
                    accumulator += renderFrameTime;

                    while (accumulator >= dt) {
                        update(dt);
                        accumulator -= dt;
                        t += dt;
                        upsCount++;
                        
                    }
                    render();
                    fpsCount++;
                    
                }
            }
        };
        threadForInitGame.start();
    }

    private static double timeInMicroseconds() {
        return System.nanoTime() / 1000.0;
    }

    public static void update(double dt) {
//        System.out.println("update");
        um.update(dt);
    }

    public static void render() {
//        System.out.println("render");
        um.render();
    }
}
