import java.awt.*;

import javax.swing.*;

class GrafikExempelEtt extends JFrame{
	private Rektangel rita;
	
	public GrafikExempelEtt(){
		setSize(500, 500);
		Container c = getContentPane();

		//skapar ett objekt av v�r Canvas-klass Duk
		rita = new Rektangel();
		//�och l�gger till det i f�nstret
		c.add(rita);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GrafikExempelEtt();
	}
}

