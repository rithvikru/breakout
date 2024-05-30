import javax.swing.*;
import java.awt.*;

public class BreakoutPanel extends JPanel
{
   private JFrame myOwner;
   
   private LockScreenPanel lockScreen;
   private LevelScreenPanel levelScreen;

   private GameEnginePanel easyScreen;
   private GameEnginePanel mediumScreen;
   private GameEnginePanel hardScreen;

   public BreakoutPanel(JFrame f) 
   {
      myOwner = f;
      
      setPreferredSize(new Dimension(1000, 600));
      setLayout(new BorderLayout());
      
      lockScreen = new LockScreenPanel(this); 
      add(lockScreen);
      
      levelScreen = new LevelScreenPanel(this);

      easyScreen = new GameEnginePanel(this, "easy");
      mediumScreen = new GameEnginePanel(this, "medium");
      hardScreen = new GameEnginePanel(this, "hard");
   }
   
   public void openLevels()
   {
      remove(lockScreen);
      add(levelScreen);
      repaint();
      revalidate();
      myOwner.pack(); 
   }

   public void openGame(String difficulty)
   {
      remove(levelScreen);
      if (difficulty == "easy") { add(easyScreen); }
      else if (difficulty == "medium") { add(mediumScreen); }
      else { add(hardScreen); }

      repaint();
      revalidate();
      myOwner.pack(); 
   }

}
