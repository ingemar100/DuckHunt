package duckhunt;

import java.awt.*;
import java.util.StringTokenizer;


public class Blinker extends java.applet.Applet implements Runnable
{
    Thread blinker = null;    // The thread that displays images
    String labelString;       // The label for the window
    int delay;                // the delay time between blinks

    public void init() {
	String blinkFrequency = "2";
	delay = 50;
	labelString = "BONUS DUCK!";
        Font font = new java.awt.Font("Ariel", Font.PLAIN, 50);
	setFont(font);
    }

    public void paint(Graphics g) {
        int fontSize = g.getFont().getSize();
	int x = 0, y = fontSize, space;
	int red =   (int)( 50 * Math.random());
	int green = (int)( 50 * Math.random());
	int blue =  (int)(256 * Math.random());
	Dimension d = getSize();
        g.setColor(Color.black);
	FontMetrics fm = g.getFontMetrics();
	space = fm.stringWidth(" ");
	for (StringTokenizer t = new StringTokenizer(labelString); t.hasMoreTokens();) {
	    String word = t.nextToken();
	    int w = fm.stringWidth(word) + space;
	    if (x + w > d.width) {
		x = 0;
		y += fontSize;
	    }
	    if (Math.random() < 0.5)
		g.setColor(new java.awt.Color((red + y*30) % 256, (green + x/3) % 256, blue));
	    else
                g.setColor(getBackground());
	    g.drawString(word, x, y);
	    x += w;
	}
    }

    public void start() {
	blinker = new Thread(this);
	blinker.start();
    }

    public void stop() {
	blinker = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
	while (blinker == me) {
            try {
                Thread.currentThread().sleep(delay);
            }
            catch (InterruptedException e) {
            }
	    repaint();
	}
    }
}
