package head;

import java.io.Serializable;

public class Account implements Serializable{
	private String aString;//Account
	private String pString;//Password
	public Account(String a,String p){
		aString=new String(a);
		pString=new String(p);
	}
	public void setPassWord(String p){
		pString=p;
	}
	public String getAccount(){
		return aString;
	}
	public String getPassword(){
		return pString;
	}
	public boolean equals(Object o){
		if(aString.equals(((Account)o).getAccount())&&pString.equals(((Account)o).getPassword())){
			return true;
		}
		else{
			return false;
		}
	}
}
