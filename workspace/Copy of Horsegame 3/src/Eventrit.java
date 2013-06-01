import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Eventrit extends JPanel implements KeyListener, ActionListener
{
	BufferedImage buffer;
	Horse hast;
	Box box[]=new Box[3];
	int time = 0;
	boolean traff=false;
	boolean gameon=true;
	FlowLayout flow;
	JButton restart;
	JLabel tid, score;
	int scaleX=200;
	int scaleY=200;
	int xbg = 0;
	String tide = "Tid: ";
	String scoree = "Score: ";
	int count, sec, min, hour;
	
	
    public Eventrit()
    {
        setIgnoreRepaint(true);
        addKeyListener(this);
        setFocusable(true);
        
        tid = new JLabel("Tid: ");
        score = new JLabel("Score: ");
        
        restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("restart");
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
    	traff = false;
    	count=0;
    	sec=0;
    	min=0;
    	hour=0;
    	gameon = true;
    	buffer = new BufferedImage(800,820,BufferedImage.TYPE_INT_RGB);
    	hast = new Horse(100,300);
    	
    	Random rnd = new Random();
    	
    	
    	for(int i=0; i<3; i++){
    	box[i] = new Box((400*i+1000),rnd.nextInt(600)+200, rnd.nextInt(100)+200);
    	}
    }

    public void update()
    {
    	hast.move();
    }

    public void checkCollisions()
    {
    	
    	for(int i=0; i<3; i++){
    	if (hast.getBounds().intersects(box[i].getBounds())){
    			hast.collision = true;
    			traff=true;}
    	
    	
    	else if(traff == false){
    			hast.collision = false;}
    	}
    }

    public void drawBuffer()
    {
    	Random rnd = new Random();
    	for(int i=0; i<3; i++)
    	{
    		if(box[i].getX()<0)
    		{
    		box[i] = new Box((1000),rnd.nextInt(600)+200, rnd.nextInt(100)+200);
    		}
    	}
    	ImageObserver observe = null;
    	Graphics2D b = buffer.createGraphics();
    	
    	b.setColor(Color.black);
    	b.fillRect(0,0,800,890);
    	
    	if(hast.collision == false){
    	//b.setColor(Color.red);
    		xbg-=6;
    		if(xbg==-804){
    			xbg=0;
    		}
    		b.drawImage(getBg(), xbg, 0, 800, 820, observe);
    		b.drawImage(getBg(), xbg+800, 0, 800, 820, observe);
    	for(int i=0; i<3; i++){
    	//b.setColor(Color.blue);
    	//b.fillRect(box[i].getX(), box[i].getY(), box[i].getWidth(), box[i].getHeight());
    	b.drawImage(box[i].getStock(), box[i].getX()-20, box[i].getY()-20, 140, box[i].getHeight()+40, observe);
    	}
    	b.drawImage(hast.getHorse(), hast.getX()-20, hast.getY()-20, scaleX, scaleY, observe);
    	
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
    public void ScaleChange(int jumpcount)
    {	int ettupp=0;
    	if(jumpcount<21){ettupp=2;}
    	if(jumpcount>20){ettupp=-2;}
    	scaleX+=ettupp;
    	scaleY+=ettupp;
    }
    
    public Image getBg()
    {
        BufferedImage background = null;
        try {
        	background = ImageIO.read(new File("background.gif"));
        } catch (IOException e) {
        }
        return background;
    }
   
    public void startGame()
    {
    	Initialize();
    	int jumpcount = 0;
    	String secnolla;
    	String minnolla;
    	int bonus=1;
    	while(gameon)
    	{
    	    try
    	    {
		    	    	count++;
		    	    	if(count>1000){
		    	    	bonus=2;
		    	    	}
		    	    	if(count%10 == 0)
		    	    	{
		    	    		sec++;	
		    	    	}	
		    	    	if(sec==60)
		    	    	{
		    	    		min++;
		    	    		sec=0;
		    	    	}
		    	    	if(min==60)
		    	    	{
		    	    		hour++;
		    	    		min=0;
		    	    	}
		    	    	if(sec<10)
		    	    	{
		    	    		secnolla="0";
		    	    	}
		    	    	else secnolla = "";
		    	    	if(min<10)
		    	    	{
		    	    		minnolla = "0";
		    	    	}
		    	    	else minnolla = "";
    	       
    	        for(int i=0; i<3; i++){
    	        box[i].move();
    	        }
    	        
    	        if(!hast.jump)
    	        {
    	        	checkCollisions();
    	        }
    	        update();
    	        drawBuffer();
    	        drawScreen();
    	        Thread.sleep(10);
    	        
    	        if(!traff){
    	        tid.setText(tide + "0" + hour + ":" + minnolla + min + ":" + secnolla + sec + "    ");
    	        score.setText(scoree + count*bonus);
    	        }
    	        
    	        if(hast.jump)
    	        {
    	        	ScaleChange(jumpcount);
    	        	jumpcount++;
    	        	
    	        	if(jumpcount>40)
    	        	{
    	        		hast.jump=false;
    	        		jumpcount = 0;
    	        	}
    	        }
    	        
    	    }
    	    catch(Exception e)
    	    {
    	        e.printStackTrace();
    	    }	
    	}
    }
    public void actionPerformed(ActionEvent ae)
    {
		String s = ae.getActionCommand();
		if(s == "restart")
		{
		Initialize();
		
		}
    }
}





