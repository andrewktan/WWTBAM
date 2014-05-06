import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Gameplay extends JPanel implements ActionListener {
    //Pics
    private BufferedImage screen;
    private BufferedImage choiceBox;
    private BufferedImage fiftyFiftyBTN;

    // Status
    private boolean correct, answered = false,fiftyFiftyCheckOne = false, fiftyFiftyCheckTwo = false;

    //Four main choices buttons
    private JLabel prompt;
    private ArrayList<JButton> choiceList = new ArrayList<JButton>();
    private JButton fiftyFiftyOne;
    private JButton fiftyFiftyTwo;

    //Visual for the question box
    private BufferedImage questionBox;

    //Player
    private String player;

    //Questions & Answers
    private ArrayList<Question> questions;
    private ArrayList<String> answers;
    private Question currentQuestion;

    //Score
    private int score;


    private ActionListener ae;

    public Gameplay(Question currentQuestion, ActionListener ae, boolean fiftyFiftyCheckOne, boolean fiftyFiftyCheckTwo) {

        //this.setPreferredSize(new Dimension(800,800));
        this.fiftyFiftyCheckOne = fiftyFiftyCheckOne;
        this.fiftyFiftyCheckTwo = fiftyFiftyCheckTwo;
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
            fiftyFiftyBTN = ImageIO.read(this.getClass().getResource("images/50_50.jpg"));

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

        // initializing lifeline buttons

        //First 5050
        fiftyFiftyOne = new JButton("",  new ImageIcon(fiftyFiftyBTN));
        fiftyFiftyOne.setHorizontalTextPosition(SwingConstants.CENTER);
        fiftyFiftyOne.setBorder(BorderFactory.createEmptyBorder());
        fiftyFiftyOne.setContentAreaFilled(false);

        fiftyFiftyOne.addActionListener(this);
        this.add(fiftyFiftyOne);

       //Second 5050
        fiftyFiftyTwo = new JButton("",  new ImageIcon(fiftyFiftyBTN));
        fiftyFiftyTwo.setHorizontalTextPosition(SwingConstants.CENTER);
        fiftyFiftyTwo.setBorder(BorderFactory.createEmptyBorder());
        fiftyFiftyTwo.setContentAreaFilled(false);

        fiftyFiftyTwo.addActionListener(this);
        this.add(fiftyFiftyTwo);


        // place buttons
        this.setLayout(null);

        Insets insets = this.getInsets();
        Dimension promptSize = prompt.getPreferredSize();

        prompt.setBounds(110 + insets.left, 195 + insets.top, 680, 2 * promptSize.height); //Prompt

        // top two answers
        choiceList.get(0).setBounds(127 + insets.left, 425 + insets.top, 300, 75);
        choiceList.get(1).setBounds(459 + insets.left, 425 + insets.top, 300, 75);

        // bottom two answer
        choiceList.get(2).setBounds(127 + insets.left, 517 + insets.top, 300, 75);
        choiceList.get(3).setBounds(459 + insets.left, 517 + insets.top, 300, 75);

        // lifelines
        fiftyFiftyOne.setBounds(300, 364, 75, 50);
        fiftyFiftyTwo.setBounds(192, 364, 75, 50);

    }


    public void fiftyFiftyLifeline(int x) {
        // declare
        int index, removed = 0, last = -1;
        Random r = new Random(); // create random object

        // select buttons to remove
        while (removed < 2) { // repeat until 2 have been removed
            index = r.nextInt(4);
            if (index!=currentQuestion.getCorrect() && index!=last) { // don't remove the correct answer, don't repeat
                remove(choiceList.get(index)); // remove button
                last = index; // keep track of already removed button
                removed++; // keep track of number removed
            }
        }

        if (x == 1)
            fiftyFiftyCheckOne = true;

        else if (x == 2)
            fiftyFiftyCheckTwo = true;

    }

    public void paintComponent(Graphics g) { //Loads background
        super.paintComponent(g); //overrides original pain component

        g.drawImage(screen, 0, 0, null);

        if (fiftyFiftyCheckOne) { //First lifeline check
            this.remove(fiftyFiftyOne);
            g.setColor(Color.black);
            g.fillRect(300, 364, 75, 50);
        }

       if (fiftyFiftyCheckTwo) { //Second lifeline check
            this.remove(fiftyFiftyTwo);
            g.setColor(Color.black);
            g.fillRect(192, 364, 75, 50);
        }



    }

    public boolean isCorrect() {
        return correct;
    }

    public boolean isAnswered() {
        return answered;
    }

    // for ActionListener interface
    public void actionPerformed(ActionEvent e) {
        boolean f = false;

        // check if user is answering question
        for (JButton b : choiceList)
            f = f || e.getSource().equals(b);

        if (f) { // if it is an answer
            answered = true;
            if (choiceList.indexOf(e.getSource()) == this.currentQuestion.getCorrect())
                correct = true;
            else
                correct = false;
        }


        // trigger MainScreen's ActionListener after flags are set
        ae.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }
}


