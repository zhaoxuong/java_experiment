package client;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import head.Account;
public class ClientSign extends JFrame{
	private JLabel jlblAccount=new JLabel("�˺�");
	private JTextField jtfAccount=new JTextField();
	private JLabel jlblPassword=new JLabel("����");
	private JTextField jtfPassword=new JTextField();
	private JButton jbtSignIn=new JButton("��¼");
	private JButton jbtSignUp=new JButton("ע��");
	
	private Socket socket;
	private DataInputStream fromServer;
	private ObjectOutputStream toServer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientSign clientSign=new ClientSign();
	}
	public ClientSign(){
		jbtSignUpSet();
		jbtSignInSet();
		
		Panel p1=new Panel();
		p1.setLayout(new BorderLayout());
		p1.add(jlblAccount, BorderLayout.WEST);
		p1.add(jtfAccount, BorderLayout.CENTER);
		
		Panel p2=new Panel();
		p2.setLayout(new BorderLayout());
		p2.add(jlblPassword, BorderLayout.WEST);
		p2.add(jtfPassword, BorderLayout.CENTER);
		
		Panel p3=new Panel();
		p3.setLayout(new GridLayout(1, 2));
		p3.add(jbtSignIn);
		p3.add(jbtSignUp);
		
		setLayout(new GridLayout(3, 1));
		add(p1);
		add(p2);
		add(p3);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sign");
		setSize(300, 300);
		setLocation(0, 600);
		setVisible(true);
	}
	
	public void jbtSignUpSet(){
		jbtSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signUpFrame();//����һ������ʾ�����û���������
			}
		});
	}

	public void jbtSignInSet(){
		jbtSignIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtfAccount.getText().length()!=0&&jtfPassword.getText().length()!=0){
					try{
						socket=new Socket("localhost", 10084);
					}
					catch (IOException ex) {
						// TODO: handle exception
						System.err.println(ex);
					}
					signInToServer();
				}
			}
		});
	}
	public void signInToServer(){
		try{
			toServer=new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(new Account(jtfAccount.getText(), jtfPassword.getText()));
			fromServer=new DataInputStream(socket.getInputStream());
			boolean b=fromServer.readBoolean();
			if(b){
				new ClientSearch(jtfAccount.getText());
			}
			else{
				System.out.println("�û��������");
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void signUpFrame(){
		JLabel jlblAccount1=new JLabel("�˺�");
		JTextField jtfAccount1=new JTextField();
		JLabel jlblPassword1=new JLabel("����");
		JTextField jtfPassword1=new JTextField();
		JButton jbtSignUp1=new JButton("ע��");
		JFrame jFrame1=new JFrame("SignUp");
		Panel p11=new Panel();
		p11.setLayout(new BorderLayout());
		p11.add(jlblAccount1, BorderLayout.WEST);
		p11.add(jtfAccount1, BorderLayout.CENTER);
		Panel p21=new Panel();
		p21.setLayout(new BorderLayout());
		p21.add(jlblPassword1, BorderLayout.WEST);
		p21.add(jtfPassword1, BorderLayout.CENTER);
		jFrame1.setLayout(new GridLayout(3, 1));
		jFrame1.add(p11);
		jFrame1.add(p21);
		jFrame1.add(jbtSignUp1);
		jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame1.setTitle("SignUp");
		jFrame1.setSize(300, 300);
		jFrame1.setVisible(true);
		jFrame1.setLocation(540, 600);
		jbtSignUp1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtfAccount1.getText().length()!=0&&jtfPassword1.getText().length()!=0){
					try{
						socket=new Socket("localhost", 10085);
					}
					catch (IOException ex) {
						// TODO: handle exception
						System.err.println(ex);
					}
					try{
						toServer=new ObjectOutputStream(socket.getOutputStream());
						toServer.writeObject(new Account(jtfAccount1.getText(), jtfPassword1.getText()));
						fromServer=new DataInputStream(socket.getInputStream());
						boolean b=fromServer.readBoolean();
						if(b){
							System.out.println("ע��ɹ���");
						}
						else{
							System.out.println("ע��ʧ��!���ڴ��û�");
						}
					}
					catch (IOException ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				}
			}
		});
	}
}
