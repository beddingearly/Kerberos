package text2;

public class M extends Message {
	M(){
		super();
	}
	
	public M(String mV){
		super(mV);
	}
	
	@Override
	public Message toC(Key key,Feistel feistel){	//����Feistel�ܹ�,����Կkey�������½�����ת��Ϊ����
		// TODO �Զ����ɵķ������
		String str = feistel.MtoC(this, key);
		Message message = new C(str);
		return message;
	}

	@Override
	public Message toM(Key key, Feistel feistel) {	//��������
		// TODO �Զ����ɵķ������
		return this;
	}
}
