/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import duckhunt.Boundary.Input;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Engine;
import java.applet.AudioClip;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Ingemar
 */
abstract public class BaseLevelState {

    protected int score = 0;
    protected Engine game;

    public BaseLevelState() {
        this.game = Engine.getInstance();
        getMusic().loop();
    }

    abstract public void move();

    abstract public void collide(List<ShootInput> inputs);

    abstract public void update(double dt);

    abstract public void render();

    abstract protected AudioClip getMusic();

    protected void changeLevel() {
        getMusic().stop();

        BaseLevelState nextLevel = LevelFactory.nextLevel(this);
        game.setLevel(nextLevel);
    }

    abstract public JPanel getPanel();

    public void addScore(int addedScore) {
        this.score += addedScore;
    }
}
