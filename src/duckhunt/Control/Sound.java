package duckhunt.Control;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Selman
 */
public class Sound {

    public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/Background_Music.wav"));
    public static final AudioClip OFFSCREEN = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/Birds_Flying_Away.wav"));
    public static final AudioClip SHOT = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/bomb-03.wav"));
    public static final AudioClip COCK = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/cockgun-02.wav"));
    public static final AudioClip BONUS = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/BonusSound.wav"));
    public static final AudioClip MAINMENU = Applet.newAudioClip(Sound.class.getResource("../SoundTrack/DuckHuntMainMenu.wav"));

}