import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;git o
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowFocusListener;

public class MainScreen extends JFrame implements ActionListener, WindowListener, WindowFocusListener
{
    private BufferedImage titlePage;
    private JButton startBtn = new JButton ("Start");
    private JButton instructionsBtn = new JButton ("Instructions");
    private JButton quitBtn = new JButton ("Quit");

    public MainScreen ()
    {
	setTitle ("WWTBAM");
	setSize (800, 450); // default size is 0,0
	setDefaultLookAndFeelDecorated (true);
	setLocationRelativeTo (null);

	JPanel p = new JPanel ();
	// populate JFrame

	p.add (startBtn);
	p.add (instructionsBtn);
	p.add (quitBtn);

	this.setContentPane (p);
    }


    // For ActionListener interface
    public void actionPerformed (ActionEvent e)
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


    // For WindowListener interface
    public void windowClosing (WindowEvent e)
    {
    }


    // Method that must be implemented because of Window Listener, does nothing
    public void windowActivated (WindowEvent e)
    {
    }


    // For WindowFocusListener interface
    public void windowGainedFocus (WindowEvent e)
    {
    }


    // For WindowFocusListener interface
    public void windowLostFocus (WindowEvent e)
    {
    }
    
    public void main (String[] args)
    {
	
    }
}




