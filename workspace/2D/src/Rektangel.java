import java.awt.*;
import java.awt.geom.*;

public class Rektangel extends Canvas{
	private Rectangle2D rektangel;
	
	public Rektangel(){
		rektangel = new Rectangle2D.Double(200, 100, 200, 50);
	}
	
	public void paint(Graphics g){
		//g�r om Graphics-objektet till Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		//�ndrar f�rgen till svart
		g2.setColor(Color.black);
		//anger tjockleken p� linjer
		g2.setStroke(new BasicStroke(5));
		//draw() ritar ut konturerna hos rektangeln
		//med tjockleken 5 och svart f�rg
		g2.draw(rektangel);

		//�ndrar f�rgen till gr�n
		g2.setColor(Color.green);
		//fyller i rektangelns inneh�ll med gr�n f�rg
		g2.fill(rektangel);

	}
	
}
