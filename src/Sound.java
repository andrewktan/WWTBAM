import java.io.*;

import javax.sound.sampled.*;

public class Sound {
    Clip clip;
    AudioInputStream audio;

    public Sound(String name)
    {
        try {
            clip = AudioSystem.getClip();
            audio = AudioSystem.getAudioInputStream(new File(name));
            clip.open(audio);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void start()
    {
        clip.start(); //start the sound
    }

    public void stop() {
        clip.stop(); //stops the sound
    }
    public void loop(int x)
    {
        clip.loop(x);
    }


}


/*********I have found this sound class to work only with the .wav file format*****************/

//from another class you can call this sound class by doing the following
/*public class w ..... {
        Sound sound1;
        sound1 = new Sound("Nebula Gray.wav");
        sound1.start(); //starts the sound
        sound1.stop(); // stops the sound

        }
        */