import java.awt.BorderLayout;
import javax.swing.*;
public class Game 
{
     JFrame window;
     Eventrit panel;
     
    public Game()//i konstruktorn så sätter jag upp panelen
    {
    	
    	JPanel f = new JPanel();//den här panelen skapar jag för att kunna lägga mer än 1 sak i window-s BorderLayout.NORTH
        window = new JFrame("HorseGame!");//här skapar jag själva rutan
        panel = new Eventrit();			//en panel som också har egenskaperna som finns i Eventrit.java
       
        window.setLayout(new BorderLayout());//väljer borderlayuot för window
        window.setSize(800,890);			//storlek på rutan
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(panel, BorderLayout.CENTER);//lägger till panelen med själva spelet
        
        f.add(panel.restart);//lägger till restart-knapp 
        f.add(panel.tid);//och tidtagning och poängräkning
        f.add(panel.score);//till f-panelen
        window.add(f, BorderLayout.NORTH);//lägger f-panelen i BorderLayout.NORTH
        
        panel.restart.setFocusable(false);//gör att fokus inte hamnar på restart-knappen; om det händer går det inte att styra hästen!
 
        window.setVisible(true);
    }
    
    public void go()
    {
        panel.startGame();//kör funktionen somm sätter igång spelet
    }

    public static void main(String[]args)
    {
        Game game = new Game();//klassisk JFrame sak som måste va med, det måste ju finnas ett objekt av Game 
        game.go();			   //för att det ska kunna bli någon ruta av det hela
    }

}
