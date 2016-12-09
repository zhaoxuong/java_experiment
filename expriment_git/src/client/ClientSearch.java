package client;
//test
//test2
/*import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import javax.xml.soap.Text;

import head.Constant;
import head.WordZan;
//赵雄：实现界面细节，设置字体大小，颜色等
public class ClientSearch extends JFrame implements Constant{
	private JLabel jlblTitle=new JLabel("My Dictionary");
	
	private JLabel jlblInput=new JLabel("Input");
	private JTextField jtfWord=new JTextField();
	private JButton jbtSearch=new JButton("Search");
	
	private JCheckBox jchkBaidu=new JCheckBox("百度");
	private JCheckBox jchkYoudao=new JCheckBox("有道");
	private JCheckBox jchkBing=new JCheckBox("必应");
	
	private JLabel jlblBaidu=new JLabel("百度");
	private JTextArea jtaBaidu=new JTextArea();
	private JButton jbtBaidu=new JButton("+1");
	private JLabel jlblYoudao=new JLabel("有道");
	private JTextArea jtaYoudao=new JTextArea();
	private JButton jbtYoudao=new JButton("+1");
	private JLabel jlblBing=new JLabel("必应");
	private JTextArea jtaBing=new JTextArea();
	private JButton jbtBing=new JButton("+1");
	
	private Socket socket;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	
	WordZan wordZan=new WordZan();//正在查询的单词
	
	public ClientSearch(){//构造函数
		try{
			socket=new Socket("localhost", 10086);
			
			jtfWordSet();
			jbtSearchSet();
			jbtBaiduSet();
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(jlblInput, BorderLayout.WEST);
		p1.add(jtfWord,BorderLayout.CENTER);
		p1.add(jbtSearch,BorderLayout.EAST);
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(1,3));
		p2.add(jchkBaidu);
		p2.add(jchkYoudao);
		p2.add(jchkBing);
		
		JPanel p012=new JPanel();
		p012.setLayout(new BorderLayout());
		p012.add(jlblTitle, BorderLayout.NORTH);
		p012.add(p1,BorderLayout.CENTER);
		p012.add(p2,BorderLayout.SOUTH);
		
		JPanel p3=new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jlblBaidu, BorderLayout.WEST);
		p3.add(jtaBaidu,BorderLayout.CENTER);
		p3.add(jbtBaidu,BorderLayout.EAST);
		JPanel p4=new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(jlblYoudao, BorderLayout.WEST);
		p4.add(jtaYoudao,BorderLayout.CENTER);
		p4.add(jbtYoudao,BorderLayout.EAST);
		JPanel p5=new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(jlblBing, BorderLayout.WEST);
		p5.add(jtaBing,BorderLayout.CENTER);
		p5.add(jbtBing,BorderLayout.EAST);
		
		JPanel p345=new JPanel();
		p345.setLayout(new GridLayout(3, 1,1,5));
		p345.add(p3);
		p345.add(p4);
		p345.add(p5);
		
		setLayout(new BorderLayout());
		add(p012, BorderLayout.NORTH);
		add(p345,BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dictionary");
		setSize(600, 600);
		setVisible(true);
	}
	public void TextSet(){//从服务器获取东西来填在客户端
		try{
			Object object=fromServer.readObject();
			wordZan.update((WordZan)object);
			jtaBaidu.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing.setText((String)(fromServer.readObject())+wordZan.getBing());
		}		
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	public void searchWord(String word){
		try{
			if(word.equals(wordZan.getWord())){
				return;
			}
			else{
				wordZan.setWord(word);
				wordZan.setBaidu(0);
				wordZan.setYoudao(0);
				wordZan.setBing(0);
			}
			toServer=new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(wordZan);
			fromServer=new ObjectInputStream(socket.getInputStream());
			TextSet();
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	
	
	public void jtfWordSet(){
		jtfWord.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchWord(jtfWord.getText());
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	public void jbtSearchSet() {
//		jbtSearch.setToolTipText("click to search");
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchWord(jtfWord.getText());
			}
		});
	}
	public void addZan(int type) {
		if(wordZan.getWord().equals("")){
			return;
		}
		if(type==ZANBAIDU){
			wordZan.addBaidu();
		}
		else if(type==ZANYOUDAO){
			wordZan.addYoudao();
		}
		else{
			wordZan.addBing();
		}
		try{
			toServer=new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(wordZan);
			fromServer=new ObjectInputStream(socket.getInputStream());
			TextSet();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void jbtBaiduSet(){
		jbtBaidu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addZan(ZANBAIDU);
			}
		});
	}
}*/


