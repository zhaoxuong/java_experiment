package client;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import head.Account;
public class ClientSign extends JFrame{
	private JLabel jlblAccount=new JLabel("’À∫≈");
	private JTextField jtfAccount=new JTextField();
	private JLabel jlblPassword=new JLabel("√‹¬Î");
	private JTextField jtfPassword=new JTextField();
	private JButton jbtSignUp=new JButton("µ«¬º");
	private JButton jbtSignIn=new JButton("◊¢≤·");
	
	private Socket socket;
	private DataInputStream fromServer;
	private ObjectOutputStream toServer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientSign clientSign=new ClientSign();
	}
	public ClientSign(){
		jbtSignInSet();
		jbtSignUpSet();
		
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
		p3.add(jbtSignUp);
		p3.add(jbtSignIn);
		
		setLayout(new GridLayout(3, 1));
		add(p1);
		add(p2);
		add(p3);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sign");
		setSize(300, 300);
		setVisible(true);
	}
	
	public void jbtSignInSet(){
		jbtSignIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signInFrame();//µØ≥ˆ“ª∏ˆøÚ£¨Ã· æ ‰»Î”√ªß√˚”Î√‹¬Î
			}
		});
	}

	public void jbtSignUpSet(){
		jbtSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtfAccount.getText().length()!=0&&jtfPassword.getText().length()!=0){
					try{
						socket=new Socket("localhost", 10085);
					}
					catch (IOException ex) {
						// TODO: handle exception
						System.err.println(ex);
					}
					signUpToServer();
				}
			}
		});
	}
	public void signUpToServer(){
		try{
			toServer=new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(new Account(jtfAccount.getText(), jtfPassword.getText()));
			fromServer=new DataInputStream(socket.getInputStream());
			int i=fromServer.readInt();
			if(i==1){
				new ClientSearch();
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
	public void signInFrame(){
		JLabel jlblAccount=new JLabel("’À∫≈");
		JTextField jtfAccount=new JTextField();
		JLabel jlblPassword=new JLabel("√‹¬Î");
		JTextField jtfPassword=new JTextField();
		JButton jbtSignIn=new JButton("◊¢≤·");
		JFrame jFrame=new JFrame("SignIn");
		Panel p1=new Panel();
		p1.setLayout(new BorderLayout());
		p1.add(jlblAccount, BorderLayout.WEST);
		p1.add(jtfAccount, BorderLayout.CENTER);
		Panel p2=new Panel();
		p2.setLayout(new BorderLayout());
		p2.add(jlblPassword, BorderLayout.WEST);
		p2.add(jtfPassword, BorderLayout.CENTER);
		jFrame.setLayout(new GridLayout(3, 1));
		jFrame.add(p1);
		jFrame.add(p2);
		jFrame.add(jbtSignIn);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("SignIn");
		jFrame.setSize(300, 300);
		jFrame.setVisible(true);
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
			int i=fromServer.readInt();
			if(i==1){
				System.out.println("◊¢≤·≥…π¶£°");
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
