package server;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Date;
import java.awt.*;

import head.*;
import data.*;

public class ServerSignIn extends JFrame{
	
	public ServerSignIn() {
		JTextArea jtaLog = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jtaLog);
		add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setTitle("ServerSignIn");
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
					Data.accountData.update((Account)object);
					toClient=new DataOutputStream(client.getOutputStream());
					toClient.writeInt(1);
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println(e);
			}
		}
	}
}
