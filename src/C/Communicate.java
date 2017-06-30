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
	TextField textSendMessage;     //������Ϣ�ı���
	JTextArea textShowMessage;
	JButton b1,b4;
	int flag=1;
	String str;
	String Server="192.168.43.25";
	//����socket�׽��֣����ӱ�����PORT�˿�
	Socket clientSocket = null;
	BufferedReader sin = null;
	PrintWriter sout = null;
	JTextArea ta;
	public Communicate()
	{
		
	}
	public Communicate(JTextArea ta){
		super();   
		d=new JFrame();                      //�½�һ�Ի���
		d.setTitle("ͼ�������");            //���ñ���
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		d.setBounds(100,100,550,450);          //���ô��ڵĴ�С
		textShowMessage=new JTextArea();
		textShowMessage.setBounds(5, 5, 525, 200);      //������ʾ������Ϣ��λ��
		textSendMessage=new TextField();
		textSendMessage.setBounds(5, 220, 525, 100);      //����������Ϣ��λ��
		b1=new JButton("����");
		b4=new JButton("�˳�");
		b1.addActionListener(this);
		b4.addActionListener(this);
		b1.setBounds(15,350,100,30);              //���á����͡���ť��λ��   
		b4.setBounds(400,350,100,30);             //���á��˳�����ť��λ��
		dialogPane.add(textShowMessage);
		dialogPane.add(textSendMessage);
		dialogPane.add(b1);
		dialogPane.add(b4);
		this.ta=ta;
		d.setVisible(true);
		try {
			clientSocket=new Socket(Server,PORT);
			//�����������������ڶ�ȡ���������͵���Ϣ
			sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    //�������������������������������Ϣ
			sout=new PrintWriter(clientSocket.getOutputStream());
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd=e.getActionCommand();  //���������¼������ť������	
		String s;
		if(cmd.equals("����"))
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
					textShowMessage.append("ͼ������ˣ�"+s+"\n");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("�˳�")){
			flag=0;
			ta.append("�Ự����\n");
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
