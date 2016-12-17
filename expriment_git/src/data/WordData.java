package data;

import head.WordZan;
import java.util.*;

public class WordData {
	private Vector<WordZan> vector=new Vector<>();
	public WordData(){
		;
	}
	public WordZan update(WordZan w){
		for(int i=0;i<vector.size();i++){
			if(vector.get(i).equals(w)){
				vector.get(i).update(w);
				return vector.get(i);
			}
		}
		vector.add(w);
		return w;
	}
}
