import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.*;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
  private JLabel welcomeText;

  private int currFrame;

  private boolean left;
  private boolean right;
  
  private Paddle paddle;
  private Ball ball;
  
  private int dX;

  private int[][] matrix;
  private String d;

  private AbstractBlock[][] board;

  public GameEnginePanel(BreakoutPanel p, String difficulty) {
    d = difficulty;
    if (difficulty == "hard") {
      dX = 4;
    }
    else {
      dX = 5;
    }

    welcomeText = new JLabel(difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1) + " Mode");
    welcomeText.setFont(new Font("Arial", Font.BOLD, 69));
    welcomeText.setForeground(Color.WHITE);
    welcomeText.setHorizontalAlignment(JLabel.CENTER);

    playButton = new JButton("Start game");
    playButton.addActionListener(new PlayListener());
    playButton.setBackground(Color.WHITE);

    setLayout(new BorderLayout());
    add(playButton, BorderLayout.SOUTH);
    add(welcomeText, BorderLayout.CENTER);

    currFrame = 0;
    myImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    myBuffer = myImage.getGraphics();
    myBuffer.setColor(BACKGROUND); 
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    animationObjects = new ArrayList <Animatable> (); 

    ball = new Ball(difficulty);
    animationObjects.add(ball);
    paddle = new Paddle();
    animationObjects.add(paddle);


    addKeyListener(new Key());  //Key is a private class defined below
    setFocusable(true);  //Don't forget this!
    
    left = false;
    right = false; //at the moment, the user is not pushing down_2 the left arrow key, so "false"

    matrix = new int[3][10];
    if (d == "easy") {
      board = new EasyBlock[3][10];
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 10; c++) {
          matrix[r][c] = 1;
          board[r][c] = new EasyBlock(85*c + 80, r * 50);
        }
      }
    }
    else if (d == "medium") {
      board = new MediumBlock[3][10];
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 10; c++) {
          matrix[r][c] = 2;
          board[r][c] = new MediumBlock(85*c + 80, r * 50);
        }
      }
    }
    else {
      board = new HardBlock[3][10];
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 10; c++) {
          matrix[r][c] = 3;
          board[r][c] = new HardBlock(85*c + 80, r * 50);
        }
      }
    }

  }

  public void paintComponent(Graphics g)
  {
    g.drawImage(myImage, 0, 0, WIDTH, HEIGHT, null);
  }

  public void animate() {
    myBuffer.setColor(BACKGROUND);
    myBuffer.fillRect(0, 0, WIDTH, HEIGHT);

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 10; c++) {
        if (matrix[r][c] >= 1) {
          board[r][c].drawMe(myBuffer);
        }
      }
    }

    for (Animatable animationObject: animationObjects) {
      animationObject.step(); 
    }

    boolean collided = false;
    if (ball.collide(paddle))
    {
      collided = true;
    }

    if (collided)
    {
      ball.setDX(ball.getDX());
      ball.setDY(-ball.getDY());
    }


    boolean gameOver;
    int sum = 0;


    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 10; c++) {
        if (matrix[r][c] >= 1 && ball.hitBlock(board[r][c])) {
          matrix[r][c]--;
          ball.setDY(-ball.getDY());
          System.out.println("Collided");
        }
        if (matrix[r][c] > 0) {
          gameOver= false;
        }
        sum+=matrix[r][c];
      }
    }

    gameOver = (sum == 0) ? true : false;

    if (gameOver) {
      myBuffer.setFont(new Font("Serif", Font.BOLD, 50));
      myBuffer.setColor(Color.WHITE);
      myBuffer.drawString("You Won!", WIDTH / 2 - 100, HEIGHT / 2);
      myBuffer.drawString("Score: " + currFrame, WIDTH / 2 - 150, HEIGHT / 2 + 60);
      saveScoreToFile(currFrame, d); // Save the score to the file
      t.stop();
    } else {
      myBuffer.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
      myBuffer.setColor(Color.WHITE);
      myBuffer.drawString(String.valueOf(currFrame), 25, 50);
      currFrame++;
    }


    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 10; c++) {
        if (matrix[r][c] >= 1 && ball.hitBlock(board[r][c])) {
            matrix[r][c]--;
            ball.setDY(-ball.getDY());
        }
      }
    }

    paddle.setX((int)MouseInfo.getPointerInfo().getLocation().getX()-250);
    System.out.println(MouseInfo.getPointerInfo().getLocation().getX()-250);

    // show frames
    myBuffer.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30)); 
    myBuffer.setColor(Color.WHITE);
    myBuffer.drawString(String.valueOf(currFrame), 25, 50);
    currFrame++;

    
    for (Animatable animationObject: animationObjects)
    {
      animationObject.drawMe(myBuffer);
    }

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
      remove(welcomeText);
      remove(playButton);

    }
  }

  private class Key extends KeyAdapter 
  {
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT && !left) 
        {
          paddle.setDX(paddle.getDX() - dX);  
          left = true;  
        }

        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && !right) 
        {
          paddle.setDX(paddle.getDX() + dX);  
          right = true;  
        }

    }
    
    public void keyReleased(KeyEvent e) 
    {  
        if(e.getKeyCode() == KeyEvent.VK_LEFT) 
        {
          paddle.setDX(paddle.getDX() + dX);  
          left = false;  
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
        {
          paddle.setDX(paddle.getDX() - dX);  
          right = false;  
        }
        
    }
  }

  private void saveScoreToFile(int score, String mode) {
    String filename = "scores.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write("Mode: " + mode + ", Score: " + score + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  /* public void playSound() {
    File f = new File("./boom.mp3");
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
  } */

}
