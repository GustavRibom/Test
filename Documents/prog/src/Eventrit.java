//Här har vi själva kärnan i programmet, det är i denna klass som alla funktioner används för att köra spelet

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Eventrit extends JPanel implements KeyListener, ActionListener//jag är tvungen att implementera både K... och A... för att jag ju har 
{//en restart-knapp som ska kunna användas och även knapptryck som ska tolkas och jag ärver JPanel eftersom för att kunna rita upp saker på skärmen  
 //behöver jag dens egenskaper och funktioner
	BufferedImage buffer;
	Horse hast;
	Box box[]=new Box[3];
	int time = 0;
	boolean traff=false;
	boolean gameon=true;
	JButton restart;
	JLabel tid, score;
	int scaleX=200; //dessa två är till för att ändra skalan på hästen när man hoppar, det gör att man ser hoppet tydligare
	int scaleY=200; //  ^
	int xbg = 0; //den här variabeln styr både bakgrundens hastighet och hjälper till att hålla ihop bakgrunden
	String tide = "Tid: ";
	String scoree = "Score: ";
	int count, sec, min, hour;//dessa variabler håller koll på tiden och scoren
	int scalejump=0;//den här variabeln är till för att hästen ska röra sig uppåt när man hoppar istället för nedåt
	int bonus=1;//ger bonuspåäng om man klarar att överleva i 100 och 1000 sekunder.
	int boxspeed=6;
	
    public Eventrit()//konstruktor
    {
        setIgnoreRepaint(true);
        addKeyListener(this);//gör det möjligt att "lyssna" efter knapptryckningar
        setFocusable(true);//gör så att focus läggs på detta objekt
        
        tid = new JLabel();//skapar JLabels för tid
        score = new JLabel();//och score
        
        restart = new JButton("Restart");//gör restart-knappen
        restart.addActionListener(this);//och lägger till actionlistener
        restart.setActionCommand("restart");//och actioncommand
    }

    public void keyTyped(KeyEvent e)//måste ha med även om jag inte använder; eftersom Keylistener är en interface-klass
    {

    }

    public void keyPressed(KeyEvent e)//kollar om man tryckt ner en knapp
    {
    	 int key = e.getKeyCode();//key får en siffra från keycode
         if (key == KeyEvent.VK_LEFT)//som kan länkas till keyevent  som det syns i if-satsen
             hast.left = true;//trycker man ner knappen så blir rörelsen sann
         if (key == KeyEvent.VK_RIGHT)
             hast.right = true;
         if (key == KeyEvent.VK_UP)
             hast.up = true;
         if (key == KeyEvent.VK_DOWN)
             hast.down = true;
         if (key == KeyEvent.VK_SPACE)
             hast.jump = true;
        
    }

    public void keyReleased(KeyEvent e)//kollar om man släppt en knapp
    {
    	int key = e.getKeyCode();//key får en siffra från keycode
        if (key == KeyEvent.VK_LEFT)//som kan länkas till keyevent  som det syns i if-satsen
        	hast.left = false;//släpper man knappen så blir rörelsen falsk
        if (key == KeyEvent.VK_RIGHT)
        	hast.right = false;
        if (key == KeyEvent.VK_UP)
        	hast.up = false;
        if (key == KeyEvent.VK_DOWN)
        	hast.down = false;
        if (key == KeyEvent.VK_SPACE)
            hast.jump = true;
    }
    
    public void Initialize()//här skapas alla objekt som ska vara med
    {
    	traff = false;//finns med för att restart ska fungera
    	count=0;//dessa har förstås och med restart att göra
    	sec=0;//^
    	min=0;//^
    	hour=0;//^
    	boxspeed=6;//^
    	gameon = true;//gör det möjligt att börja loopa spelet så att det blir rörligt
    	buffer = new BufferedImage(800,820,BufferedImage.TYPE_INT_RGB);//ritar upp duken som jag sedan lägger upp mina gif-bilder på
    	hast = new Horse(100,300);//skapar häst-objektet
    	
    	Random rnd = new Random();//gör att stockarna kan komma på random y-kordinationer och har random höjd
    	
    	
    	for(int i=0; i<3; i++){//skapar 3 lådor från början; mer blir onödigt eftersom det bara får plats 3 i bilden
    	box[i] = new Box((400*i+1000),rnd.nextInt(460)+270, rnd.nextInt(100)+200);//skapar stockarna och ger dem de värden som krävs
    	}
    }

    public void update()//ser till att hästen rör sig
    {
    	hast.move();//flyttar hästen så att nykordinat = gammalkord. + speed, se move()-funktionen i hast
    }

    public void checkCollisions()//kollar om hästen kolliderar med en stock
    {
    	
    	for(int i=0; i<3; i++){
    	if (hast.getBounds().intersects(box[i].getBounds())){//den här funktionen jämför rektanglarna som skapats (och som ligger osynliga under bilderna på stock / häst
    			hast.collision = true;//om det är en kollision så blir collision sann	
    			traff=true;}//även traff blir falsk
    	
    	
    	else if(traff == false){
    			hast.collision = false;}
    	}
    }

    public void drawBuffer()//ritar ut alla bilder på skärmen
    {
    	String mins=" minut";//när man dött så komemr ett fint medelande
    	String secs=" sekund!";
    	Random rnd = new Random();
    	for(int i=0; i<3; i++)
    	{
    		if(box[i].getX()<-50)//när lådans x-kordinat
    		{
    		box[i] = new Box((1000),rnd.nextInt(400)+200, rnd.nextInt(100)+200);
    		}
    	}
    	ImageObserver observe = null;//krävs för att drawImage ska fungera
    	Graphics2D b = buffer.createGraphics();
    	
    	b.setColor(Color.black);
    	b.fillRect(0,0,800,890);//fyller max så här många pixlar med det ja ritar upp (börjar i kordinaterna (0,0))
    	
    	if(hast.collision == false){
    		xbg-=boxspeed;
    		if(xbg<=-804){
    			xbg=0;
    		}
    		b.drawImage(getBg(), xbg, 0, 800, 820, observe);
    		b.drawImage(getBg(), xbg+800, 0, 800, 820, observe);//denna är till för att bakrunden ska kunna rulla fint, testa utan denna för att förstå bättre
    	
    	for(int i=0; i<3; i++)
    	{
    	b.drawImage(box[i].getStock(), box[i].getX()-20, box[i].getY()-20, 140, box[i].getHeight()+40, observe);//ritar upp stockarna
    	}
    	b.drawImage(hast.getHorse(), hast.getX()-20, hast.getY()-20-scalejump, scaleX, scaleY, observe);//ritar upp hästen
    	b.dispose();
    	}
    	else
    		
            b.setColor(Color.white);//detta sker om hästen kolliderar med en stock
            b.drawString("Horsie is DEAD!!!!",350,300);
            b.drawString("Din totala score var: " + count*bonus, 350, 320);
            if(min>1 || min==0)mins=" minuter";
            if(sec>1 || sec==0)secs=" sekunder!";
            b.drawString("Och du lyckades överleva i " + min + mins + " och " + sec + secs,350,340);
            b.dispose();
    }

    public void drawScreen()//ritar ut buffern
    {
    	Graphics2D g = (Graphics2D)this.getGraphics();
    	
    	g.drawImage(buffer,0,0,this);
    	Toolkit.getDefaultToolkit().sync();//synkar
    	g.dispose();
    }
    
    
    public void ScaleChange(int jumpcount)//ändrar skalan på hästen medan man hoppar så att man ser hoppet
    {	int ettupp=0;
    	if(jumpcount<24-(boxspeed/2)){ettupp=3;}
    	if(jumpcount>23-boxspeed/2){ettupp=-3;}
    	scaleX+=ettupp;
    	scaleY+=ettupp;
    	scalejump+=ettupp*2;//så att det ser ut som om man hoppar uppått, du kan testa skillnaden utan ettupp*2
    }
    
    public Image getBg()//kändes inte som bakrunden behövde ett eget objekt i och med att jag bara behöver ladda bilden och inget mer
    {

    	URL url = getClass().getResource("background.gif");
    	ImageIcon hastbild = new ImageIcon(url);
    	Image image = hastbild.getImage();
    	
        return image;
    }
   
    public void startGame()
    {
    	Initialize(); //vad den gör står i funktionen
    	int jumpcount = 0;
    	String secnolla;
    	String minnolla;
    	while(gameon)
    	{
    	    try
    	    {
    	    	if(!traff){count++;}
		    	    	if(count>300){
		    	    		bonus=2;//här e lite bonus poäng man får
		    	    		boxspeed=8;}//gör det också lite svårare, ändrar alltså hastigheten från 6 till 10
		    	    	if(count>900){
		    	    		bonus=3;//här e lite bonus poäng man får
		    	    		boxspeed=10;}//gör det också lite svårare, ändrar alltså hastigheten från 6 till 10
		    	    	if(count>1700){
		    	    		bonus=4;//här e lite bonus poäng man får
		    	    		boxspeed=12;}//gör det också lite svårare, ändrar alltså hastigheten från 6 till 10
		    	    	if(count>3000){
		    	    		bonus=5;//som d syns :)
		    	    		boxspeed=16;}//som d syns :)
		    	    	if(count%10 == 0)//ifstatserna nedan är bara till för att tiden sca visas korrekt
		    	    	{							// ----
		    	    		sec++;					// ----
		    	    	}							// ----
		    	    	if(sec==60)					// ----
		    	    	{							// ----
		    	    		min++;					// ----
		    	    		sec=0;					// ----
		    	    	}							// ----
		    	    	if(min==60)					// ----
		    	    	{							// ----
		    	    		hour++;					// ----
		    	    		min=0;					// ----
		    	    	}							// ----
		    	    	if(sec<10)					// ----
		    	    	{							// ----
		    	    		secnolla="0";			// ----
		    	    	}							// ----
		    	    	else secnolla = "";			// ----
		    	    	if(min<10)					// ----
		    	    	{							// ----
		    	    		minnolla = "0";			// ----
		    	    	}							// ----
		    	    	else minnolla = "";//här slutar dessa ifsatser
    	       
    	        for(int i=0; i<3; i++){
    	        box[i].ChangeSpeed(boxspeed);//anropar changespeed funktionen i box som ändrar varibaeln speed som isint tur styr hastigheten	
    	        box[i].move();				 //för stockarna 
    	        }
    	        
    	        if(!hast.jump)//om man inte hoppat så ska den kolla efter kollisioner
    	        {
    	        	checkCollisions();
    	        }
    	        update();//kör alla funktioner som måste köras
    	        drawBuffer();//kör alla funktioner som måste köras
    	        drawScreen();//kör alla funktioner som måste köras, vad dom gör finns beskrivet i funktionerna, man kanske kan se på namanen oxå
    	        Thread.sleep(10);
    	        
    	        
    	        tid.setText(tide + "0" + hour + ":" + minnolla + min + ":" + secnolla + sec + "    ");//skriver ut tiden i labeln
    	        score.setText(scoree + count*bonus);//skriver ut scoren
    	        
    	        
    	        if(hast.jump)
    	        {
    	        	ScaleChange(jumpcount);
    	        	jumpcount++;//gör att man får hoppa i ca 4 sekunder, blir mindre desto högre boxspeed man kommit upp i dock
    	        	
    	        	if(jumpcount>46-boxspeed)//minus boxspeed eftersom annars hoppar man ju lättare över stockarna
    	        	{
    	        		hast.jump=false;//när hoppet är klart är hoppet falskt igen
    	        		jumpcount = 0; //när hoppet är klart nollställs jumpcount
    	        	}
    	        }
    	        
    	    }
    	    catch(Exception e)
    	    {
    	        e.printStackTrace();
    	    }	
    	}
    }
    public void actionPerformed(ActionEvent ae)//actioneventent från restartknappen kör här
    {
		String s = ae.getActionCommand();//restartar man programmet behöver man bara köra Initialize() igen
		if(s == "restart")
		{
		Initialize();
		
		}
    }
}
