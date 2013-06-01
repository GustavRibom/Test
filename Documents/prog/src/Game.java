import java.awt.BorderLayout;
import javax.swing.*;
public class Game 
{
     JFrame window;
     Eventrit panel;
     
    public Game()//i konstruktorn s� s�tter jag upp panelen
    {
    	
    	JPanel f = new JPanel();//den h�r panelen skapar jag f�r att kunna l�gga mer �n 1 sak i window-s BorderLayout.NORTH
        window = new JFrame("HorseGame!");//h�r skapar jag sj�lva rutan
        panel = new Eventrit();			//en panel som ocks� har egenskaperna som finns i Eventrit.java
       
        window.setLayout(new BorderLayout());//v�ljer borderlayuot f�r window
        window.setSize(800,890);			//storlek p� rutan
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(panel, BorderLayout.CENTER);//l�gger till panelen med sj�lva spelet
        
        f.add(panel.restart);//l�gger till restart-knapp 
        f.add(panel.tid);//och tidtagning och po�ngr�kning
        f.add(panel.score);//till f-panelen
        window.add(f, BorderLayout.NORTH);//l�gger f-panelen i BorderLayout.NORTH
        
        panel.restart.setFocusable(false);//g�r att fokus inte hamnar p� restart-knappen; om det h�nder g�r det inte att styra h�sten!
 
        window.setVisible(true);
    }
    
    public void go()
    {
        panel.startGame();//k�r funktionen somm s�tter ig�ng spelet
    }

    public static void main(String[]args)
    {
        Game game = new Game();//klassisk JFrame sak som m�ste va med, det m�ste ju finnas ett objekt av Game 
        game.go();			   //f�r att det ska kunna bli n�gon ruta av det hela
    }

}
