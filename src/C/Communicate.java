package C;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import com.application.java.Util;
public class Communicate implements ActionListener{
	private static final int PORT = 8989;
	Container dialogPane;
	JFrame d;
	TextField textSendMessage;     //发送消息文本行
	JTextArea textShowMessage;
	JButton b1,b4;
	int flag=1;
	String str;
	String Server="192.168.43.25";
	//创建socket套接字，连接本机的PORT端口
	Socket clientSocket = null;
	BufferedReader sin = null;
	PrintWriter sout = null;
	JTextArea ta;
	JScrollPane jsp;
	public Communicate()
	{
		
	}
	public Communicate(JTextArea ta){
		super();   
		d=new JFrame();                      //新建一对话框
		d.setTitle("图灵机器人");            //设置标题
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		d.setBounds(100,100,550,450);          //设置窗口的大小
		textShowMessage=new JTextArea();
		jsp = new JScrollPane(textShowMessage);
		jsp.setBounds(5, 5, 525, 200);      //设置显示输入信息的位置
		textSendMessage=new TextField();
		textSendMessage.setBounds(5, 220, 525, 100);      //设置输入信息的位置
		b1=new JButton("发送");
		b4=new JButton("退出");
		b1.addActionListener(this);
		b4.addActionListener(this);
		b1.setBounds(15,350,100,30);              //设置“发送”按钮的位置   
		b4.setBounds(400,350,100,30);             //设置“退出”按钮的位置
		dialogPane.add(jsp);
		dialogPane.add(textSendMessage);
		dialogPane.add(b1);
		dialogPane.add(b4);
		this.ta=ta;
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
		// TODO Auto-generated method stub
		String cmd=e.getActionCommand();  //返回引发事件的命令按钮的名字	
		String s;
		if(cmd.equals("发送"))
		{
			s=textSendMessage.getText();
			try {
				if(s!=null)
				{
					textShowMessage.append("client : "+s+"\n");
					textSendMessage.setText(null);
					s="000110000000"+s;
					s = Util.toBinaryString(s);
					sout.println(s);
					sout.flush();
				}
				s=sin.readLine();
				if(s!=null)
				{
					s=Util.binaryToString(s);
					textShowMessage.append("图灵机器人："+s+"\n");
				}
			} catch (IOException e1) {
				textShowMessage.append("连接中断!\n");
			}
		}
		if(cmd.equals("退出")){
			flag=0;
			ta.append("会话结束\n");
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
	/*public static void main(String[] args)
	{
		Communicate c=new Communicate();
	}*/
}
