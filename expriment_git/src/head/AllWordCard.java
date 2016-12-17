package head;

import java.util.Vector;

public class AllWordCard {

	public static Vector<WordCard> vCard=new Vector<>();
	public static void Add(WordCard w){
		if(vCard.indexOf(w)!=-1){
			;
		}
		else{
			vCard.add(w);
		}
	}
	public static void Delete(WordCard w){
		for(int i=0;i<vCard.size();i++){
			if((vCard.get(i).getAccount().equals(w.getAccount()))&&
					(vCard.get(i).getWord().equals(w.getWord()))&&
					(vCard.get(i).getBest()==w.getBest())){
				vCard.remove(i);
			}
		}
	}
	public static Vector<WordCard> GetAllCard(){
		return vCard;
	}
	public static Vector<WordCard> GetAccountCard(String a){
		Vector<WordCard> tVector=new Vector<>();
		for(int i=0;i<vCard.size();i++){
			if(vCard.get(i).getAccount().equals(a)){
				tVector.add(vCard.get(i));
			}
		}
		return tVector;
	}
}
