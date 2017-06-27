package text2;

public abstract class Feistel {
	private int FNumber;
	
	Feistel(){
		this(0);
	}
	
	Feistel(int number){
		FNumber = number;
	}
	
	int getFN(){
		return FNumber;
	}
	
	/* 
	 * 在子秘钥数组的key的作用下进行加密*/
	abstract String Encryption(String Lmessage,String Rmessage,String []key);
	
	/* 
	 * 在子秘钥数组的key的作用下进行解密*/
	abstract String Decryption(String Lmessage,String Rmessage,String []key);
	
	String MtoC(Message message, Key key){
		String []str = key.childKey(FNumber);	//获取16轮子秘钥
		String s = Encryption(message.getLeft(),message.getRight(),str);
		return s;
	}
	
	String CtoM(Message message, Key key){
		String []str = key.childKey(FNumber);	//获取16轮子秘钥，并逆序 
		String []str2 = new String[str.length];
		for(int i = 0; i < str.length; i++){
			str2[i] = str[str.length - i - 1];
		}
		String s = Decryption(message.getLeft(),message.getRight(),str2);	//解密时Ls与Rs的位置与加密时相反
		return s;
	}
}
