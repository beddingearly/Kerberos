package text2;

public class DesKey extends Key {
	DesKey(){
		super();
	}
	
	public DesKey(String keyValue){
		super(keyValue);
	}
	
	@Override
	String[] childKey(int number){	//获取number轮下的子秘钥
		String []childKey = new String[number];
		String leftKey="",rightKey="";
		for(int i = 0; i < Constant.PC_1.length; i++){	//将秘钥分成左右两部分
			if(i<28)
				leftKey += this.getValue().charAt(Constant.PC_1[i]);
			else
				rightKey += this.getValue().charAt(Constant.PC_1[i]);
		}
		
		for(int i = 0; i < number; i++){
			String strLeft="" , strRight="";
			
			int move = Constant.MOVE_TIMES[i];
			for(int j = move; j < leftKey.length(); j++){	//循环左移——将move到length()-1放到前面
				strLeft += leftKey.charAt(j);
				strRight += rightKey.charAt(j);
			}
			for(int j = 0; j < move; j++){	//循环左移——将0到move-1放到后面
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
