/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ingemar
 */

class ParticleEngine {

    ArrayList<Particle> particles = new ArrayList();
    int x, y, maxParticles;

    public ParticleEngine(int maxParticles, int x, int y) {
        this.x = x;
        this.y = y;
        this.maxParticles = maxParticles;
        for (int i = 0; i < maxParticles; i++) {
            particles.add(createParticle());
        }
    }

    public void refresh(Graphics g) {
        g.clipRect(0, 0, Window.WIDTH, Window.HEIGHT);
        for (int i = 0; i < maxParticles; i++) {
            if (particles.get(i).isDead()) {
                particles.remove(i);
                particles.add(i, createParticle());
            }
            else {
                particles.get(i).move();
                particles.get(i).show(g);
            }
        }
    }

    private Particle createParticle() {
        Random r = new Random();
        
        int partX = x + r.nextInt(6) - 3;
        int partY = y + r.nextInt(6) - 3;
        int xVel = r.nextInt(10) - 5;
        int yVel = r.nextInt(10) - 5;
        int life = 500 + r.nextInt(1000);
        Particle p = new Particle(partX, partY, xVel, yVel, life,
                Color.RED);

        return p;
    }
}
