package text2;

public class EncryMessage{
	//public static String strK = "0000111100001111000011110000111100001111000011110000111100001111";
	//public static String strKK = "asdfghjk";
	
	/*public static void main(String[] args){
		String strKK = "asdfghjk";
		String ss = "asfdjsfnlasnangsa0934502*(&%&";
		String s = encryChatMessage(ss, strKK);
		System.out.println(s);
		String sss = decryMessage(s, strKK);
		System.out.println(sss);
	}*/
	//将字符串转换成二进制字符串
	public static String StrToBinstr(String str){
		char[] strChar=str.toCharArray();
		String temp="";
		for(int i=0;i<strChar.length;i++){
			 temp+="0";
			 temp+=Integer.toBinaryString(strChar[i]);	
		}
		return temp;
			 
	}
	public static String encryChatMessage(String message, String skey){
		String str = message, jta_c = "";
		byte []bt = str.getBytes();
		int l =bt.length;
		while(l%8 != 0){
			l++;
		}
		byte []newbt = new byte[l];
		for(int i = 0; i < newbt.length; i++){
			if(i < bt.length){
				newbt[i] = bt[i];
			} else {
				newbt[i] = 0;
			}
		}
		
		for(int i = 0; i < l/8; i++){
			str = "";
			for(int j = i*8; j < (i+1)*8; j++){
				String s2 = "";
	        	int a = newbt[j];
	        	s2 += a/128;
	        	s2 += (a%128)/64;
	        	s2 += (a%64)/32;
	        	s2 += (a%32)/16;
	        	s2 += (a%16)/8;
	        	s2 += (a%8)/4;
	        	s2 += (a%4)/2;
	        	s2 += a%2;
	        	str += s2;
			}
			String strK = StrToBinstr(skey);
			Key key = new DesKey(strK);
			Message Mmessage = new M(str);
			
			Feistel des = new Des(16);
			
			Message Cmessage =  Mmessage.toC(key, des);
			
			str = Cmessage.getValue();
			
			for(int k = 0; k < str.length()/8; k++){
				int a = 0;
	        	int num = 128;
	        	for(int j = k*8; j < (k+1)*8; j++){
	        		String t = "";
	        		t += str.charAt(j);
	        		a += Integer.parseInt(t)*(num);
	        		num /= 2;
	        	}
	        	char c = (char) a;
	        	jta_c += c;
			}
		}
		return jta_c;
	}
	
	/* 
	 * 解密信息*/
	public static String decryMessage(String message, String skey){
		String jta_m = "";
		String str = message;
		int l =str.length();
		int bt[] = new int[l];
		for(int i = 0; i < l; i++){
			bt[i] = str.charAt(i);
		}
		while(l%8 != 0){
			l++;
		}
		int []newbt = new int[l];
		for(int i = 0; i < newbt.length; i++){
			if(i < bt.length){
				newbt[i] = bt[i];
			} else {
				newbt[i] = 0;
			}
		}
		
		for(int i = 0; i < l/8; i++){
			str = "";
			for(int j = i*8; j < (i+1)*8; j++){
				String s2 = "";
	        	int a = newbt[j];
	        	s2 += a/128;
	        	s2 += (a%128)/64;
	        	s2 += (a%64)/32;
	        	s2 += (a%32)/16;
	        	s2 += (a%16)/8;
	        	s2 += (a%8)/4;
	        	s2 += (a%4)/2;
	        	s2 += a%2;
	        	str += s2;
			}
			String strK = StrToBinstr(skey);
			Key key = new DesKey(strK);
			Message Cmessage = new C(str);
			
			Feistel des = new Des(16);
			
			Message Mmessage =  Cmessage.toM(key, des);
			
			str = Mmessage.getValue();
			
			for(int k = 0; k < str.length()/8; k++){
				int a = 0;
	        	int num = 128;
	        	for(int j = 8*k; j < (k+1)*8; j++){
	        		String t = "";
	        		t += str.charAt(j);
	        		a += Integer.parseInt(t)*(num);
	        		num /= 2;
	        	}
	        	//System.out.println(a);
	        	char c = (char) a;
	        	jta_m += c;
			}
		}
		
		return jta_m;
	}
	
}
/* 
	 * 加密信息*/
	