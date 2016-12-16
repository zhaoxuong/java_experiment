package server;

import java.io.*;
import java.net.*;
import java.sql.Connection;

import javax.swing.*;
import java.util.Date;
import java.awt.*;

import head.*;

public class ServerSearch extends JFrame {

	Connection connection = null;
	
	public ServerSearch(Connection c) {
		connection=c;
		JTextArea jtaLog = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jtaLog);
		add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("SearchServer");
		setLocation(1040, 0);
		setVisible(true);
		try {
			ServerSocket serverSocket = new ServerSocket(10086);
			jtaLog.append(new Date() + ": Server started at socket 10086\n");
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
		ObjectOutputStream toClient;

		public Task(Socket client) {
			// TODO Auto-generated constructor stub
			this.client = client;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					fromClient = new ObjectInputStream(client.getInputStream());
					Object object = fromClient.readObject();
//					object = Data.wordData.update((WordZan) object);
					toClient = new ObjectOutputStream(client.getOutputStream());
					toClient.writeObject(object);
					toClient.writeObject(new String(((WordZan)object).getWord()));
					toClient.writeObject(new String(((WordZan)object).getWord()));
					toClient.writeObject(new String(((WordZan)object).getWord()));
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
