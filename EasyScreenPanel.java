import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.*;
import java.util.ArrayList;

public class EasyScreenPanel extends JPanel {
  private BreakoutPanel myOwner;
  private static final int HEIGHT = 600;
  private static final int WIDTH = 1000;

  private static final Color BACKGROUND = Color.BLACK;
  //fields
  private BufferedImage myImage; //temporary storage area for the graphics
  private Graphics myBuffer;
  private Timer t;
  private ArrayList < Animatable > animationObjects;

  private int currFrame;

  public EasyScreenPanel(BreakoutPanel p) {
    /* setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
  
    JLabel callout = new JLabel("Easy");
    callout.setFont(new Font("Arial", Font.BOLD, 69));
    callout.setForeground(Color.WHITE);
    callout.setHorizontalAlignment(JLabel.CENTER);
    add(callout, BorderLayout.CENTER); */

    currFrame = 0;
    myImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    myBuffer = myImage.getGraphics();
    myBuffer.setColor(BACKGROUND); //The next two lines draw the background rectangle for the first time
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    animationObjects = new ArrayList < Animatable > (); //Instantiate the Animatable ArrayList

    Animatable easy_ball = new Ball("easy");
    animationObjects.add(easy_ball);
    Animatable medium_ball = new Ball("medium");
    animationObjects.add(medium_ball);
    Animatable hard_ball = new Ball("hard");
    animationObjects.add(hard_ball);

    t = new Timer(5, new AnimationListener());

    t.start();
  }

  public void paintComponent(Graphics g) //Required method to paint to the screen
  {
    g.drawImage(myImage, 0, 0, WIDTH, HEIGHT, null); //Draw the buffered image we've stored as a field
  }

  public void animate() {
    //This performs the next animation step by drawing to myImage using commands on myBuffer
    //Then, we'll call repaint(), which calls paintComponent(), which draws myImage to the screen

    //Clear the current state of myImage by writing over it with a new blank background
    myBuffer.setColor(BACKGROUND);
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    //Loop through the ArrayList of Animatable objects; do an animation step on each one & draw it
    for (Animatable animationObject: animationObjects) {
      animationObject.step(); //do this object’s animation step
      animationObject.drawMe(myBuffer); //the object draws itself
    }

    myBuffer.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30)); //We'll use size 30 serif font, bold AND italic.
    myBuffer.setColor(Color.WHITE);
    myBuffer.drawString(String.valueOf(currFrame), 25, 50);
    currFrame++;

    System.out.println(currFrame);


    /*
        In animate(), increase the value of this new variable by 1 each time a new frame is drawn.
        Display the value of the variable in the top right corner of the animation panel.  You 
        will need to set the font, set the color to black, and call drawString.  When the value 
        of the variable reaches 500 frames of animation, call the Timer object’s stop method 
        to end the animation.  
    */



    //Call built-in JFrame method repaint(), which calls paintComponent, which puts the next frame on screen
    repaint();
  }

  private class AnimationListener implements ActionListener {
    public void actionPerformed(ActionEvent e) //Timer calls this every 5 ms
    {
      animate(); //...hence animation!
    }
  }


}
