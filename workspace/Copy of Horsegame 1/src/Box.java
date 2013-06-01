import java.awt.Rectangle;

public class Box
{
     int x,y,speed,width,height;
     boolean up, down, left, right, stop, collision;
   
    public Box(int x, int y, int height)
    {
        this.x = x;
        this.y = y;
        speed = 5;
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

    public void move()
    {
       x -= speed;
    }
}