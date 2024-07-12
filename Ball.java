import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.util.ArrayList;
import java.util.Random;

public class Ball extends AbstractBall implements Animatable
{
   private Color c;
   private int x;
   private int y;
   private int r;
   private double dX;
   private double dY;

   public Ball(String mode)
   {
      Random random = new Random();
      r = 10;
      x = 250;
      y = 250;
      c = Color.WHITE;
      if (mode == "easy"){
        dX = 2 + (3 - 2) * random.nextDouble();
        dY = 2 + (3 - 2) * random.nextDouble();
      }
      else if (mode == "medium"){
        dX = 3 + (4 - 3) * random.nextDouble();
        dY = 3 + (4 - 3) * random.nextDouble();
      }
      else {
        dX = 4 + (5 - 4) * random.nextDouble();
        dY = 4 + (5 - 4) * random.nextDouble();
      }
   }
   public void setDX(double k){
      dX = k;
   }
   public void setDY(double k){
      dY = k;
   }
   public double getDX(){
      return dX;
   } 
   public double getDY(){
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

   public boolean collide(Paddle paddle)
   {
      int thisX = getX();
      int thisY = getY();
      
      int otherX = paddle.getX();
      int otherY = 500;
      
      return (thisX-otherX > 0 && thisX-otherX < 150 && Math.abs(thisY-otherY) < r);
   }

   public boolean hitBlock(AbstractBlock block)
   {
      int thisX = getX();
      int thisY = getY();
      
      int otherX = block.getX();
      int otherY = block.getY();
      
      return (thisX-otherX > 0 && thisX-otherX < 75 && Math.abs(thisY-otherY-30) < r);
   }

   public void step(){
      if (getX()< 0 || getX() > (1000-getRadius())){
         dX *= -1;
      }
      if (getY()< 0 || getY() > (600-getRadius())){
         dY *= -1;
      }
      setX((int)(getX() + dX));
      setY((int)(getY() + dY));
   } 
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillOval(x-r, y-r, 2*r, 2*r);
   }

}
