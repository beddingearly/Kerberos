package C;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.application.java.Util;
import com.sun.jndi.cosnaming.IiopUrl.Address;

import text2.EncryMessage;

public class Authenticator implements ActionListener{
	public static final int PORT=8989;
	Container dialogPane;
	JFrame d;
	JLabel label1=new JLabel();
	JLabel label2=new JLabel();
	JLabel label3=new JLabel();
	JLabel label4=new JLabel();
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JScrollPane jsp;
	JTextArea ta;
	JTextArea idv;
	JTextField text;
	JPasswordField text1;
	static String timeStamp = new String();
	// ʱ�����
	private static String ticket = new String();
	// Ʊ��
	private static String key = new String();
	private static String keycv = new String();
	// �Գ���Կ
	
	public Authenticator()
	{
		
	}
	public Authenticator(String Idv){
		super();
		d=new JFrame();                      //�½�һ�Ի���
		d.setTitle("Authenticator");            //���ñ���
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		dialogPane.setBackground(Color.white);
		d.setBounds(700,100,620,500);          //���ô��ڵĴ�С
		label1.setText("*****************************��֤����*****************************");
		label1.setFont(new Font("",1,20));   //�������
		label1.setBounds(25, 1, 620, 80);      //������ʾ��Ϣ��λ��
		label2.setText("ID_c:");
		label2.setBounds(25, 130, 40, 25);      //������ʾ��Ϣ��λ��
		label3.setText("key:");
		label3.setBounds(25, 180, 40, 25);      //������ʾ��Ϣ��λ��
		label4.setText("ID_v:");
		label4.setBounds(25, 80, 40, 25);      //������ʾ��Ϣ��λ��
		text=new JTextField();
		text.setBounds(70, 130, 110, 25);
		text1=new JPasswordField();
		text1.setBounds(70, 180, 110, 25);
		button1=new JButton("����AS");
		button1.addActionListener(this);
		button1.setBounds(25,230,150,25);
		button2=new JButton("����TGS");
		button2.addActionListener(this);
		button2.setBounds(25,280,150,25);
		button3=new JButton("����V");
		button3.addActionListener(this);
		button3.setBounds(25,330,150,25);
		button4=new JButton("����");
		button4.addActionListener(this);
		button4.setBounds(25,380,150,25);
		ta=new JTextArea();
		ta.setFont(new Font("",1,14));
		ta.setBorder(BorderFactory.createLineBorder(Color.black));
		ta.setLineWrap(true);
		jsp=new JScrollPane(ta);
		jsp.setBounds(250, 80, 310, 330);
		idv=new JTextArea();
		idv.setBounds(70, 80, 110, 25);
		idv.setText(Idv);
		idv.setBorder(BorderFactory.createLineBorder(Color.black));
		dialogPane.add(label1);
		dialogPane.add(label2);
		dialogPane.add(label3);
		dialogPane.add(label4);
		dialogPane.add(text);
		dialogPane.add(text1);
		dialogPane.add(button1);
		dialogPane.add(button2);
		dialogPane.add(button3);
		dialogPane.add(button4);
		dialogPane.add(jsp);
		dialogPane.add(idv);
		d.setVisible(true);
	}
	//��AS����ͨ��
	public void Asrun()
	{
		Pattern p=Pattern.compile("[0-9]{8}");
		Matcher m=p.matcher(text.getText());
		if(m.matches())
		{
			
		}
		String Server="192.168.43.82";
		//����socket�׽��֣����ӱ�����PORT�˿�
		Socket clientSocket;
		BufferedReader sin = null;
		PrintWriter sout = null;
		try {
			clientSocket=new Socket(Server,PORT);
		//�����������������ڶ�ȡ���������͵���Ϣ
			sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//�������������������������������Ϣ
			sout=new PrintWriter(clientSocket.getOutputStream());
			ta.append("���ӳɹ�\n");
		} catch (IOException e) {
			ta.append(e.getMessage()+"\n");
		}
		//client��AS�ķ��
		String s3 = null;
		String string;
		String s4=CtoAS(text.getText());
		//System.out.println(s4);
		sout.println(s4);
		sout.flush();
		ta.append("�ȴ�AS�ظ�......\n");
		try {
			s3=sin.readLine();
			if(s3!=null)
			{
				ta.append("�յ�AS�ظ�\n");
				DataPacket dataPacket=new DataPacket();
				//��ȡ���ݰ�
				//System.out.println(s3);
				string = Util.binaryToString(s3);
				//System.out.println(string);
				String head =string.substring(0, 12);
				string =string.substring(12, string.length());
				if(head.equals("000100000000"))
				{
					ta.append("��֤ʧ��!\n");
				}
				else
				{
					s4 = EncryMessage.decryMessage(string, text1.getText());
					//System.out.println(s4);
					s4=head+s4;
					dataPacket.parseMessage(s4);
					ticket=dataPacket.getTicket();
					timeStamp=dataPacket.getTimeStamp();
					//System.out.println(timeStamp);
					System.out.println(ticket);
					key=dataPacket.getKey();
					//System.out.println(key.length());
					String address = InetAddress.getLocalHost().getHostAddress();
					//System.out.println(address);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//��TGS����ͨ��
	public void Tgsrun()
	{
		String Server="192.168.43.135";
		//����socket�׽��֣����ӱ�����PORT�˿�
		Socket clientSocket = null;
		BufferedReader sin = null;
		PrintWriter sout = null;
		try {
			clientSocket=new Socket(Server,PORT);
		//�����������������ڶ�ȡ���������͵���Ϣ
			sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//�������������������������������Ϣ
			sout=new PrintWriter(clientSocket.getOutputStream());
			ta.append("���ӳɹ�\n");
		} catch (IOException e) {
			ta.append(e.getMessage());
		}
		String s3 = null ;
		InetAddress addr=clientSocket.getLocalAddress();
		String address=addr.toString();
		String s4=CtoTGS(idv.getText(),ticket,text.getText(),address);
		//System.out.println(s4.length());
		s3=Util.toBinaryString(s4);
		//System.out.println(s3);	
		sout.println(s3);
		sout.flush();
		ta.append("�ȴ�TGS�ظ�......\n");
		try {
			s3=sin.readLine();
			if(s3!=null)
			{
				ta.append("�յ�TGS�ظ�\n");
				DataPacket dataPacket=new DataPacket();
				//��ȡ���ݰ�
				String string;
				string = Util.binaryToString(s3);
				//System.out.println(string);
				String head=string.substring(0, 12);
				string=string.substring(12, string.length());
				if(head.equals("000100000000"))
				{
					ta.append("��֤ʧ��!\n");
				}
				else
				{
					s4 = EncryMessage.decryMessage(string, key);		
					//System.out.println(s4);
					s4=head+s4;
					dataPacket.parseMessage(s4);
					ticket=dataPacket.getTicket();
					timeStamp=dataPacket.getTimeStamp();
					//System.out.println(timeStamp);
					//System.out.println(ticket);
					keycv=dataPacket.getKey();
					//System.out.println(key);
					//System.out.println(address);
				}	
			}
		} catch (IOException e) {
			ta.append(e.getMessage());
		}
	}
	//��Vͨ��
	//��V����ͨ��
	public void Vrun()
	{
		String Server="192.168.43.25";
		//����socket�׽��֣����ӱ�����PORT�˿�
		Socket clientSocket = null;
		BufferedReader sin = null;
		PrintWriter sout = null;
		try {
			clientSocket=new Socket(Server,PORT);
		//�����������������ڶ�ȡ���������͵���Ϣ
			sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//�������������������������������Ϣ
			sout=new PrintWriter(clientSocket.getOutputStream());
			ta.append("���ӳɹ�\n");
		} catch (IOException e) {
			ta.append(e.getMessage());
		}
		//client��V�ķ��
		String s3;
		InetAddress addr=clientSocket.getLocalAddress();
		String address=addr.toString();
		String s4=CtoV(ticket,text.getText(),address);
		s4=Util.toBinaryString(s4);
		//System.out.println(s4);
		sout.println(s4);
		sout.flush();
		ta.append("�ȴ�V�ظ�......\n");
		try {
			s3=sin.readLine();
			if(s3!=null)
			{
				ta.append("�յ�V�ظ�\n");
				//��ȡ���ݰ�
				String string;
				//System.out.println(string);
				string = Util.binaryToString(s3);
				DataPacket dp = new DataPacket();
				dp.parseMessage1(string, keycv);		
				//System.out.println("ts----->"+dp.getTimeStamp());
				String tmp = timeStamp;
				BigInteger b = new BigInteger(tmp).add(new BigInteger("1"));
				tmp = b.toString();
				//System.out.println(tmp);
				if(!dp.getTimeStamp().equals(tmp))
				{
					ta.append("��֤ʧ��!\n");
				}else{
					ta.append("��֤�ɹ�!\n");
					ta.append("��V�Ự��......\n");
					Communicate c=new Communicate(ta);					
				}			
			}
		} catch (IOException e) {
			ta.append(e.getMessage());
		}
	}
	
	//��ť����¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("����AS")){
			ta.append("����AS��......\n");
			Asrun();
		}
		if(cmd.equals("����TGS")){
			ta.append("����TGS��......\n");
			Tgsrun();
		}
		if(cmd.equals("����V")){
			ta.append("����V��......\n");
			Vrun();
		}
		if(cmd.equals("����")){
			d.dispose();
		}
	}
	/**
	 * C  to  AS���
	 * @param str ΪCID��UI�л��
	 * @return
	 */
	public static String CtoAS(String str){
		Cpackage a = new Cpackage();
		String CID = str;    //*************��UI��ȡ
		String TGSID =a.GetTGSID();
		String Timestamp =a.GetTS();
		String head = "000101110000";
		String Package = head + CID + TGSID + Timestamp;	
		return Package;
	}
	/**
	 * C to TGS���
	 * @param str Ϊ VID��UI�л�ȡ
	 * @param str1 Ϊ ticket��socket �л�ȡ
	 * @param str2 ΪCID��UI�л�ȡ
	 * @param str3 ΪAD=ԴIP
	 * @return
	 */
	public static String CtoTGS(String str,String str1,String str2,String str3){
		String IDV = str;
		String ticket = str1;
		System.out.println(ticket);
		String CID = str2 ;
		//String address="192168043094";
		Cpackage a = new Cpackage();
		String address=a.GetAD(str3);
		String  TS3 = a.GetTS();
		String head ="000100100101";
		String Authen = CID + address + TS3;  //****** ��Ҫ������Keyctgs����
		System.out.println(key.length());
		Authen=EncryMessage.encryChatMessage(Authen, key);
		System.out.println(Authen.length());
		String Package = head +IDV + ticket+ Authen ;
		return Package;
	}
	/**
	 * C to V��֤���̷��
	 * @param strΪTicket��socket ��ȡ
	 * @param str1 Ϊ Authentication��socket��ȡ
	 * @return
	 */
	public static String CtoV(String str,String str1,String str2){
		String head ="000100000101";
		String ticket = str;
		System.out.println(ticket);
		Cpackage a = new Cpackage();
		String  TS5 = a.GetTS();
		timeStamp=TS5;
		String address=a.GetAD(str2);
		String Authen = str1+address+TS5;
		Authen=EncryMessage.encryChatMessage(Authen, keycv);
		String Package = head + ticket+ Authen ;
		System.out.println("Package--->"+Package.length());
		System.out.println(Package);
		return Package;
		
	}
	/**
	 * C to V�Ự���̷��
	 * @param strΪTicket��socket ��ȡ
	 * @param str1 Ϊ Authentication��socket��ȡ
	 * @return
	 */
	public static String CtoVtalk(String str){
		String head ="000110000000";
		String ticket = str;
		String Package = head + ticket;
		return Package;
	}
}
