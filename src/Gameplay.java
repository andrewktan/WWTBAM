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
    private JButton oneBtn = new JButton ("A");
    private JButton twoBtn = new JButton ("B");
    private JButton threeBtn = new JButton ("C");
    private JButton fourBtn = new JButton ("D");
    
    //Visual for the question box
    private BufferedImage questionBox;
    
    
}
