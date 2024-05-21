import javax.swing.JFrame;

public class BreakoutDriver
{
  public static void main(String[] args)
  {
      JFrame frame = new JFrame("Final Project: Breakout");
      frame.setSize(1200, 800);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new BreakoutPanel(frame));
      frame.pack();
      frame.setVisible(true);
  }
}
