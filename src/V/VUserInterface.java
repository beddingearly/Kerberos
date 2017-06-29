package V;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VUserInterface {
	JFrame frame = new JFrame("应用服务器");
	JLabel label = new JLabel("密码");
	JTextField KeyVTgs = new JTextField(20);
	JButton button = new JButton("登录");
	JTextArea textArea = new JTextArea(12, 20);
	public VUserInterface(){
		super();
		JPanel top = new JPanel();
		top.add(label);
		top.add(KeyVTgs);
		top.add(button);
		frame.add(top,BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		VUserInterface vInterface = new VUserInterface();
	}
}
