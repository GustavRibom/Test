import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;


public class Roj extends JFrame implements ActionListener{
        
		//dessa är iconerna som gör det möjligt att se hur många bomber som finn nära
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
        private int minor[] = new int[4]; //skapar ett två-dimensionellt fält
        private int minorm[] = new int[4];//skapar ett två-dimensionellt fält
        private int kolumn=0;
        private int rad=0;     
        private Container c;
        public Roj()
        {
        c = getContentPane();
        
        setLayout(new BorderLayout());
        snyggt = new JPanel(new GridLayout(10,10)); //en spelplan på 10*10 rutor
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
               kolumn = i/10; //tar ut kordinater för rutan man klickat på
               rad = i%10;    //tar ut kordinater för rutan man klickat på
               
               if (forstatryck){ //ser till att man inte går på en bomb på första trycket
                      Random rnd = new Random();  //skapar alltså bomberna efter trycket
                      for(int n=0; n<3; n++)
                      {
                             for(int m=0; m<3; m++){
                             int mina=100+n;
                             minor[n] = rnd.nextInt(10); //väljer ut en random krodination för bomb
                             minorm[m] = rnd.nextInt(10);//väljer ut en random krodination för bomb

                             if(minorm[m]==i%10 && minor[n]==i/10){//det får inte finnas en bomb på första rutan man klickat
                                    m--;   
                             }
                             knapp[minor[n]][minorm[m]].setActionCommand(""+mina);//alla bomber får ett värde som är över 100, 
                             //tyvär är detta även kordinaten vilket gör att jag måste göra om en del saker sedan, till nim nästa inlämning, om jag får dvs
                            // knapp[minor[n]][minorm[m]].setBackground(Color.red); //ta bort detta för att slippa se minor
                      }
                      }
               forstatryck = false;   
               }
               
                      if(i>=100)//om mAn klickat på en bomb har man förlorat.
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
                    	 //här börjar den troligtvis onödigt komplicerade delen  
                    	  Queue<Integer> raden = new LinkedList<Integer>(); //är till för att man ska kunna lägga in vilka rutor som ska undersökas 
                    	  Queue<Integer> kolumnen = new LinkedList<Integer>(); //är till för att man ska kunna lägga in vilka rutor som ska undersökas 
                    	  
                    	  
                    	  raden.add(rad); 		//lägger in knappen man tryckt på som första sökobjekt
                    	  kolumnen.add(kolumn); //lägger in knappen man tryckt på som första sökobjekt
                    	  knapp[kolumn][rad].setEnabled(false); //sätter den till false, bara för att detta händer i riktiga röj, dock utan "nyansförlust"
                    	 
                    	  
                    	while(!(raden.isEmpty())){//så länge det finns objekt i kön som ska undersökas så går satsen runt.
                    	  
                    	  int nb; // antal-bomber
                    	  
                    	  nb=antalbomb(kolumnen.peek(), raden.peek());
                    	  knapp[kolumnen.peek()][raden.peek()].setEnabled(false);
                    	  rad=raden.poll();
                    	  kolumn=kolumnen.poll();
                    	  
                    	 if(nb==0){
                    		 
                    		 int m = 1; //dessa är endast till för att det inte ska undersökas utanför kanterna
                    		 int k = 1;
                    		 int n = 1;
                    		 int h = 1;
                    		 
                    		 if(rad == 0){ m=0; }//dessa är endast till för att det inte ska undersökas utanför kanterna
                    		 if(rad == 9){ n=0; }
                    		 if(kolumn == 0){ k=0; }
                    		 if(kolumn == 9){ h=0; }
                    		
                    		  if(knapp[kolumn-k][rad-m].isEnabled()){ // om den redan kollat den här knappen så är den avstängd och behöver inte kollas igen
                    		  raden.add(rad-m);       //lägger till kordinater för nästa knap i kön
                    		  kolumnen.add(kolumn-k);}//lägger till kordinater för nästa knap i kön
                    		
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
             
            
             int uppe=-1; //dessa är endast till för att det inte ska undersökas utanför kanterna
             int nere=2;
             int vanster=-1;
             int hoger=2;
             
             if(rad==0){uppe=0;} //dessa är endast till för att det inte ska undersökas utanför kanterna
             if(rad==9){nere=1;}
             if(kolumn==0){vanster=0;}
             if(kolumn==9){hoger=1;}
             
             int antalbomb=0;
             
             for(int m=uppe; m<nere; m++){
            	 for(int n=vanster; n<hoger; n++){
            		 
               
        	
        	int pi = Integer.parseInt(knapp[kolumn+n][rad+m].getActionCommand()); 
        	if(pi >= 100){antalbomb++;} //räknar antalet bomber i närheten
        	
        		
                 }}
             
             
             if(antalbomb ==0){knapp[kolumn][rad].setBackground(Color.yellow);} //sätter ut nummer beroende på hur många bomber som ligger nära
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
