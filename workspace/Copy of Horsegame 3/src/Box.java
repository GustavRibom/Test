import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box
{
     int x,y,speed,width,height;
     boolean up, down, left, right, stop, collision;
   
    public Box(int x, int y, int height)
    {
        this.x = x;
        this.y = y;
        speed = 6;
        width = 100;
        this.height = height;
        up = false;
        down = false;
        left = false;
        right = false;
        collision = false;
   }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    

    public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    public Image getStock()
    {
        BufferedImage stockbild = null;
        try {
        	stockbild = ImageIO.read(new File("stockt.gif"));
        } catch (IOException e) {
        }
        return stockbild;
    }

    public void move()
    {
       x -= speed;
    }
}