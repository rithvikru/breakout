import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LockScreenPanel extends JPanel
{
   private BreakoutPanel myOwner; 
   
   public LockScreenPanel(BreakoutPanel p)
   {
      myOwner = p;
      
      setPreferredSize(new Dimension(1000, 600));
      setBackground(Color.BLACK);
      
      setLayout(new BorderLayout());
      
      JLabel callout = new JLabel("BREAKOUT!");
      callout.setFont(new Font("Arial", Font.BOLD, 69));
      callout.setForeground(Color.WHITE);
      callout.setHorizontalAlignment(JLabel.CENTER);
      add(callout, BorderLayout.CENTER);
      
      JButton levelSelector = new JButton("Play!");
      levelSelector.addActionListener(new LevelsListener());
      add(levelSelector, BorderLayout.SOUTH);
   }
   
   private class LevelsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myOwner.openLevels(); 
      }
   }
}
