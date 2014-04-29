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
    private Graphics g;

    // objects for gameplay
    private ArrayList< Question > questions;
    private Gameplay currentGameplay;

    // scoring
    private int score = 1;


    public MainScreen ()
    {
    // read from file
    this.questions = Question.readQuestionsFromFile ("questions.xml");

	// SETUP GUI
	setTitle ("WWTBAM");
	setSize (908, 658);
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

	    currentGameplay = new Gameplay (currentQuestion, this);
        setContentPane(currentGameplay);
        pack();
        setSize (908, 658); //dimensions needed for the template picture of the questions/asnwers
    }


    // For ActionListener interface
    public void actionPerformed (ActionEvent e)
    {
	    if (e.getSource ().equals (quitBtn)) // quit button
        {
	        dispose();
        } else if (e.getSource().equals(startBtn)) { // start ptbutton
            nextScreen();
        } else { // question attempted
            if (currentGameplay.isCorrect()) {
                nextScreen();
                score *= 2;
                System.out.printf("Your score is %d.\n", score);
            } else {
                score = 1;
                setContentPane(mainMenu);
            }
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

