//här är klassen för de stock-objekt som skapas, den är väldigt lik Horse.java och jag hänvisar därför till Horse.java-s kommentarer 

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

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

    	URL url = getClass().getResource("stockt.gif");
    	ImageIcon stockbild = new ImageIcon(url);
    	Image image = stockbild.getImage();
    	
    	
        return image;
    }
    
    public void ChangeSpeed(int newspeed){
    	speed = newspeed;
    }

    public void move()
    {
       x -= speed;
    }
}