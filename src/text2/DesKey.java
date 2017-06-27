package text2;

public class DesKey extends Key {
	DesKey(){
		super();
	}
	
	public DesKey(String keyValue){
		super(keyValue);
	}
	
	@Override
	String[] childKey(int number){	//��ȡnumber���µ�����Կ
		String []childKey = new String[number];
		String leftKey="",rightKey="";
		for(int i = 0; i < Constant.PC_1.length; i++){	//����Կ�ֳ�����������
			if(i<28)
				leftKey += this.getValue().charAt(Constant.PC_1[i]);
			else
				rightKey += this.getValue().charAt(Constant.PC_1[i]);
		}
		
		for(int i = 0; i < number; i++){
			String strLeft="" , strRight="";
			
			int move = Constant.MOVE_TIMES[i];
			for(int j = move; j < leftKey.length(); j++){	//ѭ�����ơ�����move��length()-1�ŵ�ǰ��
				strLeft += leftKey.charAt(j);
				strRight += rightKey.charAt(j);
			}
			for(int j = 0; j < move; j++){	//ѭ�����ơ�����0��move-1�ŵ�����
				strLeft += leftKey.charAt(j);
				strRight += rightKey.charAt(j);
			}
			
			String str = strLeft + strRight;
			
			childKey[i] = "";
			for(int j = 0; j < Constant.PC_2.length; j++){
				childKey[i] += str.charAt(Constant.PC_2[j]);
			}
			
			leftKey = strLeft;
			rightKey = strRight;
		}
		return childKey;
	}
}
