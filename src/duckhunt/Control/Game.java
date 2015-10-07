package duckhunt.Control;

import duckhunt.Boundary.AnimationPanel;
import duckhunt.Boundary.Input;
import duckhunt.Boundary.InputContainer;
import duckhunt.GameMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;

public class Game {

    private AnimationPanel gamePanel;
    private GameMenu menuPanel;
    private final String GAME_NAME = "DuckDuckHunt";
    private final double FPS = 60;
    private int score = 0;
    private UnitManager unitManager;
    private InputContainer inputCont = new InputContainer();

    private GameState state = GameState.GAME;

    private enum GameState {
        MENU, GAME
    };

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
//                        System.out.println(upsCount);
//                        System.out.println(fpsCount);
                        upsCount = 0;
                        fpsCount = 0;
                    }

                    accumulator += renderFrameTime;

                    while (accumulator >= dt) {
                        ArrayList shots = readInput();
                        unitManager.collide(shots);
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

    private ArrayList<Input> readInput() {
        ArrayList<Input> shots = new ArrayList<>();
        LinkedList<Input> inputs = inputCont.getInputs();
        
        Iterator<Input> it = inputs.iterator();
        while(it.hasNext()){
            Input i = it.next();
            shots.add(i);
        }
        
        return shots;
    }

    public void update(double dt) {
        if (state == GameState.GAME) {
            unitManager.move();
            unitManager.update(dt);
        }
    }

    public void render() {
        if (state == GameState.MENU) {
            menuPanel.render();
        } else if (state == GameState.GAME) {
            unitManager.render();
        }
    }

    public void addScore(int amount) {
        score += amount;
        gamePanel.setScore(score);
    }
    
    private void createAndShowUI() {
        gamePanel = new AnimationPanel(inputCont);
        menuPanel = new GameMenu(inputCont);

        JFrame frame = new JFrame(GAME_NAME);
        
        frame.getContentPane().add(getActivePanel());
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
               
        unitManager = new UnitManager(gamePanel, this);
        gamePanel.setManager(unitManager);
    }
    
    private JPanel getActivePanel(){
        if (state == GameState.GAME){
            Sound.BACKGROUND.loop();
            return gamePanel;
        }
        else if (state == GameState.MENU){
            Sound.MAINMENU.loop();
            return menuPanel;
        }
        return null;
    }
}
