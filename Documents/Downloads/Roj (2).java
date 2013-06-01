import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;


public class Roj extends JFrame implements ActionListener{
        
		//dessa �r iconerna som g�r det m�jligt att se hur m�nga bomber som finn n�ra
	    Icon ett = new ImageIcon("1.gif");
	    Icon tvo = new ImageIcon("2.gif");
	    Icon tre = new ImageIcon("3.gif");
	    Icon fyr = new ImageIcon("4.gif");
	    Icon fem = new ImageIcon("5.gif");
	    Icon sex = new ImageIcon("6.gif");
	    Icon sju = new ImageIcon("7.gif");
	    Icon atta = new ImageIcon("8.gif");
	    
        private boolean forstatryck = true;
        private JPanel snyggt;
        private JButton[][] knapp = new JButton[10][10];
        private int minor[] = new int[4]; //skapar ett tv�-dimensionellt f�lt
        private int minorm[] = new int[4];//skapar ett tv�-dimensionellt f�lt
        private int kolumn=0;
        private int rad=0;     
        private Container c;
        public Roj()
        {
        c = getContentPane();
        
        setLayout(new BorderLayout());
        snyggt = new JPanel(new GridLayout(10,10)); //en spelplan p� 10*10 rutor
        for(int i=0; i<10; i++)
        {      
               for(int m=0; m<10; m++){
                      knapp[i][m] = new JButton();
               
                      snyggt.add(knapp[i][m]); 
                      knapp[i][m].addActionListener(this);
                      knapp[i][m].setActionCommand(""+i+m);//ger alla rutorna kordinationer
                      knapp[i][m].setBackground(Color.green);
                      }
        }
               
               
        c.add(snyggt, BorderLayout.CENTER);
        
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        setVisible(true);      
                      
        }

               
        public void actionPerformed(ActionEvent ae)
        {
               String s = ae.getActionCommand();
               int i = Integer.parseInt(s);
               kolumn = i/10; //tar ut kordinater f�r rutan man klickat p�
               rad = i%10;    //tar ut kordinater f�r rutan man klickat p�
               
               if (forstatryck){ //ser till att man inte g�r p� en bomb p� f�rsta trycket
                      Random rnd = new Random();  //skapar allts� bomberna efter trycket
                      for(int n=0; n<3; n++)
                      {
                             for(int m=0; m<3; m++){
                             int mina=100+n;
                             minor[n] = rnd.nextInt(10); //v�ljer ut en random krodination f�r bomb
                             minorm[m] = rnd.nextInt(10);//v�ljer ut en random krodination f�r bomb

                             if(minorm[m]==i%10 && minor[n]==i/10){//det f�r inte finnas en bomb p� f�rsta rutan man klickat
                                    m--;   
                             }
                             knapp[minor[n]][minorm[m]].setActionCommand(""+mina);//alla bomber f�r ett v�rde som �r �ver 100, 
                             //tyv�r �r detta �ven kordinaten vilket g�r att jag m�ste g�ra om en del saker sedan, till nim n�sta inl�mning, om jag f�r dvs
                            // knapp[minor[n]][minorm[m]].setBackground(Color.red); //ta bort detta f�r att slippa se minor
                      }
                      }
               forstatryck = false;   
               }
               
                      if(i>=100)//om mAn klickat p� en bomb har man f�rlorat.
                      {
                      
                      for(int n=0; n<10; n++){
                             for(int m=0; m<10; m++){
                      knapp[n][m].setEnabled(false);}
                      }
                      for(int m=0; m<10; m++){
                    	  for(int n=0; n<10; n++){
                    		 String s2 = knapp[n][m].getActionCommand();
                    		 int i2 = Integer.parseInt(s2);
                    		 if(i2>=100){
                    			 knapp[n][m].setBackground(Color.red);
                    		  }
                    	  }
                      }
                      }
               
                      else
                      {
                    	 //h�r b�rjar den troligtvis on�digt komplicerade delen  
                    	  Queue<Integer> raden = new LinkedList<Integer>(); //�r till f�r att man ska kunna l�gga in vilka rutor som ska unders�kas 
                    	  Queue<Integer> kolumnen = new LinkedList<Integer>(); //�r till f�r att man ska kunna l�gga in vilka rutor som ska unders�kas 
                    	  
                    	  
                    	  raden.add(rad); 		//l�gger in knappen man tryckt p� som f�rsta s�kobjekt
                    	  kolumnen.add(kolumn); //l�gger in knappen man tryckt p� som f�rsta s�kobjekt
                    	  knapp[kolumn][rad].setEnabled(false); //s�tter den till false, bara f�r att detta h�nder i riktiga r�j, dock utan "nyansf�rlust"
                    	 
                    	  
                    	while(!(raden.isEmpty())){//s� l�nge det finns objekt i k�n som ska unders�kas s� g�r satsen runt.
                    	  
                    	  int nb; // antal-bomber
                    	  
                    	  nb=antalbomb(kolumnen.peek(), raden.peek());
                    	  knapp[kolumnen.peek()][raden.peek()].setEnabled(false);
                    	  rad=raden.poll();
                    	  kolumn=kolumnen.poll();
                    	  
                    	 if(nb==0){
                    		 
                    		 int m = 1; //dessa �r endast till f�r att det inte ska unders�kas utanf�r kanterna
                    		 int k = 1;
                    		 int n = 1;
                    		 int h = 1;
                    		 
                    		 if(rad == 0){ m=0; }//dessa �r endast till f�r att det inte ska unders�kas utanf�r kanterna
                    		 if(rad == 9){ n=0; }
                    		 if(kolumn == 0){ k=0; }
                    		 if(kolumn == 9){ h=0; }
                    		
                    		  if(knapp[kolumn-k][rad-m].isEnabled()){ // om den redan kollat den h�r knappen s� �r den avst�ngd och beh�ver inte kollas igen
                    		  raden.add(rad-m);       //l�gger till kordinater f�r n�sta knap i k�n
                    		  kolumnen.add(kolumn-k);}//l�gger till kordinater f�r n�sta knap i k�n
                    		
                    		  if(knapp[kolumn-k][rad].isEnabled()){  
                    		  raden.add(rad);
                    		  kolumnen.add(kolumn-k);}
                    		  
                    		  if(knapp[kolumn-k][rad+n].isEnabled()){  
                    		  raden.add(rad+n);
                    		  kolumnen.add(kolumn-k);}
                    		  
                    		  if(knapp[kolumn][rad-m].isEnabled()){
                    		  kolumnen.add(kolumn);
                    		  raden.add(rad-m);}
                    		  
                    		  if(knapp[kolumn][rad+n].isEnabled()){
                    		  kolumnen.add(kolumn);
                    		  raden.add(rad+n);}
                    		  
                    		  if(knapp[kolumn+h][rad+n].isEnabled()){
                    		  raden.add(rad+n);
                    		  kolumnen.add(kolumn+h);}
                    		  
                    		  if(knapp[kolumn+h][rad].isEnabled()){
                    		  raden.add(rad);
                    		  kolumnen.add(kolumn+h);}
                    		  
                    		  if(knapp[kolumn+h][rad-m].isEnabled()){
                    		  raden.add(rad-m);
                    		  kolumnen.add(kolumn+h);}
                    		  
                    	  	}
                    	  
                      }                                            
                        
        }}
        
        
        public static void main(String[] args){
               new Roj();
               }
        
        
        public int antalbomb(int kolumn, int rad){
        	
    	  
    	 
    		 
    	     knapp[kolumn][rad].setBackground(Color.white);
             
            
             int uppe=-1; //dessa �r endast till f�r att det inte ska unders�kas utanf�r kanterna
             int nere=2;
             int vanster=-1;
             int hoger=2;
             
             if(rad==0){uppe=0;} //dessa �r endast till f�r att det inte ska unders�kas utanf�r kanterna
             if(rad==9){nere=1;}
             if(kolumn==0){vanster=0;}
             if(kolumn==9){hoger=1;}
             
             int antalbomb=0;
             
             for(int m=uppe; m<nere; m++){
            	 for(int n=vanster; n<hoger; n++){
            		 
               
        	
        	int pi = Integer.parseInt(knapp[kolumn+n][rad+m].getActionCommand()); 
        	if(pi >= 100){antalbomb++;} //r�knar antalet bomber i n�rheten
        	
        		
                 }}
             
             
             if(antalbomb ==0){knapp[kolumn][rad].setBackground(Color.yellow);} //s�tter ut nummer beroende p� hur m�nga bomber som ligger n�ra
             else if(antalbomb ==1){ knapp[kolumn][rad].setIcon(ett);}
             else if(antalbomb ==2){ knapp[kolumn][rad].setIcon(tvo);}
             else if(antalbomb ==3){ knapp[kolumn][rad].setIcon(tre);}
             else if(antalbomb ==4){ knapp[kolumn][rad].setIcon(fyr);}
             else if(antalbomb ==5){ knapp[kolumn][rad].setIcon(fem);}
             else if(antalbomb ==6){ knapp[kolumn][rad].setIcon(sex);}
             else if(antalbomb ==7){ knapp[kolumn][rad].setIcon(sju);}
             else if(antalbomb ==8){ knapp[kolumn][rad].setIcon(atta);}
      
       
    	  return antalbomb;
       }    	
               
         
}
