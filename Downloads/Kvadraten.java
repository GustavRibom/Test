
public class Kvadraten {
	int counter = 2;
	int s;
	int[][] matris;
	
	public Kvadraten(){
		
	
		int n=3;
		s=(int) Math.pow(2, n);
	
		matris = new int[s+1][s+1];
		
		matris[s/2][s/2]=1;
		matris[s/2][s/2-1]=1;
		matris[s/2-1][s/2-1]=1;
		int posx, posy;
		
		put(s/2, s/2-1, s/2-1, 0);
		
		
		
		for(int i=0; i<s; i++){
			for(int j=0; j<s; j++){
				System.out.print(matris[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void put(int side, int posx, int posy, int hur){
		if(side>4){
			put(side/2, side/2, side/2, 1);//vänster upp
			put(side/2, side+side/2+1, side/2, 0);//höger upp
			put(side/2, side/2, side+side/2+1, 0);//vänster ner
			put(side/2, side+side/2+1, side+side/2+1, 2);//höger ner
		
		}
		else{
			if(hur==0){//vänster ner, höger upp
				posx++;
				for(int i=0; i<3; i++){
					matris[posx+1+i][posy-i]=counter;
					matris[posx+1+i][posy-1-i]=counter;
					matris[posx+i][posy-1-i]=counter;
					counter++;
				}
				matris[posx][posy-2]=counter;
				matris[posx][posy-3]=counter;
				matris[posx+1][posy-3]=counter;
				counter++;
				matris[posx+2][posy]=counter;
				matris[posx+3][posy]=counter;
				matris[posx+3][posy-1]=counter;
				counter++;
				
				
			}
			else if(hur==1){//vänster upp
				for(int i=0; i<3; i++){
					matris[posx-1-i][posy-i]=counter;
					matris[posx-1-i][posy-1-i]=counter;
					matris[posx-i][posy-1-i]=counter;
					counter++;
				}
				matris[posx-2][posy]=counter;
				matris[posx-3][posy]=counter;
				matris[posx-3][posy-1]=counter;
				counter++;
				matris[posx][posy-2]=counter;
				matris[posx][posy-3]=counter;
				matris[posx-1][posy-3]=counter;
				counter++;
				
			}
			else{//höger ner
				System.out.println("HEE");
				posy++;
				posx++;
				for(int i=0; i<3; i++){
					matris[posx+i][posy+1+i]=counter;
					matris[posx+1+i][posy+1+i]=counter;
					matris[posx+1+i][posy+i]=counter;
					counter++;
				}
				matris[posx][posy+2]=counter;
				matris[posx][posy+3]=counter;
				matris[posx+1][posy+3]=counter;
				counter++;
				matris[posx+2][posy]=counter;
				matris[posx+3][posy]=counter;
				matris[posx+3][posy+1]=counter;
				counter++;
			}
			
		}
		
		
	}
	
}
