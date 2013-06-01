import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;



public class VektorDoc {

	HashMap myMap;
	ArrayList<int[]> docVektorer = new ArrayList<int[]>();
	
	VektorDoc(int AntalDim, HashMap myMap){

		this.myMap = myMap;

		
		File folder = new File("/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/");
		File[] listOfFiles = folder.listFiles();
		int[] tempVektor = new int[AntalDim];

		BufferedReader r;
		String tmp;

		for(File x : listOfFiles){
			java.util.Arrays.fill(tempVektor, 0);
			String str = "/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/"+x.toString();
			try {
				r = new BufferedReader(new InputStreamReader(new FileInputStream(x.toString())));
				while ((tmp = r.readLine()) != null){
					putInVektor(tmp, tempVektor);
					
				}
				docVektorer.add(tempVektor);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(docVektorer.get(0));
		//System.out.println(docVektorer.get(1));
		
	}


		void putInVektor(String line, int[] vektor){
			String[] s = line.split(" ");
			for(String ord : s){
				System.out.println(myMap.get(ord));
				//int i = Integer.parseInt(myMap.get(s).toString());
				//vektor[i] += 1;
			}
			
		}
}
