import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Horse
{
     int x,y,speed,width,height;
     boolean up, down, left, right, stop, collision, jump;
   
    public Horse(int x, int y)
    {
        this.x = x;
        this.y = y;
        speed = 3;
        width = 160;
        height = 160;
        up = false;
        down = false;
        left = false;
        right = false;
        collision = false;
        jump = false;
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
    
    public Image getHorse()
    {
        BufferedImage hastbild = null;
        try {
            hastbild = ImageIO.read(new File("horsie.gif"));
        } catch (IOException e) {
        }
        return hastbild;
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void move()
    {
        if(up && y>200)
            y -= speed;
        if(down && y<(815-height))
            y += speed;
        if(left && x>0)
            x -= speed;
        if(right && x<500)
            x += speed;
        if(jump)
        {
        	
        }
    }
}