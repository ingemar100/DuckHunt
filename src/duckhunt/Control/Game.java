package duckhunt.Control;

import duckhunt.Model.LevelFactory;
import duckhunt.Model.Level1;
import duckhunt.Boundary.Input;
import duckhunt.Boundary.InputContainer;
import duckhunt.Model.Menu;
import duckhunt.Model.BaseLevelState;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;

public class Game {

    private JFrame frame;
    private final String GAME_NAME = "DuckDuckHunt";
    private final double FPS = 60;
    private InputContainer inputCont;
    private static Game instance;

    private BaseLevelState currentLevel;

    private Game() {

        frame = new JFrame(GAME_NAME);
        inputCont = InputContainer.getInstance();
    }

    public void gameLoop() {
        Thread threadForInitGame = new Thread() {
            public void run() {
                setLevel(LevelFactory.nextLevel(null));
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
//                        System.out.println(upsCount);
//                        System.out.println(fpsCount);
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

    private LinkedList<Input> readInput() {
//        ArrayList<Input> shots = new ArrayList<>();
        LinkedList<Input> inputs = inputCont.getInputs();

//        Iterator<Input> it = inputs.iterator();
//        while (it.hasNext()) {
//            Input i = it.next();
//            shots.add(i);
//        }
        return inputs;
    }

    public void update(double dt) {
        LinkedList shots = readInput();
        currentLevel.collide(shots);
        currentLevel.move();
        currentLevel.update(dt);
    }

    public void render() {
        currentLevel.render();
    }

    private void createAndShowUI() {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(getActivePanel());
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setUndecorated(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private JPanel getActivePanel() {
        return currentLevel.getPanel();
    }

    public void setLevel(BaseLevelState level) {
        currentLevel = level;
        createAndShowUI();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
