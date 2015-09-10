package duckhunt;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Selman
 */
public class Sound {

    public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class.getResource("Background_Music.wav"));
    public static final AudioClip OFFSCREEN = Applet.newAudioClip(Sound.class.getResource("Birds_Flying_Away.wav"));
    public static final AudioClip SHOT = Applet.newAudioClip(Sound.class.getResource("bomb-03.wav"));
    public static final AudioClip COCK = Applet.newAudioClip(Sound.class.getResource("cockgun-02.wav"));

}
