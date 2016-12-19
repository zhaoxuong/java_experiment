package client;


//test
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;


import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import head.AllWordCard;
import head.Constant;
import head.WordCard;
import head.WordZan;

//赵雄：实现界面细节，设置字体大小，颜色等
public class ClientSearch extends JFrame implements Constant {
	private JFrame jFrame=new JFrame();
	private JLabel jlblTitle = new JLabel("My Dictionary");// 开头的名称

	private JLabel jlblInput = new JLabel();// input
	private JTextField jtfWord = new JTextField();// input 输入框
	private JButton jbtSearch = new JButton("Search");// search 按钮
	private JButton add=new JButton();
	private JButton del=new JButton();
	private JButton  ref1=new JButton();
	private JButton  ref2=new JButton();
	
	//private JButton jbtfresh = new JButton("fresh");
			
	private JCheckBox jchkBaidu = new JCheckBox("金山");
	private JCheckBox jchkYoudao = new JCheckBox("有道");
	private JCheckBox jchkBing = new JCheckBox("必应");// 3个选定钮

	private JLabel jlblBaidu1, jlblBaidu2, jlblBaidu3, jlblBaidu4;// 输出的名字
	private JTextArea jtaBaidu1 = new JTextArea(), jtaBaidu2 = new JTextArea(), jtaBaidu3 = new JTextArea(),
			jtaBaidu4 = new JTextArea();// 输出框
	private JLabel jlblYoudao1, jlblYoudao2, jlblYoudao3, jlblYoudao4;
	private JTextArea jtaYoudao1 = new JTextArea(), jtaYoudao2 = new JTextArea(), jtaYoudao3 = new JTextArea(),
			jtaYoudao4 = new JTextArea();
	private JLabel jlblBing1, jlblBing2, jlblBing3, jlblBing4;
	private JTextArea jtaBing1 = new JTextArea(), jtaBing2 = new JTextArea(), jtaBing3 = new JTextArea(),
			jtaBing4 = new JTextArea();
	
	
	private JLabel jlause=new JLabel();
	private JLabel jlaword=new JLabel();
	
	private JList<String> jluser = new JList<String>();
	private JList<String> jlword = new JList<String>();

	private JPanel pcard = new JPanel();
	private JPanel pcard1 = new JPanel();
	private JPanel pcard2 = new JPanel();// 为了cardLayout
										// 需要7个组件，不能在不同的Jpanel中调用加入同一个组件，所以备份了4个一样的组件

	
	private int flag1;
	private int flag2;
	private int flag3;
	private int flag=0;
	
	private String Sendword;
	private String Sender;
	private String  []str;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	private DataInputStream fromServer1;
	private DataOutputStream toServer1;
	ImageIcon icon1 = new ImageIcon("金山灰色.png");
	ImageIcon icon2 = new ImageIcon("有道灰色.png");
	ImageIcon icon3 = new ImageIcon("必应灰色.png");
	ImageIcon icon4 = new ImageIcon("金山蓝色.png");
	ImageIcon icon5 = new ImageIcon("有道蓝色.png");
	ImageIcon icon6 = new ImageIcon("必应蓝色.png");
	ImageIcon icon7 = new ImageIcon("赞.png");
	ImageIcon icon8 = new ImageIcon("左滑.png");
	ImageIcon icon9 = new ImageIcon("右滑.png");
	ImageIcon icon10 = new ImageIcon("刷新.png");

	Socket socketSearch;
	Socket socketFriend;
	Socket socketWords;

	WordZan wordZan = new WordZan();// 正在查询的单词

	String[] resultBaidu=null;
	String[] resultYoudao=null;
	String[] resultBing=null;
	int countBaidu=0;
	int countYoudao=0;
	int countBing=0;

	String ta = new String();

	/*
	 * public static void main(String[] args){ ClientSearch clientsearch=new
	 * ClientSearch();
	 * 
	 * }
	 */
	
