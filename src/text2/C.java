package text2;

public class C extends Message {
	C(){
		super();
	}
	
	public C(String mV){
		super(mV);
	}
	
	@Override
	public Message toM(Key key, Feistel feistel){	//����Feistel�ܹ�,����Կkey�������½�����ת��Ϊ����
		// TODO �Զ����ɵķ������
		String str = feistel.CtoM(this, key);
		Message message = new M(str);
		return message;
	}

	@Override
	public Message toC(Key key, Feistel feistel) {	//��������
		// TODO �Զ����ɵķ������
		return this;
	}
}
