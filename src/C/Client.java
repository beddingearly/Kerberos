package C;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client implements ActionListener{
	Container dialogPane;
	JFrame d;
	JLabel label1=new JLabel();
	JLabel label2=new JLabel();
	JComboBox<String> jc;
	JButton button1;
	JButton button2;
	String idv;
	JScrollPane jsp;
	public Client(){
		super();		
		String[] s={"","�����Ƽ���","ͼ�������","Ӧ�÷���3","Ӧ�÷���4"};
		d=new JFrame();                      //�½�һ�Ի���
		d.setTitle("Client");            //���ñ���
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		dialogPane.setBackground(Color.white);
		d.setBounds(800,100,450,400);          //���ô��ڵĴ�С
		label1.setText("*************Ӧ�÷���ѡ��*************");
		label1.setFont(new Font("",1,20));   //�������
		label1.setBounds(50, 5, 400, 120);      //������ʾ��Ϣ��λ��	
		label2.setText("��ѡ��Ӧ�÷���");
		label2.setBounds(70, 50, 400, 120);      //������ʾ��Ϣ��λ��		
		jc=new JComboBox<String>(s);
		jc.setBounds(200, 100, 110, 25);
		button1=new JButton("ȷ��");
		button1.addActionListener(this);
		button1.setBounds(70,250,110,25);
		button2=new JButton("�˳�");
		button2.addActionListener(this);
		button2.setBounds(250,250,110,25);
		dialogPane.add(label1);
		dialogPane.add(label2);
		dialogPane.add(jc);
		dialogPane.add(button1);
		dialogPane.add(button2);
		d.setVisible(true);
	}
	//��ť����¼�
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd=e.getActionCommand();
			if(cmd.equals("ȷ��")){
				if(jc.getSelectedItem().equals(""))
				{
					JDialog d1=new JDialog();                      //�½�һ�Ի���
					d1.setTitle("Client");            //���ñ���
					Container dialogPane1=d1.getContentPane();
					dialogPane1.setLayout(null);
					dialogPane1.setBackground(Color.white);
					d1.setBounds(800,400,300,200);          //���ô��ڵĴ�С
					JLabel label=new JLabel();
					label.setText("Ӧ�ÿ���Ϊ�գ�");
					label.setFont(new Font("",1,16));
					label.setBounds(70, 50, 200, 50);
					dialogPane1.add(label);
					d1.setVisible(true);
				}
				//d.dispose();	
				else
				{
					if(jc.getSelectedItem().equals("�����Ƽ���"))
					{
						idv="20141001";
						new Authenticator(idv);
					}
					if(jc.getSelectedItem().equals("ͼ�������"))
					{
						idv="20141002";
						new Authenticator(idv);
					}
					if(jc.getSelectedItem().equals("Ӧ�÷���3"))
					{
						idv="20141003";
						new Authenticator(idv);
					}
					if(jc.getSelectedItem().equals("Ӧ�÷���4"))
					{
						idv="20141004";
						new Authenticator(idv);
					}
				}	
			}
			if(cmd.equals("�˳�")){
				d.dispose();
			}
		}
	public static void main(String[] args)
	{
		Client client1=new Client();
		//client1.run();
		//Client client2=new Client();
		//client2.run();
	}
}