	public void WordZaninit(String in){
		wordZan.setWord(in);
		searchWord(in);
		
	}
	public void jlblTitleset() {// 设置标题
		jlblTitle.setFont(new Font("Serif", Font.BOLD, 13));
		jlblTitle.setBackground(Color.GRAY);
		jlblTitle.setPreferredSize(new Dimension(150, 30));
		jlblTitle.setIcon(icon10);
		jlblTitle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String string=jtfWord.getText();
				int is=flag;
				//System.out.println(string);
				jFrame.dispose();
				//jFrame.setVisible(false);
				//jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				//System.out.println(in);
				//System.out.println("string="+string);
					WordZan temp=wordZan;
				    new ClientSearch(ta,temp,1);
				
				
			}
			
		});
		

	}
	public void jlblbaiduset2(WordZan wordZan1){
		if(!wordZan1.GetZanbaidu())
			jlblBaidu1.setIcon(icon1);
	else
			jlblBaidu1.setIcon(icon4);

	if(!wordZan1.GetZanyoudao())
		jlblYoudao1.setIcon(icon2);
	else
		jlblYoudao1.setIcon(icon5);

	if(!wordZan1.GetZanbing())
		jlblBing1.setIcon(icon3);
	else
		jlblBing1.setIcon(icon6);

	if(!wordZan1.GetZanbaidu())
		jlblBaidu2.setIcon(icon1);
	else
		jlblBaidu2.setIcon(icon4);




	if(!wordZan1.GetZanyoudao())
		jlblYoudao2.setIcon(icon2);
	else
		jlblYoudao2.setIcon(icon5);


	if(!wordZan1.GetZanbing())
		jlblBing2.setIcon(icon3);
	else
		jlblBing2.setIcon(icon6);

	 if(!wordZan1.GetZanbaidu())
		jlblBaidu3.setIcon(icon1);
	else
		jlblBaidu3.setIcon(icon4);

	if(!wordZan1.GetZanyoudao())
		jlblYoudao3.setIcon(icon2);
	else
		jlblYoudao3.setIcon(icon5);
		
	if(!wordZan1.GetZanbing())
		jlblBing3.setIcon(icon3);
	else
		jlblBing3.setIcon(icon6);
		
		


	if(!wordZan1.GetZanbaidu())
		jlblBaidu4.setIcon(icon1);
	else
		jlblBaidu4.setIcon(icon4);



	if(!wordZan1.GetZanyoudao())
		jlblYoudao4.setIcon(icon2);
	else
		jlblYoudao4.setIcon(icon5);
				

	if(!wordZan1.GetZanbing())
		jlblBing4.setIcon(icon3);
	else
		jlblBing4.setIcon(icon6);
		
		
	}
	
	public void jlbinputset() {// 输入标签的设置
		jlblInput.setFont(new Font("Serif", Font.BOLD, 10));
		jlblInput.setBackground(Color.BLACK);
		ImageIcon icon1 = new ImageIcon("搜索.png");
		jlblInput.setIcon(icon1);
		jlblInput.setPreferredSize(new Dimension(50, 10));
		jbtSearch.setFont(new Font("TimesRoman", Font.BOLD, 10));
		jbtSearch.setPreferredSize(new Dimension(80, 10));
		
		
		add.setIcon(new ImageIcon("add.png"));
		del.setIcon(new ImageIcon("delete.png"));
		add.setPreferredSize(new Dimension(50, 20));
		del.setPreferredSize(new Dimension(80, 30));
		ref1.setIcon(new ImageIcon("刷新.png"));
		ref2.setIcon(new ImageIcon("刷新.png"));
		ref1.setPreferredSize(new Dimension(50, 20));
		ref2.setPreferredSize(new Dimension(80, 30));
		
		// jbtSearch.setBackground(Color.GRAY);
	}
	public void jbtuserset(){//发送单词卡的监听事件
		ref2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getFriend();			
			}
			
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("ok1");
				System.out.println("Sender="+Sender);
				sendWordCard(Sender, wordZan.getWord(), wordZan.getbest(), ADD);
				
			}
		});
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Num=1;
				if(str[1].equals("Baidu"))  Num=1;
				if(str[1].equals("Youdao"))  Num=2;
				if(str[1].equals("Bing"))  Num=3;
				//System.out.println("two="+" "+[0]+"qq"+Two[1]+"qq"+Two[2]);
				System.out.println("Sendword="+Sendword);
				System.out.println(Num);
				sendWordCard(wordZan.GetAccount(), Sendword,Num, DELETE);
				
			}
		
		});
		ref1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(wordZan);
				sendWordCard(wordZan.GetAccount(), wordZan.getWord(), wordZan.getbest(), UPDATE);
				
			}
		});
		
		
		
	}
	public void jlblbaiduset() {// 百度等标签的设置	
		
		jlause=new JLabel(icon8);
		jlause.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				((CardLayout) pcard1.getLayout()).show(pcard1, "second");
			}
		});
		jlaword=new JLabel(icon9);
		jlaword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				((CardLayout) pcard2.getLayout()).show(pcard2, "second");
			}
		});
		
		if(!wordZan.GetZanbaidu())
				jlblBaidu1 = new JLabel(icon1, SwingConstants.LEFT);
		else
				jlblBaidu1 = new JLabel(icon4, SwingConstants.LEFT);
		jlblBaidu1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBaidu1.getIcon() == icon1 || jlblBaidu1.getIcon() == icon7) {
					jlblBaidu1.setIcon(icon4);
					jlblBaidu2.setIcon(icon4);
					jlblBaidu3.setIcon(icon4);
					jlblBaidu4.setIcon(icon4);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();

				} else {
					jlblBaidu1.setIcon(icon1);
					jlblBaidu2.setIcon(icon1);
					jlblBaidu3.setIcon(icon1);
					jlblBaidu4.setIcon(icon1);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
					//wordZan.deBaidu();
					//flagbaidu=0;
				}

			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBaidu1.getIcon() == icon1)
					jlblBaidu1.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBaidu1.getIcon() == icon7){
						jlblBaidu1.setIcon(icon1);
						
						
				}
				

			}

		});
		if(!wordZan.GetZanyoudao())
			jlblYoudao1= new JLabel(icon2, SwingConstants.LEFT);
	else
			jlblYoudao1= new JLabel(icon5, SwingConstants.LEFT);
		jlblYoudao1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblYoudao1.getIcon() == icon2 || jlblYoudao1.getIcon() == icon7) {
					jlblYoudao1.setIcon(icon5);
					jlblYoudao2.setIcon(icon5);
					jlblYoudao3.setIcon(icon5);
					jlblYoudao4.setIcon(icon5);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				} else {
					jlblYoudao1.setIcon(icon2);
					jlblYoudao2.setIcon(icon2);
					jlblYoudao3.setIcon(icon2);
					jlblYoudao4.setIcon(icon2);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				}

			}

			public void mouseEntered(MouseEvent e) {
				if (jlblYoudao1.getIcon() == icon2)
					jlblYoudao1.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblYoudao1.getIcon() == icon7)
					jlblYoudao1.setIcon(icon2);

			}
		});
		if(!wordZan.GetZanbing())
			jlblBing1= new JLabel(icon3, SwingConstants.LEFT);
	else
			jlblBing1= new JLabel(icon6, SwingConstants.LEFT);
		jlblBing1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBing1.getIcon() == icon3 || jlblBing1.getIcon() == icon7) {
					jlblBing1.setIcon(icon6);
					jlblBing2.setIcon(icon6);
					jlblBing3.setIcon(icon6);
					jlblBing4.setIcon(icon6);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				} else {
					jlblBing1.setIcon(icon3);
					jlblBing2.setIcon(icon3);
					jlblBing3.setIcon(icon3);
					jlblBing4.setIcon(icon3);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBing1.getIcon() == icon3)
					jlblBing1.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBing1.getIcon() == icon7)
					jlblBing1.setIcon(icon3);

			}
		});

		if(!wordZan.GetZanbaidu())
			jlblBaidu2= new JLabel(icon1, SwingConstants.LEFT);
	else
			jlblBaidu2 = new JLabel(icon4, SwingConstants.LEFT);
		jlblBaidu2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBaidu2.getIcon() == icon1 || jlblBaidu2.getIcon() == icon7) {
					jlblBaidu1.setIcon(icon4);
					jlblBaidu2.setIcon(icon4);
					jlblBaidu3.setIcon(icon4);
					jlblBaidu4.setIcon(icon4);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				} else {
					jlblBaidu1.setIcon(icon1);
					jlblBaidu2.setIcon(icon1);
					jlblBaidu3.setIcon(icon1);
					jlblBaidu4.setIcon(icon1);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBaidu2.getIcon() == icon1)
					jlblBaidu2.setIcon(icon7);
				

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBaidu2.getIcon() == icon7)
					jlblBaidu2.setIcon(icon1);

			}
		});
		if(!wordZan.GetZanyoudao())
			jlblYoudao2= new JLabel(icon2, SwingConstants.LEFT);
	else
			jlblYoudao2= new JLabel(icon5, SwingConstants.LEFT);
		jlblYoudao2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblYoudao2.getIcon() == icon2) {
					jlblYoudao1.setIcon(icon5);
					jlblYoudao2.setIcon(icon5);
					jlblYoudao3.setIcon(icon5);
					jlblYoudao4.setIcon(icon5);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				} else {
					jlblYoudao1.setIcon(icon2);
					jlblYoudao2.setIcon(icon2);
					jlblYoudao3.setIcon(icon2);
					jlblYoudao4.setIcon(icon2);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblYoudao2.getIcon() == icon2 || jlblYoudao2.getIcon() == icon7)
					jlblYoudao2.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblYoudao2.getIcon() == icon7)
					jlblYoudao2.setIcon(icon2);

			}
		});
		if(!wordZan.GetZanbing())
			jlblBing2= new JLabel(icon3, SwingConstants.LEFT);
	else
			jlblBing2= new JLabel(icon6, SwingConstants.LEFT);
		jlblBing2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBing2.getIcon() == icon3 || jlblBing2.getIcon() == icon7) {
					jlblBing1.setIcon(icon6);
					jlblBing2.setIcon(icon6);
					jlblBing3.setIcon(icon6);
					jlblBing4.setIcon(icon6);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				} else {
					jlblBing1.setIcon(icon3);
					jlblBing2.setIcon(icon3);
					jlblBing3.setIcon(icon3);
					jlblBing4.setIcon(icon3);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBing2.getIcon() == icon3)
					jlblBing2.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBing2.getIcon() == icon7)
					jlblBing2.setIcon(icon3);

			}
		});
		 if(!wordZan.GetZanbaidu())
			jlblBaidu3 = new JLabel(icon1, SwingConstants.LEFT);
	else
			jlblBaidu3 = new JLabel(icon4, SwingConstants.LEFT);
		jlblBaidu3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBaidu3.getIcon() == icon1 || jlblBaidu3.getIcon() == icon7) {
					jlblBaidu1.setIcon(icon4);
					jlblBaidu2.setIcon(icon4);
					jlblBaidu3.setIcon(icon4);
					jlblBaidu4.setIcon(icon4);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				} else {
					jlblBaidu1.setIcon(icon1);
					jlblBaidu2.setIcon(icon1);
					jlblBaidu3.setIcon(icon1);
					jlblBaidu4.setIcon(icon1);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBaidu3.getIcon() == icon1)
					jlblBaidu3.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBaidu3.getIcon() == icon7)
					jlblBaidu3.setIcon(icon1);

			}
		});
		if(!wordZan.GetZanyoudao())
			jlblYoudao3= new JLabel(icon2, SwingConstants.LEFT);
	else
			jlblYoudao3= new JLabel(icon5, SwingConstants.LEFT);
		jlblYoudao3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblYoudao3.getIcon() == icon2 || jlblYoudao3.getIcon() == icon7) {
					jlblYoudao1.setIcon(icon5);
					jlblYoudao2.setIcon(icon5);
					jlblYoudao3.setIcon(icon5);
					jlblYoudao4.setIcon(icon5);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
					
				} else {
					jlblYoudao1.setIcon(icon2);
					jlblYoudao2.setIcon(icon2);
					jlblYoudao3.setIcon(icon2);
					jlblYoudao4.setIcon(icon2);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblYoudao3.getIcon() == icon2)
					jlblYoudao3.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblYoudao3.getIcon() == icon7)
					jlblYoudao3.setIcon(icon2);

			}
		});
		if(!wordZan.GetZanbing())
			jlblBing3= new JLabel(icon3, SwingConstants.LEFT);
	else
			jlblBing3= new JLabel(icon6, SwingConstants.LEFT);
		jlblBing3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBing3.getIcon() == icon3 || jlblBing3.getIcon() == icon7) {
					jlblBing1.setIcon(icon6);
					jlblBing2.setIcon(icon6);
					jlblBing3.setIcon(icon6);
					jlblBing4.setIcon(icon6);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				} else {
					jlblBing1.setIcon(icon3);
					jlblBing2.setIcon(icon3);
					jlblBing3.setIcon(icon3);
					jlblBing4.setIcon(icon3);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBing3.getIcon() == icon3)
					jlblBing3.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBing3.getIcon() == icon7)
					jlblBing3.setIcon(icon3);

			}
		});
		if(!wordZan.GetZanbaidu())
			jlblBaidu4= new JLabel(icon1, SwingConstants.LEFT);
	else
			jlblBaidu4= new JLabel(icon4, SwingConstants.LEFT);
		
		jlblBaidu4.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBaidu4.getIcon() == icon1 || jlblBaidu4.getIcon() == icon7) {
					jlblBaidu1.setIcon(icon4);
					jlblBaidu2.setIcon(icon4);
					jlblBaidu3.setIcon(icon4);
					jlblBaidu4.setIcon(icon4);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				} else {
					jlblBaidu1.setIcon(icon1);
					jlblBaidu2.setIcon(icon1);
					jlblBaidu3.setIcon(icon1);
					jlblBaidu4.setIcon(icon1);
					wordZan.SetZanbaidu(!wordZan.GetZanbaidu());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBaidu4.getIcon() == icon1)
					jlblBaidu4.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBaidu4.getIcon() == icon7)
					jlblBaidu4.setIcon(icon1);

			}
		});
		if(!wordZan.GetZanyoudao())
			jlblYoudao4= new JLabel(icon2, SwingConstants.LEFT);
	else
			jlblYoudao4= new JLabel(icon5, SwingConstants.LEFT);
		jlblYoudao4.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblYoudao4.getIcon() == icon2 || jlblYoudao4.getIcon() == icon7) {
					jlblYoudao1.setIcon(icon5);
					jlblYoudao2.setIcon(icon5);
					jlblYoudao3.setIcon(icon5);
					jlblYoudao4.setIcon(icon5);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				} else {
					jlblYoudao1.setIcon(icon2);
					jlblYoudao2.setIcon(icon2);
					jlblYoudao3.setIcon(icon2);
					jlblYoudao4.setIcon(icon2);
					wordZan.SetZanyoudao(!wordZan.GetZanyoudao());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblYoudao4.getIcon() == icon2)
					jlblYoudao4.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblYoudao4.getIcon() == icon7)
					jlblYoudao4.setIcon(icon2);

			}
		});
		if(!wordZan.GetZanbing())
			jlblBing4= new JLabel(icon3, SwingConstants.LEFT);
	else
			jlblBing4= new JLabel(icon6, SwingConstants.LEFT);
		
		jlblBing4.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (jlblBing4.getIcon() == icon3 || jlblBing4.getIcon() == icon7) {
					jlblBing1.setIcon(icon6);
					jlblBing2.setIcon(icon6);
					jlblBing3.setIcon(icon6);
					jlblBing4.setIcon(icon6);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				} else {
					jlblBing1.setIcon(icon3);
					jlblBing2.setIcon(icon3);
					jlblBing3.setIcon(icon3);
					jlblBing4.setIcon(icon3);
					wordZan.SetZanbing(!wordZan.GetZanbing());
					changeZan();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (jlblBing4.getIcon() == icon3)
					jlblBing4.setIcon(icon7);

			}

			public void mouseExited(MouseEvent e) {
				if (jlblBing4.getIcon() == icon7)
					jlblBing4.setIcon(icon3);

			}
		});

	}// 花了很大功夫来解决cardlayout，主要是因为需要备份4个备份的组件

	public void jlistset(){//Jlist的设置 
		jluser.setPreferredSize(new Dimension(100, 250));
		jluser.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jluser.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               Sender=jluser.getSelectedValue();
            }
        });
	
		jluser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2)
				 ((CardLayout) pcard1.getLayout()).show(pcard1, "first");
			}
			
			/*public void mouseExited(MouseEvent e) {
				
				((CardLayout) pcard1.getLayout()).show(pcard1, "first");
			}*/
		});
		jlword.setPreferredSize(new Dimension(100, 250));
		jlword.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jlword.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               String temp=jlword.getSelectedValue();
               System.out.println("temp="+temp);
               str=temp.split("-");
               int len;
               len=str.length;
              // System.out.println("len="+len);
               //for(int i=0;i<len;i++)
            	 //  	System.out.println(str[i]);
               
               
               Sendword=str[0];
               
            }
        });
		jlword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2)
				((CardLayout) pcard2.getLayout()).show(pcard2, "first");
			}
		});
		
		
	}
	public void jtabaiduset() {// 输出框设置


		jtaBaidu1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBing1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaYoudao1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBaidu2.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBing2.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaYoudao2.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBaidu3.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBing3.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaYoudao3.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBaidu4.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaBing4.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jtaYoudao4.setBorder(BorderFactory.createLineBorder(Color.red, 1));

	}

	public void jbxbaiduset(){//复选框设置
		jchkBaidu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jchkBaidu.isSelected())
					flag1 = 1;
				else
					flag1 = 0;
				whichshow();
				// System.out.println(flag1+" "+flag2+flag3);

			}
		});
		jchkYoudao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jchkYoudao.isSelected())
					flag2 = 1;
				else
					flag2 = 0;
				whichshow();

			}
		});
		jchkBing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jchkBing.isSelected())
					flag3 = 1;
				else
					flag3 = 0;
				whichshow();

			}
		});
		
	}
	public void jtfWordSet() {// 输入框设置,当按下火车时调用searchword方法，即相当于搜索单词
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


	public void TextSet() {// 从服务器获取东西来填在客户端
		try {
			// 有错误
			// error
			Object object = fromServer.readObject();
			wordZan.update((WordZan) object);
			// 这是从服务器获取的解释，放到文本域中，并且更新布局，即点赞多的放前面，
			//点赞次数已经存在WordZan中
			//有没有点赞也已经放在WordZan中
			jlblbaiduset2(wordZan);
			
			if (wordZan.getType()) {
				flag=1;
				fromServer1 = new DataInputStream(socketSearch.getInputStream());
				resultBaidu = new String[100];
				resultYoudao = new String[100];
				resultBing = new String[100];
				countBaidu = 0;
				countYoudao = 0;
				countBing = 0;
				countBaidu = fromServer1.readInt();
				jtaBaidu1.setText(null);
				jtaBaidu2.setText(null);
				jtaBaidu3.setText(null);
				jtaBaidu4.setText(null);
				jtaYoudao1.setText(null);
				jtaYoudao2.setText(null);
				jtaYoudao3.setText(null);
				jtaYoudao4.setText(null);
				jtaBing1.setText(null);
				jtaBing2.setText(null);
				jtaBing3.setText(null);
				jtaBing4.setText(null);
				for (int i = 0; i < countBaidu; i++) {
					resultBaidu[i] = fromServer1.readUTF();
					jtaBaidu1.append(resultBaidu[i]);
					jtaBaidu2.append(resultBaidu[i]);
					jtaBaidu3.append(resultBaidu[i]);
					jtaBaidu4.append(resultBaidu[i]);
					jtaBaidu1.append("\n");
					jtaBaidu2.append("\n");
					jtaBaidu3.append("\n");
					jtaBaidu4.append("\n");
					
				}
				jtaBaidu1.setCaretPosition(0);
				jtaBaidu2.setCaretPosition(0);
				jtaBaidu3.setCaretPosition(0);
				jtaBaidu4.setCaretPosition(0);
				countYoudao = fromServer1.readInt();
				for (int i = 0; i < countYoudao; i++) {
					resultYoudao[i] = fromServer1.readUTF();
					jtaYoudao1.append(resultYoudao[i]);
					jtaYoudao2.append(resultYoudao[i]);
					jtaYoudao3.append(resultYoudao[i]);
					jtaYoudao4.append(resultYoudao[i]);
					jtaYoudao1.append("\n");
					jtaYoudao2.append("\n");
					jtaYoudao3.append("\n");
					jtaYoudao4.append("\n");
					
				}
				jtaYoudao1.setCaretPosition(0);
				jtaYoudao2.setCaretPosition(0);
				jtaYoudao3.setCaretPosition(0);
				jtaYoudao4.setCaretPosition(0);
				countBing = fromServer1.readInt();
				for (int i = 0; i < countBing; i++) {
					resultBing[i] = fromServer1.readUTF();
					jtaBing1.append(resultBing[i]);
					jtaBing2.append(resultBing[i]);
					jtaBing3.append(resultBing[i]);
					jtaBing4.append(resultBing[i]);
					jtaBing1.append("\n");
					jtaBing2.append("\n");
					jtaBing3.append("\n");
					jtaBing4.append("\n");
				}
				jtaBing1.setCaretPosition(0);
				jtaBing2.setCaretPosition(0);
				jtaBing3.setCaretPosition(0);
				jtaBing4.setCaretPosition(0);
				
				//System.out.println(sbaidu);
			} else {// 点赞，只是更新了点赞次数，所以只更新布局
				//setjpanel();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

	public void searchWord(String word) {
		wordZan.SetAccount(ta);
		wordZan.setType(true);

		try {
				/*if (word.equals(wordZan.getWord())) {
					TextSet();
					
				} else {
					wordZan.setWord(word);
					//我觉得应该是set
				}*/
				wordZan.setWord(word); 
				toServer = new ObjectOutputStream(socketSearch.getOutputStream());
				toServer.writeObject(wordZan);
				fromServer = new ObjectInputStream(socketSearch.getInputStream());
				/*System.out.println(wordZan.getWord());
				System.out.print("baidu:  " + countBaidu + "    ");
				System.out.println(wordZan.getBaidu());
				System.out.print("youdao:  " + countYoudao + "    ");
				System.out.println(wordZan.getYoudao());
				System.out.print("bing:  " + countBing + "    ");
				System.out.println(wordZan.getBing());
				System.out.println("====="+wordZan.GetZanbaidu()+"======");
				System.out.println("====="+wordZan.GetZanyoudao()+"======");
				System.out.println("====="+wordZan.GetZanbing()+"======");*/
				TextSet();
				
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void changeZan() {
		wordZan.setType(false);
		wordZan.SetAccount(ta);
		try {
			toServer = new ObjectOutputStream(socketSearch.getOutputStream());
			toServer.writeObject(wordZan);
			fromServer = new ObjectInputStream(socketSearch.getInputStream());
			TextSet();
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	public void getFriend() {
		try {
			toServer1 = new DataOutputStream(socketFriend.getOutputStream());
			toServer1.writeInt(1);
			fromServer1 = new DataInputStream(socketFriend.getInputStream());
			int numOfActiveAccount = fromServer1.readInt();
			String []s=new String[numOfActiveAccount];
			for (int i = 0; i < numOfActiveAccount; i++) {// 读取在线用户
				s[i]=fromServer1.readUTF();
				
			}
			//所有用户
			int n=fromServer1.readInt();
			for(int i=0;i<n;i++){
				fromServer1.readUTF();
				System.out.println(i);
			}
			jluser.setListData(s);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void sendWordCard(String account, String wrod, int best, int type) {
		WordCard wordCard = new WordCard(account, wrod, best, type);
		try {
			toServer = new ObjectOutputStream(socketWords.getOutputStream());
			toServer.writeObject(wordCard);
			if (type == ADD) {
				;
			} else if (type == DELETE) {
				;
			} else {//更新
				fromServer = new ObjectInputStream(socketWords.getInputStream());
				fromServer1 = new DataInputStream(socketWords.getInputStream());
				int numOfWordCard = fromServer1.readInt();
				System.out.println("n="+" "+numOfWordCard);
				WordCard []send=new WordCard[numOfWordCard];
				String []words=new String[numOfWordCard];
				for (int i = 0; i < numOfWordCard; i++) {// 读单词卡
					send[i]=(WordCard)fromServer.readObject();
					String temp="Jinshan";
					if(send[i].getBest()==1) temp="Jinshan";
					if(send[i].getBest()==2) temp="Youdao";
					if(send[i].getBest()==3) temp="Bing";
					
					
					words[i]=send[i].getWord()+"-"+temp;
					System.out.println(words[i]);
				}
				jlword.setListData(words);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void jbtSearchSet() {
// search按钮的设置，调用searchword方法
		//jbtSearch.setFont(new Font("TimesRoman", Font.BOLD, 20));
		jbtSearch.setToolTipText("click to search");
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchWord(jtfWord.getText());
				System.out.println("dangqian"+jtfWord.getText());
			
			}
		});
	}

	public void whichshow() {
		if (flag1 == 1 && flag2 == 0 && flag3 == 0)
			((CardLayout) pcard.getLayout()).show(pcard, "first");
		if (flag1 == 0 && flag2 == 1 && flag3 == 0)
			((CardLayout) pcard.getLayout()).show(pcard, "second");
		if (flag1 == 0 && flag2 == 0 && flag3 == 1)
			((CardLayout) pcard.getLayout()).show(pcard, "third");
		if (flag1 == 1 && flag2 == 1 && flag3 == 0)
			((CardLayout) pcard.getLayout()).show(pcard, "fourth");
		if (flag1 == 1 && flag2 == 0 && flag3 == 1)
			((CardLayout) pcard.getLayout()).show(pcard, "fifth");
		if (flag1 == 0 && flag2 == 1 && flag3 == 1)
			((CardLayout) pcard.getLayout()).show(pcard, "sixth");
		if (flag1 == 1 && flag2 == 1 && flag3 == 1)
			((CardLayout) pcard.getLayout()).show(pcard, "seventh");
		if (flag1 == 0 && flag2 == 0 && flag3 == 0)
			((CardLayout) pcard.getLayout()).show(pcard, "seventh");

	}
	public void setjpanel(){
		
		flag1 = flag2 = flag3 = 0;
		//flagBaidu=flagBing=flagYoudao=0;
		jlblTitleset();
		jlbinputset();
		jlblbaiduset();
		jtabaiduset();
		jbtSearchSet();
		jtfWordSet();
		jbtSearchSet();
		jbxbaiduset();
		jlistset();	
		getFriend();
		jbtuserset();
		JScrollPane jsBaidu1 = new JScrollPane(jtaBaidu1);
		JScrollPane jsBaidu2 = new JScrollPane(jtaBaidu2);
		JScrollPane jsBaidu3 = new JScrollPane(jtaBaidu3);
		JScrollPane jsBaidu4 = new JScrollPane(jtaBaidu4);
		JScrollPane jsYoudao1 = new JScrollPane(jtaYoudao1);
		JScrollPane jsYoudao2 = new JScrollPane(jtaYoudao2);
		JScrollPane jsYoudao3 = new JScrollPane(jtaYoudao3);
		JScrollPane jsYoudao4 = new JScrollPane(jtaYoudao4);
		JScrollPane jsBing1 = new JScrollPane(jtaBing1);
		JScrollPane jsBing2 = new JScrollPane(jtaBing2);
		JScrollPane jsBing3 = new JScrollPane(jtaBing3);
		JScrollPane jsBing4 = new JScrollPane(jtaBing4);
		JScrollPane jsuser = new JScrollPane(jluser);
		JScrollPane jsword = new JScrollPane(jlword);
		 jsword.setPreferredSize(new java.awt.Dimension(100, 180));
		 jsuser.setPreferredSize(new java.awt.Dimension(80, 150));// 做滚动条处理

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		 p1.add(jlblInput, BorderLayout.WEST);
		p1.add(jtfWord, BorderLayout.CENTER);
		p1.add(jbtSearch, BorderLayout.EAST);
		JPanel p2 = new JPanel();// 输入行模块 p1
		p2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		p2.add(jchkBaidu);
		p2.add(jchkYoudao);
		p2.add(jchkBing);// 三个复选框模块 p2

		JPanel p02 = new JPanel();
		p02.setLayout(new FlowLayout(FlowLayout.CENTER));
		p02.add(jlblTitle);// 标题模块 p02

		JPanel p012 = new JPanel();
		p012.setLayout(new GridLayout(3, 1, 0, 0));
		p012.add(p02);
		p012.add(p1);
		p012.add(p2);// 上半部分 p012

		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jlblBaidu1, BorderLayout.WEST);
		p3.add(jsBaidu1, BorderLayout.CENTER);
		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(jlblYoudao1, BorderLayout.WEST);
		p4.add(jsYoudao1, BorderLayout.CENTER);
		JPanel p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(jlblBing1, BorderLayout.WEST);
		p5.add(jsBing1, BorderLayout.CENTER);// 3个小分块

		JPanel p32 = new JPanel();
		p32.setLayout(new BorderLayout());
		p32.add(jlblBaidu2, BorderLayout.WEST);
		p32.add(jsBaidu2, BorderLayout.CENTER);
		JPanel p42 = new JPanel();
		p42.setLayout(new BorderLayout());
		p42.add(jlblYoudao2, BorderLayout.WEST);
		p42.add(jsYoudao2, BorderLayout.CENTER);
		JPanel p34 = new JPanel();
		p34.setLayout(new GridLayout(2, 1, 1, 5));
		

		JPanel p33 = new JPanel();
		p33.setLayout(new BorderLayout());
		p33.add(jlblBaidu3, BorderLayout.WEST);
		p33.add(jsBaidu3, BorderLayout.CENTER);
		JPanel p52 = new JPanel();
		p52.setLayout(new BorderLayout());
		p52.add(jlblBing2, BorderLayout.WEST);
		p52.add(jsBing2, BorderLayout.CENTER);
		JPanel p43 = new JPanel();
		p43.setLayout(new BorderLayout());
		p43.add(jlblYoudao3, BorderLayout.WEST);
		p43.add(jsYoudao3, BorderLayout.CENTER);
		JPanel p53 = new JPanel();
		p53.setLayout(new BorderLayout());
		p53.add(jlblBing3, BorderLayout.WEST);
		p53.add(jsBing3, BorderLayout.CENTER);

		JPanel p333 = new JPanel();
		p333.setLayout(new BorderLayout());
		p333.add(jlblBaidu4, BorderLayout.WEST);
		p333.add(jsBaidu4, BorderLayout.CENTER);
		JPanel p444 = new JPanel();
		p444.setLayout(new BorderLayout());
		p444.add(jlblYoudao4, BorderLayout.WEST);
		p444.add(jsYoudao4, BorderLayout.CENTER);
		JPanel p555 = new JPanel();
		p555.setLayout(new BorderLayout());
		p555.add(jlblBing4, BorderLayout.WEST);
		p555.add(jsBing4, BorderLayout.CENTER);
		JPanel p35 = new JPanel();
		p35.setLayout(new GridLayout(2, 1, 1, 5));
		JPanel p45 = new JPanel();
		p45.setLayout(new GridLayout(2, 1, 1, 5));
		JPanel p345 = new JPanel();
		p345.setLayout(new GridLayout(3, 1, 1, 5));
		
		
		
		if(wordZan.getBaidu()>=wordZan.getYoudao()){
			p34.add(p32);
			p34.add(p42);
			
		}
		else
		{
			p34.add(p42);
			p34.add(p32);
		}
		if(wordZan.getBaidu()>=wordZan.getBing()){
			p35.add(p33);
			p35.add(p52);
			
		}
		else
		{
			p35.add(p52);
			p35.add(p33);
		}
		if(wordZan.getYoudao()>=wordZan.getBing()){
			p45.add(p43);
			p45.add(p53);
			
		}
		else
		{
			p45.add(p53);
			p45.add(p43);
		}
		if(wordZan.getBaidu()>=wordZan.getYoudao()){
			if(wordZan.getYoudao()>=wordZan.getBing()){
				p345.add(p333);
				p345.add(p444);
				p345.add(p555);
			}
			else
				{
				if(wordZan.getYoudao()>=wordZan.getBaidu()){
				
					p345.add(p555);
					p345.add(p333);
					p345.add(p444);
				}
				else {
					p345.add(p333);
					p345.add(p444);
					p345.add(p555);
				}
		}
			
		}
		else
		{
			if(wordZan.getBaidu()>=wordZan.getBing()){
				p345.add(p444);
				p345.add(p333);
				p345.add(p555);
			}else{
				if(wordZan.getBing()>wordZan.getYoudao()){
					p345.add(p555);
					p345.add(p444);
					p345.add(p333);
				}
				else{
					p345.add(p444);
					p345.add(p555);
					p345.add(p333);
				}
				
			}
			
		}
		
		
		/*p35.add(p33);//baidu
		p35.add(p52);//bing
		
		p34.add(p32);//baidu
		p34.add(p42);//youdao

		p45.add(p43);//youdao
		p45.add(p53);//bing
		
		p345.add(p333);
		p345.add(p444);
		p345.add(p555);
		*/
		
		
		
		
		// JPanel pcard=new JPanel();
		pcard.setLayout(new CardLayout());
		pcard.add(p3, "first");
		pcard.add(p4, "second");
		pcard.add(p5, "third");

		pcard.add(p34, "fourth");//baidu youdao
		pcard.add(p35, "fifth");//baidu bing
		pcard.add(p45, "sixth");//youdao bing
		pcard.add(p345, "seventh"); // 卡片布局管理

		((CardLayout) pcard.getLayout()).show(pcard, "seventh");

		

		/*JPanel pleft = new JPanel();
		pleft.setLayout(new GridLayout(2, 1, 10, 20));
		pleft.add(jsuser);
		pleft.add(jsword);// 右面用户状态栏*/
		
		//pleft.setBounds(580, 300, 40, 300);
		JPanel adddel=new JPanel();
		adddel.setLayout(new GridLayout(1,2));
		adddel.add(ref1);
		adddel.add(del);
		
		
		JPanel usradd=new JPanel();
		usradd.setLayout(new GridLayout(1,2));
		usradd.add(ref2);
		usradd.add(add);
		
		JPanel  pwords=new JPanel();
		pwords.setLayout(new BorderLayout());
		pwords.add(jlword, BorderLayout.NORTH);
		pwords.add(adddel,BorderLayout.CENTER);
		
		JPanel pusrs=new JPanel();
		pusrs.setLayout(new BorderLayout());
		pusrs.add(jluser, BorderLayout.NORTH);
		pusrs.add(usradd,BorderLayout.CENTER);
		
		
		
		
		
		JPanel plast = new JPanel();
		plast.setLayout(new BorderLayout());
		plast.add(p012, BorderLayout.NORTH);
		plast.add(pcard, BorderLayout.CENTER);
	
		
		plast.setPreferredSize(new Dimension(600, 500));
		
		//add(plast);
		//add(pleft,BorderLayout.EAST);
		pcard1.setLayout(new CardLayout());
		pcard1.add(jlause, "first");
		pcard1.add(pusrs, "second");
		((CardLayout) pcard1.getLayout()).show(pcard1, "first");
		
		pcard2.setLayout(new CardLayout());
		pcard2.add(jlaword, "first");
		pcard2.add(pwords, "second");
		((CardLayout) pcard2.getLayout()).show(pcard2, "first");
		
		
		JPanel puser=new JPanel();
		puser.setLayout(new FlowLayout(FlowLayout.LEFT));
		puser.add(pcard1);
		
		JPanel pword=new JPanel();
		pword.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pword.add(pcard2);
		
		
		ImageIcon bg = new ImageIcon("背景原图.jpg"); // 把背景图片显示在一个标签里
		 JLabel label = new JLabel(bg); //把标签的大小位置设置为图片刚好填充整个面
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		 //添加图到frame的第二层  
		jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		//获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法） 
		JPanel jp=(JPanel)jFrame.getContentPane();
		jp.setOpaque(false);//设置透明
		
		puser.setBackground(null);                      // 把背景设置为会  
		puser.setOpaque(false);  
		pword.setBackground(null);                      // 把背景设置为会  
		pword.setOpaque(false); 
		plast.setBackground(Color.black);                      // 把背景设置为会  
		plast.setOpaque(false); 
		
		jFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		jFrame.add(puser);
		jFrame.add(plast);
		jFrame.add(pword);
		//jbtfresh.setBounds(400, 40, 80, 20);
		//jFrame.add(jbtfresh);
		
		
		//add(pleft,BorderLayout.EAST);

		//pleft.setBounds(580, 300, 40, 300);
		// add(pzan,BorderLayout.SOUTH);

		//jFrame.setLocationRelativeTo(CENTER);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Dictionary");
		jFrame.setSize(1000, 600);
		jFrame.setVisible(true);
		jFrame.setLocation(200, 150);
	}
	public ClientSearch(String ta,WordZan in,int issearch) {
		this.ta = ta;

		try {
			socketSearch = new Socket("localhost", 10086);
			socketFriend = new Socket("localhost", 10087);
			socketWords = new Socket("localhost", 10088);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
		flag1 = flag2 = flag3 = 0;
		// flagBaidu=flagBing=flagYoudao=0;
		/*System.out.println("string="+wordZan.getWord());
		System.out.print("baidu:  " + countBaidu + "    ");
		System.out.println(wordZan.getBaidu());
		System.out.print("youdao:  " + countYoudao + "    ");
		System.out.println(wordZan.getYoudao());
		System.out.print("bing:  " + countBing + "    ");
		System.out.println(wordZan.getBing());*/
		wordZan.update(in);
		setjpanel();
		/*System.out.println(wordZan.getWord());
		System.out.print("baidu:  " + countBaidu + "    ");
		System.out.println(wordZan.getBaidu());
		System.out.print("youdao:  " + countYoudao + "    ");
		System.out.println(wordZan.getYoudao());
		System.out.print("bing:  " + countBing + "    ");
		System.out.println(wordZan.getBing());*/
		jtfWord.setText(wordZan.getWord());
		if(issearch==1)
		    searchWord(wordZan.getWord());
		
	
		
	}
}
