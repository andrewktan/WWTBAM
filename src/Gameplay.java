import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Gameplay extends JPanel implements ActionListener, WindowListener
{
    //Pics
    private BufferedImage screen;
    private BufferedImage choiceBox;

    // Status
    private boolean correct;

    //Four main choices buttons
    private JLabel prompt;
    private ArrayList < JButton > choiceList = new ArrayList < JButton > ();
    private JButton sampleBTN = new JButton("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

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

	public Gameplay (Question currentQuestion, ActionListener ae) {

       //this.setPreferredSize(new Dimension(800,800));
        this.currentQuestion = currentQuestion;
        this.ae = ae;
        answers = currentQuestion.getAnswers();

        // initializing prompt
        prompt = new JLabel(currentQuestion.getPrompt());
        //prompt.setIcon(new ImageIcon("prompt_box.jpg"));
        prompt.setForeground(Color.WHITE);
        prompt.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(prompt);
        // initializing buttons

        try { //Imports image for the screen
            screen = ImageIO.read(this.getClass().getResource("question_template.jpg"));
        } catch (IOException e) {
        }

        try { //Imports image for the buttons
            choiceBox = ImageIO.read(this.getClass().getResource("choice_box.jpg"));
        } catch (IOException e) {
        }


        try {
            for (int x = 0; x < answers.size(); x++) {
                JButton choice = new JButton(answers.get(x), new ImageIcon(choiceBox));
                choice.setHorizontalTextPosition(SwingConstants.CENTER);
                choiceList.add(choice); // initialize

                choice.setBorder(BorderFactory.createEmptyBorder());
                choice.setContentAreaFilled(false);

                this.add(choice); // add to panel

                choice.addActionListener(this);
            }
        } catch (Exception e) {
        }


        this.setLayout(null);

        Insets insets = this.getInsets();
        Dimension promptSize = prompt.getPreferredSize();
        Dimension choiceSize = sampleBTN.getPreferredSize();

        prompt.setBounds(210 + insets.left, 195 + insets.top, 500, promptSize.height);

        //Top Two
        choiceList.get(0).setBounds(127 + insets.left, 313 + insets.top, 300, 300);
        choiceList.get(1).setBounds(459 + insets.left, 313 + insets.top, 300, 300);

        //Bottom Two
        choiceList.get(2).setBounds(127 + insets.left, 405 + insets.top, 300, 300);
        choiceList.get(3).setBounds(459 + insets.left, 405 + insets.top, 300, 300);

        /*choiceList.get(1).setBounds(500 + insets.left, 450 + insets.top,choiceSize.width, choiceSize.height );

        //Bottom Two
        choiceList.get(2).setBounds(170 + insets.left, 540 + insets.top, choiceSize.width, choiceSize.height);
        choiceList.get(3).setBounds(500 + insets.left, 540 + insets.top,choiceSize.width, choiceSize.height );
        */

    }

    public void paintComponent (Graphics g) {

        super.paintComponent(g); //overrides original pain component
        g.drawImage(screen,0,0,null);
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