//test
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import head.Constant;
import head.WordZan;
//赵雄：实现界面细节，设置字体大小，颜色等
public class ClientSearch extends JFrame{
	private JLabel jlblTitle=new JLabel("My Dictionary");//开头的名称
	
	private JLabel jlblInput=new JLabel("Input");//input
	private JTextField jtfWord=new JTextField();//input 输入框
	private JButton jbtSearch=new JButton("Search");//search 按钮
	
	private JCheckBox jchkBaidu=new JCheckBox("百度");
	private JCheckBox jchkYoudao=new JCheckBox("有道");
	private JCheckBox jchkBing=new JCheckBox("必应");//3个选定钮
	
	private JLabel jlblBaidu1,jlblBaidu2,jlblBaidu3,jlblBaidu4;// 输出的名字
	private JTextArea jtaBaidu1=new JTextArea(),jtaBaidu2=new JTextArea(),jtaBaidu3=new JTextArea(),jtaBaidu4=new JTextArea();//输出框
	private JLabel jlblYoudao1,jlblYoudao2,jlblYoudao3,jlblYoudao4;
	private JTextArea jtaYoudao1=new JTextArea(),jtaYoudao2=new JTextArea(),jtaYoudao3=new JTextArea(),jtaYoudao4=new JTextArea();
	private JLabel jlblBing1,jlblBing2,jlblBing3,jlblBing4;
	private JTextArea jtaBing1=new JTextArea(),jtaBing2=new JTextArea(),jtaBing3=new JTextArea(),jtaBing4=new JTextArea();
	private JPanel pcard=new JPanel();//为了cardLayout 需要7个组件，不能在不同的Jpanel中调用加入同一个组件，所以备份了4个一样的组件
	
	private JLabel  jlblzan;
	private JLabel jlblzanBaidu=new JLabel("百度");
	private JLabel jlblzanYoudao=new JLabel("有道");
	private JLabel jlblzanBing=new JLabel("必应");
	private JButton  jbtzanBaidu,jbtzanYoudao,jbtzanBing;
	private int flagBaidu,flagYoudao,flagBing;
	
	//test
	private int flag1;
	private int flag2;
	private int flag3;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	
	
	Socket socket;
	
	WordZan wordZan=new WordZan();//正在查询的单词
	
