package duckhunt;

import javax.swing.*;

public class Game {

    private AnimationPanel panel;
    private GameMenu menuPanel;
    private final String GAME_NAME = "DuckDuckHunt";
    private final double FPS = 60;
    private int score = 0;
    private UnitManager um;

    private GameState state = GameState.GAME;

    private enum GameState {

        MENU, GAME
    };

    private void createAndShowUI() {
        panel = new AnimationPanel();
        menuPanel = new GameMenu();
        um = new UnitManager(panel, this);
        panel.setManager(um);

        JFrame frame = new JFrame(GAME_NAME);
        frame.getContentPane().add(panel);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Sound.BACKGROUND.loop();
    }

    public void gameLoop() {
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

                    if ((newTime - lastSecond) > 1000 * 1000) {
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

    private double timeInMicroseconds() {
        return System.nanoTime() / 1000.0;
    }

    public void update(double dt) {
//        System.out.println("update");
        if (state == GameState.GAME) {
            um.update(dt);
        }
    }

    public void render() {
//        System.out.println("render");

        if (state == GameState.MENU) {
            menuPanel.render();
        } else if (state == GameState.GAME) {
            um.render();
        }
    }

    public void addScore(int amount) {
        score += amount;
        panel.setScore(score);
    }
}
