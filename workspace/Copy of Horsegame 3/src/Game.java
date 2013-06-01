import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game 
{
     JButton restart;
     JLabel time;
     JFrame window;
     Eventrit panel;
     boolean gameon;
     //ActionListener rs;
     
    public Game()
    {
    	
    	JPanel f = new JPanel();
        window = new JFrame("HorseGame!");
        panel = new Eventrit();
       
        //panel.add(panel.test);
       
        //time = new JLabel("Tid:");
        //restart = new JButton("Restart");
        //restart.addActionListener(this);
        //restart.setActionCommand("restart");
        window.setLayout(new BorderLayout());
        //window.setLayout(BorderLayout.NORTH(new FlowLayout));
        window.setSize(800,890);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(panel, BorderLayout.CENTER);
        
        f.add(panel.restart);
        f.add(panel.tid);
        f.add(panel.score);
        //f.add(time);
        window.add(f, BorderLayout.NORTH);
        
        f.setFocusable(false);
        panel.restart.setFocusable(false);
        //restart.addActionListener(rs);
        //restart.setFocusable(false);
        window.setVisible(true);
    }
    
    public void go()
    {
        panel.startGame();
    }

    public static void main(String[]args)
    {
        Game game = new Game();
        game.go();
    }
	
		
	
	
    

}
