import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Gameplay extends JFrame implements ActionListener, WindowListener
{
    //Four main choices buttons
    private ArrayList< JButton > choiceList = new ArrayList < JButton > ();

    //Visual for the question box
    private BufferedImage questionBox;

    //Player
    private String player;

    //Questions & Answers
    private ArrayList < Question > questions;
    private ArrayList < String > answers;

    //Score
    private int score;

    //Menu
    private JMenuBar mainMENU = new JMenuBar ();
    private JMenu InstructionsMENU = new JMenu ("Information");
    private JMenuItem newMENU = new JMenuItem ("New Game");

    public Gameplay ()
    {
    this.questions = QuestionReader.readQuestionsFromFile ("questions.xml");
	Question currentQuestion = questions.get ((int) (Math.random () * questions.size ()) + 1);
	answers = currentQuestion.getAnswers ();

	try
	{
	    for (int x = 0 ; x < answers.size () ; x++)
	    {
		choiceList.add (new JButton (answers.get (x)));
	    }

	}

	catch (Exception e)
	{

	}




    }

    // for ActionListener interface
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

    // Method that must be implemented because of Window Listener, does nothing
    public void windowClosing (WindowEvent e)
    {
    }

    // Method that must be implemented because of Window Listener, does nothing
    public void windowActivated (WindowEvent e)
    {
    }
}

