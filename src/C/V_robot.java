package C;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.application.java.Util;

public class V_robot implements ActionListener{
	private static final int PORT = 8989;
	Container dialogPane;
	JFrame d;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JScrollPane jsp1;
	JScrollPane jsp2;
	JScrollPane jsp3;
	JTextField n;
	JTextArea xishu;
	JTextArea youzhi;
	JTextArea ta;
	static JTextArea tax;
	JButton b1;
	JButton b2;
	String Server="192.168.1.105";
	//创建socket套接字，连接本机的PORT端口
	Socket clientSocket = null;
	BufferedReader sin = null;
	PrintWriter sout = null;
	public V_robot()
	{
		
	}
	public V_robot(JTextArea tax){
		super();
		this.tax=tax;
		d=new JFrame();                      //新建一对话框
		d.setTitle("V_robot");            //设置标题
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		dialogPane.setBackground(Color.white);
		d.setBounds(700,100,620,550);          //设置窗口的大小
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		label4=new JLabel();
		label1.setText("方程维数n：");
		label2.setText("方程系数a：");
		label3.setText("方程右值b：");
		label4.setText("方程的解为：");
		label1.setBounds(10, 10, 100, 25);
		label2.setBounds(10, 40, 100, 25);
		label3.setBounds(400, 40, 100, 25);
		label4.setBounds(10, 220, 100, 25);
		n=new JTextField();
		n.setBounds(100, 10, 480, 25);
		xishu=new JTextArea();
		xishu.setFont(new Font("",1,14));
		youzhi=new JTextArea();
		youzhi.setFont(new Font("",1,14));
		ta=new JTextArea();
		jsp1=new JScrollPane(xishu);
		jsp1.setBounds(10, 70, 350, 150);
		jsp2=new JScrollPane(youzhi);
		jsp2.setBounds(400, 70, 180, 150);
		jsp3=new JScrollPane(ta);
		jsp3.setBounds(10, 250, 570, 150);
		b1=new JButton();
		b1.setText("发送");
		b1.addActionListener(this);
		b2=new JButton();
		b2.setText("退出");
		b2.addActionListener(this);
		b1.setBounds(10, 410, 100, 25);
		b2.setBounds(480, 410, 100, 25);
		dialogPane.add(label1);
		dialogPane.add(label2);
		dialogPane.add(label3);
		dialogPane.add(label4);
		dialogPane.add(jsp1);
		dialogPane.add(jsp2);
		dialogPane.add(jsp3);
		dialogPane.add(n);
		dialogPane.add(b1);
		dialogPane.add(b2);
		d.setVisible(true);
		try {
			clientSocket=new Socket(Server,PORT);
			//创建缓冲区对象，用于读取服务器发送的信息
			sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    //创建缓冲区对象，用于向服务器发送信息
			sout=new PrintWriter(clientSocket.getOutputStream());
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();  //返回引发事件的命令按钮的名字	
		String s1,s2,s3;
		String string=new String();
		if(cmd.equals("发送"))
		{
			s1=n.getText();
			System.out.println(s1);
			s2=xishu.getText();
			System.out.println(s2);
			string+=s1;
			
			/*try {
				if(s!=null)
				{
					s="0001100000000"+s;
					s = Util.toBinaryString(s);
					sout.println(s);
					sout.flush();
				}
				s=sin.readLine();
				if(s!=null)
				{
					s=Util.binaryToString(s);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
		}
		if(cmd.equals("退出"))
		{
			tax.append("会话结束\n");
			try {
				sout.println("000110000000@");
				sout.flush();
				clientSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			d.dispose();
		}
	}
	public static void main(String[] args)
	{
		V_robot robot =new V_robot(tax);
		//robot.xishu.setText("robot : "+"Hello!What can I do for you?");
	}

}
