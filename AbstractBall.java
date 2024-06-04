import javax.swing.*;
import java.awt.*;

abstract class AbstractBall
{
   private int dX;
   private int dY;
   private int level;
   public abstract void setDX(double k);
   public abstract void setDY(double k);
   public abstract double getDX();
   public abstract double getDY();
   public abstract int getRadius();
   public abstract int getX();
   public abstract int getY();
   public abstract Color getColor();
   public abstract void setRadius(int rValue);
   public abstract void setX(int xValue);
   public abstract void setY(int yValue);
   public abstract void setColor(Color cValue);
   public abstract void step();
   public abstract void drawMe(Graphics g);
}
