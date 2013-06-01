import java.io.*;
import java.util.*;


public class VektorIndex {
	private HashMap myMap;
	
	VektorIndex(){
		Process pr;
		try {
			//Vi använder wc -l för att få fram antalet rader då kommandot är effektivt, den kan läsa en fil med 78 miljoner rader på
			// 41 sekunder på Gustavs laptop. Betydligt snabbare än readLines
			pr = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "wc -l /afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/vektorrymd > nrLines"});
			pr.waitFor();
			
			FileInputStream fil = new FileInputStream("/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/nrLines");
			BufferedReader r = new BufferedReader(new InputStreamReader(fil));
			String s = r.readLine();
			String[] s2 = s.split(" ");
			int i = Integer.parseInt(s2[0]);
			fil.close();
			
			fil = new FileInputStream("/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/vektorrymd");
			r = new BufferedReader(new InputStreamReader(fil));
			myMap = new HashMap(i);
			
			for (int j = 0; j < i; j++){
				myMap.put(r.readLine(), j);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	HashMap getMap(){
		return myMap;
	}
}
