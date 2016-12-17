package head;

import java.io.*;

public class WordZan implements Serializable{//某个单词的赞数目
	private String word="";
	private int baidu=0;
	private int youdao=0;
	private int bing=0;
	private boolean type;//true: 搜索 false: 点赞
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
	public int getBing(){
		return bing;
	}
	public void setBing(int n){
		bing=n;
	}
	public void addBing(){
		bing++;
	}
	public boolean getType(){
		return type;
	}
	public void setType(boolean b){
		type=b;
	}
}
