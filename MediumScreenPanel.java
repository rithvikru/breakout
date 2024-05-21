import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MediumScreenPanel extends JPanel
{
   private BreakoutPanel myOwner; 

   public MediumScreenPanel(BreakoutPanel p)
   {
      setLayout(new BorderLayout());
      
      JLabel callout = new JLabel("Medium");
      callout.setFont(new Font("Arial", Font.BOLD, 69));
      callout.setForeground(Color.WHITE);
      callout.setHorizontalAlignment(JLabel.CENTER);
      add(callout, BorderLayout.CENTER);
   }

}
