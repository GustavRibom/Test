import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tokenizer4{
	private int nrDoc;
	
	Tokenizer4() {
		
		File folder = new File("/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/");
		File[] listOfFiles = folder.listFiles(); 
		
		nrDoc = listOfFiles.length;

		Process pr;

		try {
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				pr = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/tokenizer < " +
						"/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/"+listOfFiles[i].getName()+" | " +
								"sort -u >> /afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/temp"});
				pr.waitFor();

			}
			pr = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "sort -u /afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/temp > " +
					"/afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/vektorrymd"});
			pr.waitFor();

			pr = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "rm /afs/nada.kth.se/home/3/u1qvl923/workspace/Kex/dokument/temp"});
			pr.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	int getNrDoc(){
		return nrDoc;
	}
}
