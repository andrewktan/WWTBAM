import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Gameplay extends JPanel implements ActionListener, WindowListener
{
    //Four main choices buttons
    private JLabel prompt;
    private ArrayList < JButton > choiceList = new ArrayList < JButton > ();

    //Visual for the question box
    private BufferedImage questionBox;

    //Player
    private String player;

    //Questions & Answers
    private ArrayList < Question > questions;
    private ArrayList < String > answers;
    private Question currentQuestion;

    //Score
    private int score;

    //Menu
    private JMenuBar menuBar = new JMenuBar ();
    private JMenu GameMENU = new JMenu ("Game");
    private JMenuItem newGameITEM = new JMenuItem ("New Game");
    private JMenuBar mainMENU = new JMenuBar ();
    private JMenu InstructionsMENU = new JMenu ("Information");
    private JMenuItem newMENU = new JMenuItem ("New Game");
    private ActionListener ae;

	public Gameplay (Question currentQuestion, ActionListener ae)
	{
    this.currentQuestion = currentQuestion;
    this.ae = ae;
    answers = currentQuestion.getAnswers();

    // initializing prompt
    prompt = new JLabel(currentQuestion.getPrompt());
    this.add(prompt);
    // initializing buttons
    try
    {
    	for (int x = 0 ; x < answers.size () ; x++)
	    {
	        choiceList.add (new JButton (answers.get(x))); // initialize
            this.add(choiceList.get(x)); // add to panel
            choiceList.get(x).addActionListener(this);
            choiceList.get(x).addActionListener(ae);
	    }
    }
    catch (Exception e)
    {
    }

	}

	// for ActionListener interface
	public void actionPerformed (ActionEvent e)
	{
        if (choiceList.indexOf(e.getSource()) == this.currentQuestion.getCorrect())
            System.out.println("CORRECT");
        else
            System.out.println("WRONG");
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
	public void windowClosing (WindowEvent e)
	{
	}

	// Method that must be implemented because of Window Listener, does nothing
	public void windowActivated (WindowEvent e)
	{
	}
    }


