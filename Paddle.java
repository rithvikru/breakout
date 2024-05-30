import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Paddle implements Animatable
{
   private int x;
   private int y;
   private int dX;

   public Paddle()
   {
     dX = 0;
     x = 440;
     y = 500;
   }

   public void setDX(int k){
      dX = k;
   }

   public int getDX(){
      return dX;
   } 

   public int getX()
   {
      return x;
   }

   public int getY()
   {
      return y;
   }
   
   //modifiers
   public void setX(int xValue)
   {
      x = xValue;
   }

  public void step()  //Implement Animatable's required step()
  {
    x += dX;
  }
   
   public void drawMe(Graphics g)
   {
      g.setColor(Color.WHITE);
      g.fillRect(x, y, 150, 10);
   }

}
