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
    private BufferedImage audiencePollBTN;

    // Status
    private boolean correct, answered = false;
    //5050
    boolean fiftyFiftyCheckOne = false, fiftyFiftyCheckTwo = false, returnOne = false, returnTwo = false;
    //audience poll
    boolean audiencePollCheck1 = false, audiencePollCheck2 = false, returnThree = false, returnFour = false;

    //Four main choices buttons
    private JLabel prompt;
    private ArrayList<JButton> choiceList = new ArrayList<JButton>();

    private JButton fiftyFiftyOne;
    private JButton fiftyFiftyTwo;

    private JButton audiencePollOne;
    private JButton audiencePollTwo;

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
    int confirmation;

    private Sound gameMUSIC;


    private ActionListener ae;


    Object[] options = {"YES", "NO"};

    //Gameplay constructor reads in a read question and lifeline boolean checks
    public Gameplay(Question currentQuestion, ActionListener ae, boolean fiftyFiftyCheckOne, boolean fiftyFiftyCheckTwo, boolean audiencePollCheck1, boolean audiencePollCheck2) {


        //this.setPreferredSize(new Dimension(800,800));
        this.fiftyFiftyCheckOne = fiftyFiftyCheckOne;
        this.fiftyFiftyCheckTwo = fiftyFiftyCheckTwo;
        this.audiencePollCheck1 = audiencePollCheck1;
        this.audiencePollCheck2 = audiencePollCheck2;
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
            audiencePollBTN = ImageIO.read(this.getClass().getResource("images/audience_poll.jpg"));

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
        fiftyFiftyOne = new JButton("", new ImageIcon(fiftyFiftyBTN));
        fiftyFiftyOne.setHorizontalTextPosition(SwingConstants.CENTER);
        fiftyFiftyOne.setBorder(BorderFactory.createEmptyBorder());
        fiftyFiftyOne.setContentAreaFilled(false);

        fiftyFiftyOne.addActionListener(this);
        this.add(fiftyFiftyOne);

        //Second 5050
        fiftyFiftyTwo = new JButton("", new ImageIcon(fiftyFiftyBTN));
        fiftyFiftyTwo.setHorizontalTextPosition(SwingConstants.CENTER);
        fiftyFiftyTwo.setBorder(BorderFactory.createEmptyBorder());
        fiftyFiftyTwo.setContentAreaFilled(false);

        fiftyFiftyTwo.addActionListener(this);
        this.add(fiftyFiftyTwo);

        //First Audience poll
        audiencePollOne = new JButton("", new ImageIcon(audiencePollBTN));
        audiencePollOne.setHorizontalTextPosition(SwingConstants.CENTER);
        audiencePollOne.setBorder(BorderFactory.createEmptyBorder());
        audiencePollOne.setContentAreaFilled(false);

        audiencePollOne.addActionListener(this);
        this.add(audiencePollOne);

        //Second Audience poll
        audiencePollTwo = new JButton("", new ImageIcon(audiencePollBTN));
        audiencePollTwo.setHorizontalTextPosition(SwingConstants.CENTER);
        audiencePollTwo.setBorder(BorderFactory.createEmptyBorder());
        audiencePollTwo.setContentAreaFilled(false);

        audiencePollTwo.addActionListener(this);
        this.add(audiencePollTwo);


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
        fiftyFiftyTwo.setBounds(195, 364, 75, 50);

        audiencePollOne.setBounds(510, 364, 75, 50);
        audiencePollTwo.setBounds(618, 364, 75, 50);

    }

    public void paintComponent(Graphics g) { //Loads background
        super.paintComponent(g); //overrides original pain component

        g.drawImage(screen, 0, 0, null);

        //Redraws the screen with or without lifelines based on if they have been used

        if (fiftyFiftyCheckOne) { //First lifeline check
            this.remove(fiftyFiftyOne);
            g.setColor(Color.black);
            g.fillRect(300, 364, 75, 50);
        }

        if (fiftyFiftyCheckTwo) { //Second lifeline check
            this.remove(fiftyFiftyTwo);
            g.setColor(Color.black);
            g.fillRect(195, 364, 75, 50);
        }

        if (audiencePollCheck1) { //First audience poll
            this.remove(audiencePollOne);
            g.setColor(Color.black);
            g.fillRect(510, 364, 75, 50);
        }

        if (audiencePollCheck2) { //Second audience poll
            this.remove(audiencePollTwo);
            g.setColor(Color.black);
            g.fillRect(618, 364, 75, 50);
        }


    }

    public void fiftyFiftyLifeline(int x) {
        // declare
        int index, removed = 0, last = -1;
        Random r = new Random(); // create random object

        // select buttons to remove
        while (removed < 2) { // repeat until 2 have been removed
            index = r.nextInt(4);
            if (index != currentQuestion.getCorrect() && index != last) { // don't remove the correct answer, don't repeat
                remove(choiceList.get(index)); // remove button
                last = index; // keep track of already removed button
                removed++; // keep track of number removed
            }
        }


        //checks which lifeline has been used
        if (x == 1)
            fiftyFiftyCheckOne = true;

        else if (x == 2)
            fiftyFiftyCheckTwo = true;

    }

    public void audiencePollLifeLine(int x) {
        // JFrame
        JFrame pollScreen = new JFrame();

        //Makes a pop up screen for audience poll to open up
        pollScreen.setSize(530, 120);
        pollScreen.setLocationRelativeTo(null);
        pollScreen.setDefaultLookAndFeelDecorated(true);
        pollScreen.setResizable(false);
        pollScreen.setVisible(true);
        pollScreen.setTitle("Audience Poll");

        JPanel poll = new JPanel();

        //makes labels and probability integers
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        ArrayList<Integer> probs = new ArrayList<Integer>();

        int subtotal, remaining, total = 100, numPolled = 0;
        ArrayList<String> ans = currentQuestion.getAnswers();
        Random r = new Random();

        for (int i = 0; i < 4; i++) { // loop over answers
            // generate random integer with normal dist
            probs.add((currentQuestion.isCorrect(i)) ?
                    (int) (r.nextGaussian() * 100 + 600) : // if it is the correct answer
                    (int) (r.nextGaussian() * 70 + 220)); // if it is a wrong answer
            numPolled += probs.get(i); // add to total
        }

        for (int i = 0; i < 4; i++) { // loop over answers
            labels.add(new JLabel(
                    String.format("%.1f%% of the audience thinks the answer is %s",
                            (float) probs.get(i) / numPolled * 100, //displays the probability in percent
                            ans.get(i))
            ));
        }

        for (JLabel label : labels) // add all the labels
            poll.add(label, FlowLayout.LEFT);

        pollScreen.setContentPane(poll);

        //checks which number of audience poll has been used
        if (x == 1)
            audiencePollCheck1 = true;

        else if (x == 2)
            audiencePollCheck2 = true;


    }

    public boolean fiftyFiftyOnePressed() {
        return returnOne;
    } //check if fifty fifty one has been pressed. communicates with mainscreen

    public boolean fiftyFiftyTwoPressed() {
        return returnTwo;
    } //check if fifty fifty two has been pressed. communicates with mainscreen

    public boolean audiencePollOnePressed() {
        return returnThree;
    } //check if audience poll one has been pressed. communicates with mainscreen

    public boolean audiencePollTwoPressed() {
        return returnFour;
    } //check if audience poll two has been pressed. communicates with mainscreen

    public boolean isCorrect() {
        return correct;
    } //checks for correct answer

    public boolean isAnswered() {
        return answered;
    } //checks for any answer

    // for ActionListener interface
    public void actionPerformed(ActionEvent e) {
        boolean f = false;

        // check if user is answering question
        for (JButton b : choiceList)
            f = f || e.getSource().equals(b);

        if (f) { // if it is an answer

            confirmation = JOptionPane.showOptionDialog(this, "Is this your final answer?", "Confirm Choice",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            if (confirmation == JOptionPane.YES_OPTION)
                answered = true;

            if (choiceList.indexOf(e.getSource()) == this.currentQuestion.getCorrect())
                correct = true;
            else
                correct = false;
        }

        if (e.getSource().equals(fiftyFiftyOne))
            returnOne = true;

        if (e.getSource().equals(fiftyFiftyTwo))
            returnTwo = true;
        if (e.getSource().equals(audiencePollOne))
            returnThree = true;
        if (e.getSource().equals(audiencePollTwo))
            returnFour = true;

        // trigger MainScreen's ActionListener after flags are set
        ae.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }
}


