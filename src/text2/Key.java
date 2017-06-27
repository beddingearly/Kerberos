package text2;

public abstract class Key {
	private int keyLength;
	private String keyValue;
	
	Key(){
		this(null);
	}
	
	Key(String keyV){
		keyLength = keyV.length();
		keyValue = keyV;
	}
	
	int getLength(){
		return keyLength;
	}
	
	String getValue(){
		return keyValue;
	}
	
	abstract String[] childKey(int number);	//��ȡnumber���µ�����Կ�������������Կ����
}
