import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Gameplay extends JFrame implements ActionListener, WindowListener
{
    //Four main choices buttons    
    private JButton[] choiceList = new {new JButton ("A"), new JButton ("B"), new JButton ("C"), new JButton ("D")};

    //Visual for the question box
    private BufferedImage questionBox;

    //Player
    private String player;
    
    //Score
    private int score;


    public Gameplay ()
    {
	


    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowDeactivated (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowDeiconified (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowIconified (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowOpened (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowClosed (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowActivated (WindowEvent e)
    {
    }
}

