import javax.swing.*;
import java.awt.*;

public class BreakoutPanel extends JPanel
{
   private JFrame myOwner;
   
   private LockScreenPanel lockScreen;
   private LevelScreenPanel levelScreen;

   private EasyScreenPanel easyScreen;
   private MediumScreenPanel mediumScreen;
   private HardScreenPanel hardScreen;
                                         
   public BreakoutPanel(JFrame f) 
   {
      myOwner = f;
      
      setLayout(new BorderLayout());
      
      lockScreen = new LockScreenPanel(this); 
      add(lockScreen);
      
      levelScreen = new LevelScreenPanel(this);

      /*easyScreen = new EasyScreenPanel(this);*/
      mediumScreen = new MediumScreenPanel(this);
      hardScreen = new HardScreenPanel(this);
   }
   
   public void openLevels()
   {
      remove(lockScreen);
      add(levelScreen);
      repaint();
      revalidate();
      myOwner.pack(); 
   }

   public void openEasy(BreakoutPanel b)
   {
      remove(levelScreen);
      add(new EasyScreenPanel(b));
      repaint();
      revalidate();
      myOwner.pack(); 
   }

   public void openMedium()
   {
      remove(levelScreen);
      add(mediumScreen);
      repaint();
      revalidate();
      myOwner.pack(); 
   }

   public void openHard()
   {
      remove(levelScreen);
      add(hardScreen);
      repaint();
      revalidate();
      myOwner.pack(); 
   }
}
