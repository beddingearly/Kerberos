package text2;

public abstract class Message {
	private int mLength;
	private String mValue;
	
	Message(){
		this(null);
	}
	
	Message(String mV){
		mLength = mV.length();
		mValue = mV;
	}
	
	int getLength(){
		return mLength;
	}
	
	public String getValue(){
		return mValue;
	}
	
	/* 
	 * ��ȡ��벿��*/
	String getLeft(){
		String str = "";
		for(int i = 0; i < mLength/2; i++){
			str += mValue.charAt(i);
		}
		return str;
	}
	
	/* 
	 * ��ȡ�Ұ벿��*/
	String getRight(){
		String str = "";
		for(int i = mLength/2; i < mLength; i++){
			str += mValue.charAt(i);
		}
		return str;
	}
	
	
	public abstract Message toC(Key key,Feistel feistel);	//������ת��Ϊ����
	
	public abstract Message toM(Key key,Feistel feistel);	//������ת��Ϊ����
	
}
