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
	 * ������Կ�����key�������½��м���*/
	abstract String Encryption(String Lmessage,String Rmessage,String []key);
	
	/* 
	 * ������Կ�����key�������½��н���*/
	abstract String Decryption(String Lmessage,String Rmessage,String []key);
	
	String MtoC(Message message, Key key){
		String []str = key.childKey(FNumber);	//��ȡ16������Կ
		String s = Encryption(message.getLeft(),message.getRight(),str);
		return s;
	}
	
	String CtoM(Message message, Key key){
		String []str = key.childKey(FNumber);	//��ȡ16������Կ�������� 
		String []str2 = new String[str.length];
		for(int i = 0; i < str.length; i++){
			str2[i] = str[str.length - i - 1];
		}
		String s = Decryption(message.getLeft(),message.getRight(),str2);	//����ʱLs��Rs��λ�������ʱ�෴
		return s;
	}
}
