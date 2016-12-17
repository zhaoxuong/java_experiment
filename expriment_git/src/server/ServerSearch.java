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
import def.*;


public class ServerSearch extends JFrame {

	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	YouDaodef resultYouDao = new YouDaodef();
	JinShan_def resultJinShan = new JinShan_def();
	Bing_def resultBing = new Bing_def();
	
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
			ServerSocket serverSocketSearch = new ServerSocket(10086);
			jtaLog.append(new Date() + ": Server started at socket 10086\n");
			ServerSocket serverSocketFriend = new ServerSocket(10087);
			jtaLog.append(new Date() + ": Server started at socket 10087\n");
			ServerSocket serverSocketWords = new ServerSocket(10088);
			jtaLog.append(new Date() + ": Server started at socket 10088\n");
			while (true) {
				Socket clientSearch = serverSocketSearch.accept();
				Socket clientFriend = serverSocketFriend.accept();
				Socket clientWords = serverSocketWords.accept();
				jtaLog.append(new Date() + ": A client connected\n");
				jtaLog.append("The client's IP address " + clientSearch.getInetAddress().getHostAddress() + '\n');
				TaskSearch taskSearch = new TaskSearch(clientSearch);
				new Thread(taskSearch).start();
				TaskFriend taskFriend = new TaskFriend(clientFriend);
				new Thread(taskFriend).start();
				TaskWords taskWords = new TaskWords(clientWords);
				new Thread(taskWords).start();
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("70: "+e);
		}
	}

	class TaskSearch implements Runnable, Constant {
		private Socket client;
		ObjectInputStream fromClient;
		ObjectOutputStream toClient;
		DataOutputStream toClient1;

		public TaskSearch(Socket client) {
			// TODO Auto-generated constructor stub
			this.client = client;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					ActiveAccount.GetActiveAccount();
					fromClient = new ObjectInputStream(client.getInputStream());
					Object object = fromClient.readObject();
					object=updateWordZanFromDatabase(object);
					toClient = new ObjectOutputStream(client.getOutputStream());
					toClient1=new DataOutputStream(client.getOutputStream());
					toClient.writeObject(object);
					//object还是不能写基本数据类型
					if(((WordZan)object).getType()){
						
						resultJinShan.getJinShanValue(((WordZan)object).getWord());
						toClient1.writeInt(resultJinShan.getCount());
						for(int i=0;i<resultJinShan.getCount();i++){
							toClient1.writeUTF((resultJinShan.getGetresult())[i]);
						}

						resultYouDao.getYouDaoValue(((WordZan)object).getWord());
						toClient1.writeInt(resultYouDao.getCount());
						for(int i=0;i<resultYouDao.getCount();i++){
							toClient1.writeUTF((resultYouDao.getGetresult())[i]);
						}
						
						resultBing.getBingValue(((WordZan)object).getWord());
						toClient1.writeInt(resultBing.getCount());
						for(int i=0;i<resultBing.getCount();i++){
							toClient1.writeUTF((resultBing.getGetresult())[i]);
						}
					}
					else{//只传回WordZan就行了，不用传解释
						;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.105: "+e);
			}
		}
		public Object updateWordZanFromDatabase(Object o){
			String a = ((WordZan) o).getWord();
			int tbaidu=0;
			int tyoudao=0;
			int tbing=0;

			//数据库主键问题，或者直接在这里控制
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT word, baidu, youdao, bing FROM [dbo].[WordZan] WHERE word='" + a + "'");
				if (resultSet.next()) {
					tbaidu=resultSet.getInt(2);
					tyoudao=resultSet.getInt(3);
					tbing=resultSet.getInt(4);
				} 
				else {
					statement.executeUpdate(
							"INSERT  [dbo].[WordZan] ( [word], [baidu],[youdao],[bing] ) VALUES  ( '" + a + "', 0,0,0);");
				}
				if(((WordZan)o).getType()){
					//从客户端发来的都是0不用比较大小
					((WordZan)o).setBaidu(tbaidu);
					((WordZan)o).setYoudao(tyoudao);
					((WordZan)o).setBing(tbing);
				}
				else{
					if(((WordZan)o).getBaidu()>tbaidu){
						tbaidu=((WordZan)o).getBaidu();
						statement.executeUpdate("UPDATE WordZan SET baidu="+tbaidu+" WHERE word='" + a + "'");
					}else{
						((WordZan)o).setBaidu(tbaidu);
					}
					if(((WordZan)o).getYoudao()>tyoudao){
						tbaidu=((WordZan)o).getYoudao();
						statement.executeUpdate("UPDATE WordZan SET youdao="+tyoudao+" WHERE word='" + a + "'");
					}else{
						((WordZan)o).setYoudao(tyoudao);;
					}
					if(((WordZan)o).getBing()>tbing){
						tbaidu=((WordZan)o).getBing();
						statement.executeUpdate("UPDATE WordZan SET bing="+tbing+" WHERE word='" + a + "'");
					}else{
						((WordZan)o).setBing(tbing);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return o;
		}

	}
	class TaskFriend implements Runnable, Constant {
		private Socket client;
		DataInputStream fromClient;
		DataOutputStream toClient;

		public TaskFriend(Socket client) {
			// TODO Auto-generated constructor stub
			this.client = client;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					fromClient = new DataInputStream(client.getInputStream());
					fromClient.readInt();
					toClient = new DataOutputStream(client.getOutputStream());
					Vector<String> v=ActiveAccount.GetActiveAccount();
					toClient.writeInt(v.size());
					for(int i=0;i<v.size();i++){
						toClient.writeUTF(v.get(i));
					}
				}
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.208: "+e);
			}
		}
	}
	class TaskWords implements Runnable, Constant {
		private Socket client;
		ObjectInputStream fromClient;
		ObjectOutputStream toClient;
		DataOutputStream toClient1;

		public TaskWords(Socket client) {
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
					if(((WordCard)object).getType()==ADD){
						AllWordCard.Add(((WordCard)object));
					}
					else if(((WordCard)object).getType()==DELETE){
						AllWordCard.Delete(((WordCard)object));
					}
					else{//更新，把自己的用户名发过来
						toClient = new ObjectOutputStream(client.getOutputStream());
						toClient1=new DataOutputStream(client.getOutputStream());
						Vector<WordCard> tVector=AllWordCard.GetAccountCard(((WordCard)object).getAccount());
						toClient1.writeInt(tVector.size());
						for(int i=0;i<tVector.size();i++){
							toClient.writeObject(tVector.get(i));
						}
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.105: "+e);
			}
		}
	}
}

