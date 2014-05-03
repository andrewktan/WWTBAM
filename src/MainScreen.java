import org.omg.CORBA.BAD_INV_ORDER;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainScreen extends JFrame implements ActionListener, WindowListener, WindowFocusListener
{
    // objects for GUI
    private BufferedImage mainScreen, toolBar;
    private JPanel mainMenu = new JPanel();
    private ScorePanel moneyTree = new ScorePanel();
    private JButton startBtn = new JButton("Start");
    private JButton instructionsBtn = new JButton("Instructions");
    private JButton quitBtn = new JButton("Quit");

    //Menus
    private JMenuBar menuBar = new JMenuBar ();
    private JMenu GameMENU = new JMenu ("Game");
    private JMenuItem newGameITEM = new JMenuItem ("New Game");
    private JMenu InstructionsMENU = new JMenu ("Information");

    // objects for gameplay and confirmations
    private ArrayList< Question > questions;
    private Gameplay currentGameplay;

    //Confirmation
    Object[] options = {"YES", "NO"};
    Object[] options2 = {"CONTINUE", "WALKAWAY"};
    int confirmation, decision;

    private boolean fiftyFiftyUsed = false;

    // create buttons


    public MainScreen ()
    {
        //Menus
        menuBar.add(GameMENU);
        menuBar.add(InstructionsMENU);
        GameMENU.add(newGameITEM);
        newGameITEM.addActionListener(this);
        this.setJMenuBar(menuBar);
        // read from file
        questions = Question.readQuestionsFromFile ("questions.xml");

        // SETUP GUI
        setTitle ("Who Wants to Be a Millionaire?");
        setSize (908, 658);
        setDefaultLookAndFeelDecorated (true);
        setResizable(false);
        setLocationRelativeTo (null);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        try { //Imports image for the screen
            mainScreen = ImageIO.read(this.getClass().getResource("images/main_screen.jpg"));
            toolBar = ImageIO.read (this.getClass ().getResource ("images/icon.jpg"));
        } catch (IOException e) {
        }

        this.setIconImage(toolBar);


        // add ActionListeners
        quitBtn.addActionListener (this);
        startBtn.addActionListener(this);

        //customize buttons

        startBtn.setBackground(Color.WHITE);
        instructionsBtn.setBackground(Color.WHITE);
        quitBtn.setBackground(Color.WHITE);


        // set up title image
        JLabel title = new JLabel (new ImageIcon (mainScreen));
        title.setPreferredSize (new Dimension (600, 620));
        mainMenu.add(title, BorderLayout.WEST);
        mainMenu.setBackground(Color.WHITE);

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

        // manage layout of screen
        setContentPane(currentGameplay);
        add(moneyTree);
        Insets insets = getInsets();
        moneyTree.setBounds(899 + insets.left, 1, 250, 650); // place money tree
        pack();
        setSize(900 + 250, 675); //dimensions needed for the template picture of the questions/answers/money-tree
    }


    // For ActionListener interface
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource ().equals (quitBtn)) // quit button
        {
            dispose();
        } else if ((e.getSource().equals(startBtn)) || (e.getSource().equals(newGameITEM))) { // start button
            nextScreen();
//<<<<<<< HEAD
        } else

        {
            if (!currentGameplay.isAnswered()) { // if something else is pressed
                if (true && !fiftyFiftyUsed) { // NEED TO CHECK IF 50/50 IS CHOSEN
                    fiftyFiftyUsed = true;
                    currentGameplay.fiftyFiftyLifeline(); // run lifeline
                    currentGameplay.repaint();
                }
            }

            else if (currentGameplay.isAnswered()) {
            confirmation = JOptionPane.showOptionDialog(this, "Is this your final answer?", "Confirm Choice",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if(confirmation == JOptionPane.YES_OPTION){

                    if (currentGameplay.isCorrect()) { // if correct

                        moneyTree.incrementScore(); // increase score
                        moneyTree.repaint();

                        decision = JOptionPane.showOptionDialog(this, "CONGRATULATIONS! YOU HAVE WON $" + moneyTree.getScore() + "\n\nWalk away with $" +moneyTree.getScore() +" or continue?", "CONGRATULATIONS!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[1]);
                        if (decision == JOptionPane.YES_OPTION) {

                            nextScreen(); // display next question
                            System.out.printf("Your score is: %d\n", moneyTree.getScore());
                        }
                        else{
                            moneyTree.resetScore(); // reset score
                            setContentPane(mainMenu); // return to main menu
                            questions = Question.readQuestionsFromFile ("questions.xml"); // reload questions
                            this.setSize(908, 675);
                        }

                    } else { // if incorrect
                        moneyTree.resetScore(); // reset score
                        setContentPane(mainMenu); // return to main menu
                        questions = Question.readQuestionsFromFile ("questions.xml"); // reload questions
                        this.setSize(908, 675);
                    }

                }
        }
        }

        /*{
            // question attempted
=======
        } else if (!currentGameplay.isAnswered()) { // if something else is pressed
            if (true && !fiftyFiftyUsed) { // NEED TO CHECK IF 50/50 IS CHOSEN
                fiftyFiftyUsed = true;
                currentGameplay.fiftyFiftyLifeline(); // run lifeline
                currentGameplay.repaint();
            }
        } else { // if answered
>>>>>>> 2be48e74cbf390561fa6d19cfc5901c2e63b82cd
            if (currentGameplay.isCorrect()) { // if correct
                nextScreen(); // display next question
                moneyTree.incrementScore(); // increase score
                System.out.printf("Your score is: %d\n", moneyTree.getScore());
            } else { // if incorrect
                moneyTree.resetScore(); // reset score
                setContentPane(mainMenu); // return to main menu
                questions = Question.readQuestionsFromFile ("questions.xml"); // reload questions
                this.setSize(908, 675);
            }
<<<<<<< HEAD
        }*/
//=======
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

}

