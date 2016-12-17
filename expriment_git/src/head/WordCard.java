package head;

import java.io.Serializable;

public class WordCard implements Serializable, Constant{

	private String account;
	private String word;
	private int best;
	private int type;//��ӻ���ɾ�����߻�ȡ�����£�
	
	public WordCard(String a,String w,int b,int t){
		account=new String(a);
		word=new String(w);
		best=b;
		type=t;
	}
	public String getAccount(){
		return account;
	}
	public String getWord(){
		return word;
	}
	public int getBest(){
		return best;
	}
	public void setType(int t){
		type=t;
	}
	public int getType(){
		return type;
	}
}
