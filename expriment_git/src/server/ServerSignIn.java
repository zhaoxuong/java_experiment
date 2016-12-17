package server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.util.Date;
import java.util.Vector;
import java.awt.*;

import head.*;

public class ServerSignIn extends JFrame{
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public ServerSignIn(Connection c) {
		connection=c;
		JTextArea jtaLog = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jtaLog);
		add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("SignInServer");//µÇÂ¼
		setLocation(520, 0);
		setVisible(true);
		try {
			ServerSocket serverSocket = new ServerSocket(10084);
			jtaLog.append(new Date() + ": Server started at socket 10084\n");
			while (true) {
				Socket client = serverSocket.accept();
				jtaLog.append(new Date() + ": A client connected\n");
				jtaLog.append("The client's IP address " + client.getInetAddress().getHostAddress() + '\n');
				Task task = new Task(client);
				new Thread(task).start();
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

	class Task implements Runnable, Constant {
		private String tAccount;
		private Socket client;
		ObjectInputStream fromClient;
		DataOutputStream toClient;
		public Task(Socket client) {
			this.client = client;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					fromClient=new ObjectInputStream(client.getInputStream());
					Object object=fromClient.readObject();
					boolean b = false;
					tAccount=((Account) object).getAccount();
					b = log(object);
					toClient=new DataOutputStream(client.getOutputStream());
					toClient.writeBoolean(b);
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSignIn.73: "+e);
				ActiveAccount.LogOut(tAccount);
			}
		}
		public boolean log(Object o){
			String a = ((Account) o).getAccount();
			String p = ((Account) o).getPassword();


			try {
				statement = connection.createStatement();
				resultSet = statement
						.executeQuery("SELECT aString, pString FROM [dbo].[Account] WHERE aString='" + a + "' AND pString='" + p + "'");
				if (resultSet.next()) {
					ActiveAccount.LogIn(a);
					return true;
				} 
				else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
}
