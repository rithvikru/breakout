import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HardScreenPanel extends JPanel
{
   private BreakoutPanel myOwner; 

   public HardScreenPanel(BreakoutPanel p)
   {
      setLayout(new BorderLayout());
      
      JLabel callout = new JLabel("Hard");
      callout.setFont(new Font("Arial", Font.BOLD, 69));
      callout.setForeground(Color.WHITE);
      callout.setHorizontalAlignment(JLabel.CENTER);
      add(callout, BorderLayout.CENTER);
   }

}
