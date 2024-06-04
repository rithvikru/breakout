import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class MediumBlock extends AbstractBlock
{
   private int level;
   private Color c;
   private int x;
   private int y;
   private int w;
   private int h;
   public MediumBlock(int xV, int yV)
   {
      level = 2;
      x = xV;
      y = yV;
      w = 75;
      h = 30;
      c = Color.YELLOW;
   }
   public int getX()
   {
      return x;  
   }
    public int getY()
   {
      return y;  
   }
   public int getW()
   {
      return w;  
   }
    public int getH()
   {
      return h;  
   }
    public void setx(int xV)
   {
      x = xV;  
   }
    public void sety(int yV)
   {
      y = yV;  
   }
    public void setw(int wV)
   {
      w = wV;
   }
    public void seth(int hV)
   {
      h = hV;
   }
   public void drawMe(Graphics g){
      g.setColor(c);
      g.fillRect(x, y, w, h);
   }
}
