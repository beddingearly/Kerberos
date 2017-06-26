package text1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder; 

public class as_welcome {  
	
	public static String[] divTicketTGS(String TTGS){
		//DES() to ttgs;
		String ttgs = "";
		String[] s = new String[6];
		s[0] = ttgs.substring(0, 8);//8 Kc,tgs
		s[1] = ttgs.substring(8, 16);//8 IDc
		s[2] = ttgs.substring(16, 45);//29 ADc
		s[3] = ttgs.substring(45, 53);//8 IDtgs
		s[4] = ttgs.substring(53, 59);//6 TS2
		s[5] = ttgs.substring(59, 63);//4 Lifetime2
		
		return s;
	}
	
	public static String[] divTicketV(String TV){
		//DES
		String tv = "";
		String[] s = new String[6];
		s[0] = tv.substring(0, 8);//8 Kc,v
		s[1] = tv.substring(8, 16);//8 IDc
		s[2] = tv.substring(16, 45);//29 ADc
		s[3] = tv.substring(45, 53);//8 IDv
		s[4] = tv.substring(53, 59);//6 TS4
		s[5] = tv.substring(59, 63);//4 Lifetime4
	
		return s;
	}
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
    	as_welcome s=new as_welcome();  
        s.ini();  
    }  
    public void ini(){ 
    	
        JFrame jf=new JFrame();  
        jf.setTitle("AS×¢²á"); 
        JPanel jp=new JPanel();  
        JTextField textIP=new JTextField();  
        JTextField textPort=new JTextField();  
        
        JButton btn=new JButton("×¢²á");  
        JLabel ipJLA=new JLabel("ID");  
        JLabel portJLA=new JLabel("ÃÜÂë");  
          
        btn.setPreferredSize(new Dimension(50,30));  
          
        jf.setSize(new Dimension(450,250));  
          
        BorderLayout bla=new BorderLayout();  
        jf.setLayout(bla);  
        jp.setBackground(Color.white);  
        jp.setLayout(null);  
          
        jp.add(ipJLA);  
        jp.add(portJLA);  
        jp.add(textIP);  
        jp.add(textPort);  
        jp.add(btn);  
          
        ipJLA.setBounds(50, 30, 50, 30);  
        portJLA.setBounds(30, 65, 50, 30);  
        textIP.setBounds(65,30 , 300, 30); //
        textPort.setBounds(65,65 , 300, 30); // 
        btn.setBounds(175,100,80,30);  
          
        jf.add(jp);  
        jf.setVisible(true);  
        
        //btn.addActionListener(l);
    }  
}  
