import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RulesScreenPanel extends JPanel
{
   private BreakoutPanel myOwner; 

   public RulesScreenPanel(BreakoutPanel p)
   {
      myOwner = p;

      setPreferredSize(new Dimension(1000, 600)); //Set size here.
      setLayout(new BorderLayout());
      
      setBackground(Color.BLACK);
      
      JLabel heading = new JLabel("Rules:");
      JLabel rules = new JLabel("Breakout is a pong game, that requires you to bounce the ball of your paddle onto the block matrix above until they are all broken and gone.");
      heading.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
      rules.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
      heading.setForeground(Color.WHITE);
      rules.setForeground(Color.WHITE);
      add(heading, BorderLayout.NORTH);
      add(rules, BorderLayout.CENTER);
   }
}
