import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Ball extends AbstractBall implements Animatable
{
   private Color c;
   private int x;
   private int y;
   private int r;
   private int dX;
   private int dY;
   public Ball(String mode)
   {
      r = 10;
      x = 250;
      y = 250;
      c = Color.WHITE;
      if (mode == "easy"){
        dX = 3;
        dY = 3;
      }
      else if (mode == "medium"){
        dX = 4;
        dY = 4;
      }
      else {
        dX = 5;
        dY = 5;
      }
   }
   public void setdX(int k){
      dX = k;
   }
   public void setdY(int k){
      dY = k;
   }
   public int getdX(){
      return dX;
   } 
   public int getdY(){
      return dY;
   } 
   public int getRadius()
   {
      return r;
   }
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public Color getColor()
   {
      return c;
   }
   
   //modifiers
   public void setRadius(int rValue)
   {
      r = rValue;
   }
   public void setX(int xValue)
   {
      x = xValue;
   }
   public void setY(int yValue)
   {
      y = yValue;
   }
   public void setColor(Color cValue)
   {
      c = cValue;
   }
   public void step(){
      if (getX()< 0 || getX() > (1000-getRadius())){
         dX *= -1;
      }
      if (getY()< 0 || getY() > (600-getRadius())){
         dY *= -1;
      }
      setX(getX() + dX);
      setY(getY() + dY);
   } 
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillOval(x-r, y-r, 2*r, 2*r);
   }

}
