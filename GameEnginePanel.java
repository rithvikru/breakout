import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.*;
import java.util.ArrayList;

public class GameEnginePanel extends JPanel {
  private BreakoutPanel myOwner;

  private static final int HEIGHT = 600;
  private static final int WIDTH = 1000;

  private static final Color BACKGROUND = Color.BLACK;
  //fields
  private BufferedImage myImage; 
  private Graphics myBuffer;
  private Timer t;
  private ArrayList <Animatable> animationObjects;

  private JButton playButton;

  private int currFrame;

  private boolean left;
  private boolean right;
  
  private Paddle paddle;
  
  private int dX;

  public GameEnginePanel(BreakoutPanel p, String difficulty) {
    if (difficulty == "hard") {
      dX = 4;
    }
    else {
      dX = 5;
    }

    playButton = new JButton("Start game");
    playButton.addActionListener(new PlayListener());
    playButton.setBackground(Color.WHITE);

    setLayout(new BorderLayout());
    add(playButton, BorderLayout.SOUTH);

    currFrame = 0;
    myImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    myBuffer = myImage.getGraphics();
    myBuffer.setColor(BACKGROUND); 
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    animationObjects = new ArrayList <Animatable> (); 

    Animatable ball = new Ball(difficulty);
    animationObjects.add(ball);
    paddle = new Paddle();
    animationObjects.add(paddle);


    addKeyListener(new Key());  //Key is a private class defined below
    setFocusable(true);  //Don't forget this!
    
    left = false;
    right = false; //at the moment, the user is not pushing down_2 the left arrow key, so "false"

  }

  public void paintComponent(Graphics g)
  {
    g.drawImage(myImage, 0, 0, WIDTH, HEIGHT, null);
  }

  public void animate() {
    myBuffer.setColor(BACKGROUND);
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    for (Animatable animationObject: animationObjects) {
      animationObject.step(); 
      animationObject.drawMe(myBuffer); 
    }

    myBuffer.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30)); 
    myBuffer.setColor(Color.WHITE);
    myBuffer.drawString(String.valueOf(currFrame), 25, 50);
    currFrame++;

    System.out.println(currFrame);

    repaint();
  }

  private class AnimationListener implements ActionListener {
    public void actionPerformed(ActionEvent e) 
    {
      animate(); 
    }
  }

  private class PlayListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      t = new Timer(5, new AnimationListener());

      t.start();
      remove(playButton);

    }
  }

  private class Key extends KeyAdapter //Make ONE class that EXTENDS KeyAdapter, and tell it what to do when keys are pressed or released
  {
    public void keyPressed(KeyEvent e) //Make ONE method for key presses; this is overridden, and will be called whenever a key is pressed
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT && !left) //e.getKeyCode() lets us retrieve which key was just pushed.  !left lets us know the user isn't already holding the left arrow down_2.
        {
          paddle.setDX(paddle.getDX() - dX);  //Subtract 2 from Square's dX value, effectively setting the value to 0.
          left = true;  //Now, the user is holding down_2 the left key, so set this to true.  Why do we need to keep track of this?  So that holding down_2 one (or even two) key works as expected.
        }

        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && !right) //e.getKeyCode() lets us retrieve which key was just pushed.  !left lets us know the user isn't already holding the left arrow down_2.
        {
          paddle.setDX(paddle.getDX() + dX);  //Subtract 2 from Square's dX value, effectively setting the value to 0.
          right = true;  //Now, the user is holding down_2 the left key, so set this to true.  Why do we need to keep track of this?  So that holding down_2 one (or even two) key works as expected.
        }

    }
    
    public void keyReleased(KeyEvent e) //Also overridden; ONE method that will be called any time a key is released
    {  
        if(e.getKeyCode() == KeyEvent.VK_LEFT) // If the user lets go of the left arrow
        {
          paddle.setDX(paddle.getDX() + dX);  //Again: add 2, don't set to 0 precisely.  
          left = false;  //User is no longer holding the left key, so set this back to false.
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) // If the user lets go of the left arrow
        {
          paddle.setDX(paddle.getDX() - dX);  //Again: add 2, don't set to 0 precisely.  
          right = false;  //User is no longer holding the left key, so set this back to false.
        }
        
    }
  }

}
