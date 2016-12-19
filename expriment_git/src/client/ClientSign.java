package client;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import head.Account;
import head.WordZan;
public class ClientSign extends JFrame{
	private JLabel jlblAccount=new JLabel("’À∫≈");
	private JTextField jtfAccount=new JTextField();
	private JLabel jlblPassword=new JLabel("√‹¬Î");
	private JTextField jtfPassword=new JTextField();
	private JButton jbtSignIn=new JButton("µ«¬º");
	private JButton jbtSignUp=new JButton("◊¢≤·");
	private JFrame jFrame1=new JFrame();
	private JFrame jFrame2=new JFrame();
	
	
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
		jtfAccount.setPreferredSize(new Dimension(100, 20));
		jtfPassword.setPreferredSize(new Dimension(100, 20));
		
		Panel p1=new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(jlblAccount);
		p1.add(jtfAccount);
		
		Panel p2=new Panel();
		p2.setLayout(new FlowLayout());
		p2.add(jlblPassword);
		p2.add(jtfPassword);
		
		Panel p3=new Panel();
		p3.setLayout(new FlowLayout());
		p3.add(jbtSignIn);
		p3.add(jbtSignUp);
		
		
		
		jFrame2.setLayout(new GridLayout(3, 1,10,10));
		jFrame2.add(p1);
		jFrame2.add(p2);
		jFrame2.add(p3);
		
		
		
		
		jFrame2.setLocationRelativeTo(null);
		jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame2.setTitle("Sign");
		jFrame2.setSize(250, 150);
		jFrame2.setLocation(540, 200);
		jFrame2.setVisible(true);
	}
	
	public void jbtSignUpSet(){
		jbtSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signUpFrame();//µØ≥ˆ“ª∏ˆøÚ£¨Ã· æ ‰»Î”√ªß√˚”Î√‹¬Î
				jFrame2.setVisible(false);
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
				new ClientSearch(jtfAccount.getText(),new WordZan(),0,new beifen());
			}
			else{
				System.out.println("”√ªß√‹¬Î¥ÌŒÛ£°");
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void signUpFrame(){
		JLabel jlblAccount1=new JLabel("’À∫≈");
		JTextField jtfAccount1=new JTextField();
		JLabel jlblPassword1=new JLabel("√‹¬Î");
		JTextField jtfPassword1=new JTextField();
	    JButton jbtSignIn1=new JButton("µ«¬º");
		JButton jbtSignUp1=new JButton("◊¢≤·");
		JFrame jFrame1=new JFrame("SignUp");
		jtfAccount1.setPreferredSize(new Dimension(100, 20));
		jtfPassword1.setPreferredSize(new Dimension(100, 20));
		Panel p11=new Panel();
		p11.setLayout(new FlowLayout());
		p11.add(jlblAccount1);
		p11.add(jtfAccount1);
		Panel p21=new Panel();
		p21.setLayout(new FlowLayout());
		p21.add(jlblPassword1);
		p21.add(jtfPassword1);
		Panel p31=new Panel();
		p31.setLayout(new FlowLayout());
		p31.add(jbtSignIn1);
		p31.add(jbtSignUp1);
		jFrame1.setLayout(new GridLayout(3, 1));
		jFrame1.add(p11);
		jFrame1.add(p21);
		jFrame1.add(p31);
		jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame1.setTitle("SignUp");
		jFrame1.setSize(250, 150);
		jFrame1.setVisible(true);
		jFrame1.setLocation(540, 200);
		jbtSignIn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame1.setVisible(false);
				jFrame2.setVisible(true);
			}
		});
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
							System.out.println("◊¢≤·≥…π¶£°");
						}
						else{
							System.out.println("◊¢≤· ß∞‹!¥Ê‘⁄¥À”√ªß");
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
