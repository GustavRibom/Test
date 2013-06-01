
public class BagofWords {
	BagofWords(){
	Tokenizer4 myTok = new Tokenizer4();
	VektorIndex myIndex = new VektorIndex();
	VektorDoc myVDoc = new VektorDoc(myTok.getNrDoc(), myIndex.getMap());
	}
}
