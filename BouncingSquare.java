import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BouncingSquare extends Square implements Animatable {
   private int dX;
   private int dY;
   private int size;
   
   public BouncingSquare()
   {
      super(50, 100, 100, Color.BLACK);
      size = 50;
      dX = 3;
      dY = 3;
   }
   
   /*
      Add a 7-arg constructor that lets us specify an inflating circle of any size, at any 
      x-position, y-position, of any color, with custom values for dX, minX, and maxX.
      */
   public BouncingSquare(int sizeV, int x, int y, Color c, int dXV, int dYV) {
      super(sizeV, x, y, c);
      size = sizeV;
      dX = dXV;
      dY = dYV;
   }
   
   
   //accessors
   public int getDX()
   {
      return dX;
   }
   public int getDY()
   {
      return dY;
   }
      
   //modifiers
   public void setDX(int dXValue)
   {
      dX = dXValue;
   }
   public void setDY(int dYValue)
   {
      dY = dYValue;
   }
      
   //instance methods
   public void step()  //Implement Animatable's required step()
   {
      //Check to see if our circle is too small
      //If so, make sure dX is positive (radius is increasing)
      if(getX() < 0)
      {
         dX *= -1;
      }
      
      //Check to see if our circle is too big
      //If so, make sure dX is negative (radius is decreasing)
      if(getX()+size > 500)
      {
         dX *= -1;
      }
      setX(getX() + dX);  //Change the radius a bit - either out or in - for each animation step
      
      if(getY() < 0)
      {
         dY *= -1;
      }
      
      //Check to see if our circle is too big
      //If so, make sure dX is negative (radius is decreasing)
      if(getY()+size > 500)
      {
         dY *= -1;
      }
      setY(getY() + dY);  //Change the radius a bit - either out or in - for each animation step

   }

}