	/*public static void main(String[] args){		
		ClientSearch clientsearch=new ClientSearch();
		
	}*/
	public void jlblTitleset(){//设置标题
		jlblTitle.setFont(new Font("Serif", Font.BOLD, 20));
		jlblTitle.setBackground(Color.GRAY);
		
		
	}
	public void jlbinputset(){//输入标签的设置
		jlblInput.setFont(new Font("Serif", Font.BOLD, 15));
		jlblInput.setBackground(Color.BLACK);
		jbtSearch.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
		//jbtSearch.setBackground(Color.GRAY);	
	}
	public void jlblbaiduset(){//百度等标签的设置
		ImageIcon icon1=new ImageIcon("baidu .jpg");
		ImageIcon icon2=new ImageIcon("youdao.jpg");
		ImageIcon icon3=new ImageIcon("bing.jpg");
		jlblBaidu1=new JLabel(icon1,SwingConstants.LEFT);
		jlblYoudao1=new JLabel(icon2,SwingConstants.LEFT);
		jlblBing1=new JLabel(icon3,SwingConstants.LEFT);
		jlblBaidu2=new JLabel(icon1,SwingConstants.LEFT);
		jlblYoudao2=new JLabel(icon2,SwingConstants.LEFT);
		jlblBing2=new JLabel(icon3,SwingConstants.LEFT);
		jlblBaidu3=new JLabel(icon1,SwingConstants.LEFT);
		jlblYoudao3=new JLabel(icon2,SwingConstants.LEFT);
		jlblBing3=new JLabel(icon3,SwingConstants.LEFT);
		jlblBaidu4=new JLabel(icon1,SwingConstants.LEFT);
		jlblYoudao4=new JLabel(icon2,SwingConstants.LEFT);
		jlblBing4=new JLabel(icon3,SwingConstants.LEFT);
		
	}
	public void jtabaiduset(){//输出框
		jtaBaidu1.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBing1.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaYoudao1.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBaidu2.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBing2.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaYoudao2.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBaidu3.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBing3.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaYoudao3.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBaidu4.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaBing4.setBorder(BorderFactory.createLineBorder(Color.red,2));
		jtaYoudao4.setBorder(BorderFactory.createLineBorder(Color.red,2));
		
		
	}
	public void jtfWordSet(){//输入框设置
		jtfWord.setFont(new Font(Font.DIALOG, Font.PLAIN | Font.ROMAN_BASELINE, 20));
		jtfWord.setBackground(Color.white);
		jtfWord.setForeground(Color.GREEN);
		jtfWord.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchWord(jtfWord.getText());
				jtfWord.requestFocusInWindow();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	
	public void jlblzanset(){
		jlblzan=new JLabel("亲，求赞哦");
		jlblzan.setFont(new Font("Serif", Font.BOLD, 15));
		jlblzan.setBackground(Color.BLACK);
		jlblzanBaidu.setFont(new Font("Serif", Font.BOLD, 15));
		jlblzanBaidu.setBackground(Color.BLACK);
		jlblzanYoudao.setFont(new Font("Serif", Font.BOLD, 15));
		jlblzanYoudao.setBackground(Color.BLACK);
		jlblzanBing.setFont(new Font("Serif", Font.BOLD, 15));
		jlblzanBing.setBackground(Color.BLACK);
		
	}
	
	public void jbtzanset(){
		 jbtzanBaidu=new JButton("赞");
		 jbtzanBaidu.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
	     jbtzanBaidu.setBackground(Color.gray);
	     jbtzanYoudao=new JButton("赞");
		 jbtzanYoudao.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
	     jbtzanYoudao.setBackground(Color.gray);
	     jbtzanBing=new JButton("赞");
		 jbtzanBing.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
	     jbtzanBing.setBackground(Color.gray);		
	}
	
	public void TextSet(){//从服务器获取东西来填在客户端
		try{
			Object object=fromServer.readObject();
			wordZan.update((WordZan)object);
			jtaBaidu1.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao1.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing1.setText((String)(fromServer.readObject())+wordZan.getBing());
			jtaBaidu2.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao2.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing2.setText((String)(fromServer.readObject())+wordZan.getBing());
			jtaBaidu3.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao3.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing3.setText((String)(fromServer.readObject())+wordZan.getBing());
			jtaBaidu3.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao3.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing3.setText((String)(fromServer.readObject())+wordZan.getBing());
			jtaBaidu4.setText((String)(fromServer.readObject())+wordZan.getBaidu());
			jtaYoudao4.setText((String)(fromServer.readObject())+wordZan.getYoudao());
			jtaBing4.setText((String)(fromServer.readObject())+wordZan.getBing());
		}		
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	public void searchWord(String word){
		try{
			if(word.equals(wordZan.getWord())){
				return;
			}
			else{
				wordZan.setWord(word);
				wordZan.setBaidu(0);
				wordZan.setYoudao(0);
				wordZan.setBing(0);
			}
			toServer=new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(wordZan);
			fromServer=new ObjectInputStream(socket.getInputStream());
			TextSet();
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	
	/*public void searchWord(String word){//搜做实现，具体在server这里只是调用
		try{
			toServer.writeUTF(word);
			jtaBaidu1.setText(fromServer.readUTF());
			jtaYoudao1.setText(fromServer.readUTF());
			jtaBing1.setText(fromServer.readUTF());
			jtaBaidu2.setText(fromServer.readUTF());
			jtaYoudao2.setText(fromServer.readUTF());
			jtaBing2.setText(fromServer.readUTF());
			jtaBaidu3.setText(fromServer.readUTF());
			jtaYoudao3.setText(fromServer.readUTF());
			jtaBing3.setText(fromServer.readUTF());
			jtaBaidu4.setText(fromServer.readUTF());
			jtaYoudao4.setText(fromServer.readUTF());
			jtaBing4.setText(fromServer.readUTF());
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}*/
	public void jbtSearchSet() {//search按钮的设置
		jbtSearch.setFont(new Font("TimesRoman", Font.BOLD, 20));
		jbtSearch.setToolTipText("click to search");
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchWord(jtfWord.getText());
			}
		});
	}
	public void whichshow(){
		if(flag1==1&&flag2==0&&flag3==0)   ((CardLayout)pcard.getLayout()).show(pcard,"first");
		if(flag1==0&&flag2==1&&flag3==0)   ((CardLayout)pcard.getLayout()).show(pcard,"second");
		if(flag1==0&&flag2==0&&flag3==1)   ((CardLayout)pcard.getLayout()).show(pcard,"third");
		if(flag1==1&&flag2==1&&flag3==0)   ((CardLayout)pcard.getLayout()).show(pcard,"fourth");
		if(flag1==1&&flag2==0&&flag3==1)   ((CardLayout)pcard.getLayout()).show(pcard,"fifth");
		if(flag1==0&&flag2==1&&flag3==1)   ((CardLayout)pcard.getLayout()).show(pcard,"sixth");
		if(flag1==1&&flag2==1&&flag3==1)   ((CardLayout)pcard.getLayout()).show(pcard,"seventh");
		if(flag1==0&&flag2==0&&flag3==0)   ((CardLayout)pcard.getLayout()).show(pcard,"seventh");

	}
	public ClientSearch() {
	
		try{
			socket=new Socket("localhost", 10086);
			//fromServer=new DataInputStream(socket.getInputStream());
			//toServer=new DataOutputStream(socket.getOutputStream());
			
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
		flag1=flag2=flag3=0;
		flagBaidu=flagBing=flagYoudao=0;
		jlblTitleset();
		jlbinputset();
		jlblbaiduset();
		jtabaiduset();
		jbtSearchSet();
		jbtzanset();
		jlblzanset();
		jtfWordSet();
		jbtSearchSet();
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(jlblInput, BorderLayout.WEST);
		p1.add(jtfWord,BorderLayout.CENTER);
		p1.add(jbtSearch,BorderLayout.EAST);
		JPanel p2=new JPanel();//输入行模块 p1
		p2.setLayout(new GridLayout(1,3));
		p2.add(jchkBaidu);
		p2.add(jchkYoudao);
		p2.add(jchkBing);//三个复选框模块 p2
		
		JPanel p02=new JPanel();
		p02.setLayout(new FlowLayout(FlowLayout.CENTER));
		p02.add(jlblTitle);//标题模块 p02
		
		JPanel p012=new JPanel();
		p012.setLayout(new BorderLayout());
		p012.add(p02, BorderLayout.NORTH);
		p012.add(p1,BorderLayout.CENTER);
		p012.add(p2,BorderLayout.SOUTH);//上半部分  p012
		
		JPanel p3=new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jlblBaidu1, BorderLayout.WEST);
		p3.add(jtaBaidu1,BorderLayout.CENTER);
		JPanel p4=new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(jlblYoudao1, BorderLayout.WEST);
		p4.add(jtaYoudao1,BorderLayout.CENTER);
		JPanel p5=new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(jlblBing1, BorderLayout.WEST);
		p5.add(jtaBing1,BorderLayout.CENTER);//3个小分块		
		
		
		
		JPanel p32=new JPanel();
		p32.setLayout(new BorderLayout());
		p32.add(jlblBaidu2, BorderLayout.WEST);
		p32.add(jtaBaidu2,BorderLayout.CENTER);
		JPanel p42=new JPanel();
		p42.setLayout(new BorderLayout());
		p42.add(jlblYoudao2, BorderLayout.WEST);
		p42.add(jtaYoudao2,BorderLayout.CENTER);	
		JPanel p34=new JPanel();
		p34.setLayout(new GridLayout(2, 1,1,5));
		p34.add(p32);
		p34.add(p42);
		
		
		
		
		
		JPanel p33=new JPanel();
		p33.setLayout(new BorderLayout());
		p33.add(jlblBaidu3, BorderLayout.WEST);
		p33.add(jtaBaidu3,BorderLayout.CENTER);
		JPanel p52=new JPanel();
		p52.setLayout(new BorderLayout());
		p52.add(jlblBing2, BorderLayout.WEST);
		p52.add(jtaBing2,BorderLayout.CENTER);
		JPanel p35=new JPanel();
		p35.setLayout(new GridLayout(2, 1,1,5));
		p35.add(p33);
		p35.add(p52);
		
		
		
		JPanel p43=new JPanel();
		p43.setLayout(new BorderLayout());
		p43.add(jlblYoudao3,BorderLayout.WEST);
		p43.add(jtaYoudao3,BorderLayout.CENTER);
		JPanel p53=new JPanel();
		p53.setLayout(new BorderLayout());
		p53.add(jlblBing3, BorderLayout.WEST);
		p53.add(jtaBing3,BorderLayout.CENTER);
		JPanel p45=new JPanel();
		p45.setLayout(new GridLayout(2, 1,1,5));
		p45.add(p43);
		p45.add(p53);
		
		
		
		JPanel p333=new JPanel();
		p333.setLayout(new BorderLayout());
		p333.add(jlblBaidu4, BorderLayout.WEST);
		p333.add(jtaBaidu4,BorderLayout.CENTER);
		JPanel p444=new JPanel();
		p444.setLayout(new BorderLayout());
		p444.add(jlblYoudao4, BorderLayout.WEST);
		p444.add(jtaYoudao4,BorderLayout.CENTER);
		JPanel p555=new JPanel();
		p555.setLayout(new BorderLayout());
		p555.add(jlblBing4, BorderLayout.WEST);
		p555.add(jtaBing4,BorderLayout.CENTER);
		JPanel p345=new JPanel();
		p345.setLayout(new GridLayout(3, 1,1,5));
		p345.add(p333);
		p345.add(p444);
		p345.add(p555);
		
		//JPanel pcard=new JPanel();
		pcard.setLayout(new CardLayout());		
		pcard.add(p3, "first");
		pcard.add(p4, "second");
		pcard.add(p5,"third");
		
		pcard.add(p34, "fourth");
		pcard.add(p35,"fifth");
		pcard.add(p45,"sixth");
		pcard.add(p345,"seventh");  //卡片布局管理
		
		((CardLayout)pcard.getLayout()).show(pcard,"seventh");
		
		jchkBaidu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  if(jchkBaidu.isSelected()) flag1=1;else flag1=0;
				  whichshow();
				  //System.out.println(flag1+" "+flag2+flag3);
				
			}
		});
        jchkYoudao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jchkYoudao.isSelected()) flag2=1;else flag2=0;
				whichshow();
				
			}
		});
         jchkBing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                if(jchkBing.isSelected()) flag3=1;else flag3=0;			
				whichshow();
				
			}
		});
         
         
         
		JPanel pzan=new JPanel();
		pzan.setLayout(new GridLayout(1, 7,2,1));
		pzan.add(jlblzan);
		/*pzan.add(pzan1);
		pzan.add(pzan2);
		pzan3.add(pzan3);*/
		pzan.add(jbtzanBaidu);
		pzan.add(jlblzanBaidu);
		pzan.add(jbtzanYoudao);
		pzan.add(jlblzanYoudao);
		pzan.add(jbtzanBing);
		pzan.add(jlblzanBing);
		
		jbtzanBaidu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagBaidu=1-flagBaidu;
				if(flagBaidu==1)
					 jbtzanBaidu.setBackground(Color.red);
				else
					 jbtzanBaidu.setBackground(Color.GRAY);
			}
		});
         jbtzanYoudao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagYoudao=1-flagYoudao;
				if(flagYoudao==1)
					 jbtzanYoudao.setBackground(Color.red);
				else
					 jbtzanYoudao.setBackground(Color.GRAY);
			}
		});
         jbtzanBing.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		flagBing=1-flagBing;
		if(flagBing==1)
			 jbtzanBing.setBackground(Color.red);
		else
			 jbtzanBing.setBackground(Color.GRAY);
	}
});
		
		
		
		
		
		
		
		/*JPanel p345=new JPanel();
		p345.setLayout(new GridLayout(3, 1,1,5));
		p345.add(p3);
		p345.add(p4);
		p345.add(p5);*/
		
		setLayout(new BorderLayout());
		add(p012, BorderLayout.NORTH);
		add(pcard,BorderLayout.CENTER);
		add(pzan,BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dictionary");
		setSize(647, 405);
		setVisible(true);
	}
}

