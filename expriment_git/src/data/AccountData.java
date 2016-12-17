package data;

import head.Account;
import java.util.*;

public class AccountData {
	private Vector<Account> vector=new Vector<>();
	public AccountData(){
		;
	}
	public void update(Account a){
		int i=0;
		for(;i<vector.size();i++){
			if(vector.get(i).getAccount().equals(a.getAccount())){
				vector.get(i).setPassWord(a.getPassword());
			}
		}
		if(i==vector.size()){
			vector.add(a);
		}
	}
	public Vector<Account> getVector(){
		return vector;
	}
}
