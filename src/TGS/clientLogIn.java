package TGS;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class clientLogIn implements ActionListener{
	Container dialogPane;
	JDialog d;
	JLabel label1=new JLabel();
	JLabel label2=new JLabel();
	JTextField id;
	JTextField key;
	JButton sure;
	JButton end;
	public clientLogIn()
	{
		super();
		d=new JDialog(); 
		id=new JTextField();
		key=new JTextField();
		sure=new JButton("ȷ��");
		end =new JButton("����");
		d.setTitle("ע��");
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		d.setBounds(800,100,500,300);          //���ô��ڵĴ�С
		label1.setText("ID:");
		label1.setBounds(100, 5, 100, 120);      //������ʾ��Ϣ��λ��
		label2.setText("key:");
		label2.setBounds(100, 5, 100, 200);      //������ʾ��Ϣ��λ��
		id.setBounds(150, 50, 200, 25);      //������ʾ��Ϣ��λ��
		key.setBounds(150, 90, 200, 25);      //������ʾ��Ϣ��λ��
		sure.addActionListener((ActionListener) this);
		sure.setBounds(80,200,100,25);
		end.addActionListener(this);
		end.setBounds(280,200,100,25);
		dialogPane.add(label1);
		dialogPane.add(label2);
		dialogPane.add(id);
		dialogPane.add(key);
		dialogPane.add(sure);
		dialogPane.add(end);
		d.setVisible(true);
	}
	public String getID()
	{
		return id.getText();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("ȷ��")){
			linksql a=new linksql();
			a.Linksql(id.getText(), key.getText());
			Container di;
			JDialog d1=new JDialog();
			di=d1.getContentPane();
			di.setLayout(null);
			JLabel label=new JLabel();
			d1.setBounds(800,100,300,200);
			label.setBounds(100, 20, 150, 100); 
			label.setText("ע��ɹ���");
			label.setFont(new Font("",1,20));
			di.add(label);
			d1.setVisible(true);
		}	
		if(cmd.equals("����")){
			d.dispose();
		}
	}
}
