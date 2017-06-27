package text2;

public class M extends Message {
	M(){
		super();
	}
	
	public M(String mV){
		super(mV);
	}
	
	@Override
	public Message toC(Key key,Feistel feistel){	//利用Feistel架构,在秘钥key的作用下将明文转换为密文
		// TODO 自动生成的方法存根
		String str = feistel.MtoC(this, key);
		Message message = new C(str);
		return message;
	}

	@Override
	public Message toM(Key key, Feistel feistel) {	//返回自身
		// TODO 自动生成的方法存根
		return this;
	}
}
