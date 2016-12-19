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
	Statement statement1 = null;
	ResultSet resultSet = null;

	YouDaodef resultYouDao = new YouDaodef();
	JinShan_def resultJinShan = new JinShan_def();
	Bing_def resultBing = new Bing_def();

	public ServerSearch(Connection c) {
		connection = c;
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
			System.err.println("70: " + e);
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
					object = updateWordZanFromDatabase(object);
					toClient = new ObjectOutputStream(client.getOutputStream());
					toClient1 = new DataOutputStream(client.getOutputStream());
					toClient.writeObject(object);
					// object还是不能写基本数据类型
					if (((WordZan) object).getType()) {

						resultJinShan.getJinShanValue(((WordZan) object).getWord());
						toClient1.writeInt(resultJinShan.getCount());
						for (int i = 0; i < resultJinShan.getCount(); i++) {
							toClient1.writeUTF((resultJinShan.getGetresult())[i]);
						}

						resultYouDao.getYouDaoValue(((WordZan) object).getWord());
						toClient1.writeInt(resultYouDao.getCount());
						for (int i = 0; i < resultYouDao.getCount(); i++) {
							toClient1.writeUTF((resultYouDao.getGetresult())[i]);
						}

						resultBing.getBingValue(((WordZan) object).getWord());
						toClient1.writeInt(resultBing.getCount());
						for (int i = 0; i < resultBing.getCount(); i++) {
							toClient1.writeUTF((resultBing.getGetresult())[i]);
						}
					} else {// 只传回WordZan就行了，不用传解释
						;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.105: " + e);
			}
		}

		public Object updateWordZanFromDatabase(Object o) {
			String account = ((WordZan) o).GetAccount();
			String a = ((WordZan) o).getWord();

			try {
				statement = connection.createStatement();
				statement1 = connection.createStatement();
				if (((WordZan) o).getType()) {// 为了得到总的赞数
					resultSet = statement.executeQuery(
							"SELECT word, baidu, youdao, bing FROM [dbo].[WordZan] WHERE word='" + a + "'");
					if (resultSet.next()) {
						((WordZan) o).setBaidu(resultSet.getInt(2));
						((WordZan) o).setYoudao(resultSet.getInt(3));
						((WordZan) o).setBing(resultSet.getInt(4));
					} else {
						statement1
								.executeUpdate("INSERT  [dbo].[WordZan] ( [word], [baidu],[youdao],[bing] ) VALUES  ( '"
										+ a + "', 0,0,0);");
						((WordZan) o).setBaidu(0);
						((WordZan) o).setYoudao(0);
						((WordZan) o).setBing(0);
					}

					resultSet = statement.executeQuery("SELECT baidu, youdao, bing FROM [dbo].[zan] WHERE account='"
							+ account + "' AND word='" + a + "'");
					if (resultSet.next()) {
						if (resultSet.getInt(1) == 1) {
							((WordZan) o).SetZanbaidu(true);
						} else {
							((WordZan) o).SetZanbaidu(false);
						}
						if (resultSet.getInt(2) == 1) {
							((WordZan) o).SetZanyoudao(true);
						} else {
							((WordZan) o).SetZanyoudao(false);
						}
						if (resultSet.getInt(3) == 1) {
							((WordZan) o).SetZanbing(true);
						} else {
							((WordZan) o).SetZanbing(false);
						}
					} else {
						((WordZan) o).SetZanbaidu(false);
						((WordZan) o).SetZanyoudao(false);
						((WordZan) o).SetZanbing(false);
					}
				} else {
					resultSet = statement.executeQuery("SELECT baidu, youdao, bing FROM [dbo].[zan] WHERE account='"
							+ account + "' AND word='" + a + "'");
					if (resultSet.next()) {
						if (resultSet.getInt(1) == 1) {
							if (((WordZan) o).GetZanbaidu() == false) {
								statement1.executeUpdate(
										"UPDATE zan SET baidu=0 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET baidu=baidu-1 WHERE word='" + a + "'");
								((WordZan) o).deBaidu();
							}
						} else {
							if (((WordZan) o).GetZanbaidu() == true) {
								statement1.executeUpdate(
										"UPDATE zan SET baidu=1 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET baidu=baidu+1 WHERE word='" + a + "'");
								((WordZan) o).addBaidu();
							}
						}
						if (resultSet.getInt(2) == 1) {
							if (((WordZan) o).GetZanyoudao() == false) {
								statement1.executeUpdate(
										"UPDATE zan SET youdao=0 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET youdao=youdao-1 WHERE word='" + a + "'");
								((WordZan) o).deYoudao();
							}
						} else {
							if (((WordZan) o).GetZanyoudao() == true) {
								statement1.executeUpdate(
										"UPDATE zan SET youdao=1 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET youdao=youdao+1 WHERE word='" + a + "'");
								((WordZan) o).addYoudao();
							}
						}
						if (resultSet.getInt(3) == 1) {
							if (((WordZan) o).GetZanbing() == false) {
								statement1.executeUpdate(
										"UPDATE zan SET bing=0 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET bing=bing-1 WHERE word='" + a + "'");
								((WordZan) o).deBing();
							}
						} else {
							if (((WordZan) o).GetZanbing() == true) {
								statement1.executeUpdate(
										"UPDATE zan SET bing=1 WHERE account='" + account + "' AND word='" + a + "'");
								statement1.executeUpdate("UPDATE WordZan SET bing=bing+1 WHERE word='" + a + "'");
								((WordZan) o).addBing();
							}
						}
					} else {
						int t1 = 0;
						int t2 = 0;
						int t3 = 0;
						if (((WordZan) o).GetZanbaidu() == true) {
							t1 = 1;
							statement.executeUpdate("UPDATE WordZan SET baidu=baidu+1 WHERE word='" + a + "'");
						}
						if (((WordZan) o).GetZanyoudao() == true) {
							t2 = 1;
							statement.executeUpdate("UPDATE WordZan SET youdao=youdao+1 WHERE word='" + a + "'");
						}
						if (((WordZan) o).GetZanbing() == true) {
							t3 = 1;
							statement.executeUpdate("UPDATE WordZan SET bing=bing+1 WHERE word='" + a + "'");
						}
						statement.executeUpdate(
								"INSERT  [dbo].[zan] ([account], [word], [baidu],[youdao],[bing] ) VALUES  ( '"
										+ account + "','" + a + "', " + t1 + "," + t2 + "," + t3 + ");");
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
					Vector<String> v = ActiveAccount.GetActiveAccount();
					toClient.writeInt(v.size());
					for (int i = 0; i < v.size(); i++) {
						toClient.writeUTF(v.get(i));
					}
					try {
						statement = connection.createStatement();
						resultSet = statement.executeQuery(
								"SELECT count(*) as row FROM [dbo].[Account] ");
						resultSet.next();
						toClient.writeInt(resultSet.getInt("row"));
						resultSet = statement.executeQuery(
								"SELECT aString FROM [dbo].[Account] ");
						while(resultSet.next()){
							toClient.writeUTF(resultSet.getString(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.208: " + e);
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
					if (((WordCard) object).getType() == ADD) {
						AllWordCard.Add(((WordCard) object));
					} else if (((WordCard) object).getType() == DELETE) {
						AllWordCard.Delete(((WordCard) object));
					} else {// 更新，把自己的用户名发过来
						toClient = new ObjectOutputStream(client.getOutputStream());
						toClient1 = new DataOutputStream(client.getOutputStream());
						Vector<WordCard> tVector = AllWordCard.GetAccountCard(((WordCard) object).getAccount());
						toClient1.writeInt(tVector.size());
						for (int i = 0; i < tVector.size(); i++) {
							toClient.writeObject(tVector.get(i));
						}
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println("ServerSearch.105: " + e);
			}
		}
	}
}
