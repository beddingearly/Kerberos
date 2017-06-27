package text2;

public class C extends Message {
	C(){
		super();
	}
	
	public C(String mV){
		super(mV);
	}
	
	@Override
	public Message toM(Key key, Feistel feistel){	//利用Feistel架构,在秘钥key的作用下将密文转换为明文
		// TODO 自动生成的方法存根
		String str = feistel.CtoM(this, key);
		Message message = new M(str);
		return message;
	}

	@Override
	public Message toC(Key key, Feistel feistel) {	//返回自身
		// TODO 自动生成的方法存根
		return this;
	}
}
