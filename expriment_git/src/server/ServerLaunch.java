package server;

import java.sql.*;

import head.Constant;

public class ServerLaunch implements Constant{
	
	public static void main(String[] args){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(driverName);
			System.out.println("���������ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}
		try {
			connection = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("�������ݿ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("SQLServer����ʧ�ܣ�");
		}
		
		Runnable signIn=new LaunchSignIn(connection);
		Runnable signUp=new LaunchSignUp(connection);
		Runnable search=new LaunchSearch(connection);
		
		Thread signInThread=new Thread(signIn);//��¼10084
		Thread signUpThread=new Thread(signUp);//10085
		Thread searchThread=new Thread(search);//10086
		
		signInThread.start();
		signUpThread.start();
		searchThread.start();
		
	}
}
class LaunchSearch implements Runnable{
	Connection connection;
	public LaunchSearch(Connection c) {
		connection=c;
	}
	public void run() {
		new ServerSearch(connection);
	}
}
class LaunchSignIn implements Runnable{
	Connection connection;
	public LaunchSignIn(Connection c){
		connection=c;
	}
	public void run() {
		new ServerSignIn(connection);
	}	
}
class LaunchSignUp implements Runnable{
	Connection connection;
	public LaunchSignUp(Connection c) {
		connection=c;
	}
	public void run(){
		new ServerSignUp(connection);
	}
}
