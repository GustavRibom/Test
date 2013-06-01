//Detta �r h�st-objektet d�r alla h�stens funktioner och egenskaper sparas

import java.awt.*;//h�r importerar jag allt som beh�vs f�r de olika funktioner som genomf�rs
import java.net.URL;
import javax.swing.ImageIcon;

public class Horse
{
     int x,y,speed,width,height;
     boolean up, down, left, right, stop, collision, jump;
   
    public Horse(int x, int y)//konstruktorn, de flesta varibaler talar f�r sig sj�lva
    {
        this.x = x;
        this.y = y;
        speed = 3;
        width = 160;
        height = 160;
        up = false;	//dessa variabler �r till f�r att styra h�sten 
        down = false;
        left = false;
        right = false;
        jump = false;
        collision = false; //�r till f�r att kunna kolla om h�sten har krockat med en stock
   }
    
    public int getX()//klassiska funktioner f�r att h�mta varibelv�rden
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
    
    public Image getHorse()//h�mtar bilden p� h�sten och sparar den i varaibeln image
    {
    	
    	URL url = getClass().getResource("horsie.gif");
    	ImageIcon hastbild = new ImageIcon(url);
    	Image image = hastbild.getImage();
    	
    	
        return image;
    }
    
    public Rectangle getBounds() //den h�r funktionen �r till f�r att jag p� ett l�tt s�tt ska kunna kolla kollisioner mellan rektanglar
    {							//det finns n�mligen en annan funktion som kollar intersects men som bara fungerar f�r Rectangle-objekt
        return new Rectangle(getX(),getY(),getWidth(),getHeight());//skickar ett rektangel-objekt som �r lite mindre �n h�stens skala eftersom
    }												     //jag inte vill att man ska f�rlora utan att h�sten synbart �r i kontakt med en stock

    public void move()//den h�r funktionen kontrollerar hastigheten som h�sten kommer r�ra sig i beroende p� vilken knapp man trycker p�
    {
        if(up && y>200)//det som st�r efter &&-tecknen �r till f�r att begr�nsa h�stens r�relsyta, man vill inte att den ska kunna dra utanf�r kanten
            y -= speed+2; //jag har gjort s� att man r�r sig lite snabbare i y-led f�r att f�rb�ttra "spelk�nslan" lite
        if(down && y<(815-height))
            y += speed+2;
        if(left && x>0)
            x -= speed;
        if(right && x<500)
            x += speed;

    }
}