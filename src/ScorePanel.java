import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePanel extends JPanel {

    private BufferedImage bgimg;

    protected int scoreInd;

    // score levels
    protected int[] scores = {0, 500, 1000, 2000, 3000, 5000,
            7500, 10000, 15000, 25000, 50000,
            75000, 150000, 250000, 500000, 1000000};

    //checks points so u go back to a specific amount
    protected int[] checkpoints = {0, 0, 0, 0, 0, 5,
            5, 5, 5, 5, 10,
            10, 10, 10, 10, 15};

    ScorePanel() //default constructor
    {
        // set size
        setSize(250, 658);
        setVisible(true);
        scoreInd = 0;

        // load background image
        try {
            bgimg = ImageIO.read(getClass().getResource("images/money_tree.jpg"));
        } catch (IOException e) {
        }
    }

    public void paintComponent(Graphics g) { //Loads background
        super.paintComponent(g);
        g.drawImage(bgimg, 0, 0, null); // draw background image
        g.setColor(new Color(255, 255, 255, 100));

        //moves up the translucent rectangle up by a specifc number of coordinates
        g.fillRect(0, 605 - (scoreInd * 28 + 1), 250, 35);
    }

    public int getScore() {
        return scores[scoreInd]; //gets the specifc score at any given time
    }

    public int getIndex() {
        return scoreInd;
    }

    public void resetScore() {
        scoreInd = 0; //starts the score back to zero
    }

    public void incrementScore() {
        scoreInd += 1; //increments the score
    }

    public int getCheckpointScore() {
        return scores[checkpoints[scoreInd]]; //gets which checkpoint to return to
    }
}
