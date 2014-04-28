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
    // Status
    private boolean correct;

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
            JButton choice = new JButton(answers.get(x));
	        choiceList.add (choice); // initialize
            this.add(choice); // add to panel
            choice.addActionListener(this);
	    }
    }
    catch (Exception e)
    {
    }

	}

    public boolean isCorrect() {
        return correct;
    }

	// for ActionListener interface
	public void actionPerformed (ActionEvent e)
	{
        // check if correct
        if (choiceList.indexOf(e.getSource()) == this.currentQuestion.getCorrect())
            correct = true;
        else
            correct = false;

        // trigger MainScreen's ActionListener
        ae.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
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


