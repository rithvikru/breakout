import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public abstract class AbstractBlock 
{
private int level;
private Color c;
private int x;
private int y;
private int w;
private int h;
public abstract int getX();
public abstract int getY();
public abstract int getW();
public abstract int getH();
public abstract void setx(int xV);
public abstract void sety(int yV);
public abstract void setw(int wV);
public abstract void seth(int hV);
public abstract void drawMe(Graphics g);
}

