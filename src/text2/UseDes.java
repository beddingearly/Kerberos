package text2;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class UseDes {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		String strM = "00011011"+"00011011"+"00001111"+"00001111"+"00001111"+"00001111"+"00001111"+"00001111";
		Message Mmessage = new M(strM);
		
		String strK = "11110000"+"11110000"+"11110000"+"11110000"+"11110000"+"11110000"+"11110000"+"11110000";
		Key key = new DesKey(strK);
		
		System.out.println("原文——"+Mmessage.getValue());
		System.out.println("秘钥——"+key.getValue());
		
		Feistel des = new Des(16);
		
		Message Cmessage =  Mmessage.toC(key, des);
		
		System.out.println("密文——"+Cmessage.getValue());
		
		Message Mmessage2 =  Cmessage.toM(key, des);
		
		System.out.println("明文——"+Mmessage2.getValue());
		/*
		String  path="C:\\Windows\\System32\\calc.exe";   
        Runtime   runTime   =   Runtime.getRuntime();   
        try {
			runTime.exec("cmd   /c   start   "   +   path);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} */
        
        /*String s = "12345\0\0\0";
        System.out.println(s);
        System.out.println(s.getBytes().length);
        for(int i = 0; i < s.getBytes().length; i++){
        	String s2 = "";
        	int a = s.getBytes()[i];
        	System.out.println(a);
        	s2 += a/128;
        	s2 += (a%128)/64;
        	s2 += (a%64)/32;
        	s2 += (a%32)/16;
        	s2 += (a%16)/8;
        	s2 += (a%8)/4;
        	s2 += (a%4)/2;
        	s2 += a%2;
        	
        	System.out.println(s2);
        	
        	a = 0;
        	int num = 128;
        	for(int j = 0; j < s2.length(); j++){
        		String t = "";
        		t += s2.charAt(j);
        		a += Integer.parseInt(t)*(num);
        		num /= 2;
        	}
        	System.out.println(a);
        	
        	char c = (char) a;
        	System.out.println(c);
        }
        
        System.out.println(s.getBytes());*/
		
		JFileChooser fileChooser = new JFileChooser("D:\\");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION){ 
			String filePath= fileChooser.getSelectedFile().getAbsolutePath();
			
		}

	}
}
