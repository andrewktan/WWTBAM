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


    private ActionListener ae;

	public Gameplay (Question currentQuestion, ActionListener ae) {

       //this.setPreferredSize(new Dimension(800,800));
        this.currentQuestion = currentQuestion;
        this.ae = ae;
        answers = currentQuestion.getAnswers();

        // initializing prompt
        prompt = new JLabel(currentQuestion.getPrompt());
        prompt.setForeground(Color.WHITE);
        prompt.setHorizontalAlignment(SwingConstants.CENTER);

        prompt.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        this.add(prompt);

        try { //Imports image for the screen
            screen = ImageIO.read(this.getClass().getResource("images/question_template.jpg"));
            choiceBox = ImageIO.read(this.getClass().getResource("images/choice_box.jpg"));
        } catch (IOException e) {
        }

        // initializing buttons

        try {
            for (int x = 0; x < answers.size(); x++) {
                JButton choice = new JButton(answers.get(x), new ImageIcon(choiceBox));
                choice.setHorizontalTextPosition(SwingConstants.CENTER);
                choiceList.add(choice); // initialize

                choice.setBorder(BorderFactory.createEmptyBorder());
                choice.setContentAreaFilled(false);
                choice.setForeground(Color.YELLOW);

                this.add(choice); // add to panel

                choice.addActionListener(this);
            }
        } catch (Exception e) {
        }


        this.setLayout(null);

        Insets insets = this.getInsets();
        Dimension promptSize = prompt.getPreferredSize();

        prompt.setBounds(110 + insets.left, 195 + insets.top, 680, 2*promptSize.height); //Prompt

        //Top Two
        choiceList.get(0).setBounds(127 + insets.left, 425 + insets.top, 300, 75);
        choiceList.get(1).setBounds(459 + insets.left, 425 + insets.top, 300, 75);

        //Bottom Two
        choiceList.get(2).setBounds(127 + insets.left, 517 + insets.top, 300, 75);
        choiceList.get(3).setBounds(459 + insets.left, 517 + insets.top, 300, 75);


    }

    public void paintComponent (Graphics g) { //Loads background
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


