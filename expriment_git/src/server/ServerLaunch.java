package server;

public class ServerLaunch {
	
	public static void main(String[] args){
		Runnable search=new LaunchSearch();
		Runnable signIn=new LaunchSignIn();
		Runnable signUp=new LaunchSignUp();
		
		Thread searchThread=new Thread(search);
		Thread signInThread=new Thread(signIn);
		Thread signUpThread=new Thread(signUp);
		
		searchThread.start();
		signInThread.start();
		signUpThread.start();
	}
}
class LaunchSearch implements Runnable{
	public void run() {
		new ServerSearch();
	}
}
class LaunchSignIn implements Runnable{
	public void run() {
		new ServerSignIn();
	}	
}
class LaunchSignUp implements Runnable{
	public void run(){
		new ServerSignUp();
	}
}
