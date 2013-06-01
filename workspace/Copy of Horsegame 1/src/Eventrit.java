import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.*;

public class Eventrit extends JPanel implements KeyListener
{
	BufferedImage buffer;
	Horse hast;
	Box box[]=new Box[20];
	int time = 0;
	boolean traff=false;
	boolean gameon=true;
	FlowLayout flow;
	JButton test;
	
    public Eventrit()
    {
    	flow = new FlowLayout();
    	test = new JButton("heejj");
        setIgnoreRepaint(true);
        addKeyListener(this);
        setFocusable(true);
    }

    public void keyTyped(KeyEvent e)//måste ha med även om jag inte använder; eftersom Keylistener är en interface-klass
    {

    }

    public void keyPressed(KeyEvent e)
    {
    	 int key = e.getKeyCode();
         if (key == KeyEvent.VK_LEFT)
             hast.left = true;
         if (key == KeyEvent.VK_RIGHT)
             hast.right = true;
         if (key == KeyEvent.VK_UP)
             hast.up = true;
         if (key == KeyEvent.VK_DOWN)
             hast.down = true;
         if (key == KeyEvent.VK_SPACE)
             hast.jump = true;
        
    }

    public void keyReleased(KeyEvent e)
    {
    	int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
        	hast.left = false;
        if (key == KeyEvent.VK_RIGHT)
        	hast.right = false;
        if (key == KeyEvent.VK_UP)
        	hast.up = false;
        if (key == KeyEvent.VK_DOWN)
        	hast.down = false;
        if (key == KeyEvent.VK_SPACE)
            hast.jump = true;
    }
    
    public void Initialize()
    {
    	gameon = true;
    	buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
    	hast = new Horse(100,100);
    	
    	Random rnd = new Random();
    	
    	
    	for(int i=0; i<20; i++){
    	box[i] = new Box((400*i+1000),rnd.nextInt(600)-100, rnd.nextInt(100)+200);
    	}
    }

    public void update()
    {
    	hast.move();
    }

    public void checkCollisions()
    {
    	
    	for(int i=0; i<20; i++){
    	if (hast.getBounds().intersects(box[i].getBounds())){
    			hast.collision = true;
    			traff=true;}
    	
    	
    	else if(traff == false){
    			hast.collision = false;}
    	}
    }

    public void drawBuffer()
    {
    	ImageObserver observe = null;
    	Graphics2D b = buffer.createGraphics();
    	
    	b.setColor(Color.black);
    	b.fillRect(0,0,800,600);
    	
    	if(hast.collision == false){
    	//b.setColor(Color.red);
    	
    	b.drawImage(hast.getHorse(), hast.getX(), hast.getY(), 100, 100, observe);
    	
    	for(int i=0; i<20; i++){
    	b.setColor(Color.blue);
    	b.fillRect(box[i].getX(), box[i].getY(), box[i].getWidth(), box[i].getHeight());
    	}
    	
    	b.dispose();
    	}
    	else
            b.setColor(Color.white);
            b.drawString("Horsie is DEAD!!!!",350,300);
            b.dispose();
    }

    public void drawScreen()
    {
    	Graphics2D g = (Graphics2D)this.getGraphics();
    	
    	g.drawImage(buffer,0,0,this);
    	Toolkit.getDefaultToolkit().sync();
    	g.dispose();
    }
    public int timecalc()
    {
    	time++;
    	return time;
    	
    }
    public void startGame()
    {
    	Initialize();
    	while(gameon)
    	{
    	    try
    	    {
    	        update();
    	        checkCollisions();
    	        drawBuffer();
    	        drawScreen();
    	        timecalc();
    	        Thread.sleep(10);
    	        for(int i=0; i<20; i++){
    	        box[i].move();
    	        }
    	        if(traff){
    	        	gameon=false;
    	        }
    	    }
    	    catch(Exception e)
    	    {
    	        e.printStackTrace();
    	    }	
    	}
    }
   
}