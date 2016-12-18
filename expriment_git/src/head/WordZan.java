package head;

import java.awt.SecondaryLoop;
import java.io.*;

public class WordZan implements Serializable{//某个单词的赞数目
	private String account;
	private String word=" ";
	
	private int baidu=0;
	private int youdao=0;
	private int bing=0;
	
	private boolean zanbaidu=false;
	private boolean zanyoudao=false;
	private boolean zanbing=false;
	
	
	private boolean type;//true: 搜索 false: 点赞
	public void SetZanbaidu(boolean b){
		zanbaidu=b;
	}
	public boolean GetZanbaidu(){
		return zanbaidu;
	}
	public void SetZanyoudao(boolean b){
		zanyoudao=b;
	}
	public boolean GetZanyoudao(){
		return zanyoudao;
	}
	public void SetZanbing(boolean b){
		zanbing=b;
	}
	public boolean GetZanbing(){
		return zanbing;
	}
	public void SetAccount(String a){
		account=a;
	}
	public String GetAccount(){
		return account;
	}
	public WordZan(){
		;
	}
	public WordZan(String w){
		word=w;
	}
	public void update(WordZan w){//赞数跟新
		baidu=w.getBaidu();
		youdao=w.getYoudao();
		bing=w.getBing();
		type=w.getType();
		zanbaidu=w.GetZanbaidu();
		zanyoudao=w.GetZanyoudao();
		zanbing=w.GetZanbing();
		word=w.getWord();
	}
	public String getWord(){
		return word;
	}
	public void setWord(String word){
		this.word=word;
	}
	public int getBaidu(){
		return baidu;
	}
	public void setBaidu(int n){
		baidu=n;
	}
	public void addBaidu(){
		baidu++;
	}
	
	public int getYoudao(){
		return youdao;
	}
	public void setYoudao(int n){
		youdao=n;
	}
	public void addYoudao(){
		youdao++;
	}
	public void deYoudao(){
		youdao--;
	}
	public int getBing(){
		return bing;
	}
	public void setBing(int n){
		bing=n;
	}
	public void addBing(){
		bing++;
	}
	public void deBing(){
		bing--;
	}
	public boolean getType(){
		return type;
	}
	public void setType(boolean b){
		type=b;
	}
	public void deBaidu() {
		// TODO Auto-generated method stub
		baidu--;
	}
	public int getbest(){
		int max = (baidu > youdao) ? baidu : youdao;
		max = (max > bing) ? max : bing;
		if(max==baidu) return 1;
		else
		if(max==youdao) return 2;
		else 
			return 3;
	}
	
}
