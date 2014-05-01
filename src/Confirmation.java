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

public class Confirmation extends JPanel implements ActionListener
{
    //prompt
    private JLabel prompt;

    //check
    boolean check;

    //choices
    private JButton yesBTN;
    private JButton noBTN;

    private ActionListener action;

   public Confirmation(ActionListener action)
   {
       this.action = action;

       yesBTN = new JButton("Yes");
       noBTN = new JButton("Walk Away");

       prompt = new JLabel("Is this your final choice?");

       yesBTN.addActionListener(this);
       noBTN.addActionListener(this);

       this.add(yesBTN);
       this.add(noBTN);
       this.add(prompt);
       setVisible(true);


   }

    public boolean getResponse () {return check;}

    // for ActionListener interface
    public void actionPerformed (ActionEvent e)
    {
       if (e.getSource().equals(yesBTN)) {
       check = true;
       }

        else
           check = false;

        // trigger MainScreen's ActionListener
        action.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));


    }

}
