//Detta är häst-objektet där alla hästens funktioner och egenskaper sparas

import java.awt.*;//här importerar jag allt som behövs för de olika funktioner som genomförs
import java.net.URL;
import javax.swing.ImageIcon;

public class Horse
{
     int x,y,speed,width,height;
     boolean up, down, left, right, stop, collision, jump;
   
    public Horse(int x, int y)//konstruktorn, de flesta varibaler talar för sig själva
    {
        this.x = x;
        this.y = y;
        speed = 3;
        width = 160;
        height = 160;
        up = false;	//dessa variabler är till för att styra hästen 
        down = false;
        left = false;
        right = false;
        jump = false;
        collision = false; //är till för att kunna kolla om hästen har krockat med en stock
   }
    
    public int getX()//klassiska funktioner för att hämta varibelvärden
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
    
    public Image getHorse()//hämtar bilden på hästen och sparar den i varaibeln image
    {
    	
    	URL url = getClass().getResource("horsie.gif");
    	ImageIcon hastbild = new ImageIcon(url);
    	Image image = hastbild.getImage();
    	
    	
        return image;
    }
    
    public Rectangle getBounds() //den här funktionen är till för att jag på ett lätt sätt ska kunna kolla kollisioner mellan rektanglar
    {							//det finns nämligen en annan funktion som kollar intersects men som bara fungerar för Rectangle-objekt
        return new Rectangle(getX(),getY(),getWidth(),getHeight());//skickar ett rektangel-objekt som är lite mindre än hästens skala eftersom
    }												     //jag inte vill att man ska förlora utan att hästen synbart är i kontakt med en stock

    public void move()//den här funktionen kontrollerar hastigheten som hästen kommer röra sig i beroende på vilken knapp man trycker på
    {
        if(up && y>200)//det som står efter &&-tecknen är till för att begränsa hästens rörelsyta, man vill inte att den ska kunna dra utanför kanten
            y -= speed+2; //jag har gjort så att man rör sig lite snabbare i y-led för att förbättra "spelkänslan" lite
        if(down && y<(815-height))
            y += speed+2;
        if(left && x>0)
            x -= speed;
        if(right && x<500)
            x += speed;

    }
}