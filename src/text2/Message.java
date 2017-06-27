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
	 * 获取左半部分*/
	String getLeft(){
		String str = "";
		for(int i = 0; i < mLength/2; i++){
			str += mValue.charAt(i);
		}
		return str;
	}
	
	/* 
	 * 获取右半部分*/
	String getRight(){
		String str = "";
		for(int i = mLength/2; i < mLength; i++){
			str += mValue.charAt(i);
		}
		return str;
	}
	
	
	public abstract Message toC(Key key,Feistel feistel);	//将明文转换为密文
	
	public abstract Message toM(Key key,Feistel feistel);	//将密文转换为明文
	
}
