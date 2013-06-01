//H�r har vi sj�lva k�rnan i programmet, det �r i denna klass som alla funktioner anv�nds f�r att k�ra spelet

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Eventrit extends JPanel implements KeyListener, ActionListener//jag �r tvungen att implementera b�de K... och A... f�r att jag ju har 
{//en restart-knapp som ska kunna anv�ndas och �ven knapptryck som ska tolkas och jag �rver JPanel eftersom f�r att kunna rita upp saker p� sk�rmen  
 //beh�ver jag dens egenskaper och funktioner
	BufferedImage buffer;
	Horse hast;
	Box box[]=new Box[3];
	int time = 0;
	boolean traff=false;
	boolean gameon=true;
	JButton restart;
	JLabel tid, score;
	int scaleX=200; //dessa tv� �r till f�r att �ndra skalan p� h�sten n�r man hoppar, det g�r att man ser hoppet tydligare
	int scaleY=200; //  ^
	int xbg = 0; //den h�r variabeln styr b�de bakgrundens hastighet och hj�lper till att h�lla ihop bakgrunden
	String tide = "Tid: ";
	String scoree = "Score: ";
	int count, sec, min, hour;//dessa variabler h�ller koll p� tiden och scoren
	int scalejump=0;//den h�r variabeln �r till f�r att h�sten ska r�ra sig upp�t n�r man hoppar ist�llet f�r ned�t
	int bonus=1;//ger bonusp��ng om man klarar att �verleva i 100 och 1000 sekunder.
	int boxspeed=6;
	
    public Eventrit()//konstruktor
    {
        setIgnoreRepaint(true);
        addKeyListener(this);//g�r det m�jligt att "lyssna" efter knapptryckningar
        setFocusable(true);//g�r s� att focus l�ggs p� detta objekt
        
        tid = new JLabel();//skapar JLabels f�r tid
        score = new JLabel();//och score
        
        restart = new JButton("Restart");//g�r restart-knappen
        restart.addActionListener(this);//och l�gger till actionlistener
        restart.setActionCommand("restart");//och actioncommand
    }

    public void keyTyped(KeyEvent e)//m�ste ha med �ven om jag inte anv�nder; eftersom Keylistener �r en interface-klass
    {

    }

    public void keyPressed(KeyEvent e)//kollar om man tryckt ner en knapp
    {
    	 int key = e.getKeyCode();//key f�r en siffra fr�n keycode
         if (key == KeyEvent.VK_LEFT)//som kan l�nkas till keyevent  som det syns i if-satsen
             hast.left = true;//trycker man ner knappen s� blir r�relsen sann
         if (key == KeyEvent.VK_RIGHT)
             hast.right = true;
         if (key == KeyEvent.VK_UP)
             hast.up = true;
         if (key == KeyEvent.VK_DOWN)
             hast.down = true;
         if (key == KeyEvent.VK_SPACE)
             hast.jump = true;
        
    }

    public void keyReleased(KeyEvent e)//kollar om man sl�ppt en knapp
    {
    	int key = e.getKeyCode();//key f�r en siffra fr�n keycode
        if (key == KeyEvent.VK_LEFT)//som kan l�nkas till keyevent  som det syns i if-satsen
        	hast.left = false;//sl�pper man knappen s� blir r�relsen falsk
        if (key == KeyEvent.VK_RIGHT)
        	hast.right = false;
        if (key == KeyEvent.VK_UP)
        	hast.up = false;
        if (key == KeyEvent.VK_DOWN)
        	hast.down = false;
        if (key == KeyEvent.VK_SPACE)
            hast.jump = true;
    }
    
    public void Initialize()//h�r skapas alla objekt som ska vara med
    {
    	traff = false;//finns med f�r att restart ska fungera
    	count=0;//dessa har f�rst�s och med restart att g�ra
    	sec=0;//^
    	min=0;//^
    	hour=0;//^
    	boxspeed=6;//^
    	gameon = true;//g�r det m�jligt att b�rja loopa spelet s� att det blir r�rligt
    	buffer = new BufferedImage(800,820,BufferedImage.TYPE_INT_RGB);//ritar upp duken som jag sedan l�gger upp mina gif-bilder p�
    	hast = new Horse(100,300);//skapar h�st-objektet
    	
    	Random rnd = new Random();//g�r att stockarna kan komma p� random y-kordinationer och har random h�jd
    	
    	
    	for(int i=0; i<3; i++){//skapar 3 l�dor fr�n b�rjan; mer blir on�digt eftersom det bara f�r plats 3 i bilden
    	box[i] = new Box((400*i+1000),rnd.nextInt(460)+270, rnd.nextInt(100)+200);//skapar stockarna och ger dem de v�rden som kr�vs
    	}
    }

    public void update()//ser till att h�sten r�r sig
    {
    	hast.move();//flyttar h�sten s� att nykordinat = gammalkord. + speed, se move()-funktionen i hast
    }

    public void checkCollisions()//kollar om h�sten kolliderar med en stock
    {
    	
    	for(int i=0; i<3; i++){
    	if (hast.getBounds().intersects(box[i].getBounds())){//den h�r funktionen j�mf�r rektanglarna som skapats (och som ligger osynliga under bilderna p� stock / h�st
    			hast.collision = true;//om det �r en kollision s� blir collision sann	
    			traff=true;}//�ven traff blir falsk
    	
    	
    	else if(traff == false){
    			hast.collision = false;}
    	}
    }

    public void drawBuffer()//ritar ut alla bilder p� sk�rmen
    {
    	String mins=" minut";//n�r man d�tt s� komemr ett fint medelande
    	String secs=" sekund!";
    	Random rnd = new Random();
    	for(int i=0; i<3; i++)
    	{
    		if(box[i].getX()<-50)//n�r l�dans x-kordinat
    		{
    		box[i] = new Box((1000),rnd.nextInt(400)+200, rnd.nextInt(100)+200);
    		}
    	}
    	ImageObserver observe = null;//kr�vs f�r att drawImage ska fungera
    	Graphics2D b = buffer.createGraphics();
    	
    	b.setColor(Color.black);
    	b.fillRect(0,0,800,890);//fyller max s� h�r m�nga pixlar med det ja ritar upp (b�rjar i kordinaterna (0,0))
    	
    	if(hast.collision == false){
    		xbg-=boxspeed;
    		if(xbg<=-804){
    			xbg=0;
    		}
    		b.drawImage(getBg(), xbg, 0, 800, 820, observe);
    		b.drawImage(getBg(), xbg+800, 0, 800, 820, observe);//denna �r till f�r att bakrunden ska kunna rulla fint, testa utan denna f�r att f�rst� b�ttre
    	
    	for(int i=0; i<3; i++)
    	{
    	b.drawImage(box[i].getStock(), box[i].getX()-20, box[i].getY()-20, 140, box[i].getHeight()+40, observe);//ritar upp stockarna
    	}
    	b.drawImage(hast.getHorse(), hast.getX()-20, hast.getY()-20-scalejump, scaleX, scaleY, observe);//ritar upp h�sten
    	b.dispose();
    	}
    	else
    		
            b.setColor(Color.white);//detta sker om h�sten kolliderar med en stock
            b.drawString("Horsie is DEAD!!!!",350,300);
            b.drawString("Din totala score var: " + count*bonus, 350, 320);
            if(min>1 || min==0)mins=" minuter";
            if(sec>1 || sec==0)secs=" sekunder!";
            b.drawString("Och du lyckades �verleva i " + min + mins + " och " + sec + secs,350,340);
            b.dispose();
    }

    public void drawScreen()//ritar ut buffern
    {
    	Graphics2D g = (Graphics2D)this.getGraphics();
    	
    	g.drawImage(buffer,0,0,this);
    	Toolkit.getDefaultToolkit().sync();//synkar
    	g.dispose();
    }
    
    
    public void ScaleChange(int jumpcount)//�ndrar skalan p� h�sten medan man hoppar s� att man ser hoppet
    {	int ettupp=0;
    	if(jumpcount<24-(boxspeed/2)){ettupp=3;}
    	if(jumpcount>23-boxspeed/2){ettupp=-3;}
    	scaleX+=ettupp;
    	scaleY+=ettupp;
    	scalejump+=ettupp*2;//s� att det ser ut som om man hoppar upp�tt, du kan testa skillnaden utan ettupp*2
    }
    
    public Image getBg()//k�ndes inte som bakrunden beh�vde ett eget objekt i och med att jag bara beh�ver ladda bilden och inget mer
    {

    	URL url = getClass().getResource("background.gif");
    	ImageIcon hastbild = new ImageIcon(url);
    	Image image = hastbild.getImage();
    	
        return image;
    }
   
    public void startGame()
    {
    	Initialize(); //vad den g�r st�r i funktionen
    	int jumpcount = 0;
    	String secnolla;
    	String minnolla;
    	while(gameon)
    	{
    	    try
    	    {
    	    	if(!traff){count++;}
		    	    	if(count>300){
		    	    		bonus=2;//h�r e lite bonus po�ng man f�r
		    	    		boxspeed=8;}//g�r det ocks� lite sv�rare, �ndrar allts� hastigheten fr�n 6 till 10
		    	    	if(count>900){
		    	    		bonus=3;//h�r e lite bonus po�ng man f�r
		    	    		boxspeed=10;}//g�r det ocks� lite sv�rare, �ndrar allts� hastigheten fr�n 6 till 10
		    	    	if(count>1700){
		    	    		bonus=4;//h�r e lite bonus po�ng man f�r
		    	    		boxspeed=12;}//g�r det ocks� lite sv�rare, �ndrar allts� hastigheten fr�n 6 till 10
		    	    	if(count>3000){
		    	    		bonus=5;//som d syns :)
		    	    		boxspeed=16;}//som d syns :)
		    	    	if(count%10 == 0)//ifstatserna nedan �r bara till f�r att tiden sca visas korrekt
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
		    	    	else minnolla = "";//h�r slutar dessa ifsatser
    	       
    	        for(int i=0; i<3; i++){
    	        box[i].ChangeSpeed(boxspeed);//anropar changespeed funktionen i box som �ndrar varibaeln speed som isint tur styr hastigheten	
    	        box[i].move();				 //f�r stockarna 
    	        }
    	        
    	        if(!hast.jump)//om man inte hoppat s� ska den kolla efter kollisioner
    	        {
    	        	checkCollisions();
    	        }
    	        update();//k�r alla funktioner som m�ste k�ras
    	        drawBuffer();//k�r alla funktioner som m�ste k�ras
    	        drawScreen();//k�r alla funktioner som m�ste k�ras, vad dom g�r finns beskrivet i funktionerna, man kanske kan se p� namanen ox�
    	        Thread.sleep(10);
    	        
    	        
    	        tid.setText(tide + "0" + hour + ":" + minnolla + min + ":" + secnolla + sec + "    ");//skriver ut tiden i labeln
    	        score.setText(scoree + count*bonus);//skriver ut scoren
    	        
    	        
    	        if(hast.jump)
    	        {
    	        	ScaleChange(jumpcount);
    	        	jumpcount++;//g�r att man f�r hoppa i ca 4 sekunder, blir mindre desto h�gre boxspeed man kommit upp i dock
    	        	
    	        	if(jumpcount>46-boxspeed)//minus boxspeed eftersom annars hoppar man ju l�ttare �ver stockarna
    	        	{
    	        		hast.jump=false;//n�r hoppet �r klart �r hoppet falskt igen
    	        		jumpcount = 0; //n�r hoppet �r klart nollst�lls jumpcount
    	        	}
    	        }
    	        
    	    }
    	    catch(Exception e)
    	    {
    	        e.printStackTrace();
    	    }	
    	}
    }
    public void actionPerformed(ActionEvent ae)//actioneventent fr�n restartknappen k�r h�r
    {
		String s = ae.getActionCommand();//restartar man programmet beh�ver man bara k�ra Initialize() igen
		if(s == "restart")
		{
		Initialize();
		
		}
    }
}
