package head;

import java.util.Vector;

public class ActiveAccount {

	public static Vector<String> vActiveAccount=new Vector<>();
	public static void LogIn(String a){
		if(vActiveAccount.indexOf(a)!=-1){
			;
		}
		else{
			vActiveAccount.add(a);
		}
	}
	public static void LogOut(String a){
		vActiveAccount.remove(a);
	}
	public static Vector<String> GetActiveAccount(){
		for(int i=0;i<vActiveAccount.size();i++){
			System.out.println(vActiveAccount.get(i));
		}
		return vActiveAccount;
	}
}
