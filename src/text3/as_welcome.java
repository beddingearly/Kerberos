package text3;
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

public class as_welcome implements ActionListener{  	 
	Container dialogPane;
	JDialog d;
	JLabel label1=new JLabel();
	JButton button1;
	JButton button2;
	JTextArea ta;
    public void ini(){ 
    	d=new JDialog();                      //�½�һ�Ի���
		d.setTitle("AS������");            //���ñ���
		dialogPane=d.getContentPane();
		dialogPane.setLayout(null);
		dialogPane.setBackground(Color.white);
		d.setBounds(700,100,620,500);          //���ô��ڵĴ�С
		label1.setText("*****************************AS����*****************************");
		label1.setFont(new Font("",1,20));   //�������
		label1.setBounds(25, 1, 620, 80);      //������ʾ��Ϣ��λ��		
		button1=new JButton("ע��");
		button1.addActionListener(this);
		button1.setBounds(55,400,110,25);
		button2=new JButton("�˳�");
		button2.addActionListener(this);
		button2.setBounds(425,400,110,25);
		ta=new JTextArea();
		ta.setBounds(25, 70, 550, 300);
		ta.setFont(new Font("",1,16));
		ta.setBorder(BorderFactory.createLineBorder(Color.black));
		dialogPane.add(label1);
		dialogPane.add(button1);
		dialogPane.add(button2);
		dialogPane.add(ta);
		d.setVisible(true);
    	}
    public void actionPerformed(ActionEvent e) {
    		String cmd=e.getActionCommand();
    		if(cmd.equals("ע��")){
    			clientLogIn client =new clientLogIn();
    		}
    		if(cmd.equals("�˳�")){
    			d.dispose();
    		}
    	}
}