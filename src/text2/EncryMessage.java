package text2;

public class EncryMessage{
	//public static String strK = "0000111100001111000011110000111100001111000011110000111100001111";
	//public static String strKK = "asdfghjk";
	
	public static void main(String[] args){
		String strKK = "asdfghjk";
		String ss = "0010101010101010010001010101010101010101010101001010101010111101010010101001010101";
		String s = encryChatMessage(ss, strKK);
		String temp2 = decryMessage(s, strKK);
		System.out.println("一次解密 "+temp2);
		String tmp = encryChatMessage(s, strKK);
		String sss = decryMessage(tmp, strKK);
		String temp = decryMessage(sss, strKK);
		System.out.println("明文          "+ss);
		System.out.println("一次加密 "+s);
		System.out.println("二次加密 "+tmp);
		System.out.println("一次解密 "+sss);
		System.out.println("二次解密 "+temp);
	}
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
		String s = "";
		int l =str.length();
		while(l%8 != 0){
			l++;
			message += (char)0;
		}
		
		for(int i = 0; i < l/8; i++){
			str = "";
				for(int j = i*8; j < (i+1)*8; j++){
					String s2 = "";
					String stem = "";
					stem += message.charAt(j);
					int a = (int)message.charAt(j);
		        	s2 += a/128;
		        	s2 += (a%128)/64;
		        	s2 += (a%64)/32;
		        	s2 += (a%32)/16;
		        	s2 += (a%16)/8;
		        	s2 += (a%8)/4;
		        	s2 += (a%4)/2;
		        	s2 +=  a%2;
		        	str += s2;
				}
			/*if(i < message.length()/8){
				for(int j = i*8; j < (i+1)*8; j++){
					String s2 = "";
					String stem = "";
					//stem += message.charAt(j);
		        	int a = (int)message.charAt(j);
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
			}
			if(i >= message.length()/8){
				for(int j = i*8; j < (i+1)*8; j++){
					String s2 = "";
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	str += s2;
				}
			}*/
			String strK = StrToBinstr(skey);
			Key key = new DesKey(strK);
			Message Mmessage = new M(str);
			
			Feistel des = new Des(16);
			
			Message Cmessage =  Mmessage.toC(key, des);
			
			
			str = Cmessage.getValue();
			
			//System.out.println(Mmessage);
			
			for(int k = 0; k < str.length()/8; k++){
				int a = 0;
	        	int num = 128;
	        	for(int j = k*8; j < (k+1)*8; j++){
	        		String t = "";
	        		t += str.charAt(j);
	        		a += Integer.parseInt(t)*(num);
	        		num /= 2;
	        	}
	        	s += a;
	        	char c = (char) a;
	        	jta_c += c;
			}
		}
		//System.out.println(s);
		//return s;
		return jta_c;
	}
	
	/* 
	 * 解密信息*/
	public static String decryMessage(String message, String skey){
		String jta_m = "", s = "";
		String str = message;
		int l =str.length();
		while(l%8 != 0){
			l++;
			message += (char)0;
		}
		String tt = "";
		for(int i = 0; i < l/8; i++){
			str = "";
			for(int j = i*8; j < (i+1)*8; j++){
				String s2 = "";
				String stem = "";
				stem += message.charAt(j);
				int a = (int)message.charAt(j);
	        	s2 += a/128;
	        	s2 += (a%128)/64;
	        	s2 += (a%64)/32;
	        	s2 += (a%32)/16;
	        	s2 += (a%16)/8;
	        	s2 += (a%8)/4;
	        	s2 += (a%4)/2;
	        	s2 +=  a%2;
	        	str += s2;
			}
			/*if(i < message.length()/8){
				for(int j = i*8; j < (i+1)*8; j++){
					String s2 = "";
					String stem = "";
					stem += message.charAt(j);
					int a = (int)message.charAt(j);
		        	s2 += a/128;
		        	s2 += (a%128)/64;
		        	s2 += (a%64)/32;
		        	s2 += (a%32)/16;
		        	s2 += (a%16)/8;
		        	s2 += (a%8)/4;
		        	s2 += (a%4)/2;
		        	s2 +=  a%2;
		        	str += s2;
				}
			}
			if(i >= message.length()/8){
				for(int j = i*8; j < (i+1)*8; j++){
					String s2 = "";
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	s2 += 0;
		        	str += s2;
				}
			}*/
			
			String strK = StrToBinstr(skey);
			Key key = new DesKey(strK);
			Message Cmessage = new C(str);
			
			Feistel des = new Des(16);
			Message Mmessage =  Cmessage.toM(key, des);
			//System.out.println(Mmessage);
			str = Mmessage.getValue();
			//System.out.println(str);
			for(int k = 0; k < str.length()/8; k++){
				int a = 0;
	        	int num = 128;
	        	for(int j = 8*k; j < (k+1)*8; j++){
	        		String t = "";
	        		t += str.charAt(j);
	        		tt+=t;
	        		a += Integer.parseInt(t)*(num);
	        		num /= 2;
	        	}
	        	//s +=a;
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
	