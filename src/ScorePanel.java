import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;

public class ScorePanel extends JPanel {
    ScorePanel() {
        // set size
        setSize(250, 658);
        setVisible(true);
    }

    public void paintComponent (Graphics g) { //Loads background
        super.paintComponent(g); //overrides original pain component
        try {
            g.drawImage(ImageIO.read(getClass().getResource("images/money_tree.jpg")),
                    0, 0, null);
        } catch (IOException e) {
        }


    }
}
