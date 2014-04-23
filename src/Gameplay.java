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
    ArrayList < JButton > choiceList = new ArrayList < JButton > ();

    //Visual for the question box
    private BufferedImage questionBox;

    //Player
    private String player;

    //Questions & Answers
    ArrayList < Question > questions;
    ArrayList < String > answers;

    //Score
    private int score;

    //Menu
    private JMenuBar mainMENU = new JMenuBar ();
    private JMenu InstructionsMENU = new JMenu ("Information");
    private JMenuItem newMENU = new JMenuItem ("New Game");

    this.questions = QuestionReader.readQuestionsFromFile ("questions.xml");

    public Gameplay ()
    {

	Question currentQuestion = questions.get ((int) (Math.random () * questions.size ()) + 1));
	answers = currentQuestion.getAnswers ();

	try
	{
	    for (int x = 0 ; x < answers.size () ; x++)
	    {
		choiceList.add (new JButton (answers.get (x)));
	    }

	}

	catch (IOException e)
	{

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


    // Method that must be implemented because of Window Listener, does nothing
    public void windowActivated (WindowEvent e)
    {
    }
}

