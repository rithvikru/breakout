import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LevelScreenPanel extends JPanel
{
   private BreakoutPanel myOwner; 

   public LevelScreenPanel(BreakoutPanel p)
   {
      myOwner = p;

      setPreferredSize(new Dimension(1000, 600)); //Set size here.
      setLayout(new GridLayout(1, 3));
      
      setBackground(Color.BLACK);
      
      JButton easySelector = new JButton("Easy");
      easySelector.addActionListener(new EasyListener());
      easySelector.setBackground(Color.GREEN);
      
      JButton mediumSelector = new JButton("Medium");
      mediumSelector.addActionListener(new MediumListener());
      mediumSelector.setBackground(Color.YELLOW);
      
      JButton hardSelector = new JButton("Hard");
      hardSelector.addActionListener(new HardListener());
      hardSelector.setBackground(Color.RED);
      
      easySelector.setHorizontalAlignment(JLabel.CENTER);
      mediumSelector.setHorizontalAlignment(JLabel.CENTER);
      hardSelector.setHorizontalAlignment(JLabel.CENTER);
      
      add(easySelector);
      add(mediumSelector);
      add(hardSelector);
   }

   private class EasyListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myOwner.openGame("easy"); 
      }
   }

   private class MediumListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myOwner.openGame("medium"); 
      }
   }

   private class HardListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myOwner.openGame("hard");
      }
   }
}
