import java.awt.*;
import java.awt.geom.*;

public class Rektangel extends Canvas{
	private Rectangle2D rektangel;
	
	public Rektangel(){
		rektangel = new Rectangle2D.Double(200, 100, 200, 50);
	}
	
	public void paint(Graphics g){
		//gör om Graphics-objektet till Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		//ändrar färgen till svart
		g2.setColor(Color.black);
		//anger tjockleken på linjer
		g2.setStroke(new BasicStroke(5));
		//draw() ritar ut konturerna hos rektangeln
		//med tjockleken 5 och svart färg
		g2.draw(rektangel);

		//ändrar färgen till grön
		g2.setColor(Color.green);
		//fyller i rektangelns innehåll med grön färg
		g2.fill(rektangel);

	}
	
}
