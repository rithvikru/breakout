import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class MidBlock extends AbstractBlock
{
   private int level;
   private Color c;
   private int x;
   private int y;
   private int w;
   private int h;
   int[][] blocks = new int[4][10];
   public MidBlock(int xV, int yV)
   {
      level = 2;
      for (int i = 0; i < blocks.length; i++)
               {
                  for (int c = 0; c < blocks[0].length; c++)
                  {  blocks[i][c]=level;}}
      x = xV;
      y = yV;
      w = 90;
      h = 50;
      c = Color.ORANGE;
   }
   public int getx()
   {
      return x;  
   }
    public int gety()
   {
      return y;  
   }
    public int getw()
   {
      return w;  
   }
    public int geth()
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