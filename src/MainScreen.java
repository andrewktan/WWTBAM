import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

public class MainScreen extends JFrame implements ActionListener, WindowListener, WindowFocusListener
{
    // objects for GUI
    private BufferedImage titlePage;
    private JPanel mainMenu = new JPanel();
    private JButton startBtn, instructionsBtn, quitBtn;

    // objects for gameplay
    private ArrayList< Question > questions;

    public MainScreen ()
    {
    // read from file
    this.questions = QuestionReader.readQuestionsFromFile ("questions.xml");

	// SETUP GUI
	setTitle ("WWTBAM");
	setSize (800, 450);
	setDefaultLookAndFeelDecorated (true);
    setResizable(false);
	setLocationRelativeTo (null);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	// create buttons
	startBtn = new JButton ("Start");
	instructionsBtn = new JButton ("Instructions");
	quitBtn = new JButton ("Quit");
	// add ActionListeners
	quitBtn.addActionListener (this);
    startBtn.addActionListener(this);

	// populate JPanel
    mainMenu.add (startBtn);
    mainMenu.add (instructionsBtn);
    mainMenu.add(quitBtn);

	this.setContentPane (mainMenu);
    }


    public void nextScreen ()
    {
        // if there are no more questions, end game
        if (questions.size() == 0) {
            dispose();
            return;
        }

        // randomly select question
	    Question currentQuestion = questions.remove((int) (Math.random() * questions.size()));

	    Gameplay next = new Gameplay (currentQuestion, this);
        this.setContentPane(next);
        pack();
        setSize (800, 450);
    }


    // For ActionListener interface
    public void actionPerformed (ActionEvent e)
    {
	    if (e.getSource ().equals (quitBtn))
        {
	        dispose ();
        } else if (e.getSource().equals(startBtn)) {
            nextScreen();
        } else {
            nextScreen();
        }

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

