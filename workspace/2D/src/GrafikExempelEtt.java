import java.awt.*;

import javax.swing.*;

class GrafikExempelEtt extends JFrame{
	private Rektangel rita;
	
	public GrafikExempelEtt(){
		setSize(500, 500);
		Container c = getContentPane();

		//skapar ett objekt av vår Canvas-klass Duk
		rita = new Rektangel();
		//…och lägger till det i fönstret
		c.add(rita);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GrafikExempelEtt();
	}
}

